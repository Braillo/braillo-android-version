package com.example.braillo.Utility;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.palette.graphics.Palette;


public class ColorHelper {

    static ArrayList<String[]> colorList = new ArrayList<>();

    static {
        colorList.add(new String[]{"maroon", "128", "0", "0"});
        colorList.add(new String[]{"dark red", "139", "0", "0"});
        colorList.add(new String[]{"brown", "165", "42", "42"});
        colorList.add(new String[]{"firebrick", "178", "34", "34"});
        colorList.add(new String[]{"crimson", "220", "20", "60"});
        colorList.add(new String[]{"red", "255", "0", "0"});
        colorList.add(new String[]{"tomato", "255", "99", "71"});
        colorList.add(new String[]{"coral", "255", "127", "80"});
        colorList.add(new String[]{"indian red", "205", "92", "92"});
        colorList.add(new String[]{"light coral", "240", "128", "128"});
        colorList.add(new String[]{"dark salmon", "233", "150", "122"});
        colorList.add(new String[]{"salmon", "250", "128", "114"});
        colorList.add(new String[]{"light salmon", "255", "160", "122"});
        colorList.add(new String[]{"orange red", "255", "69", "0"});
        colorList.add(new String[]{"dark orange", "255", "140", "0"});
        colorList.add(new String[]{"orange color", "255", "165", "0"});
        colorList.add(new String[]{"gold", "255", "215", "0"});
        colorList.add(new String[]{"dark golden rod", "184", "134", "11"});
        colorList.add(new String[]{"golden rod", "218", "165", "32"});
        colorList.add(new String[]{"pale golden rod", "238", "232", "170"});
        colorList.add(new String[]{"dark khaki", "189", "183", "107"});
        colorList.add(new String[]{"khaki", "240", "230", "140"});
        colorList.add(new String[]{"olive", "128", "128", "0"});
        colorList.add(new String[]{"yellow", "255", "255", "0"});
        colorList.add(new String[]{"yellow green", "154", "205", "50"});
        colorList.add(new String[]{"dark olive green", "85", "107", "47"});
        colorList.add(new String[]{"olive drab", "107", "142", "35"});
        colorList.add(new String[]{"lawn green", "124", "252", "0"});
        colorList.add(new String[]{"chart reuse", "127", "255", "0"});
        colorList.add(new String[]{"green yellow", "173", "255", "47"});
        colorList.add(new String[]{"dark green", "0", "100", "0"});
        colorList.add(new String[]{"green", "0", "128", "0"});
        colorList.add(new String[]{"forest green", "34", "139", "34"});
        colorList.add(new String[]{"lime", "0", "255", "0"});
        colorList.add(new String[]{"lime green", "50", "205", "50"});
        colorList.add(new String[]{"light green", "144", "238", "144"});
        colorList.add(new String[]{"pale green", "152", "251", "152"});
        colorList.add(new String[]{"dark sea green", "143", "188", "143"});
        colorList.add(new String[]{"medium spring green", "0", "250", "154"});
        colorList.add(new String[]{"spring green", "0", "255", "127"});
        colorList.add(new String[]{"sea green", "46", "139", "87"});
        colorList.add(new String[]{"medium aqua marine", "102", "205", "170"});
        colorList.add(new String[]{"medium sea green", "60", "179", "113"});
        colorList.add(new String[]{"light sea green", "32", "178", "170"});
        colorList.add(new String[]{"dark slate gray", "47", "79", "79"});
        colorList.add(new String[]{"teal", "0", "128", "128"});
        colorList.add(new String[]{"dark cyan", "0", "139", "139"});
        colorList.add(new String[]{"aqua", "0", "255", "255"});
        colorList.add(new String[]{"cyan", "0", "255", "255"});
        colorList.add(new String[]{"light cyan", "224", "255", "255"});
        colorList.add(new String[]{"dark turquoise", "0", "206", "209"});
        colorList.add(new String[]{"turquoise", "64", "224", "208"});
        colorList.add(new String[]{"medium turquoise", "72", "209", "204"});
        colorList.add(new String[]{"pale turquoise", "175", "238", "238"});
        colorList.add(new String[]{"aqua marine", "127", "255", "212"});
        colorList.add(new String[]{"powder blue", "176", "224", "230"});
        colorList.add(new String[]{"cadet blue", "95", "158", "160"});
        colorList.add(new String[]{"steel blue", "70", "130", "180"});
        colorList.add(new String[]{"corn flower blue", "100", "149", "237"});
        colorList.add(new String[]{"deep sky blue", "0", "191", "255"});
        colorList.add(new String[]{"dodger blue", "30", "144", "255"});
        colorList.add(new String[]{"light blue", "173", "216", "230"});
        colorList.add(new String[]{"sky blue", "135", "206", "235"});
        colorList.add(new String[]{"light sky blue", "135", "206", "250"});
        colorList.add(new String[]{"midnight blue", "25", "25", "112"});
        colorList.add(new String[]{"navy", "0", "0", "128"});
        colorList.add(new String[]{"dark blue", "0", "0", "139"});
        colorList.add(new String[]{"medium blue", "0", "0", "205"});
        colorList.add(new String[]{"blue", "0", "0", "255"});
        colorList.add(new String[]{"royal blue", "65", "105", "225"});
        colorList.add(new String[]{"blue violet", "138", "43", "226"});
        colorList.add(new String[]{"indigo", "75", "0", "130"});
        colorList.add(new String[]{"dark slate blue", "72", "61", "139"});
        colorList.add(new String[]{"slate blue", "106", "90", "205"});
        colorList.add(new String[]{"medium slate blue", "123", "104", "238"});
        colorList.add(new String[]{"medium purple", "147", "112", "219"});
        colorList.add(new String[]{"dark magenta", "139", "0", "139"});
        colorList.add(new String[]{"dark violet", "148", "0", "211"});
        colorList.add(new String[]{"dark orchid", "153", "50", "204"});
        colorList.add(new String[]{"medium orchid", "186", "85", "211"});
        colorList.add(new String[]{"purple", "128", "0", "128"});
        colorList.add(new String[]{"thistle", "216", "191", "216"});
        colorList.add(new String[]{"plum", "221", "160", "221"});
        colorList.add(new String[]{"violet", "238", "130", "238"});
        colorList.add(new String[]{"magenta<", "255", "0", "255"});
        colorList.add(new String[]{"orchid", "218", "112", "214"});
        colorList.add(new String[]{"medium violet red", "199", "21", "133"});
        colorList.add(new String[]{"pale violet red", "219", "112", "147"});
        colorList.add(new String[]{"deep pink", "255", "20", "147"});
        colorList.add(new String[]{"hot pink", "255", "105", "180"});
        colorList.add(new String[]{"light pink", "255", "182", "193"});
        colorList.add(new String[]{"pink", "255", "192", "203"});
        colorList.add(new String[]{"antique white", "250", "235", "215"});
        colorList.add(new String[]{"beige", "245", "245", "220"});
        colorList.add(new String[]{"bisque", "255", "228", "196"});
        colorList.add(new String[]{"blanched almond", "255", "235", "205"});
        colorList.add(new String[]{"wheat", "245", "222", "179"});
        colorList.add(new String[]{"corn silk", "255", "248", "220"});
        colorList.add(new String[]{"lemon chiffon", "255", "250", "205"});
        colorList.add(new String[]{"light golden rod yellow", "250", "250", "210"});
        colorList.add(new String[]{"light yellow", "255", "255", "224"});
        colorList.add(new String[]{"saddle brown", "139", "69", "19"});
        colorList.add(new String[]{"sienna", "160", "82", "45"});
        colorList.add(new String[]{"chocolate", "210", "105", "30"});
        colorList.add(new String[]{"peru", "205", "133", "63"});
        colorList.add(new String[]{"sandy brown", "244", "164", "96"});
        colorList.add(new String[]{"burly wood", "222", "184", "135"});
        colorList.add(new String[]{"tan", "210", "180", "140"});
        colorList.add(new String[]{"rosy brown", "188", "143", "143"});
        colorList.add(new String[]{"moccasin", "255", "228", "181"});
        colorList.add(new String[]{"navajo white", "255", "222", "173"});
        colorList.add(new String[]{"peach puff", "255", "218", "185"});
        colorList.add(new String[]{"misty rose", "255", "228", "225"});
        colorList.add(new String[]{"lavender blush", "255", "240", "245"});
        colorList.add(new String[]{"linen", "250", "240", "230"});
        colorList.add(new String[]{"old lace", "253", "245", "230"});
        colorList.add(new String[]{"papaya whip", "255", "239", "213"});
        colorList.add(new String[]{"sea shell", "255", "245", "238"});
        colorList.add(new String[]{"mint cream", "245", "255", "250"});
        colorList.add(new String[]{"slate gray", "112", "128", "144"});
        colorList.add(new String[]{"light slate gray", "119", "136", "153"});
        colorList.add(new String[]{"light steel blue", "176", "196", "222"});
        colorList.add(new String[]{"lavender", "230", "230", "250"});
        colorList.add(new String[]{"floral white", "255", "250", "240"});
        colorList.add(new String[]{"alice blue", "240", "248", "255"});
        colorList.add(new String[]{"ghost white", "248", "248", "255"});
        colorList.add(new String[]{"honeydew", "240", "255", "240"});
        colorList.add(new String[]{"ivory", "255", "255", "240"});
        colorList.add(new String[]{"azure", "240", "255", "255"});
        colorList.add(new String[]{"snow", "255", "250", "250"});
        colorList.add(new String[]{"black", "0", "0", "0"});
        colorList.add(new String[]{"dim gray ", "105", "105", "105"});
        colorList.add(new String[]{"gray ", "128", "128", "128"});
        colorList.add(new String[]{"dark gray ", "169", "169", "169"});
        colorList.add(new String[]{"silver", "192", "192", "192"});
        colorList.add(new String[]{"light gray ", "211", "211", "211"});
        colorList.add(new String[]{"gainsboro", "220", "220", "220"});
        colorList.add(new String[]{"white smoke", "245", "245", "245"});
        colorList.add(new String[]{"white", "255", "255", "255"});
    }

    private Bitmap bitmap;
    private Activity activity;



    private void logSwatchData(Palette.Swatch swatch, String swatchName) {

        int rgbColor = swatch.getRgb();
        // Gets the HSL values
        // Hue between 0 and 360
        // Saturation between 0 and 1
        // Lightness between 0 and 1
        float[] hslValues = swatch.getHsl();
        // Gets the number of pixels represented by this swatch
        int pixelCount = swatch.getPopulation();
        // Gets an appropriate title text color
        int titleTextColor = swatch.getTitleTextColor();
        // Gets an appropriate body text color
        int bodyTextColor = swatch.getBodyTextColor();

        Log.d("Swatches ", "[ " + swatchName + " ] : " + "HSL values: [" + hslValues[0] + "°," + String.format("%.0f", hslValues[1] * 100) + "%" + ", " + String.format("%.0f", hslValues[2] * 100) + "% ]" +
                " ,RGB: " + rgbColor +
                " ,PixelCount: " + pixelCount +
                " ,TitleTextColor: " + titleTextColor +
                " ,BodyTextColor: " + bodyTextColor);
    }
    public void getColor(Activity activity,Bitmap bitmap){
        this.bitmap = bitmap;
        this.activity = activity;
        paletteProcessing();
    }
    private void paletteProcessing() {


        Palette.PaletteAsyncListener paletteListener = new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette palette) {
                // access palette colors here


                List<Palette.Swatch> swatchs = palette.getSwatches();
                Log.d("Swatches size: ", String.valueOf(swatchs.size()));
                int max = 0;
                Palette.Swatch mostUsedColor = null;
                for (Palette.Swatch swatch : swatchs) {
                    logSwatchData(swatch, "ALL Swatchs :");
                    int pixelCount = swatch.getPopulation();
                    if (Math.max(pixelCount, max) != max) {
                        max = Math.max(pixelCount, max);
                        mostUsedColor = swatch;
                    }
                }

                Log.d("Swatches :Max pop  ", String.valueOf(max));
                logSwatchData(mostUsedColor, "MOST Used Color");
                int red = (mostUsedColor.getRgb() >> 16) & 0x0ff;
                int green = (mostUsedColor.getRgb() >> 8) & 0x0ff;
                int blue = (mostUsedColor.getRgb()) & 0x0ff;
                Color c = new Color();

                String[] namedColor = getColorName(red, green, blue);
                if (namedColor != null) {
                    Voice.speak(activity, "ColorDetection/" + namedColor[0] + ".mp3", true);
                } else
                    Voice.speak(activity, "AppCommand/can not identify.mp3", true);
                Log.d("Color Data :", red + "," + green + "," + blue);
            }
        };


        if (bitmap != null && !bitmap.isRecycled()) {
            Bitmap b = bitmap;
            Palette.from(bitmap).generate(paletteListener);
        }

    }

    private String[] getColorName(int red, int green, int blue) { //To find the closest color name
        int r, g, b, max = 66000, min = 0;
        String[] name = null;
        for (String[] color : colorList) {
            r = Integer.parseInt(color[1]);
            g = Integer.parseInt(color[2]);
            b = Integer.parseInt(color[3]);
            min = (((red - r) * (red - r) + (green - g) * (green - g) + (blue - b) * (blue - b)) / 3);
            if (max > min) {
                max = min;
                name = color;
            }
        }
        Log.d("Color", Arrays.toString(name));
        return name;
    }


}
