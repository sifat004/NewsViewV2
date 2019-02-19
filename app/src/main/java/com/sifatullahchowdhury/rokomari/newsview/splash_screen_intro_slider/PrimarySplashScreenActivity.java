package com.sifatullahchowdhury.rokomari.newsview.splash_screen_intro_slider;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sifatullahchowdhury.rokomari.newsview.home_activity.HomeActivity;
import com.sifatullahchowdhury.rokomari.newsview.preference.PreferenceManager;
import com.sifatullahchowdhury.rokomari.newsview.R;

public class PrimarySplashScreenActivity extends AppCompatActivity {

    LinearLayout layout_bars;
    TextView[] bottomBars;
    int[] screens;
    Button skip, next;
    ViewPager vp;
    MyViewPagerAdapter myvpAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen);

        if (!PreferenceManager.isFirstLaunch()) {
            launchSecondarySplash();
            finish();
        }

        initializeUiComponents();

    }

    public void initializeUiComponents() {

        setViewPager();
        setBottomBars();
        setButtons();


    }

    public void setViewPager() {

        vp = findViewById(R.id.view_pager);
        myvpAdapter = new MyViewPagerAdapter();
        vp.setAdapter(myvpAdapter);
        vp.addOnPageChangeListener(viewPagerPageChangeListener);

    }

    public void setBottomBars() {

        layout_bars = findViewById(R.id.layoutBars);


        screens = new int[]{
                R.layout.intro_screen_1,
                R.layout.intro_screen_2,
                R.layout.intro_screen_3
        };


        coloredBars(0);
    }


    public void setButtons() {

        skip = findViewById(R.id.skip);
        next = findViewById(R.id.next);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skip();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();

            }
        });
    }


    public void next() {
        int i = getItem(+1);
        if (i < screens.length) {
            vp.setCurrentItem(i);
        } else {
            launchMain();
        }
    }

    public void skip() {
        launchMain();
    }

    private void coloredBars(int thisScreen) {

        bottomBars = new TextView[screens.length];

        layout_bars.removeAllViews();
        for (int i = 0; i < bottomBars.length; i++) {
            bottomBars[i] = new TextView(this);
            bottomBars[i].setTextSize(100);
            bottomBars[i].setText(Html.fromHtml("¯"));
            layout_bars.addView(bottomBars[i]);
            bottomBars[i].setTextColor(getResources().getColor(R.color.inactive));
        }
        if (bottomBars.length > 0)
            bottomBars[thisScreen].setTextColor(getResources().getColor(R.color.active));
    }

    private int getItem(int i) {
        return vp.getCurrentItem() + i;
    }

    private void launchMain() {
        PreferenceManager.setFirstTimeLaunch(false);
        startActivity(new Intent(PrimarySplashScreenActivity.this, HomeActivity.class));
        finish();
    }

    private void launchSecondarySplash() {
        PreferenceManager.setFirstTimeLaunch(false);
        startActivity(new Intent(PrimarySplashScreenActivity.this, SecondarySplashScreenActivity.class));
        finish();
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            coloredBars(position);
            if (position == screens.length - 1) {
                next.setText(R.string.start);
                skip.setVisibility(View.GONE);
            } else {
                next.setText(getString(R.string.next));
                skip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater inflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(screens[position], container, false);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View v = (View) object;
            container.removeView(v);
        }

        @Override
        public boolean isViewFromObject(View v, Object object) {
            return v == object;
        }
    }
}

