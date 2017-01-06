package com.demo.shawn.newsapp.data.db.manager;

import com.demo.shawn.newsapp.R;
import com.demo.shawn.newsapp.base.App;
import com.demo.shawn.newsapp.data.db.NewsChannelDao;
import com.demo.shawn.newsapp.utils.PreferencesUitls;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hxy on 2016/12/13 0013.
 * <p>
 * description :
 */

public class NewsChannelManager {
    public void initNewsChannels() {
        //第一次进入,db的表中没有数据,所以手动添加数据
        if (!PreferencesUitls.hasInitChannels()) {
            NewsChannelDao dao = App.getDaoSession().getNewsChannelDao();
            List<String> channelName = Arrays.asList(App.getAppContext().getResources()
                    .getStringArray(R.array.news_channel_name));



        }
    }
}
