package com.example.printerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.acme.btp.PrintIntentHandler;
import com.example.printerdemo.imageconversion.BitmapColoumn;
import com.example.printerdemo.imageconversion.CieBluetoothPrinter;
import com.example.printerdemo.imageconversion.PrintColumnParam;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

public class MainActivity extends AppCompatActivity {

    CieBluetoothPrinter cieBluetoothPrinter = new CieBluetoothPrinter();
    Bitmap mergerBitMap = null;
    ImageView image;
    int test = 0;
    int test1 =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.image);
        TextView tv_version = findViewById(R.id.smart_billing_version);
        String application_version = new AppConstants(getApplicationContext()).getAppVersion();
        tv_version.setText(application_version);
        findViewById(R.id.btnSetPrefPrinter).setOnClickListener(view -> {
            Intent i = new Intent(this, PrintIntentHandler.class);
            i.putExtra("CONFIG", "SET_PRINTER");
            startActivity(i);
        });

        findViewById(R.id.btnClearPrefPrinter).setOnClickListener(view -> {
            Intent i = new Intent(this, PrintIntentHandler.class);
            i.putExtra("CONFIG", "CLEAR_PRINTER");
            startActivity(i);
        });

        findViewById(R.id.btnPrinterTest).setOnClickListener(view -> {
            Intent i = new Intent(this, PrintIntentHandler.class);
            i.putExtra("CONFIG", "TEST_PRINTER");
            startActivity(i);
        });


        findViewById(R.id.btnSetSpeed).setOnClickListener(view -> {
            Intent i = new Intent(this, PrintIntentHandler.class);
            i.putExtra("CONFIG", "SET_SPEED");
            long iSpeed = ((Spinner) findViewById(R.id.spPrinterSpeed)).getSelectedItemId();
            switch ((int) iSpeed) {
                case 0:
                    i.putExtra("SPEED", 1);
                    break;
                case 1:
                    i.putExtra("SPEED", 5);
                    break;
                case 2:
                    i.putExtra("SPEED", 10);
                    break;
                case 3:
                    i.putExtra("SPEED", 20);
                    break;
            }
            startActivity(i);
        });

        findViewById(R.id.btnImagePrint).setOnClickListener(view -> {
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inScaled = false;
          //  Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.header_image, o);
            Bitmap bmp = printText();

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            String sBase64PngImage = Base64.encodeToString(byteArray, Base64.DEFAULT);

            Intent i = new Intent(this, PrintIntentHandler.class);
            i.putExtra("DATA_TYPE", "PNG_B64_IMAGE");
            i.putExtra("PRINT_DATA", sBase64PngImage);
            startActivity(i);

            stream = new ByteArrayOutputStream();
           /* bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] pngByteArray = stream.toByteArray();
            Intent iba = new Intent(this, PrintIntentHandler.class);
            iba.putExtra("DATA_TYPE", "PNG_BYTE_ARRAY");
            iba.putExtra("PRINT_DATA", pngByteArray);
            startActivity(iba);*/

            Typeface tf = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL);
            /*for (int j = 1; j <= 2; j++) {
                i.putExtra("DATA_TYPE", "PNG_B64_IMAGE");
                bmp = createTextBmpWithFont("\n" + j + "\n", Layout.Alignment.ALIGN_CENTER, 36, tf, true, true, false, 576);
                stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byteArray = stream.toByteArray();
                sBase64PngImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
                i.putExtra("PRINT_DATA", sBase64PngImage);
                Log.d("Intent Print Test", "Print Intent : " + j);
                startActivity(i);
            }*/
        });
    }

    private static Bitmap createTextBmpWithFont(String sTxt, Layout.Alignment alignment, int iFontSize, Typeface tf, boolean bBold, boolean bItalic, boolean bUnderline, int iPrintWidthPixels) {

        if (iFontSize < 16) {
            iFontSize = 16;
        }

        if (iFontSize >= 38) {
            iFontSize = 38;
        }

        TextPaint tp = new TextPaint();
        tp.setColor(BLACK);
        tp.setTextSize(iFontSize);
        tp.setFakeBoldText(bBold);
        tp.setUnderlineText(bUnderline);
        tp.setTypeface(tf);

        StaticLayout layout = new StaticLayout(sTxt, tp, iPrintWidthPixels, alignment, 1, 1, true);

        int tlHeight = layout.getHeight();

        Bitmap.Config conf = Bitmap.Config.RGB_565;
        Bitmap bmp = Bitmap.createBitmap(iPrintWidthPixels, tlHeight, conf);

        Canvas canvas = new Canvas(bmp);
        canvas.drawColor(WHITE);

        layout.draw(canvas);
        return bmp;
    }

    public Bitmap printText() {
        Bitmap icon = BitmapFactory.decodeResource(getResources(),
                R.drawable.header_image);
        List<Bitmap> bitmapList = new ArrayList<>();

       //   bitmapList.add(CieBluetoothPrinter.BitmapAlignment(cieBluetoothPrinter.getResizedBitmap(icon,300,100),576,1));
           bitmapList.add(printDetailsData3(cieBluetoothPrinter, "ಉಪ ವಿಭಾಗ/Kannada", "540059"));
       // bitmapList.add(bitmap1);
      //  bitmapList.add(printDetailsData1(cieBluetoothPrinter, "సబ్ డివిజన్/Telugu", "540038"));
       bitmapList.add(printDetailsData3(cieBluetoothPrinter, "उप प्रभाग/Hindi", "540038"));
        // bitmapList.add(printDetailsData1(cieBluetoothPrinter, "ಆರ್.ಆರ್ ನಂಬರ್/RRNO", "87512546"));
//        bitmapList.add(printDetailsData3(cieBluetoothPrinter, "ಆರ್.ಆರ್ ನಂಬರ್/RRNO", "87512546"));
       /* bitmapList.add(printDetailsData3(cieBluetoothPrinter, "ಜಕಾತಿ/Tariff", "LT2"));
        bitmapList.add(printDetailsData3(cieBluetoothPrinter, "ಮಂ.ಪ್ರಮಾಣ/Sanct Load", "HP: " + "0" + " " + "KW:" + "1"));
      //  bitmapList.add(printDetailsData3(cieBluetoothPrinter, "ಬಲ್ಲಿಂಗ್ ಅವಧಿ", printData("10/05/2021", "-", "10/06/2021")));
        bitmapList.add(printDetailsData3(cieBluetoothPrinter, "ರೀಡಿಂಗ ದಿನಾಂಕ", "10/06/2021"));
        bitmapList.add(printDetailsData3(cieBluetoothPrinter, "ಬಿಲ್ ಸಂಖ್ಯೆ/BillNo", "10/06/20214445144"));
        bitmapList.add(printDetailsData3(cieBluetoothPrinter, "ಮೀಟರ್ ಸಂಖ್ಯೆ/Meter SlNo", "54345824923549554"));
        bitmapList.add(printDetailsData3(cieBluetoothPrinter, "ಇಂದಿನ ಮಾಪನ/Pres Rdg", "10/09/2021" + " / " + "NOR"));
        bitmapList.add(printDetailsData3(cieBluetoothPrinter, "ಹಿಂದಿನ ಮಾಪನ/Prev Rdg", "871454"));
        bitmapList.add(printDetailsData3(cieBluetoothPrinter, "ಮಾಪನ ಸ್ಥಿರಾಂಕ/Constant", "10"));
        bitmapList.add(printDetailsData3(cieBluetoothPrinter, "ಬಳಕೆ/Consumption", "100"));
        bitmapList.add(printDetailsData3(cieBluetoothPrinter, "ಸರಾಸರಿ/Average", "54"));
        bitmapList.add(printDetailsData3(cieBluetoothPrinter, "ದಾಖಲಿತ ಬೇಡಿಕೆ", "0"));
        bitmapList.add(printDetailsData3(cieBluetoothPrinter, "ConnLd/RatedLd", "1"));
        bitmapList.add(printDetailsData3(cieBluetoothPrinter, "ಪವರ ಫ್ಯಾಕ್ಟರ/Power Factor", "0"));
        bitmapList.add(printDetailsData3(cieBluetoothPrinter, "ಬಿಲ್/PAYABLE", "100"));*/
        // bitmapList.add(printDetailsData2(cieBluetoothPrinter,"Sudhakar KR","Android Developer"));
      /*  bitmapList.add(bitmap1);
        bitmapList.add(bitmap2);*/

      /*  String value = "8105038588"; // Whatever you need to encode in the QR code
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(value, BarcodeFormat.CODABAR,200,50);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            bitmapList.add(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }

        try {
            bitmapList.add(cieBluetoothPrinter.encodeAsBitmap(value,100,100));
        } catch (WriterException e) {
            e.printStackTrace();
        }*/

        try {
            mergerBitMap = new BitmapColoumn().CombineBitmapVertically1((ArrayList) bitmapList);
        } catch (Exception exception) {
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            exception.printStackTrace();
        }

        image.setImageBitmap(mergerBitMap);
        return mergerBitMap;
        // mergerBitMap = CieBluetoothPrinter.toGrayscale(mergerBitMap);


        // new CieBluetoothPrinter.CombineTwoBitmap(this).execute(bitmapList,filepath1("ConvertedImages"));
    }

    private static final String SEMI = ":";
    private static final String SPACE = " ";

    private Bitmap printDetailsData3(CieBluetoothPrinter mPrinter, String value, String result) {
        int text = 22;
        String[] sCol1 = {SPACE + SPACE + SPACE + value};
        PrintColumnParam pcp1stCol = new PrintColumnParam(sCol1, 48, Layout.Alignment.ALIGN_NORMAL, text, Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD));
        String[] sCol2 = {SEMI};
        PrintColumnParam pcp2ndCol = new PrintColumnParam(sCol2, 1, Layout.Alignment.ALIGN_CENTER, text);
        String[] sCol3 = {result + SPACE + SPACE + SPACE};
        PrintColumnParam pcp3rdCol = new PrintColumnParam(sCol3, 51, Layout.Alignment.ALIGN_OPPOSITE, text, Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD));
        return mPrinter.PrintTable(pcp1stCol, pcp2ndCol,pcp3rdCol);
    }
}