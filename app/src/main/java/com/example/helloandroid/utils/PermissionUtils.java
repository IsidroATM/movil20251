package com.example.helloandroid.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

public class PermissionUtils {

    public static boolean checkCameraPermission(Activity activity) {
        return activity.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestCameraPermission(Activity activity) {
        activity.requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
    }
}
