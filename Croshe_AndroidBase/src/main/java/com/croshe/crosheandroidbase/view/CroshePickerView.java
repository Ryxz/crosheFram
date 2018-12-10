package com.croshe.crosheandroidbase.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.croshe.crosheandroidbase.R;
import com.croshe.crosheandroidbase.entity.JsonBean;
import com.croshe.crosheandroidbase.entity.ProvinceBean;
import com.croshe.crosheandroidbase.listener.OnOptionsSelectResultListener;
import com.croshe.crosheandroidbase.util.GetJsonDataUtil;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by niuyongwei on 17/5/23.
 */

public class CroshePickerView {
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;

    private boolean isLoaded = false;

    public static CroshePickerView croshePickerView;


    public static CroshePickerView getInstance() {
        if (croshePickerView == null)
            return new CroshePickerView();
        return croshePickerView;
    }

    /**
     * 显示时间
     *
     * @param context 上下文
     * @param title   标题
     * @param type    显示类型 如 年-月-日
     */
    public void showTime(Context context, String title, TimePickerView.Type type, TimePickerView.OnTimeSelectListener onTimeSelectListener) {
        TimePickerView pvTime = new TimePickerView.Builder(context, onTimeSelectListener).setSubmitColor(Color.WHITE)//确定按钮文字颜色
                .setTitleText(title)
                .setTitleColor(Color.WHITE)
                .setCancelColor(Color.WHITE)//取消按钮文字颜色
                .setTitleBgColor(context.getResources().getColor(R.color.colorPrimary)).setType(type)
                .build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();

    }

    /**
     * 自定义条件选择
     *
     * @param context
     * @param arrayList1
     * @param onOptionsSelectResultListener
     */
    public void showCondition(Context context, String title, final ArrayList<ProvinceBean> arrayList1, final OnOptionsSelectResultListener onOptionsSelectResultListener) {

        /**
         * 注意 ：如果是三级联动的数据(省市区等)，请参照 JsonDataActivity 类里面的写法。
         */

        OptionsPickerView pvOptions = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = arrayList1.get(options1).getPickerViewText()
                       /* + options3Items.get(options1).get(options2).get(options3).getPickerViewText()*/;
                onOptionsSelectResultListener.cityInfo(tx, "", "");
            }
        })
                .setTitleText(title)
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.BLACK)//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .setTitleBgColor(context.getResources().getColor(R.color.colorPrimary))
                .setCancelColor(Color.WHITE)
                .setSubmitColor(Color.WHITE)
                .setTitleColor(Color.WHITE)
//                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                .setLabels("省", "市", "区")
                .build();

        //pvOptions.setSelectOptions(1,1);

        /*pvOptions.setPicker(options1Items);//一级选择器*/
        pvOptions.setPicker(arrayList1);//二级选择器
        pvOptions.show();
        /*pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器*/


//模拟数据
//        ArrayList<ProvinceBean> options1Items = new ArrayList<>();
//        ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
//        //选项1
//        options1Items.add(new ProvinceBean(0, "广东", "描述部分", "其他数据"));
//        options1Items.add(new ProvinceBean(1, "湖南", "描述部分", "其他数据"));
//        options1Items.add(new ProvinceBean(2, "广西", "描述部分", "其他数据"));
//
//        //选项2
//        ArrayList<String> options2Items_01 = new ArrayList<>();
//        options2Items_01.add("广州");
//        options2Items_01.add("佛山");
//        options2Items_01.add("东莞");
//        options2Items_01.add("珠海");
//        ArrayList<String> options2Items_02 = new ArrayList<>();
//        options2Items_02.add("长沙");
//        options2Items_02.add("岳阳");
//        options2Items_02.add("株洲");
//        options2Items_02.add("衡阳");
//        ArrayList<String> options2Items_03 = new ArrayList<>();
//        options2Items_03.add("桂林");
//        options2Items_03.add("玉林");
//        options2Items.add(options2Items_01);
//        options2Items.add(options2Items_02);
//        options2Items.add(options2Items_03);


    }

    /**
     * 显示城市
     *
     * @param context
     * @param onOptionsSelectResultListener
     */
    public void showCityPickerView(final Context context, final OnOptionsSelectResultListener onOptionsSelectResultListener) {// 弹出选择器
        if (thread == null) {//如果已创建就不再重新创建子线程了

            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 写子线程中的操作,解析省市区数据
                    initJsonData(context);

                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            OptionsPickerView pvOptions = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                    onOptionsSelectResultListener.cityInfo(options1Items.get(options1).getPickerViewText(), options2Items.get(options1).get(options2), options3Items.get(options1).get(options2).get(options3));
                                }
                            })

                                    .setTitleText("城市选择")
                                    .setDividerColor(Color.BLACK)
                                    .setTitleColor(Color.WHITE)
                                    .setCancelColor(Color.WHITE)
                                    .setSubmitColor(Color.WHITE)
                                    .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                                    .setContentTextSize(20)
                                    .setTitleBgColor(context.getResources().getColor(R.color.colorPrimary))
                                    .setOutSideCancelable(false)// default is true
                                    .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
                            pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
                            pvOptions.show();
                        }
                    });

                }
            });
            thread.start();
        }

    }


    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:

                    break;

                case MSG_LOAD_SUCCESS:
                    isLoaded = true;
                    break;

                case MSG_LOAD_FAILED:
                    break;

            }
        }
    };

    private void initJsonData(Context context) {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(context, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {

                    for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }


    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }
}
