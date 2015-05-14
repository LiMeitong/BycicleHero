package com.course.byciclehero;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MySellListViewAdapter extends BaseAdapter {

	List<AVObject>list;

	LayoutInflater inflater;

	FragmentActivity activity;

	public MySellListViewAdapter(FragmentActivity fragmentActivity, List<AVObject> list) {
		// TODO Auto-generated constructor stub
		this.list = list;
		this.activity = fragmentActivity;
		inflater = (LayoutInflater) fragmentActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public AVObject getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View view, ViewGroup arg2) {


        // TODO Auto-generated method stub
        if (view == null) {
            AVObject objectItem = getItem(arg0);
            String descriptionString = objectItem.getString("DescriptionString");
            String rewardString = objectItem.getString("Price");


            /**
             * set view
             */
            view = inflater.inflate(R.layout.itemsell, null);
            TextView reward = (TextView) view.findViewById(R.id.priceText);
            reward.setText(rewardString);
            TextView description = (TextView) view.findViewById(R.id.sellDescriptionText);
            description.setText(descriptionString);

            ImageView imageView = (ImageView) view.findViewById(R.id.sellitemImage);
            AVFile imageFile = objectItem.getAVFile("ImageFile");
            if( imageFile != null ){
                String imageUrl = imageFile.getUrl();
                URL url = null;

                try {
                    url = new URL(imageUrl);
                    try {
                        Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                        imageView.setImageBitmap(image);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }


        }


		return view;
	}
	
	class ViewHolder {
		TextView tv;
		ImageView im;
	}

}
