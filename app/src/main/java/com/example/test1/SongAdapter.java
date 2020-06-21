package com.example.test1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.test1.R;
import com.example.test1.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SongAdapter extends BaseAdapter {
    private ArrayList<Song> data;
    private List<Song> databackup;
    private LayoutInflater inflater;
    private Activity activity;

    public SongAdapter(Activity activity, ArrayList<Song> data) {
        this.activity = activity;
        this.data = data;
        this.inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        databackup=new ArrayList<Song>();
        databackup.addAll(data);
    }

    @Override
    public int getCount() {
        if(data!=null)
        {
            return data.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(data!=null)
        {
            return data.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        if(data!=null)
        {
            return ((Song)data.get(position)).getId();
        }
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(vi==null)
        {
            vi=inflater.inflate(R.layout.item,null);
        }
        TextView txtNameSong=(TextView)vi.findViewById(R.id.txtSongName);
        txtNameSong.setText(data.get(position).getSongName());
        TextView txtSingerName=(TextView)vi.findViewById(R.id.txtSinger);
        txtSingerName.setText(data.get(position).getSingerName());
        TextView txtTime=(TextView)vi.findViewById(R.id.txtTime);
        txtTime.setText(data.get(position).getTime());
        return vi;
    }
    public void Filter(String s)
    {
        s=s.toLowerCase(Locale.getDefault());
        data.clear();
        if (s.length()==0)
        {
            data.addAll(databackup);
        }
        else
        {
            for (Song item :  databackup) {
                if(item.getSingerName().toLowerCase(Locale.getDefault()).contains(s))
                {
                    data.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

}
