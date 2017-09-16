package com.hdmes.crane001;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/9/11 0011.
 */
public class MyAdapter  extends BaseAdapter {

    private Context context;

    private List<Food> data ;

    public MyAdapter(Context context, List<Food> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){

            convertView = LayoutInflater.from(context).inflate(R.layout.activity_crane, null);

        }
        ImageView iv_img = (ImageView) convertView.findViewById(R.id.iv_img);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tv_desc = (TextView) convertView.findViewById(R.id.tv_desc);

        Food food = data.get(position);

        if(food.getImgData()==null){
            iv_img.setImageResource(R.drawable.bg_edittext);//cinema_1
        }else{
            byte [] imgData = food.getImgData();
            Bitmap bm  = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
            iv_img.setImageBitmap(bm);
        }

        tv_name.setText( food.getName());
        tv_desc.setText( food.getDesc());

        return convertView;
    }

}