package com.demo.shawn.newsapp.data.db.manager;

import com.demo.shawn.newsapp.R;
import com.demo.shawn.newsapp.base.App;
import com.demo.shawn.newsapp.common.ApiConstants;
import com.demo.shawn.newsapp.data.bean.NewsChannel;
import com.demo.shawn.newsapp.data.db.NewsChannelDao;
import com.demo.shawn.newsapp.utils.PreferencesUitls;

import org.greenrobot.greendao.query.Query;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hxy on 2016/12/13 0013.
 * <p>
 * description :
 */

public class NewsChannelManager {
    public static void initNewsChannels() {
        //第一次进入,db的表中没有数据,所以手动添加数据
        if (!PreferencesUitls.hasInitChannels()) {
            NewsChannelDao dao = App.getDaoSession().getNewsChannelDao();
            List<String> channelName = Arrays.asList(App.getAppContext().getResources()
                    .getStringArray(R.array.news_channel_name));

            List<String> channelId = Arrays.asList(App.getAppContext().getResources()
                    .getStringArray(R.array.news_channel_id));

            for (int i = 0; i < channelName.size(); i++) {
                NewsChannel channel = new NewsChannel(channelName.get(i), channelId.get(i)
                        , ApiConstants.getType(channelId.get(i)), i <= 5, i, i == 0);
                dao.insert(channel);
            }
            PreferencesUitls.setInitChannelsFlag();
        }
    }

    public static List<NewsChannel> loadNewsChannelsMine() {
        Query<NewsChannel> newsChannelQuery = App.getDaoSession().getNewsChannelDao().queryBuilder().where
                (NewsChannelDao.Properties.NewsChannelSelect.eq(true)).orderAsc(NewsChannelDao.Properties
                .NewsChannelIndex).build();
        return newsChannelQuery.list();
    }
}
