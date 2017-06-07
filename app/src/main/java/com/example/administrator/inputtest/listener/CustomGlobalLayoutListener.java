package com.example.administrator.inputtest.listener;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;

import com.example.administrator.inputtest.utils.DisplayUtil;

/**
 * Created by Administrator on 2017/6/7.
 */

public class CustomGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {

    private Context mContext;
    private View mRootView;
    private View mScrollToView;
    private EditText mEditView;

    /**
     * @param context      context
     * @param rootView     可以滚动的布局
     * @param scrollToView 界面上被遮挡的位于最底部的布局(控件)
     * @param mEditView    输入框光标的隐藏
     */
    public CustomGlobalLayoutListener(Context context, View rootView, View scrollToView, EditText
            mEditView) {
        this.mContext = context;
        this.mRootView = rootView;
        this.mScrollToView = scrollToView;
        this.mEditView = mEditView;
    }

    @Override
    public void onGlobalLayout() {
        Rect rect = new Rect();
        mRootView.getWindowVisibleDisplayFrame(rect);
        int rootInvisibleHeight = mRootView.getRootView().getHeight() - rect.bottom;
        if (rootInvisibleHeight > 100) {
            int[] location = new int[2];
            mScrollToView.getLocationInWindow(location);
            int scrollHeight = (location[1] + mScrollToView.getHeight()) - rect.bottom;
            mRootView.scrollTo(0, scrollHeight + DisplayUtil.dp2px(mContext, 30));
        } else {
            mEditView.setCursorVisible(false);
            mRootView.scrollTo(0, 0);
        }
    }
}
