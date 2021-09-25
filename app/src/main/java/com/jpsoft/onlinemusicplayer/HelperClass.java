package com.jpsoft.onlinemusicplayer;

import android.Manifest;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jpsoft.onlinemusicplayer.model.QuranAudioModel;
import com.jpsoft.onlinemusicplayer.network.ApiClient;
import com.jpsoft.onlinemusicplayer.network.ApiUtils;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.krishna.fileloader.request.MultiFileLoadRequest;

import java.util.ArrayList;
import java.util.List;

import static com.jpsoft.onlinemusicplayer.network.ApiUtils.IMAGE_BASE_URL;

public class HelperClass {
    Context context;
    QuranActivityInterface callback;

    public HelperClass(Context context, QuranActivityInterface callback) {
        this.context = context;
        this.callback = callback;
    }

    private static final String TAG = "HelperClass";

    public void userPermission() {
        Dexter.withContext(context)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                if (report.areAllPermissionsGranted()) {
                    callback.onPermissionGranted();
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                callback.onPermissionDenied();
                token.continuePermissionRequest();
            }
        }).check();

    }
}
