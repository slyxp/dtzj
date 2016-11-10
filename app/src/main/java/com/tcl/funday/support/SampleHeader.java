package com.tcl.funday.support;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.tcl.funday.R;

/**
 * @author Liyang Sun
 * @Description:
 * @date 2016/11/10 15:10
 * @copyright HAWK
 */

public class SampleHeader extends RelativeLayout {
    public SampleHeader(Context context) {
        super(context);
        init(context);
    }

    public SampleHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SampleHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {

        inflate(context, R.layout.sample_header, this);
    }
}
