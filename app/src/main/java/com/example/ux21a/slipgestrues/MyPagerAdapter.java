package com.example.ux21a.slipgestrues;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * メニューのためのアダプタ
 */
public class MyPagerAdapter extends PagerAdapter {
    List<View> viewList;

    public MyPagerAdapter(List<View> list) {
        this.viewList = list;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // コンテナに指定のページを追加
        container.addView(viewList.get(position));

        return viewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0.equals(arg1);
    }
}
