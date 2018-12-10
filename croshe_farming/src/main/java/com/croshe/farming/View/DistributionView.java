package com.croshe.farming.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.croshe.crosheandroidbase.view.CrosheBottomTabMenuView;
import com.croshe.crosheandroidbase.view.CrosheTabView;
import com.croshe.farming.MainActivity;
import com.croshe.farming.R;
import com.flyco.tablayout.listener.OnTabSelectListener;

/**
 * Created by Administrator on 2017/6/29.
 */

public class DistributionView extends FrameLayout {
    private LinearLayout ll_head;

    public DistributionView(Context context) {
        super(context);
        LinearLayout.inflate(context, R.layout.layout_dietribution_view,this);
        ll_head = (LinearLayout) findViewById(R.id.ll_diehead);
        creatHead();
    }

    public DistributionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DistributionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public void creatHead(){
        String[] titles = new String[]{"待配送", "已配送", "已签收", "待评价",};
        int[] unSelect = new int[]{0, 0, 0, 0};
        int[] Select = new int[]{0, 0, 0, 0};
        CrosheBottomTabMenuView crosheBottomTabMenuView = new CrosheBottomTabMenuView(getContext(), titles, unSelect, Select, getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorAccent), new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
            }

            @Override
            public void onTabReselect(int position) {

            }
        },"2");
        ll_head.addView(crosheBottomTabMenuView);
    }
    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        int newHeightSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthSpec, newHeightSpec);
    }

}
