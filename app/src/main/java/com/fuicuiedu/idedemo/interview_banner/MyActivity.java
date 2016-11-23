package com.fuicuiedu.idedemo.interview_banner;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MyActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<ImageView> imageViews;
    private List<ImageView> dots;
    private ViewPagerAdapter viewPagerAdapter;
    private TextView textView;

    private String[] titles=new String[]{
        "立冬01",
        "立冬02",
        "立冬03",
        "立冬04",
        "立冬05",
    };

    private int[] imageIds=new int[]{
            R.drawable.lidong1,
            R.drawable.lidong2,
            R.drawable.lidong3,
            R.drawable.lidong4,
            R.drawable.lidong5,
    };


    private int oldPosition=0;
    private int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        imageViews=new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ImageView ima=new ImageView(this);
            ima.setBackgroundResource(imageIds[i]);
            imageViews.add(ima);
        }
        dots=new ArrayList<>();
        dots.add((ImageView) findViewById(R.id.image01));
        dots.add((ImageView) findViewById(R.id.image02));
        dots.add((ImageView) findViewById(R.id.image03));
        dots.add((ImageView) findViewById(R.id.image04));
        dots.add((ImageView) findViewById(R.id.image05));

        textView= (TextView) findViewById(R.id.textView);
        textView.setText(titles[0]);

        viewPager= (ViewPager) findViewById(R.id.viewpager);
        viewPagerAdapter=new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                textView.setText(titles[position]);
                dots.get(position).setBackgroundResource(R.drawable.dot_focused);
                dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);

                oldPosition=position;
                currentPosition=position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public class ViewPagerAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageViews.get(position));
            return imageViews.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViews.get(position));
        }
    }

    private ScheduledExecutorService ses;
    @Override
    protected void onStart() {
        super.onStart();
        ses= Executors.newSingleThreadScheduledExecutor();
        ses.scheduleWithFixedDelay(
                new TimerTask1(),
                2,2,
                TimeUnit.SECONDS
        );
    }

    private class TimerTask1 implements Runnable{

        @Override
        public void run() {
            currentPosition=(currentPosition+1)%imageIds.length;
            handler.sendEmptyMessage(0);
        }
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            viewPager.setCurrentItem(currentPosition);
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        if(ses!=null){
            ses.shutdown();
            ses=null;
        }
    }
}
