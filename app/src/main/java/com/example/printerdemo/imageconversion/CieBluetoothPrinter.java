package com.example.printerdemo.imageconversion;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;


import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

public class CieBluetoothPrinter {

    private static int threshold = 127;
    private static int maxImgPrintWidth = 384;
    private static int[][] Floyd16x16 = new int[][]{{0, 128, 32, 160, 8, 136, 40, 168, 2, 130, 34, 162, 10, 138, 42, 170}, {192, 64, 224, 96, 200, 72, 232, 104, 194, 66, 226, 98, 202, 74, 234, 106}, {48, 176, 16, 144, 56, 184, 24, 152, 50, 178, 18, 146, 58, 186, 26, 154}, {240, 112, 208, 80, 248, 120, 216, 88, 242, 114, 210, 82, 250, 122, 218, 90}, {12, 140, 44, 172, 4, 132, 36, 164, 14, 142, 46, 174, 6, 134, 38, 166}, {204, 76, 236, 108, 196, 68, 228, 100, 206, 78, 238, 110, 198, 70, 230, 102}, {60, 188, 28, 156, 52, 180, 20, 148, 62, 190, 30, 158, 54, 182, 22, 150}, {252, 124, 220, 92, 244, 116, 212, 84, 254, 126, 222, 94, 246, 118, 214, 86}, {3, 131, 35, 163, 11, 139, 43, 171, 1, 129, 33, 161, 9, 137, 41, 169}, {195, 67, 227, 99, 203, 75, 235, 107, 193, 65, 225, 97, 201, 73, 233, 105}, {51, 179, 19, 147, 59, 187, 27, 155, 49, 177, 17, 145, 57, 185, 25, 153}, {243, 115, 211, 83, 251, 123, 219, 91, 241, 113, 209, 81, 249, 121, 217, 89}, {15, 143, 47, 175, 7, 135, 39, 167, 13, 141, 45, 173, 5, 133, 37, 165}, {207, 79, 239, 111, 199, 71, 231, 103, 205, 77, 237, 109, 197, 69, 229, 101}, {63, 191, 31, 159, 55, 183, 23, 151, 61, 189, 29, 157, 53, 181, 21, 149}, {254, 127, 223, 95, 247, 119, 215, 87, 253, 125, 221, 93, 245, 117, 213, 85}};
    private static int[][] Floyd8x8 = new int[][]{{0, 32, 8, 40, 2, 34, 10, 42}, {48, 16, 56, 24, 50, 18, 58, 26}, {12, 44, 4, 36, 14, 46, 6, 38}, {60, 28, 52, 20, 62, 30, 54, 22}, {3, 35, 11, 43, 1, 33, 9, 41}, {51, 19, 59, 27, 49, 17, 57, 25}, {15, 47, 7, 39, 13, 45, 5, 37}, {63, 31, 55, 23, 61, 29, 53, 21}};
    private static int[][] Floyd4x4 = new int[][]{{0, 8, 2, 10}, {12, 4, 14, 6}, {3, 11, 1, 9}, {15, 7, 13, 5}};

    public Bitmap printUnicodeText(String var1, Layout.Alignment var2, TextPaint var3) {
        if (var1 != null && var1.length() != 0) {
            {
                Bitmap var4 = bitmap(var1, var2, var3);
                return var4;
            }
        } else return null;
    }

    private static int BtpLineWidth = 384;

    protected static Bitmap bitmap(String var1, Layout.Alignment var2, TextPaint var3) {
        TextPaint var4 = new TextPaint();
        if (var3 != null) {
            var4.set(var3);
            var4.setTypeface(Typeface.DEFAULT_BOLD);
        } else {
            var4.setColor(BLACK);
            var4.setTextSize(16.0F);
        }

        StaticLayout var5 = new StaticLayout(var1, var4, 70, var2, 1.0F, 1.0F, true);
        int var6 = var5.getHeight();
        Bitmap.Config var7 = Bitmap.Config.ARGB_8888;
        Bitmap var8 = Bitmap.createBitmap(100, var6, var7);
        Canvas var9 = new Canvas(var8);
        var9.drawColor(-1);
        var5.draw(var9);
        return var8;
    }

    public Bitmap getResizedBitmap(Bitmap image, int width, int height) {
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public Bitmap PrintTable(PrintColumnParam var1, PrintColumnParam var2, PrintColumnParam var3) {
        Bitmap var4 = (new BitmapColoumn(576, var1, var2, var3)).ConvertingToBitmap();
        return var4;
    }

    public Bitmap PrintTable(PrintColumnParam var1, PrintColumnParam var2) {
        Bitmap var3 = (new BitmapColoumn(576, var1, var2)).ConvertingToBitmap();
        return var3;
    }

    public Bitmap PrintTable(PrintColumnParam var1, PrintColumnParam var2, PrintColumnParam var3, PrintColumnParam var4) {
        Bitmap var5 = (new BitmapColoumn(576, var1, var2, var3, var4)).ConvertingToBitmap();
        return var5;
    }

    public Bitmap PrintTable(PrintColumnParam var1, PrintColumnParam var2, PrintColumnParam var3, PrintColumnParam var4, PrintColumnParam var5) {
        Bitmap var6 = (new BitmapColoumn(576, var1, var2, var3, var4, var5)).ConvertingToBitmap();
        return var6;
    }

    public Bitmap PrintTable(PrintColumnParam var1, PrintColumnParam var2, PrintColumnParam var3, PrintColumnParam var4, PrintColumnParam var5, PrintColumnParam var6) {
        Bitmap var7 = (new BitmapColoumn(576, var1, var2, var3, var4, var5, var6)).ConvertingToBitmap();
        return var7;
    }

    private static String CharToString(CharSequence var0) {
        for (int var1 = 0; var1 < var0.length(); ++var1) {
            if (var0.charAt(var1) > 255) {
                return "UTF-8";
            }
        }

        return null;
    }

    public static Bitmap scaleImage(Bitmap var0) {
        Point var1 = point(var0.getWidth(), var0.getHeight());
        return Bitmap.createScaledBitmap(var0, var1.x, var1.y, false);
    }

    private static Point point(int var0, int var1) {
        Point var2 = new Point();
        int var4;
        if (var0 > maxImgPrintWidth) {
            float var3 = (float) var0 / (float) var1;
            var0 = maxImgPrintWidth;
            var1 = (int) ((float) var0 / var3);
        } else if (var0 % 8 != 0) {
            var4 = var0;
            var0 = var0 % 8 > 0 ? var0 / 8 * 8 : var0;
            var1 = (int) ((float) var0 / (float) var4 * (float) var1);
        }

        var2.x = var0;
        var2.y = var1;
        var4 = var2.x * var2.y / 16;
        return var2;
    }

    public static Bitmap ditherBitmap(Bitmap var0) {
        Bitmap var1 = b(var0);
        int var2 = var1.getWidth();
        int var3 = var1.getHeight();
        int[] var4 = new int[var2 * var3];
        var1.getPixels(var4, 0, var2, 0, 0, var2, var3);
        int var5 = 0;

        for (int var6 = 0; var6 < var3; ++var6) {
            for (int var7 = 0; var7 < var2; ++var7) {
                if ((var4[var5] & 255) <= Floyd16x16[var7 & 15][var6 & 15] && Color.alpha(var4[var5]) != 0) {
                    var4[var5] = -16777216;
                } else {
                    var4[var5] = -1;
                }

                ++var5;
            }
        }

        var1.setPixels(var4, 0, var2, 0, 0, var2, var3);
        return var1;
    }

    protected static Bitmap b(Bitmap var0) {
        int var2 = var0.getHeight();
        int var1 = var0.getWidth();
        Bitmap var3 = Bitmap.createBitmap(var1, var2, Bitmap.Config.ARGB_8888);
        Canvas var4 = new Canvas(var3);
        Paint var5 = new Paint();
        ColorMatrix var6 = new ColorMatrix();
        var6.setSaturation(0.0F);
        ColorMatrixColorFilter var7 = new ColorMatrixColorFilter(var6);
        var5.setColorFilter(var7);
        var4.drawBitmap(var0, 0.0F, 0.0F, var5);
        return var3;
    }

    protected static Bitmap a(Bitmap var0, int var1, int var2, boolean var3, boolean var4) {
        if (var0 != null && var1 > 0) {
            int var5 = var0.getWidth();
            if (var5 >= var1) {
                return var0;
            } else {
                int var6 = var0.getHeight();
                Bitmap var7 = Bitmap.createBitmap(var1, var6, Bitmap.Config.ARGB_8888);
                Canvas var8 = new Canvas(var7);
                if (var4) {
                    var8.drawColor(-16777216);
                } else {
                    var8.drawColor(-1);
                }

                switch (var2) {
                    case 0:
                        if (var3) {
                            var8.drawBitmap(var0, 0.0F, 0.0F, (Paint) null);
                            return var7;
                        }

                        return var0;
                    case 1:
                        var8.drawBitmap(var0, (float) ((var1 - var5) / 2), 0.0F, (Paint) null);
                        return var7;
                    case 2:
                        var8.drawBitmap(var0, (float) (var1 - var5), 0.0F, (Paint) null);
                        return var7;
                    default:
                        return var0;
                }
            }
        } else {
            return var0;
        }
    }

    protected static Bitmap a(Bitmap var0, int var1, int var2, boolean var3) {
        return a(var0, var1, var2, false, var3);
    }

    public static Bitmap BinarizeImage(Bitmap var0, boolean var1, boolean var2, int var3) {
        int var9 = var0.getWidth();
        int var10 = var0.getHeight();

        int var4;
        int var7;
        int var8;
        int var11;
        int var12;
        for (var11 = 0; var11 < var9; ++var11) {
            for (var12 = 0; var12 < var10; ++var12) {
                var4 = var0.getPixel(var11, var12);
                var7 = Color.red(var4);
                int var6 = Color.green(var4);
                int var5 = Color.blue(var4);
                var8 = Color.alpha(var4);
                var7 = (int) (0.21D * (double) var7 + 0.71D * (double) var6 + 0.07D * (double) var5);
                var0.setPixel(var11, var12, Color.argb(var8, var7, var7, var7));
            }
        }

        if (var3 > 0) {
            var11 = var3;
        } else {
            var11 = d(var0);
        }

        for (var12 = 0; var12 < var10; ++var12) {
            for (int var13 = 0; var13 < var9; ++var13) {
                var4 = var0.getPixel(var13, var12);
                var7 = Color.red(var4);
                var8 = Color.alpha(var4);
                if (var1 && var8 < var11) {
                    if (var2) {
                        var0.setPixel(var13, var12, -16777216);
                    } else {
                        var0.setPixel(var13, var12, -1);
                    }
                } else if (var7 > var11) {
                    if (var2) {
                        var0.setPixel(var13, var12, -16777216);
                    } else {
                        var0.setPixel(var13, var12, -1);
                    }
                } else if (var2) {
                    var0.setPixel(var13, var12, -1);
                } else {
                    var0.setPixel(var13, var12, -16777216);
                }
            }
        }

        return var0;
    }

    private static int d(Bitmap var0) {
        int[] var1 = e(var0);
        int var2 = var0.getHeight() * var0.getWidth();
        float var3 = 0.0F;

        for (int var4 = 0; var4 < 256; ++var4) {
            var3 += (float) (var4 * var1[var4]);
        }

        float var12 = 0.0F;
        int var5 = 0;
        float var7 = 0.0F;
        threshold = 0;

        for (int var8 = 0; var8 < 256; ++var8) {
            var5 += var1[var8];
            if (var5 != 0) {
                int var6 = var2 - var5;
                if (var6 == 0) {
                    break;
                }

                var12 += (float) (var8 * var1[var8]);
                float var9 = var12 / (float) var5;
                float var10 = (var3 - var12) / (float) var6;
                float var11 = (float) var5 * (float) var6 * (var9 - var10) * (var9 - var10);
                if (var11 > var7) {
                    var7 = var11;
                    threshold = var8;
                }
            }
        }

        return threshold;
    }

    private static int[] e(Bitmap var0) {
        int[] var1 = new int[256];
        int var2 = var0.getWidth();
        int var3 = var0.getHeight();

        int var4;
        for (var4 = 0; var4 < var1.length; ++var4) {
            var1[var4] = 0;
        }

        for (var4 = 0; var4 < var2; ++var4) {
            for (int var5 = 0; var5 < var3; ++var5) {
                int var6 = Color.red(var0.getPixel(var4, var5));
                int var10002 = var1[var6]++;
            }
        }

        return var1;
    }

    public static Bitmap toGrayscale(Bitmap srcImage) {

        Bitmap bmpGrayscale = Bitmap.createBitmap(srcImage.getWidth(), srcImage.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bmpGrayscale);
        Paint paint = new Paint();

        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        canvas.drawBitmap(srcImage, 0, 0, paint);

        return bmpGrayscale;
    }

    protected static Bitmap BitmapAlignment(Bitmap bitmap, int var1, int var2) {
        if (bitmap != null && var1 > 0) {
            int var5 = bitmap.getWidth();
            if (var5 >= var1) {
                return bitmap;
            } else {
                int var6 = bitmap.getHeight();
                Bitmap var7 = Bitmap.createBitmap(var1, var6, Bitmap.Config.ARGB_8888);
                Canvas var8 = new Canvas(var7);
                var8.drawColor(WHITE);
                switch (var2) {
                    case 0:
                        var8.drawBitmap(bitmap, 0.0F, 0.0F, (Paint) null);
                        return var7;
                    case 1:
                        var8.drawBitmap(bitmap, (float) ((var1 - var5) / 2), 0.0F, (Paint) null);
                        return var7;
                    case 2:
                        var8.drawBitmap(bitmap, (float) (var1 - var5), 0.0F, (Paint) null);
                        return var7;
                    default:
                        return bitmap;
                }
            }
        }
        return bitmap;
    }
}
