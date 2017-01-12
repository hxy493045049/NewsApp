package com.demo.shawn.newsapp.module.contract.impl.view;

import com.demo.shawn.newsapp.data.bean.NewsChannel;
import com.demo.shawn.newsapp.module.contract.NewsContract;

import java.util.List;

/**
 * Created by hxy on 2017/1/10 0010.
 * <p>
 * description :
 */
public interface NewsView extends NewsContract.View {
    void initViewPager(List<NewsChannel> newsChannels);
}
