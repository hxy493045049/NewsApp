package com.demo.shawn.newsapp.base;

import android.support.annotation.NonNull;

/**
 * Created by hxy on 2016/12/7 0007.
 * <p>
 * description :
 */

public interface BasePresenter {
    void onCreate();

    void attachView(@NonNull BaseView view);

    void onDestroy();
}
