package com.example.printerdemo.imageconversion;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

import java.util.ArrayList;

public class BitmapColoumn {

    private PrintColumnParam a;
    private PrintColumnParam b;
    private PrintColumnParam c;
    private PrintColumnParam d;
    private PrintColumnParam e;
    private PrintColumnParam f;
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private int j = 0;
    private int k = 0;
    private int l = 0;
    private int m = 576;
    private int n = 2;

    public BitmapColoumn() {

    }

    public BitmapColoumn(int var1, PrintColumnParam var2, PrintColumnParam var3) {
        this.m = var1;
        this.a = var2;
        this.b = var3;
        this.n = 2;
    }

    public BitmapColoumn(int var1, PrintColumnParam var2, PrintColumnParam var3, PrintColumnParam var4) {
        this.m = var1;
        this.a = var2;
        this.b = var3;
        this.c = var4;
        this.n = 3;
    }

    public BitmapColoumn(int var1, PrintColumnParam var2, PrintColumnParam var3, PrintColumnParam var4, PrintColumnParam var5) {
        this.m = var1;
        this.a = var2;
        this.b = var3;
        this.c = var4;
        this.d = var5;
        this.n = 4;
    }

    public BitmapColoumn(int var1, PrintColumnParam var2, PrintColumnParam var3, PrintColumnParam var4, PrintColumnParam var5, PrintColumnParam var6) {
        this.m = var1;
        this.a = var2;
        this.b = var3;
        this.c = var4;
        this.d = var5;
        this.e = var6;
        this.n = 5;
    }

    public BitmapColoumn(int var1, PrintColumnParam var2, PrintColumnParam var3, PrintColumnParam var4, PrintColumnParam var5, PrintColumnParam var6, PrintColumnParam var7) {
        this.m = var1;
        this.a = var2;
        this.b = var3;
        this.c = var4;
        this.d = var5;
        this.e = var6;
        this.f = var7;
        this.n = 6;
    }

    public Bitmap ConvertingToBitmap() {
        ArrayList var1 = new ArrayList();
        float var2 = (float) (this.a.a + this.b.a);
        int var3 = Math.max(a.values.length, this.b.values.length);
        if (this.n >= 3) {
            var3 = Math.max(var3, this.c.values.length);
            var2 += (float) this.c.a;
        }

        if (this.n >= 4) {
            var3 = Math.max(var3, this.d.values.length);
            var2 += (float) this.d.a;
        }

        if (this.n >= 5) {
            var3 = Math.max(var3, this.e.values.length);
            var2 += (float) this.e.a;
        }

        if (this.n == 6) {
            var3 = Math.max(var3, this.f.values.length);
            var2 += (float) this.f.a;
        }

        this.g = (int) ((float) this.m / var2 * (float) this.a.a);
        this.h = (int) ((float) this.m / var2 * (float) this.b.a);
        if (this.n >= 3) {
            //this.h = (int)((float)this.m / var2 * (float)this.b.a);
            this.i = this.m - this.g - this.h;
        }

        if (this.n >= 4) {
            this.i = (int) ((float) this.m / var2 * (float) this.c.a);
            this.j = this.m - this.g - this.h - this.i;
        }

        if (this.n >= 5) {
            this.j = (int) ((float) this.m / var2 * (float) this.d.a);
            this.k = this.m - this.g - this.h - this.i - this.j;
        }

        if (this.n == 6) {
            this.k = (int) ((float) this.m / var2 * (float) this.e.a);
            this.l = this.m - this.g - this.h - this.i - this.j - this.k;
        }

        for (int var4 = 0; var4 < var3; ++var4) {
            var1.add(this.a(var4));
        }

        return CombineBitmapVertically(var1);
    }

    private Bitmap a(int var1) {
        ArrayList var2 = new ArrayList();
        var2.add(bitmapAlignment(var1 < this.a.values.length ? this.a.values[var1] : " ", this.a.alignment, this.g, this.a.b, this.a.typeface));
        var2.add(bitmapAlignment(var1 < this.b.values.length ? this.b.values[var1] : " ", this.b.alignment, this.h, this.b.b, this.b.typeface));
        if (this.n >= 3) {
            var2.add(bitmapAlignment(var1 < this.c.values.length ? this.c.values[var1] : " ", this.c.alignment, this.i, this.c.b, this.c.typeface));
        }

        if (this.n >= 4) {
            var2.add(bitmapAlignment(var1 < this.d.values.length ? this.d.values[var1] : " ", this.d.alignment, this.j, this.d.b, this.d.typeface));
        }

        if (this.n >= 5) {
            var2.add(bitmapAlignment(var1 < this.e.values.length ? this.e.values[var1] : " ", this.e.alignment, this.k, this.e.b, this.e.typeface));
        }

        if (this.n >= 6) {
            var2.add(bitmapAlignment(var1 < this.f.values.length ? this.f.values[var1] : " ", this.f.alignment, this.l, this.f.b, this.f.typeface));
        }

        return CombineBitmapHorizontally(var2);
    }

    private Bitmap bitmapAlignment(String var1, Layout.Alignment var2, int var3, int var4, Typeface var5) {
        TextPaint var6 = new TextPaint();
        var6.setColor(Color.BLACK);
        var6.setTextSize((float) var4);
        var6.setFakeBoldText(var5.isBold());
        var6.setTypeface(var5);

        StaticLayout var7 = new StaticLayout(var1, var6, var3, var2, 1.2F, 1.0F, true);
        Bitmap.Config var8 = Bitmap.Config.ARGB_8888;
        Bitmap var9 = Bitmap.createBitmap(var3, var7.getHeight(), var8);
        Canvas var10 = new Canvas(var9);
        var10.drawColor(Color.TRANSPARENT);
        var7.draw(var10);
        return var9;
    }



    private Bitmap CombineBitmapHorizontally(ArrayList var1) {
        if (var1.size() == 0) {
            return null;
        } else if (var1.size() == 1) {
            return (Bitmap) var1.get(0);
        } else {
            int var2 = 0;
            int var3 = 0;

            for (int var4 = 0; var4 < var1.size(); ++var4) {
                var3 = Math.max(var3, ((Bitmap) var1.get(var4)).getHeight());
                var2 += ((Bitmap) var1.get(var4)).getWidth();
            }

            Bitmap var8 = Bitmap.createBitmap(var2, var3, Bitmap.Config.ARGB_8888);
            Canvas var5 = new Canvas(var8);
            var5.drawColor(-1);
            int var6 = 0;

            for (int var7 = 0; var7 < var1.size(); ++var7) {
                var6 = var7 == 0 ? 0 : var6 + ((Bitmap) var1.get(var7 - 1)).getWidth();
                var5.drawBitmap((Bitmap) var1.get(var7), (float) var6, 0.0F, (Paint) null);
            }

            return var8;
        }
    }

    public Bitmap CombineBitmapVertically(ArrayList var1) {
        if (var1.size() == 0) {
            return null;
        } else if (var1.size() == 1) {
            return (Bitmap) var1.get(0);
        } else {
            int var2 = 0;
            int var3 = 0;

            for (int var4 = 0; var4 < var1.size(); ++var4) {
                if (var4 < var1.size() - 1) {
                    var2 = ((Bitmap) var1.get(var4)).getWidth() > ((Bitmap) var1.get(var4 + 1)).getWidth() ? ((Bitmap) var1.get(var4)).getWidth() : ((Bitmap) var1.get(var4 + 1)).getWidth();
                }

                var3 += ((Bitmap) var1.get(var4)).getHeight();
            }

            Bitmap var8 = Bitmap.createBitmap(var2, var3, Bitmap.Config.ARGB_8888);
            Canvas var5 = new Canvas(var8);
            var5.drawColor(Color.TRANSPARENT);
            int var6 = 0;
            try {
                for (int var7 = 0; var7 < var1.size(); ++var7) {
                    var6 = var7 == 0 ? 0 : var6 + ((Bitmap) var1.get(var7 - 1)).getHeight();
                    var5.drawBitmap((Bitmap) var1.get(var7), 0.0F, (float) var6, (Paint) null);
                    ((Bitmap) var1.get(var7)).recycle();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            return var8;
        }
    }

    public Bitmap CombineBitmapVertically1(ArrayList var1) {
        if (var1.size() == 0) {
            return null;
        } else if (var1.size() == 1) {
            return (Bitmap) var1.get(0);
        } else {
            int var2 = 0;
            int var3 = 0;

            for (int var4 = 0; var4 < var1.size(); ++var4) {
                if (var4 < var1.size() - 1) {
                    var2 = Math.max(var2, ((Bitmap) var1.get(var4)).getWidth());
                }

                var3 += ((Bitmap) var1.get(var4)).getHeight();
            }

            Bitmap var8 = Bitmap.createBitmap(var2, var3, Bitmap.Config.ARGB_8888);
            Canvas var5 = new Canvas(var8);
            var5.drawColor(Color.WHITE);
            int var6 = 0;
            for (int var7 = 0; var7 < var1.size(); ++var7) {
                var6 = var7 == 0 ? 0 : var6 + ((Bitmap) var1.get(var7 - 1)).getHeight();
                var5.drawBitmap((Bitmap) var1.get(var7), 0.0F, (float) var6, (Paint) null);
                ((Bitmap) var1.get(var7)).recycle();
            }

            return var8;
        }

    }
}
