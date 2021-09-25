package com.jpsoft.onlinemusicplayer.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.jpsoft.onlinemusicplayer.ActionPlayingCallbacks;
import com.jpsoft.onlinemusicplayer.MusicService;
import com.jpsoft.onlinemusicplayer.NotificationReceiver;
import com.jpsoft.onlinemusicplayer.R;
import com.jpsoft.onlinemusicplayer.databinding.ActivityMusicBinding;
import com.jpsoft.onlinemusicplayer.model.TrackFiles;

import java.util.ArrayList;

import static com.jpsoft.onlinemusicplayer.ApplicationClass.ACTION_NEXT;
import static com.jpsoft.onlinemusicplayer.ApplicationClass.ACTION_PLAY;
import static com.jpsoft.onlinemusicplayer.ApplicationClass.ACTION_PREV;
import static com.jpsoft.onlinemusicplayer.ApplicationClass.CHANNEL_ID_2;

public class MusicActivity extends AppCompatActivity implements ActionPlayingCallbacks, ServiceConnection {
    private static final String TAG = "MusicActivity";

    ActivityMusicBinding binding;
    ArrayList<TrackFiles> trackFiles ;
    int position = 0;
    boolean isPlaying = false;
    MusicService musicService;
    MediaSessionCompat mediaSession;
    NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMusicBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        trackFiles = TrackFiles.populateTrackFiles();
        binding.songTitle.setText(trackFiles.get(position).getTitle());
        binding.imageView.setImageResource(trackFiles.get(position).getThumbnail());

        mediaSession = new MediaSessionCompat(this, "PlayAudio");

        setUpListener();
    }

    private void setUpListener() {
        binding.next.setOnClickListener(v -> {
            nextClicked();
        });
        binding.play.setOnClickListener(v -> {
            playClicked();
        });
        binding.prev.setOnClickListener(v -> {
            prevClicked();
        });
    }

    @Override
    public void nextClicked() {
        if (position==3)
            position = 0;
        else
            position++;
        //position = position == 3 ? 0 : position++;
        binding.songTitle.setText(trackFiles.get(position).getTitle());
        binding.imageView.setImageResource(trackFiles.get(position).getThumbnail());
        if (!isPlaying) {
            showNotification(R.drawable.ic_play_circle);
        } else {
            showNotification(R.drawable.ic_pause_circle);
        }
    }

    @Override
    public void prevClicked() {
        if (position==0)
            position = 3;
        else
            position--;
        //position = position == 0 ? 3 : position--;
        binding.songTitle.setText(trackFiles.get(position).getTitle());
        binding.imageView.setImageResource(trackFiles.get(position).getThumbnail());
        if (!isPlaying) {
            showNotification(R.drawable.ic_play_circle);
        } else {
            showNotification(R.drawable.ic_pause_circle);
        }
    }

    @Override
    public void playClicked() {
        if (!isPlaying) {
            isPlaying = true;
            binding.play.setImageResource(R.drawable.ic_pause_circle);
            showNotification(R.drawable.ic_pause_circle);
        } else {
            isPlaying = false;
            binding.play.setImageResource(R.drawable.ic_play_circle);
            showNotification(R.drawable.ic_play_circle);
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        MusicService.MyBinder myBinder = (MusicService.MyBinder) service;
        musicService = myBinder.getService();
        musicService.setPlayingCallbacks(MusicActivity.this);
        Log.d(TAG, "onServiceConnected: " + musicService);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        musicService = null;
        Log.d(TAG, "onServiceDisconnected: " + musicService);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this, MusicService.class);
        bindService(intent, this, BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(this);
    }

    public void showNotification(int playPauseBtn) {
        Intent intent = new Intent(this, MusicActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Intent prevIntent = new Intent(this, NotificationReceiver.class).setAction(ACTION_PREV);
        PendingIntent prevPendingIntent = PendingIntent.getBroadcast(this, 0, prevIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent nextIntent = new Intent(this, NotificationReceiver.class).setAction(ACTION_NEXT);
        PendingIntent nextPendingIntent = PendingIntent.getBroadcast(this, 0, nextIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent playIntent = new Intent(this, NotificationReceiver.class).setAction(ACTION_PLAY);
        PendingIntent playPendingIntent = PendingIntent.getBroadcast(this, 0, playIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Bitmap picture = BitmapFactory.decodeResource(getResources(), trackFiles.get(position).getThumbnail());

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID_2)
                .setSmallIcon(trackFiles.get(position).getThumbnail())
                .setLargeIcon(picture)
                .setContentTitle(trackFiles.get(position).getTitle())
                .setContentText(trackFiles.get(position).getSinger())
                .addAction(R.drawable.ic_previous, "Previous", prevPendingIntent)
                .addAction(playPauseBtn, "Play", playPendingIntent)
                .addAction(R.drawable.ic_next, "Next", nextPendingIntent)
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setMediaSession(mediaSession.getSessionToken()))
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setContentIntent(contentIntent)
                .setOnlyAlertOnce(true)
                .build();

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0,notification);
    }

    @Override
    protected void onDestroy() {
        destroyService();
        super.onDestroy();
    }

    private void destroyService(){
        NotificationManager nManager = ((NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE));
        nManager.cancelAll();
    }
}