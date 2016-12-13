package com.demo.shawn.newsapp.data.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by hxy on 2016/12/13 0013.
 * <p>
 * description :
 */
@Entity(nameInDb = "NewsChannelTable")
public class NewsChannel {
    @Id
    private String newsChannelName;
    @Property
    @NotNull
    private String newsChannelId;
    @Property
    @NotNull
    private String newsChannelType;
    @Property
    @NotNull
    private boolean newsChannelSelect;
    @Property
    @NotNull
    private int newsChannelIndex;
    @Property
    private Boolean newsChannelFixed;

    @Generated(hash = 1788271019)
    public NewsChannel(String newsChannelName, @NotNull String newsChannelId,
            @NotNull String newsChannelType, boolean newsChannelSelect,
            int newsChannelIndex, Boolean newsChannelFixed) {
        this.newsChannelName = newsChannelName;
        this.newsChannelId = newsChannelId;
        this.newsChannelType = newsChannelType;
        this.newsChannelSelect = newsChannelSelect;
        this.newsChannelIndex = newsChannelIndex;
        this.newsChannelFixed = newsChannelFixed;
    }

    @Generated(hash = 566079451)
    public NewsChannel() {
    }

    public String getNewsChannelName() {
        return this.newsChannelName;
    }

    public void setNewsChannelName(String newsChannelName) {
        this.newsChannelName = newsChannelName;
    }

    public String getNewsChannelId() {
        return this.newsChannelId;
    }

    public void setNewsChannelId(String newsChannelId) {
        this.newsChannelId = newsChannelId;
    }

    public String getNewsChannelType() {
        return this.newsChannelType;
    }

    public void setNewsChannelType(String newsChannelType) {
        this.newsChannelType = newsChannelType;
    }

    public boolean getNewsChannelSelect() {
        return this.newsChannelSelect;
    }

    public void setNewsChannelSelect(boolean newsChannelSelect) {
        this.newsChannelSelect = newsChannelSelect;
    }

    public int getNewsChannelIndex() {
        return this.newsChannelIndex;
    }

    public void setNewsChannelIndex(int newsChannelIndex) {
        this.newsChannelIndex = newsChannelIndex;
    }

    public Boolean getNewsChannelFixed() {
        return this.newsChannelFixed;
    }

    public void setNewsChannelFixed(Boolean newsChannelFixed) {
        this.newsChannelFixed = newsChannelFixed;
    }
}
