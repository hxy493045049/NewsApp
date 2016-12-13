package com.demo.shawn.newsapp.mvp.contract.impl;

import android.support.annotation.NonNull;

import com.demo.shawn.newsapp.base.BaseContract;
import com.demo.shawn.newsapp.base.impl.BasePresenterImpl;
import com.demo.shawn.newsapp.data.bean.NewsChannel;
import com.demo.shawn.newsapp.mvp.contract.NewsContract;

import java.util.List;

/**
 * Created by hxy on 2016/12/12 0012.
 * <p>
 * description :
 */

public class NewPresenterImpl extends BasePresenterImpl<NewsContract.View, List<NewsChannel>>
        implements NewsContract.Presenter {
    @Override
    public void onChannelDbChanged() {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(@NonNull BaseContract.View view) {

    }

    @Override
    public void onDestroy() {

    }
}
