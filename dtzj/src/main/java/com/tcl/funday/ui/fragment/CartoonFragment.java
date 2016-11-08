package com.tcl.funday.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tcl.funday.R;

/**
 * @author Liyang Sun
 * @Description:
 * @date 2016/11/7 19:47
 * @copyright HAWK
 */

public class CartoonFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View cartoonView = inflater.inflate(R.layout.fragment_cartoon, container, false);
        return cartoonView;
    }
}
