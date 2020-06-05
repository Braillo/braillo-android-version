package com.example.braillo.Models.Helper;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;

import com.example.braillo.Models.Recognition;

import org.tensorflow.lite.Interpreter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetectionModelClassifier {


    // Only return this many results.
    private static final int NUM_DETECTIONS = 10;
    // Float model
    private static final float IMAGE_MEAN = 128.0f;
    private static final float IMAGE_STD = 128.0f;
    // options for model interpreter
    private final Interpreter.Options tfliteOptions = new Interpreter.Options();
    String modelPath, labelPath;
    Activity activity;
    Map<Integer, Object> outputMap, outputMapq;
    long startOffset;
    long declaredLength;
    AssetFileDescriptor fileDescriptor;
    // options for model interpreter
    FileInputStream inputStream;
    FileChannel fileChannel;
    BufferedReader reader;
    String line;
    int width;
    int height;
    float scaleWidth;
    float scaleHeight;
    Matrix matrix;


    // selected classifier information received from extras
    Bitmap resizedBitmap;
    Bitmap bitmap;
    List<Recognition> recognitions;
    // outputLocations: array of shape [Batchsize, NUM_DETECTIONS,4]
    // contains the location of detected boxes
    private float[][][] outputLocations;
    // outputClasses: array of shape [Batchsize, NUM_DETECTIONS]
    // contains the classes of detected boxes
    private float[][] outputClasses;
    // outputScores: array of shape [Batchsize, NUM_DETECTIONS]
    // contains the scores of detected boxes
    private float[][] outputScores;
    // numDetections: array of shape [Batchsize]
    // contains the number of detected boxes
    private float[] numDetections;
    // outputLocations: array of shape [Batchsize, NUM_DETECTIONS,4]
    // contains the location of detected boxes
    private byte[][][] outputLocationsq;
    // outputClasses: array of shape [Batchsize, NUM_DETECTIONS]
    // contains the classes of detected boxes
    private byte[][] outputClassesq;
    // outputScores: array of shape [Batchsize, NUM_DETECTIONS]
    // contains the scores of detected boxes
    private byte[][] outputScoresq;
    // numDetections: array of shape [Batchsize]
    // contains the number of detected boxes
    private byte[] numDetectionsq;
    // tflite graph
    private Interpreter tflite;
    // holds all the possible labels for model
    private List<String> labelList;
    // holds the selected image data as bytes
    private ByteBuffer imgData = null;
    private Bitmap img;
    private boolean quant;
    // input image dimensions for the Inception Model
    private int DIM_IMG_SIZE_X = 300;
    private int DIM_IMG_SIZE_Y = 300;
    private int DIM_PIXEL_SIZE = 3;
    // int array to hold image data
    private int[] intValues;
    public DetectionModelClassifier(Activity activity, Bitmap bitmap, String modelpath, String LabelPath, boolean quant) {
        this.activity = activity;
        this.modelPath = modelpath;
        this.labelPath = LabelPath;
        this.quant = quant;
        img = bitmap;

        // initialize array that holds image data
        intValues = new int[DIM_IMG_SIZE_X * DIM_IMG_SIZE_Y];

        //initilize graph and labels
        try {
            tflite = new Interpreter(loadModelFile(), tfliteOptions);
            labelList = loadLabelList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    // loads tflite grapg from file
    private MappedByteBuffer loadModelFile() throws IOException {
        fileDescriptor = activity.getAssets().openFd(modelPath);
        inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        fileChannel = inputStream.getChannel();
        startOffset = fileDescriptor.getStartOffset();
        declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    // converts bitmap to byte array which is passed in the tflite graph
    private void convertBitmapToByteBuffer(Bitmap bitmap) {
        if (imgData == null) {
            return;
        }
        //put posstion to start
        imgData.rewind();
        bitmap.getPixels(intValues, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        // loop through all pixels
        int pixel = 0;
        for (int i = 0; i < DIM_IMG_SIZE_X; ++i) {
            for (int j = 0; j < DIM_IMG_SIZE_Y; ++j) {
                final int val = intValues[pixel++];
                // get rgb values from intValues where each int holds the rgb values for a pixel.
                // if quantized, convert each rgb value to a byte, otherwise to a float
                if (quant) {
                    imgData.put((byte) ((val >> 16) & 0xFF));
                    imgData.put((byte) ((val >> 8) & 0xFF));
                    imgData.put((byte) (val & 0xFF));
                } else {
                    imgData.putFloat((((val >> 16) & 0xFF) - IMAGE_MEAN) / IMAGE_STD);
                    imgData.putFloat((((val >> 8) & 0xFF) - IMAGE_MEAN) / IMAGE_STD);
                    imgData.putFloat((((val) & 0xFF) - IMAGE_MEAN) / IMAGE_STD);
                }

            }
        }
    }

    // loads the labels from the label txt file in assets into a string array
    private List<String> loadLabelList() throws IOException {
        labelList = new ArrayList<String>();
        reader =
                new BufferedReader(new InputStreamReader(activity.getAssets().open(labelPath)));

        while ((line = reader.readLine()) != null) {
            labelList.add(line);
        }
        reader.close();
        return labelList;
    }

    // resizes bitmap to given dimensions
    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        width = bm.getWidth();
        height = bm.getHeight();
        scaleWidth = ((float) newWidth) / width;
        scaleHeight = ((float) newHeight) / height;
        matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }

    public void classifer() {
        // initialize byte array. The size depends if the input data needs to be quantized or not
        if (quant) {
            //allocate byte buffer
            imgData =
                    ByteBuffer.allocateDirect(
                            DIM_IMG_SIZE_X * DIM_IMG_SIZE_Y * DIM_PIXEL_SIZE);
        } else {
            imgData =
                    ByteBuffer.allocateDirect(
                            4 * DIM_IMG_SIZE_X * DIM_IMG_SIZE_Y * DIM_PIXEL_SIZE);
        }
        //retrive byte buffer after assignment
        imgData.order(ByteOrder.nativeOrder());

        // initialize probabilities array. The datatypes that array holds depends if the input data needs to be quantized or not


        outputLocations = new float[1][NUM_DETECTIONS][4];
        outputClasses = new float[1][NUM_DETECTIONS];
        outputScores = new float[1][NUM_DETECTIONS];
        numDetections = new float[1];


        // resize the bitmap to the required input size to the CNN
        bitmap = getResizedBitmap(img, DIM_IMG_SIZE_X, DIM_IMG_SIZE_Y);
        // convert bitmap to byte array
        convertBitmapToByteBuffer(bitmap);

        Object[] inputArray = {imgData};
        outputMap = new HashMap<>();
        outputMap.put(0, outputLocations);
        outputMap.put(1, outputClasses);
        outputMap.put(2, outputScores);
        outputMap.put(3, numDetections);

        outputMapq = new HashMap<>();
        outputMapq.put(0, outputLocationsq);
        outputMapq.put(1, outputClassesq);
        outputMapq.put(2, outputScoresq);
        outputMapq.put(3, numDetectionsq);
        // pass byte data to the graph
        if (quant) {
            tflite.runForMultipleInputsOutputs(inputArray, outputMap);

        } else {
            tflite.runForMultipleInputsOutputs(inputArray, outputMap);

        }
        // display the results


    }

    public List<Recognition> getAllOutPut() {
        recognitions = new ArrayList<>(NUM_DETECTIONS);
        for (int i = 0; i < NUM_DETECTIONS; ++i) {
            final RectF detection =
                    new RectF(
                            outputLocations[0][i][1] * DIM_IMG_SIZE_X,
                            outputLocations[0][i][0] * DIM_IMG_SIZE_X,
                            outputLocations[0][i][3] * DIM_IMG_SIZE_X,
                            outputLocations[0][i][2] * DIM_IMG_SIZE_X);

            int labelOffset = 1;
            recognitions.add(
                    new Recognition(
                            "" + i,
                            labelList.get((int) outputClasses[0][i] + labelOffset),
                            outputScores[0][i],
                            detection));
        }

        return recognitions;
    }


}
