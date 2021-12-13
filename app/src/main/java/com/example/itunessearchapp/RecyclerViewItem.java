package com.example.itunessearchapp;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewItem {
    int resultCount;
    ArrayList<ApiResult> results;
}
class ApiResult{
    String artworkUrl100;   //앨범 사진
    String trackName;       //곡 이름
    String collectionName;  //앨범 이름
    String artistName;      //가수 이름
    String releaseDate;     //발매년도
    String trackTimeMillis; //곡 재생시간

}
