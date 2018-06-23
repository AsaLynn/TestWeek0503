package com.test.week0503;

import android.os.Bundle;

import com.example.demonstrate.DialogPage;
import com.example.demonstrate.FirstActivity;
import com.test.week0503.listener.PageItemLis1;

public class MainActivity extends FirstActivity {

    @Override
    protected void click0() {
        DialogPage
                .getInstance()
                .setOnDialogItemListener(new PageItemLis1(this));
    }
}
