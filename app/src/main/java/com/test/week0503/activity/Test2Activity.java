package com.test.week0503.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.demonstrate.DemonstrateUtil;
import com.gyf.barlibrary.ImmersionBar;
import com.test.week0503.R;
import com.test.week0503.adapter.MyRxNetWorkListener;
import com.test.week0503.model.NetWorkBean;
import com.test.week0503.net.NetApi;
import com.xadapter.OnItemClickListener;
import com.xadapter.OnXBindListener;
import com.xadapter.adapter.XRecyclerViewAdapter;
import com.xadapter.holder.XViewHolder;

import java.util.List;

import butterknife.BindView;
import io.reactivex.network.manager.RxNetWork;

/**
 * 项目拆解,周考3技能1,2018.03.26
 */
public class Test2Activity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.product_rv)
    RecyclerView productRv;

    @Override
    protected void initData() {
        RxNetWork
                .getInstance()
                .setBaseUrl(NetApi.ZL_BASE_API)
                .getApi(
                        getClass().getSimpleName(),
                        RxNetWork.observable(NetApi.ZLService.class)
                                .getList("daily", 20, 0),
                        new MyRxNetWorkListener() {
                            @Override
                            public void onNetWorkSuccess(List<NetWorkBean> data) {
                                mAdapter.addAllData(data);
                            }
                        }
                );
    }

    private XRecyclerViewAdapter<NetWorkBean> mAdapter;

    @Override
    protected void initView() {
        productRv.setHasFixedSize(true);
        productRv.setLayoutManager(new LinearLayoutManager(productRv.getContext()));
        mAdapter = new XRecyclerViewAdapter<>();

        productRv.setAdapter(
                mAdapter.setLayoutId(R.layout.network_item)
                        .onXBind(new OnXBindListener<NetWorkBean>() {
                            @Override
                            public void onXBind(XViewHolder holder, int position, NetWorkBean netWorkBean) {
                                Glide.with(holder.getContext())
                                        .load(netWorkBean.getTitleImage())
                                        .apply(
                                                new RequestOptions()
                                                        .placeholder(R.mipmap.ic_launcher_round)
                                                        .error(R.mipmap.ic_launcher_round)
                                                        .centerCrop()
                                        )
                                        .into(holder.getImageView(R.id.list_image));
                                holder.setTextView(R.id.list_tv, netWorkBean.getTitle());
                            }
                        })
                        .setOnItemClickListener(new OnItemClickListener<NetWorkBean>() {
                            @Override
                            public void onItemClick(View view, int position, NetWorkBean info) {
                                Toast.makeText(view.getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
                            }
                        })
        );
    }

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
        toolbar.setNavigationIcon(R.mipmap.ic_back);
        setSupportActionBar(toolbar);
        //返回按钮点击
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DemonstrateUtil.showToastResult(Test2Activity.this, "返回按钮!");
            }
        });
        //设置toolBar上的MenuItem点击事件
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_settings:
                        DemonstrateUtil.showToastResult(Test2Activity.this, "完成按钮!");
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
