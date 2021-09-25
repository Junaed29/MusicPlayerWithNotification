package com.jpsoft.onlinemusicplayer.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.jpsoft.onlinemusicplayer.ActionPlayingCallbacks;
import com.jpsoft.onlinemusicplayer.HelperClass;
import com.jpsoft.onlinemusicplayer.MyViewModel;
import com.jpsoft.onlinemusicplayer.QuranActivityInterface;
import com.jpsoft.onlinemusicplayer.R;
import com.jpsoft.onlinemusicplayer.databinding.ActivityMainBinding;
import com.jpsoft.onlinemusicplayer.model.QuranAudioModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements QuranActivityInterface, ActionPlayingCallbacks {

    private static final String TAG = "MainActivityMusic";
    MediaPlayer mMediaPlayer;
    HelperClass helperClass;
    ActivityMainBinding binding;
    int currentIndex = 0;
    private ArrayList<Integer> songs;
    private List<QuranAudioModel> quranAudioModelArrayList = new ArrayList<>();
    private MyViewModel myViewModel;
    private int downloaded = 0;
    private final boolean firstTimePlay = false;
    boolean isPlaying = false;
    private int totalDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        myViewModel.setUp(this, this);
        quranAudioModelArrayList = QuranAudioModel.getDummyData();
        mMediaPlayer = new MediaPlayer();

        helperClass = new HelperClass(this, this);
        helperClass.userPermission();

        setupListener();
    }

    private void setupListener() {

        binding.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                    mMediaPlayer.pause();
                    binding.play.setImageResource(R.drawable.ic_play_circle);
                } else if (currentIndex == 0) {
                    playAudio(currentIndex);
                    binding.play.setImageResource(R.drawable.ic_pause_circle);
                } else {

                    mMediaPlayer.start();
                    binding.play.setImageResource(R.drawable.ic_pause_circle);
                }

            }
        });

        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (mMediaPlayer != null)
                    binding.play.setImageResource(R.drawable.ic_pause_circle);
                if (currentIndex < quranAudioModelArrayList.size() - 1){
                    currentIndex++;
                    playAudio(currentIndex);
                } else {
                    currentIndex = 0;
                    mMediaPlayer.stop();
                    binding.play.setImageResource(R.drawable.ic_play_circle);
                }
            }
        });


        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMediaPlayer != null)
                    binding.play.setImageResource(R.drawable.ic_pause_circle);
                if (currentIndex < quranAudioModelArrayList.size() - 1) {
                    currentIndex++;
                    playAudio(currentIndex);
                } else {
                    currentIndex = 0;
                    mMediaPlayer.stop();
                    binding.play.setImageResource(R.drawable.ic_play_circle);
                }

            }
        });


        binding.prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMediaPlayer != null)
                    binding.play.setImageResource(R.drawable.ic_pause_circle);
                if (currentIndex > 0)
                    currentIndex--;
                else
                    currentIndex = quranAudioModelArrayList.size() - 1;

                playAudio(currentIndex);
            }
        });

    }

    @Override
    public void onPermissionGranted() {
        totalDownload = QuranAudioModel.getDummyData().size();
        binding.rlProgress.setVisibility(View.VISIBLE);
        myViewModel.downloadFiles(QuranAudioModel.getDummyData());
    }

    @Override
    public void onPermissionDenied() { }

    @Override
    public void onDownloaded() {
        quranAudioModelArrayList.get(downloaded).setFilePath(this.getExternalFilesDir(null) + "/download/MyDarbar/" + quranAudioModelArrayList.get(downloaded).getSurahName() + "_" + downloaded + ".mp3");
        Log.d(TAG, "onDownloaded: " + quranAudioModelArrayList.get(downloaded).toString());
        downloaded++;
        binding.progressText.setText(downloaded + "/" + totalDownload);
        if (downloaded == totalDownload) {
            binding.rlProgress.setVisibility(View.GONE);
            Toast.makeText(this, "Download Complete", Toast.LENGTH_SHORT).show();
        }
    }

    private void playAudio(int position) {
        mMediaPlayer.reset();
        if (mMediaPlayer.isPlaying())
            mMediaPlayer.stop();

        Log.d(TAG, "playAudio: Url" + quranAudioModelArrayList.get(position).getFilePath());
        binding.songTitle.setText(quranAudioModelArrayList.get(position).getSurahName() + position);

        try {
            mMediaPlayer.setDataSource(quranAudioModelArrayList.get(position).getFilePath());
            mMediaPlayer.prepare();
        } catch (Exception e) {
            Log.d(TAG, "playAudio: " + e);
        }

        mMediaPlayer.start();
    }

    @Override
    public void nextClicked() {

    }

    @Override
    public void prevClicked() {

    }

    @Override
    public void playClicked() {

    }
}