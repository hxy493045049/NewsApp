package com.demo.shawn.newsapp.module.contract.impl.presenter;

import android.support.annotation.NonNull;

import com.demo.shawn.newsapp.base.BaseContract;
import com.demo.shawn.newsapp.base.impl.BasePresenterImpl;
import com.demo.shawn.newsapp.data.bean.NewsChannel;
import com.demo.shawn.newsapp.module.contract.NewsContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by hxy on 2016/12/12 0012.
 * <p>
 * description :
 */

public class NewPresenterImpl extends BasePresenterImpl<NewsContract.View, List<NewsChannel>>
        implements NewsContract.Presenter {
    @Inject
    public NewPresenterImpl() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        loadNewsChannels();
    }

    @Override
    public void attachView(@NonNull BaseContract.View view) {
    }

    @Override
    public void onDestroy() {
    }

    private void loadNewsChannels() {
    }

    @Override
    public void success(List<NewsChannel> data) {
        super.success(data);
        mView.initViewPager(data);
    }

    @Override
    public void onChannelDbChanged() {
        loadNewsChannels();
    }
}
