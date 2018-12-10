package com.croshe.farming.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.croshe.crosheandroidbase.CroSheAppContext;
import com.croshe.crosheandroidbase.activity.CropImageActivity;
import com.croshe.crosheandroidbase.entity.ProvinceBean;
import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.crosheandroidbase.listener.OnOptionsSelectResultListener;
import com.croshe.crosheandroidbase.view.CrosheBottomTabMenuView;
import com.croshe.crosheandroidbase.view.CrosheManyTabView;
import com.croshe.crosheandroidbase.view.CroshePickerView;
import com.croshe.crosheandroidbase.view.CrosheTabView;
import com.croshe.crosheandroidbase.view.CrosheViewPageView;
import com.croshe.farming.MainActivity;
import com.croshe.farming.R;
import com.croshe.farming.adapter.AddressListAdapter;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by niuyongwei on 17/5/23.
 */

public class DemoActivity extends CrosheBaseActivity {

    private List<String> list = new ArrayList<>();
    private Button btn_click;
    private MaterialRefreshLayout materialRefreshLayout;
    private RecyclerView recyclerView;
    private AddressListAdapter addressListAdapter;
    private LinearLayout ll_r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        initView();
        createPickerView();
        createRef();
        createTabView();
    }

    @Override
    public void initView() {

        /**
         * 设置toolbar的一些属性 集成CrosheBaseActivity才可以  布局文件必须有@layout/croshe_currency_toolba
         */
        initToolBar();
        setTitle("农业");
        setBackImg(R.mipmap.img_back);
//      setRightImg(R.mipmap.ic_launcher);
        setRightText("完成");
        setLeftText("订单详情");
        f(R.id.btn_04).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 打开相册并裁剪图片
                 */
//                CroSheAppContext.getInstance().goAlbumAndPhotograph(context, 1, 2000);
                /**
                 * 打开相册不裁剪图片
                 */
                CroSheAppContext.getInstance().goAlbumAndPhotograph(context, 9, 3000);
            }
        });
    }


    /**
     * 一些底部弹出数据选择器
     */
    public void createPickerView() {
        f(R.id.btn_01).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CroshePickerView.getInstance().showTime(context, "选择时间", TimePickerView.Type.ALL, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {

                    }
                });


            }
        });
        f(R.id.btn_02).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CroshePickerView.getInstance().showCityPickerView(context, new OnOptionsSelectResultListener() {

                    @Override
                    public void cityInfo(String province, String city, String area) {

                        Toast.makeText(context, province + city + area, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        f(R.id.btn_03).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<ProvinceBean> options1Items = new ArrayList<>();
                //选项1
                options1Items.add(new ProvinceBean(0, "广东", "描述部分", "其他数据"));
                options1Items.add(new ProvinceBean(1, "湖南", "描述部分", "其他数据"));
                options1Items.add(new ProvinceBean(2, "广西", "描述部分", "其他数据"));
                CroshePickerView.getInstance().showCondition(context, "自定义条件", options1Items, new OnOptionsSelectResultListener() {
                    @Override
                    public void cityInfo(String province, String city, String area) {
                        Toast.makeText(context, province + city + area, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


    /**
     * 创建刷新控件
     */
    public void createRef() {
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        recyclerView = f(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        addressListAdapter = new AddressListAdapter(context, list);
        recyclerView.setAdapter(addressListAdapter);
        materialRefreshLayout = (MaterialRefreshLayout) findViewById(R.id.refresh);
        materialRefreshLayout.setLoadMore(true);
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                materialRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        materialRefreshLayout.finishRefresh();

                    }
                }, 1000);

            }

            @Override
            public void onfinish() {
            }

            @Override
            public void onRefreshLoadMore(final MaterialRefreshLayout materialRefreshLayout) {
                materialRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.add("");
                        list.add("");
                        list.add("");
                        list.add("");
                        list.add("");
                        list.add("");
                        list.add("");
                        list.add("");
                        list.add("");
                        list.add("");
                        materialRefreshLayout.finishRefreshLoadMore();

                    }
                }, 1000);


            }
        });
    }


    /**
     * 创建选项卡级轮播图
     */
    public void createTabView() {

        //轮播图
        ll_r = f(R.id.ll_r);
        List<String> list1 = new ArrayList<>();
        list1.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495541879513&di=62b6b17e2a0a38a7affa4029561733a3&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20170521%2F5bdc2d9d127a40a58ef95d76eb3a6510_th.jpg");
        list1.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495541879512&di=b6dd4efecf57aa20ad76e02d3dabf50c&imgtype=0&src=http%3A%2F%2Fimg1.gamedog.cn%2F2013%2F09%2F28%2F43-13092Q646420-51.jpg");
        list1.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495541879512&di=333cda512e4b1603f11fc8b33adedc51&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn%2F20170104%2F6982-fxzkfuk2022813.jpg");
        list1.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495541879511&di=73e40662629e22dd486491c3948902e9&imgtype=0&src=http%3A%2F%2Fpic33.nipic.com%2F20130910%2F1754547_121230122000_2.jpg");
        list1.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495541879509&di=e85fe1f2d0dfb84c042d6785e707a6e4&imgtype=0&src=http%3A%2F%2Fwww.pp3.cn%2Fuploads%2F201602%2F20160221007.jpg");
        CrosheViewPageView crosheViewPageView = new CrosheViewPageView(context, list1, 100);
        ll_r.addView(crosheViewPageView);


        //选项卡固定个数
        String[] titles = new String[]{
                "首页", "分类", "哈偶", "耶耶", "哦哦"
        };
        int[] unSelect = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
        int[] Select = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
        CrosheTabView crosheTabView = new CrosheTabView(context, titles, unSelect, Select, getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorAccent), new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        ll_r.addView(crosheTabView);


        //选项卡无限个数
        CrosheManyTabView crosheManyTabView = new CrosheManyTabView(this, context, titles, unSelect, Select, getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorAccent), new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        ll_r.addView(crosheManyTabView);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {

    }

    @Override
    public void onClick(View view) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2000) {
            if (resultCode == -1) {
                String[] paths = (String[]) data.getExtras().get("paths");
                if (null != paths && paths.length > 0) {
                    Intent intent = new Intent(context, CropImageActivity.class);
                    intent.putExtra(CropImageActivity.EXTRA_IMAGE_PATH, paths[0]);
                    startActivityForResult(intent, 3000);
                }

            }
        } else if (requestCode == 3000) {
            if (resultCode == -1) {
                String paths = (String) data.getExtras().get("image_path");
            }
        }
    }

}
