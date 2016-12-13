package com.demo.shawn.newsapp.base;

import android.support.annotation.NonNull;

/**
 * Created by hxy on 2016/12/13 0013.
 * <p>
 * description :
 */

public interface BaseContract {
    interface View {
        void showProgress();

        void hideProgress();

        void showMsg(String message);
    }

    interface Presenter {
        void onCreate();

        void attachView(@NonNull View view);

        void onDestroy();
    }
}
