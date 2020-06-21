package com.example.test1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test1.MainActivity;
import com.example.test1.R;

public class Add extends AppCompatActivity {
    private Button btnAdd,btnBack;
    private EditText txtId,txtNameSong,txtArtist,txtTime;
    private SongDB songDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initComponent();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtId.getText().toString().equals(""))
                {
                    Toast.makeText(Add.this,"Bạn phải nhập id",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(songDB.checkId(Integer.parseInt(txtId.getText().toString())))
                {
                    Toast.makeText(Add.this,"Bài hát đã tồn tại, vui lòng nhập bài hát khác",Toast.LENGTH_LONG).show();
                    return;
                }
                if(txtNameSong.getText().toString().trim().equals(""))
                {
                    Toast.makeText(Add.this,"Bạn phải nhập tên bài hát",Toast.LENGTH_LONG).show();
                    return;
                }
                if(txtArtist.getText().toString().equals(""))
                {
                    Toast.makeText(Add.this,"Bạn phải nhập tên ca sỹ",Toast.LENGTH_LONG).show();
                    return;
                }
                if(txtTime.getText().toString().equals(""))
                {
                    Toast.makeText(Add.this,"Bạn phải nhập thời lượng bài hát",Toast.LENGTH_LONG).show();
                    return;
                }
                Song songNew =new Song();
                songNew.setId(Integer.parseInt(txtId.getText().toString()));
                songNew.setSongName(txtNameSong.getText().toString());
                songNew.setSingerName(txtArtist.getText().toString());
                songNew.setTime(txtTime.getText().toString());
                songDB.AddSong(songNew);
                Toast.makeText(Add.this,"Thêm bài hát thành công",Toast.LENGTH_LONG).show();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iten=new Intent(Add.this, MainActivity.class);
                startActivity(iten);
            }
        });
    }

    private void initComponent() {
        btnAdd=(Button)findViewById(R.id.btnADD);
        btnBack=(Button)findViewById(R.id.btnBack);
        txtId=(EditText)findViewById(R.id.txtID);
        txtNameSong=(EditText)findViewById(R.id.txtSongName);
        txtArtist=(EditText)findViewById(R.id.txtArtist);
        txtTime=(EditText)findViewById(R.id.txtTimer);
        songDB=new SongDB(this,"SongDatabase",null,1);
    }
}
