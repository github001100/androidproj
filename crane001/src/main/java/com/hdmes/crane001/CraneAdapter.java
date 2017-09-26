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
 * Created by Administrator on 2017/9/21.
 */

public  class CraneAdapter  extends BaseAdapter {

    private Context context;
    private List<Crane> data;

    public CraneAdapter(Context context, List<Crane> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.activity_crane2, null);

        }
        //ImageView iv_img = (ImageView) convertView.findViewById(R.id.iv_img);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_1);
        TextView tv_desc = (TextView) convertView.findViewById(R.id.tv_2);

        Crane crane = data.get(position);

/*        if (crane.getImgData() == null) {
            iv_img.setImageResource(R.drawable.ren);//cinema_1
        } else {
            byte[] imgData = crane.getImgData();
            Bitmap bm = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
            iv_img.setImageBitmap(bm);
        }*/

        tv_name.setText(crane.getEqui_Num());
        tv_desc.setText(crane.getCraneIP());

        return convertView;
    }
}
