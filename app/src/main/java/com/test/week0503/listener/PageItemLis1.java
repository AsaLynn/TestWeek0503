package com.test.week0503.listener;

import android.app.Activity;

import com.example.demonstrate.adapter.testname.p1.w3.BaseT5P1W3ILis;
import com.test.week0503.R;
import com.test.week0503.activity.Test1Activity;
import com.test.week0503.activity.Test2Activity;

/**
 * Created by think on 2018/3/21.
 * 项目拆解,安卓第5月,周考3,技能题,页面1条目监听.
 */

public class PageItemLis1 extends BaseT5P1W3ILis {

    public PageItemLis1(Activity activity) {
        super(activity);
    }

    @Override
    public Class<?> getStartActivity(int which) {
        if (which == 0) {
            return Test1Activity.class;
        } else if (which == 1) {
            return Test2Activity.class;
        }
        return null;
    }

    @Override
    public int getDialogListId() {
        return R.array.test6_week3_dialog1_items;
    }
}
