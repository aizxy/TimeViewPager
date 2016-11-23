package com.fuicuiedu.idedemo.interview_banner;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TextView textView;
    private List<View> dds;
    private List<ImageView> iis;

    private int oldPosition=0;
    private int currentPosition;

    private String[] ss=new String[]{
        "红","蓝","白","飞机","呵呵"
    };
    private int[] ii=new int[]{
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e
    };

    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        viewPager= (ViewPager) findViewById(R.id.second_viewpager);
        textView= (TextView) findViewById(R.id.second_textview);
        textView.setText("红");
        dds=new ArrayList<>();
        dds.add(findViewById(R.id.view00));
        dds.add(findViewById(R.id.view01));
        dds.add(findViewById(R.id.view02));
        dds.add(findViewById(R.id.view03));
        dds.add(findViewById(R.id.view04));


        iis=new ArrayList<>();
        for(int i=0;i<5;i++){
            ImageView iv=new ImageView(this);
            iv.setBackgroundResource(ii[i]);
            iis.add(iv);
        }
        adapter=new MyAdapter();
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                textView.setText(ss[position]);
                dds.get(position).setBackgroundResource(R.drawable.second_f);
                dds.get(oldPosition).setBackgroundResource(R.drawable.second_n);
                oldPosition=position;
                currentPosition=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return ii.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(iis.get(position));
            return iis.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(iis.get(position));
        }
    }
}
