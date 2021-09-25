package com.jpsoft.onlinemusicplayer.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class QuranAudioModel{
	private String surahName ;
	private String verse;
	private String audioUrl;
	private String filePath;

    public QuranAudioModel(String surahName, String verse, String audioUrl) {
        this.surahName = surahName;
        this.verse = verse;
        this.audioUrl = audioUrl;
    }

    public String getSurahName() {
        return surahName;
    }

    public String getVerse() {
        return verse;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public static List<QuranAudioModel> getDummyData(){
        List<QuranAudioModel> quranAudioModelList = new ArrayList<>();
        quranAudioModelList.add(new QuranAudioModel("Al-Fatihah","Bismillah hir rahman nir raheem","http://darbar.arkhairul.xyz/upload/verse/1630512889.mp3"));
        quranAudioModelList.add(new QuranAudioModel("Al-Fatihah","Alhamdulillahi Rabbil ‘aalameen","http://darbar.arkhairul.xyz/upload/verse/1630512912.mp3"));
        quranAudioModelList.add(new QuranAudioModel("Al-Fatihah","Ar-Rahmaanir-Raheem","http://darbar.arkhairul.xyz/upload/verse/1630512923.mp3"));
        quranAudioModelList.add(new QuranAudioModel("Al-Fatihah","Maliki Yawmi-Deen","http://darbar.arkhairul.xyz/upload/verse/1630512933.mp3"));
        quranAudioModelList.add(new QuranAudioModel("Al-Fatihah","iyyaka na’budu wa iyyaka nastaeen","http://darbar.arkhairul.xyz/upload/verse/1630512943.mp3"));
        quranAudioModelList.add(new QuranAudioModel("Al-Fatihah","Ihdinas Siraatal Mustaqeem","http://darbar.arkhairul.xyz/upload/verse/1630512953.mp3"));
        quranAudioModelList.add(new QuranAudioModel("Al-Fatihah","Siraatal lazeena an’amta ‘alaihim ghayril maghdoobi ‘alaihim wa lad daaalleen","http://darbar.arkhairul.xyz/upload/verse/1630512962.mp3"));
        return quranAudioModelList;
    }

    @Override
    public String toString() {
        return "QuranAudioModel{" +
                "surahName='" + surahName + '\'' +
                ", verse='" + verse + '\'' +
                ", audioUrl='" + audioUrl + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}