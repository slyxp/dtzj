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
 * @date 2016/11/9 10:18
 * @copyright HAWK
 */

public class IntegrateFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View integrateView = inflater.inflate(R.layout.fragment_integrate, container, false);

        return integrateView;
    }
}
