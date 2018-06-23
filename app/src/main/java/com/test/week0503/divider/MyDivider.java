package com.test.week0503.divider;

import android.content.Context;

import com.yanyusong.y_divideritemdecoration.Y_Divider;
import com.yanyusong.y_divideritemdecoration.Y_DividerBuilder;
import com.yanyusong.y_divideritemdecoration.Y_DividerItemDecoration;

/**
 * Created by think on 2018/3/21.
 */

public class MyDivider extends Y_DividerItemDecoration {

    private int widthDp;

    public MyDivider(Context context) {
        super(context);
        widthDp = 10;
    }

    @Override
    public Y_Divider getDivider(int itemPosition) {
        Y_Divider divider = null;
        switch (itemPosition % 2) {
            case 0:
                //每一行的第一个显示left,top,right,bottom
                if (itemPosition > 1) {
                    divider = new Y_DividerBuilder()
                            .setLeftSideLine(true, 0xffffffff, widthDp, 0, 0)
                            .setRightSideLine(true, 0xffffffff, widthDp, 0, 0)
                            .setBottomSideLine(true, 0xffffffff, widthDp, 0, 0)
                            .create();
                } else {
                    divider = new Y_DividerBuilder()
                            .setLeftSideLine(true, 0xffffffff, widthDp, 0, 0)
                            .setTopSideLine(true, 0xffffffff, widthDp, 0, 0)
                            .setRightSideLine(true, 0xffffffff, widthDp, 0, 0)
                            .setBottomSideLine(true, 0xffffffff, widthDp, 0, 0)
                            .create();
                }
                break;
            case 1:
                //每一行的第二个显示top,rignt和bottom
                if (itemPosition > 1) {
                    divider = new Y_DividerBuilder()
                            .setRightSideLine(true, 0xffffffff, widthDp, 0, 0)
                            .setBottomSideLine(true, 0xffffffff, widthDp, 0, 0)
                            .create();
                } else {
                    divider = new Y_DividerBuilder()
                            .setTopSideLine(true, 0xffffffff, widthDp, 0, 0)
                            .setRightSideLine(true, 0xffffffff, widthDp, 0, 0)
                            .setBottomSideLine(true, 0xffffffff, widthDp, 0, 0)
                            .create();
                }
                break;
            default:
                break;
        }
        return divider;
    }
}
