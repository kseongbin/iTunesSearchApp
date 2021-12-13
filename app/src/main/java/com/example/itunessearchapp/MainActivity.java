package com.example.itunessearchapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText et;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    ArrayList<ApiResult> apiResults= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        items.add(new RecyclerViewItem("trackName", "분:초", "collectionName", "발매년도", "artistName", R.drawable.ic_launcher_background));
//        items.add(new RecyclerViewItem("trackName", "분:초", "collectionName", "발매년도", "artistName", R.drawable.ic_launcher_background));
//        items.add(new RecyclerViewItem("trackName", "분:초", "collectionName", "발매년도", "artistName", R.drawable.ic_launcher_background));

        recyclerView= findViewById(R.id.recyclerview);
        adapter = new RecyclerViewAdapter(this, apiResults);
        recyclerView.setAdapter(adapter);
    }//onCreate method...

    public void clickBtn(View view) {
        apiResults.clear();
        adapter.notifyDataSetChanged();

        et = findViewById(R.id.et);

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("https://itunes.apple.com");
        builder.addConverterFactory(ScalarsConverterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<RecyclerViewItem> call = retrofitService.searchItunesApp(et.getText().toString(), "KR", "song");
        call.enqueue(new Callback<RecyclerViewItem>() {
            @Override
            public void onResponse(Call<RecyclerViewItem> call, Response<RecyclerViewItem> response) {
//                Toast.makeText(MainActivity.this, "성공", Toast.LENGTH_SHORT).show();
                ArrayList<ApiResult> results =response.body().results;
                if(results.size()==0){
                    Toast.makeText(MainActivity.this, "검색결과가 없습니다.", Toast.LENGTH_LONG).show();
                    return;
                }

                for (ApiResult result:results){
                    apiResults.add(result);
                    adapter.notifyItemInserted(apiResults.size()-1);

                }
//                Toast.makeText(MainActivity.this, results.size()+"", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<RecyclerViewItem> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        et.setText("");
        et.clearFocus();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                int itemTotalCount = recyclerView.getAdapter().getItemCount() - 1;
                if (lastVisibleItemPosition == itemTotalCount) {
                    Toast.makeText(MainActivity.this, "마지막 페이지입니다.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }//clickBtn method...


}//MainActivity class..