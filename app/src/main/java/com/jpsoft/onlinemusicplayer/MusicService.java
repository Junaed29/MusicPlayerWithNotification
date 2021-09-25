package com.jpsoft.onlinemusicplayer;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import static com.jpsoft.onlinemusicplayer.ApplicationClass.ACTION_NEXT;
import static com.jpsoft.onlinemusicplayer.ApplicationClass.ACTION_PLAY;
import static com.jpsoft.onlinemusicplayer.ApplicationClass.ACTION_PREV;
import static com.jpsoft.onlinemusicplayer.ApplicationClass.MY_ACTION_NAME;

public class MusicService extends Service {
    private IBinder mBinder = new MyBinder();
    private static final String TAG = "MusicService";
    ActionPlayingCallbacks playingCallbacks;
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return mBinder;
    }

    public class MyBinder extends Binder{
        public MusicService getService(){
            return MusicService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String actionName = intent.getStringExtra(MY_ACTION_NAME);
        if (actionName != null)
            switch (actionName) {
                case ACTION_PLAY:
                    if (playingCallbacks!=null)
                        playingCallbacks.playClicked();
                    break;
                case ACTION_PREV:
                    if (playingCallbacks!=null)
                        playingCallbacks.prevClicked();
                    break;
                case ACTION_NEXT:
                    if (playingCallbacks!=null)
                        playingCallbacks.nextClicked();
                    break;

            }
        return START_STICKY;
    }

    public void setPlayingCallbacks(ActionPlayingCallbacks playingCallbacks) {
        this.playingCallbacks = playingCallbacks;
    }
}