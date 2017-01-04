package com.base.adev.view.coverflow;

import android.view.View;
import android.view.ViewGroup;

public interface ICoverFlowAdapter {

    int getCount();

    Object getItem(int position);

    long getItemId(int position);

    View getView(int position, View convertView, ViewGroup parent);

    void getData(View view, int position);

}
