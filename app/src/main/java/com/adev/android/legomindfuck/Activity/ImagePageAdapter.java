package com.adev.android.legomindfuck.Activity;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adev.android.legomindfuck.R;

public class ImagePageAdapter extends PagerAdapter {

    private Context context;
    int[] images;
    String[] titles;
    String[] descriptions;
    LayoutInflater inflater;

    public ImagePageAdapter(Context c, int[] imgs, String[] t, String[] d)  {
        this.context = c;
        this.images = imgs;
        this.titles = t;
        this.descriptions = d;
    }

    @Override
    public int getCount()   {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v==obj;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position)    {

        final ImageView image;
        final TextView title;
        final TextView description;


        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View itemView = inflater.inflate(R.layout.pager_item, container, false);

        image = (ImageView) itemView.findViewById(R.id.pager_img);
        image.bringToFront();
        title = (TextView) itemView.findViewById(R.id.pager_title);
        description = (TextView) itemView.findViewById(R.id.pager_desc);

        image.setImageResource(images[position]);
        title.setText(titles[position]);
        description.setText(descriptions[position]);

        ((ViewPager) container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ((ViewPager) container).removeView((ConstraintLayout) object);
    }
}
