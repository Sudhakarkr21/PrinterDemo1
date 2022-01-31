package com.example.printerdemo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;

public class AppConstants {
    private final Context context;

    public AppConstants(Context context) {
        this.context = context;
    }

    public String getAppVersion() {
        String applicationVersion;
        // Getting Application version number
        PackageInfo pInfo;
        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            applicationVersion = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            applicationVersion = " ";
        }
        return applicationVersion;
    }
}