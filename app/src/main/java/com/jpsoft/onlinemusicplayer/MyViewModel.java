package com.jpsoft.onlinemusicplayer;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.downloader.Error;
import com.downloader.OnDownloadListener;
import com.downloader.PRDownloader;
import com.jpsoft.onlinemusicplayer.model.QuranAudioModel;
import com.tonyodev.fetch2.AbstractFetchListener;
import com.tonyodev.fetch2.Download;
import com.tonyodev.fetch2.Fetch;
import com.tonyodev.fetch2.FetchConfiguration;
import com.tonyodev.fetch2.FetchListener;
import com.tonyodev.fetch2.NetworkType;
import com.tonyodev.fetch2.Request;
import com.tonyodev.fetch2core.DownloadBlock;
import com.tonyodev.fetch2core.Func;
import com.tonyodev.fetch2okhttp.OkHttpDownloader;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.List;

import okhttp3.OkHttpClient;

public class MyViewModel extends ViewModel {
    private Fetch fetch;
    private Context context;
    private static final String TAG = "MyViewModel";
    private MutableLiveData<Integer> downloadProgress;
    QuranActivityInterface callback;

    public void setUp(Context context,QuranActivityInterface callback){
        this.context = context;
        this.callback = callback;
        PRDownloader.initialize(context);
    }


    public void downloadFiles(List<QuranAudioModel> audioModelList) {
        for (int i = 0; i< audioModelList.size(); i++){
            Log.d(TAG, "downloadFiles: "+audioModelList.get(i).getSurahName());
            File file = new File(context.getExternalFilesDir(null)+"/download/MyDarbar/"+audioModelList.get(i).getSurahName()+"_"+i+".mp3");
            Log.d(TAG, "downloadFiles: "+file.getAbsolutePath());
            if (file.exists())
                callback.onDownloaded();
            else{
                PRDownloader.download(audioModelList.get(i).getAudioUrl(),context.getExternalFilesDir(null)+"/download/MyDarbar/",audioModelList.get(i).getSurahName()+"_"+i+".mp3")
                        .build()
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                callback.onDownloaded();
                            }

                            @Override
                            public void onError(Error error) {
                                Log.d(TAG, "onError: "+error.getServerErrorMessage());
                            }
                        });
            }
        }
    }
}
