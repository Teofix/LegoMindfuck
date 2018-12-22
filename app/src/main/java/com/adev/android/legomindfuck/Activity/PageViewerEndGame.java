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
        this.context = context;
        this.title = title;
        this.manoDX = manoDX;
        this.manoSX = manoSX;
        this.braccioDX = braccioDX;
        this.braccioSX = braccioSX;
        this.baseDX = baseDX;
        this.baseSX = baseSX;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        container.removeAllViews();

        View itemView = inflater.inflate(R.layout.page_endgame, container, false);

        t = (TextView) itemView.findViewById(R.id.textView7);
        t.setText(title[position]);

        tmanoDX = (TextView) itemView.findViewById(R.id.clickmanodx);
        tmanoDX.setText(manoDX[position]);
        tmanoSX = (TextView) itemView.findViewById(R.id.clickmanosx);
        tmanoSX.setText(manoSX[position]);

        tbraccioDX = (TextView) itemView.findViewById(R.id.clickbracciodx);
        tbaseDX.setText(baseDX[position]);
        tbraccioSX = (TextView) itemView.findViewById(R.id.clickbracciosx);
        tbaseSX.setText(braccioSX[position]);

        tbaseDX = (TextView) itemView.findViewById(R.id.clickbasedx);
        tbaseDX.setText(baseDX[position]);
        tbaseSX = (TextView) itemView.findViewById(R.id.clickbasesx);
        tbaseSX.setText(baseSX[position]);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ((ViewPager) container).removeView((ConstraintLayout) object);
    }
}
