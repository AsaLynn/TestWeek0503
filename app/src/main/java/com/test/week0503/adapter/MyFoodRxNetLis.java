package com.test.week0503.adapter;

import com.test.week0503.model.FoodBean;

import io.reactivex.network.manager.RxNetWorkListener;

/**
 * Created by think on 2018/3/21.
 */

public class MyFoodRxNetLis implements RxNetWorkListener<FoodBean>{
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
    public void onNetWorkSuccess(FoodBean data) {

    }
}
