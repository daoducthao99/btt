package com.example.test1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test1.Add;
import com.example.test1.R;
import com.example.test1.Song;
import com.example.test1.SongAdapter;
import com.example.test1.SongDB;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Song> lstSong;
    private SongAdapter songAdapter;
    private EditText txtSearch;
    private SongDB songDB;
    private Button btntoAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        songDB=new SongDB(this,"SongDatabase",null,1);
        initSong();
        btntoAdd=(Button)findViewById(R.id.btnToAdd);
        lstSong=songDB.getAllSong();
        listView=(ListView)findViewById(R.id.listViewSong);
        songAdapter=new SongAdapter(this,lstSong);
        listView.setAdapter(songAdapter);
        registerForContextMenu(listView);
        txtSearch=(EditText)findViewById(R.id.edtSearch);
        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                songAdapter.Filter(txtSearch.getText().toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        listView.setLongClickable(true);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final Song s = lstSong.get(position);
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Xác nhận xoá");
                builder.setMessage("Bạn có chắc chắn muốn xoá bài hát này không?");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        songDB.deleteSong(s.getId());
                        lstSong.remove(position);
                        songAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
                return false;
            }
        });
        btntoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Add.class);
                startActivity(intent);
            }
        });
    }

    private void initSong()
    {
        songDB.AddSong(new Song(1,"Phút cuối","Bằng Kiều","3.27"));
        songDB.AddSong(new Song(2,"Bông hồng thủ tinh","Bức Tường","4.18"));
        songDB.AddSong(new Song(3,"Hà nội mùa thu","Mỹ Linh","4.11"));
        songDB.AddSong(new Song(4,"Bà tôi","Tùng Dương","3.57"));
        songDB.AddSong(new Song(5,"Giọt hồng","Quang Dũng","4.01"));
        songDB.AddSong(new Song(6,"Trái tim bên lề","Bằng Kiều","4.41"));
    }
}
