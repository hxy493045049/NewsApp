package com.demo.shawn.newsapp.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.style.UpdateAppearance;

/**
 * Created by hxy on 2016/12/6 0006.
 * <p>
 * description :
 */

public class GreenDbHelper extends DaoMaster.OpenHelper {

    public GreenDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        // TODO: 2016/12/6 0006  
    }
}
