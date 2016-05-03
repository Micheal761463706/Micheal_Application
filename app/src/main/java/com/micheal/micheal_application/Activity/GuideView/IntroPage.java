package com.micheal.micheal_application.Activity.GuideView;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.micheal.micheal_application.Activity.MainActivity;
import com.micheal.micheal_application.Adapter.BaseActivity;
import com.micheal.micheal_application.R;

import java.util.ArrayList;
import java.util.List;

public class IntroPage extends BaseActivity implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private List<ImageView> mListData;
    private ViewPagerAdapter mViewPagerAdapter;
    private boolean canJump; // 用于判断是否可以跳转到主页面

    // 获取布局id
    @Override
    protected int contentView() {
        return R.layout.activity_intro_page;
    }

    /**
     *  初始化方法
     */
    @Override
    protected void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager_IntroPage);
        viewPager.addOnPageChangeListener(this);
    }

    /**
     *  加载数据
     */
    @Override
    protected void initData() {
        mListData = new ArrayList<>();
        for (int i = 1; i <=5 ; i++){
            int resid = getResources().getIdentifier("welcome_"+i, "drawable", getPackageName());
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(resid);
            mListData.add(imageView);
        }
        mViewPagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(mViewPagerAdapter);
    }

    /**
     *  ViewPage的监听方法
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if( position == (mListData.size()-1) && positionOffset == 0 && positionOffsetPixels ==0 ){
            if(canJump){
                // 表示可以到最后一页，可以跳转
                startActivity(new Intent(getApplicationContext(), MainActivity.class), R.anim.activity_in_right, R.anim.activity_out_down_08_50);
                // 事件执行一次后进行重置,避免事件多次触发
                canJump = false;
            }
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        // 当滑动屏幕时设置为true否则为false，与onPageScrolled互相配合
        if(state == ViewPager.SCROLL_STATE_DRAGGING){
            canJump = true;
        }else{
            canJump = false;
        }
    }

    /**
     *  ViewPager适配器
     */
    class ViewPagerAdapter extends PagerAdapter{

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mListData.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mListData.get(position));
            return mListData.get(position);
        }

        @Override
        public int getCount() {
            return mListData.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
