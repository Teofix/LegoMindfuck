package com.adev.android.legomindfuck.Activity;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adev.android.legomindfuck.R;

public class PageViewerEndGame extends PagerAdapter {

    private Context context;
    private String[] title;
    private int[] manoDX;
    private int[] manoSX;
    private int[] braccioDX;
    private int[] braccioSX;
    private int[] baseDX;
    private int[] baseSX;
    private LayoutInflater inflater;

    private TextView t;
    private TextView tmanoDX;
    private TextView tmanoSX;
    private TextView tbraccioDX;
    private TextView tbraccioSX;
    private TextView tbaseDX;
    private TextView tbaseSX;

    public PageViewerEndGame (Context context, String[] title, int[] manoDX, int[] manoSX, int[] braccioDX, int[] braccioSX, int[] baseDX, int[] baseSX){
        this.context = context;
        this.title = title;
        this.manoDX = manoDX;
        this.manoSX = manoSX;
        this.braccioDX = braccioDX;
        this.braccioSX = braccioSX;
        this.baseDX = baseDX;
        this.baseSX = baseSX;
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View itemView = inflater.inflate(R.layout.page_endgame, container, false);

        t = (TextView) itemView.findViewById(R.id.textView7);
        t.setText(title[position]);

        tmanoDX = (TextView) itemView.findViewById(R.id.clickmanodx);
        tmanoDX.setText(manoDX[position]);
        tmanoSX = (TextView) itemView.findViewById(R.id.clickmanosx);
        tmanoSX.setText(manoSX[position]);

        tbraccioDX = (TextView) itemView.findViewById(R.id.clickbracciodx);
        tbaseDX.setText(braccioDX[position]);
        tbraccioSX = (TextView) itemView.findViewById(R.id.clickbracciosx);
        tbaseSX.setText(braccioSX[position]);

        tbaseDX = (TextView) itemView.findViewById(R.id.clickbasedx);
        tbaseDX.setText(baseDX[position]);
        tbaseSX = (TextView) itemView.findViewById(R.id.clickbasesx);
        tbaseSX.setText(baseSX[position]);


        ((ViewPager) container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ((ViewPager) container).removeView((ConstraintLayout) object);
    }
}
