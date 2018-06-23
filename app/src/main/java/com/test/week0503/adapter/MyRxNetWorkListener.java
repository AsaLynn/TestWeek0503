package com.test.week0503.adapter;

import com.test.week0503.model.NetWorkBean;

import java.util.List;

import io.reactivex.network.manager.RxNetWorkListener;

/**
 * Created by think on 2018/3/21.
 */

public class MyRxNetWorkListener implements RxNetWorkListener<List<NetWorkBean>>{
    @Override
    public void onNetWorkStart() {

    }

    @Override
    public void onNetWorkError(Throwable e) {

    }

    @Override
    public void onNetWorkComplete() {

    }

    @Override
    public void onNetWorkSuccess(List<NetWorkBean> data) {

    }
}
