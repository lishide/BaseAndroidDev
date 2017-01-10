package com.example.dev.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.base.adev.activity.BaseActivity;
import com.example.dev.R;
import com.lljjcoder.citypickerview.widget.CityPicker;

public class CityPickerActivity extends BaseActivity {

    private String title;

    private Button btnPicker;
    private TextView tvResult;
    private String defProvinceName = "辽宁省";
    private String defCityName = "大连市";
    private String defDistrictName = "中山区";

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.activity_city_picker);
    }

    @Override
    protected void initView() {
        btnPicker = (Button) findViewById(R.id.btn_picker);
        tvResult = (TextView) findViewById(R.id.tv_result);
    }

    @Override
    protected void initLogic() {
        mToolbar.setTitle(title);

        btnPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CityPicker cityPicker = new CityPicker.Builder(context).textSize(20)
                        .title("选择地区")
                        .titleTextColor("#ffffff")
                        .backgroundPop(0xa0000000)
                        .titleBackgroundColor("#0381F4")
                        .confirTextColor("#ffffff")
                        .cancelTextColor("#ffffff")
                        .province(defProvinceName)
                        .city(defCityName)
                        .district(defDistrictName)
                        .textColor(Color.parseColor("#000000"))
                        .provinceCyclic(true)
                        .cityCyclic(false)
                        .districtCyclic(false)
                        .visibleItemsCount(7)
                        .itemPadding(10)
                        .build();

                cityPicker.show();
                cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
                    @Override
                    public void onSelected(String... citySelected) {
                        tvResult.setText("选择结果：\n省：" + citySelected[0]
                                + "\n市：" + citySelected[1]
                                + "\n区：" + citySelected[2]
                                + "\n邮编：" + citySelected[3]);
                    }
                });
            }
        });
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        title = extras.getString("title");
    }

}
