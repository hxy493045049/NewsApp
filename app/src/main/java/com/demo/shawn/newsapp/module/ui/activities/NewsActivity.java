package com.demo.shawn.newsapp.module.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.demo.shawn.newsapp.R;
import com.demo.shawn.newsapp.base.BaseActivity;
import com.demo.shawn.newsapp.event.ChannelChangeEvent;
import com.demo.shawn.newsapp.module.contract.impl.presenter.NewPresenterImpl;
import com.demo.shawn.newsapp.utils.RxBus;

import javax.inject.Inject;

import rx.functions.Action1;

/**
 * Created by hxy on 2016/12/7 0007.
 * <p>
 * description :
 */
public class NewsActivity extends BaseActivity {
    @Inject
    NewPresenterImpl mNewsPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void init() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSubscription = RxBus.getInstance().toObservable(ChannelChangeEvent.class)
                .subscribe(new Action1<ChannelChangeEvent>() {
                    @Override
                    public void
                    call(ChannelChangeEvent channelChangeEvent) {
                        mNewsPresenter.onChannelDbChanged();
                    }
                });
    }
}
