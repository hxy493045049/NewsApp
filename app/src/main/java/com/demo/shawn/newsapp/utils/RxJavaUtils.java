package com.demo.shawn.newsapp.utils;

import rx.Subscription;

/**
 * Created by hxy on 2016/12/9 0009.
 * <p>
 * description :
 */

public class RxJavaUtils {

    public static void cancelSubscription(Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
