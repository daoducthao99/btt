package com.example.test1;

public class Song {
    private int Id;
    private String SongName;
    private String SingerName;
    private String Time;

    public Song() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Song(int id, String songName, String singerName, String time) {
        Id = id;
        SongName = songName;
        SingerName = singerName;
        Time = time;
    }

    public Song(String songName, String singerName, String time) {

        SongName = songName;
        SingerName = singerName;
        Time = time;
    }

    public String getSongName() {
        return SongName;
    }

    public void setSongName(String songName) {
        SongName = songName;
    }

    public String getSingerName() {
        return SingerName;
    }

    public void setSingerName(String singerName) {
        SingerName = singerName;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
