package com.adev.android.legomindfuck.Activity;

import android.content.Context;
import android.support.annotation.NonNull;
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
    private Integer[] manoDX;
    private Integer[] manoSX;
    private Integer[] braccioDX;
    private Integer[] braccioSX;
    private Integer[] baseDX;
    private Integer[] baseSX;
    private LayoutInflater inflater;

    private TextView t;
    private TextView tmanoDX;
    private TextView tmanoSX;
    private TextView tbraccioDX;
    private TextView tbraccioSX;
    private TextView tbaseDX;
    private TextView tbaseSX;

    public PageViewerEndGame (Context context, String[] title, Integer[] manoDX, Integer[] manoSX, Integer[] braccioDX, Integer[] braccioSX, Integer[] baseDX, Integer[] baseSX){
        this.context=context;
        this.title=title;
        this.manoDX=manoDX;
        this.manoSX=manoSX;
        this.braccioDX=braccioDX;
        this.braccioSX=braccioSX;
        this.baseDX=baseDX;
        this.baseSX=baseSX;
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view==o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View itemView = inflater.inflate(R.layout.page_endgame, container, false);

        t= (TextView) itemView.findViewById(R.id.textView7);
        tmanoDX= (TextView) itemView.findViewById(R.id.clickmanodx);
        tmanoSX= (TextView) itemView.findViewById(R.id.clickmanosx);

        tbraccioDX= (TextView) itemView.findViewById(R.id.clickbracciodx);
        tbraccioSX= (TextView) itemView.findViewById(R.id.clickbracciosx);

        tbaseDX= (TextView) itemView.findViewById(R.id.clickbasedx);
        tbaseSX= (TextView) itemView.findViewById(R.id.clickbasesx);


        ((ViewPager) container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ((ViewPager) container).removeView((ConstraintLayout) object);
    }
}
