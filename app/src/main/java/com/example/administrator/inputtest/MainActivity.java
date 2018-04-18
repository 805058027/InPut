package com.example.administrator.inputtest;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.administrator.inputtest.listener.CustomGlobalLayoutListener;

/**
 * 注意：软键盘的弹出需要在主配置文件里面配置参数
 *
 */
public class MainActivity extends AppCompatActivity {

    private ScrollView mScrollView;
    private LinearLayout rootView;
    private EditText mEditText;
    private CustomGlobalLayoutListener customGlobalLayoutListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initID();
        initSetting();
    }

    /**
     * 初始化ID
     */
    private void initID() {
        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        rootView = (LinearLayout) findViewById(R.id.rootView);
        mEditText = (EditText) findViewById(R.id.report_Comment);
    }

    /**
     * 软键盘弹出和输入框的光标设置
     */
    private void initSetting() {
        customGlobalLayoutListener = new CustomGlobalLayoutListener(this, mScrollView, rootView,
                mEditText);
        mScrollView.getViewTreeObserver().addOnGlobalLayoutListener(customGlobalLayoutListener);
        // 编辑框设置触摸监听
        mEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_DOWN == event.getAction()) {
                    mEditText.setCursorVisible(true);// 再次点击显示光标
                }
                return false;
            }
        });
    }

    /**
     * 控制输入框光标的显示隐藏（在此demo中没有使用,可以自行测试）
     *
     * @param parentRoot EditText的父布局
     * @param mEditText  EditText本身
     */
    private void mEditCursor(final View parentRoot, final EditText mEditText) {
        parentRoot.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        Rect r = new Rect();
                        parentRoot.getWindowVisibleDisplayFrame(r);
                        int screenHeight = parentRoot.getRootView().getHeight();
                        int heightDifference = screenHeight - (r.bottom);
                        if (heightDifference > 200) {
                            //软键盘显示
                            mEditText.setFocusable(true);
                            mEditText.setFocusableInTouchMode(true);
                            mEditText.requestFocus();
                            mEditText.findFocus();
                            InputMethodManager imm = (InputMethodManager) getSystemService
                                    (Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(mEditText, InputMethodManager.SHOW_FORCED);
                        } else {
                            mEditText.clearFocus();
                        }
                    }
                });

        mEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mEditText.setFocusable(true);
                mEditText.setFocusableInTouchMode(true);
                return false;
            }
        });
    }
}
