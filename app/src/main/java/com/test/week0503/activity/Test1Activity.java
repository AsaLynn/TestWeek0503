package com.test.week0503.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.demonstrate.DemonstrateUtil;
import com.example.demonstrate.utils.LogUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.test.week0503.R;
import com.test.week0503.adapter.MyFoodRxNetLis;
import com.test.week0503.divider.MyDivider;
import com.test.week0503.model.FoodBean;
import com.test.week0503.net.NetApi;
import com.test.week0503.utils.UC;
import com.xadapter.OnItemClickListener;
import com.xadapter.OnXBindListener;
import com.xadapter.adapter.XRecyclerViewAdapter;
import com.xadapter.holder.XViewHolder;

import butterknife.BindView;
import io.reactivex.network.manager.RxNetWork;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * 项目拆解,周考3技能1,2018.03.26
 */
public class Test1Activity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.product_rv)
    RecyclerView productRv;

    @Override
    protected void initData() {
        productRv.setHasFixedSize(true);
        mAdapter = new XRecyclerViewAdapter<>();
        productRv.setAdapter(
                mAdapter.setLayoutId(R.layout.food_item)
                        .onXBind(new OnXBindListener<FoodBean.DataBean>() {
                            @Override
                            public void onXBind(XViewHolder holder, int position, FoodBean.DataBean netWorkBean) {
                                Glide.with(holder.getContext())
                                        .load(netWorkBean.getPic())
                                        .apply(
                                                RequestOptions
                                                        .bitmapTransform(new RoundedCornersTransformation(
                                                                10,
                                                                0,
                                                                RoundedCornersTransformation.CornerType.TOP)
                                                        )
                                                        .placeholder(R.mipmap.ic_launcher_round)
                                                        .error(R.mipmap.ic_launcher_round)
                                                        /*.centerInside()*/
                                        )
                                        .into(holder.getImageView(R.id.list_image));


                                holder.setTextView(R.id.list_tv, netWorkBean.getTitle());
                                holder.setTextView(
                                        R.id.price_tv,
                                        UC.TAG_RMB.concat(String.valueOf(netWorkBean.getNum()))
                                );
                            }
                        })
                        .setOnItemClickListener(new OnItemClickListener<FoodBean.DataBean>() {
                            @Override
                            public void onItemClick(View view, int position, FoodBean.DataBean info) {
                                Toast.makeText(view.getContext(), info.getTitle(), Toast.LENGTH_SHORT).show();
                            }
                        })
        );


        GridLayoutManager gridLayoutManager = new GridLayoutManager(productRv.getContext(), 2);

        productRv.setLayoutManager(gridLayoutManager);
        //条目分割线.
        productRv.addItemDecoration(new MyDivider(this));
//        productRv.addItemDecoration(new GridItemDecoration(this));

        RxNetWork
                .getInstance()
                .setBaseUrl(NetApi.DISH_BASE_API)
                .getApi(
                        getClass().getSimpleName(),
                        RxNetWork.observable(
                                NetApi.DishServer.class)
                                .getDishList(1, 10, 1),
                        new MyFoodRxNetLis() {
                            @Override
                            public void onNetWorkSuccess(FoodBean data) {
                                LogUtils.i(data.toString());
                                mAdapter.addAllData(data.getData());

                            }
                        }
                );
    }

    private XRecyclerViewAdapter<FoodBean.DataBean> mAdapter;


    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarView(R.id.top_view)
                .navigationBarColor(R.color.colorPrimary)
                .fullScreen(true)
                .addTag("PicAndColor")  //给上面参数打标记，以后可以通过标记恢复
                .init();

        if (ImmersionBar.hasNavigationBar(this)) {
            mImmersionBar.navigationBarColor(R.color.colorPrimary).init();
        }
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //返回按钮显示以及点击
        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DemonstrateUtil.showToastResult(Test1Activity.this, "返回按钮!");
            }
        });
        //设置toolBar上的MenuItem点击事件
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_settings:
                        DemonstrateUtil.showToastResult(Test1Activity.this, "完成按钮!");
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_test1;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;



    }

}
