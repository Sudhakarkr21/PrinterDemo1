package com.example.printerdemo.imageconversion;

import android.graphics.Typeface;
import android.text.Layout;

public class PrintColumnParam {


    int a = 33;
    int b = 26;
    String[] values;
    Layout.Alignment alignment;
    Typeface typeface;

    public PrintColumnParam(String[] var1, int var2, Layout.Alignment var3, int var4, Typeface var5) {
        this.alignment = Layout.Alignment.ALIGN_NORMAL;
        values = var1;
        this.alignment = var3;
        this.typeface = var5;
        this.b(var2);
        this.a(var4);
    }

    public PrintColumnParam(String[] var1, int var2, Layout.Alignment var3, int var4) {
        this.alignment = Layout.Alignment.ALIGN_NORMAL;
        this.values = var1;
        this.alignment = var3;
        this.typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL);
        this.b(var2);
        this.a(var4);
    }

    public PrintColumnParam(String[] var1, int var2, Layout.Alignment var3) {
        this.alignment = Layout.Alignment.ALIGN_NORMAL;
        this.values = var1;
        this.alignment = var3;
        this.typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
        this.b = 22;
        this.b(var2);
    }

    public PrintColumnParam(String[] var1, int var2) {
        this.alignment = Layout.Alignment.ALIGN_NORMAL;
        this.values = var1;
        this.alignment = Layout.Alignment.ALIGN_NORMAL;
        this.typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
        this.b = 22;
        this.b(var2);
    }

    private void a(int var1) {
        /*if (var1 >= 18 && var1 <= 38) {
            this.b = 22;
        } else*/ {
            this.b = var1;
        }

    }

    private void b(int var1) {
        if (var1 >= 0 && var1 <= 100) {
            this.a = var1;
        } else {
            this.a = 16;
        }

    }
}
