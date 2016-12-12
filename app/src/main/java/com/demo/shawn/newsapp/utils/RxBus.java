package com.demo.shawn.newsapp.utils;


import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by hxy on 2016/12/12 0012.
 * <p>
 * description :
 */

public class RxBus {
    private static volatile RxBus sRxBus;
    private final Subject<Object, Object> mBus;

    private RxBus() {
        mBus = new SerializedSubject<>(PublishSubject.create());
    }

    public static RxBus getInstance() {
        return Holder.instance;
    }

    public void post(Object o) {
        mBus.onNext(o);
    }

    public <T> Observable<T> toObservable(Class<T> eventType) {
        return mBus.ofType(eventType);
    }

    static class Holder {
        private final static RxBus instance = new RxBus();
    }
}
