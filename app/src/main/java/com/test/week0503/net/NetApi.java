package com.test.week0503.net;

import com.test.week0503.model.FoodBean;
import com.test.week0503.model.NetWorkBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * by y on 2017/6/16.
 */

public class NetApi {

//https://zhuanlan.zhihu.com/api/columns/daily/posts?limit=20&offset=0
    public static final String ZL_BASE_API = "https://zhuanlan.zhihu.com/api/";

 //    http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=10&page=1
    public static final String DISH_BASE_API = "http://www.qubaobei.com/";
    public interface ZLService {
        @GET("columns/" + "{suffix}/posts")
        Observable<List<NetWorkBean>> getList(@Path("suffix") String suffix,
                                              @Query("limit") int limit,
                                              @Query("offset") int offset);
    }

    public interface DishServer {
        @GET("ios/cf/dish_list.php")
        Observable<FoodBean> getDishList(
                @Query("stage_id") int stage_id,
                @Query("limit") int limit,
                @Query("page") int page
        );
    }


//    setUri("http://www.qubaobei.com/ios/cf/dish_list.php");
//    addParameter("stage_id", "1");
//    addParameter("limit", "10");
//    addParameter("page", page);
}
