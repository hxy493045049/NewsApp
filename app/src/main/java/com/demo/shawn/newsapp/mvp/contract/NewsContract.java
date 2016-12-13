package com.demo.shawn.newsapp.mvp.contract;


import com.demo.shawn.newsapp.base.BaseContract;

/**
 * Created by hxy on 2016/12/12 0012.
 * <p>
 * description :
 */
public interface NewsContract {
    interface View extends BaseContract.View {

    }

    interface Presenter extends BaseContract.Presenter {
        void onChannelDbChanged();
    }
}
