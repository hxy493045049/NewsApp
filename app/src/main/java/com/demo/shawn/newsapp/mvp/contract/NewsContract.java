package com.demo.shawn.newsapp.mvp.contract;

import com.demo.shawn.newsapp.base.BasePresenter;
import com.demo.shawn.newsapp.base.BaseView;

/**
 * Created by hxy on 2016/12/12 0012.
 * <p>
 * description :
 */
public interface NewsContract {
    interface View extends BaseView{

    }

    interface Presenter extends BasePresenter{
        void onChannelDbChanged();
    }
}
