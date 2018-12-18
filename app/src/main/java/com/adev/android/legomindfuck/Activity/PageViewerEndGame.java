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

    Context context;
    String[] title;
    Integer[] manoDX;
    Integer[] manoSX;
    Integer[] braccioDX;
    Integer[] braccioSX;
    Integer[] baseDX;
    Integer[] baseSX;
    LayoutInflater inflater;

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
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.page_endgame, container, false);

        t= itemView.findViewById(R.id.textView7);
        tmanoDX= itemView.findViewById(R.id.clickmanodx);
        tmanoSX= itemView.findViewById(R.id.clickmanosx);

        tbraccioDX= itemView.findViewById(R.id.clickbracciodx);
        tbraccioSX= itemView.findViewById(R.id.clickbracciosx);

        tbaseDX= itemView.findViewById(R.id.clickbasedx);
        tbaseSX= itemView.findViewById(R.id.clickbasesx);


        ((ViewPager) container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ((ViewPager) container).removeView((ConstraintLayout) object);
    }
}
