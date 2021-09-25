package com.jpsoft.onlinemusicplayer.model;

import com.jpsoft.onlinemusicplayer.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TrackFiles {
    private String title;
    private String singer;
    private int thumbnail;

    public TrackFiles(String title, String singer, int thumbnail) {
        this.title = title;
        this.singer = singer;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    @NotNull
    public static ArrayList<TrackFiles> populateTrackFiles() {
        ArrayList<TrackFiles> trackFiles = new ArrayList<>();
        TrackFiles trackFiles1 = new TrackFiles(
                "Track 1 Title",
                "Track 1 singer",
                R.drawable.ball_1
        );
        trackFiles.add(trackFiles1);

        TrackFiles trackFiles2 = new TrackFiles(
                "Track 2 Title",
                "Track 2 singer",
                R.drawable.ball_2
        );
        trackFiles.add(trackFiles2);

        TrackFiles trackFiles3 = new TrackFiles(
                "Track 3 Title",
                "Track 3 singer",
                R.drawable.ball_3
        );
        trackFiles.add(trackFiles3);

        TrackFiles trackFiles4 = new TrackFiles(
                "Track 4 Title",
                "Track 4 singer",
                R.drawable.ball_4
        );
        trackFiles.add(trackFiles4);

        TrackFiles trackFiles5 = new TrackFiles(
                "Track 5 Title",
                "Track 5 singer",
                R.drawable.ball_5
        );
        trackFiles.add(trackFiles5);

        return trackFiles;
    }
}
