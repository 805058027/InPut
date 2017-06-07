package com.example.administrator.inputtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.administrator.inputtest.listener.CustomGlobalLayoutListener;

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
        customGlobalLayoutListener = new CustomGlobalLayoutListener(this, mScrollView, rootView,
                mEditText);
        mScrollView.getViewTreeObserver().addOnGlobalLayoutListener(customGlobalLayoutListener);
    }

    /**
     * 初始化ID
     */
    private void initID() {
        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        rootView = (LinearLayout) findViewById(R.id.rootView);
        mEditText = (EditText) findViewById(R.id.report_Comment);
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

    //控制输入框光标的显示隐藏
       /* reportEditParent.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        Rect r = new Rect();
                        reportEditParent.getWindowVisibleDisplayFrame(r);
                        int screenHeight = reportEditParent.getRootView()
                                .getHeight();
                        int heightDifference = screenHeight - (r.bottom);
                        if (heightDifference > 200) {
                            //软键盘显示
                            reportComment.setFocusable(true);
                            reportComment.setFocusableInTouchMode(true);
                            reportComment.requestFocus();
                            reportComment.findFocus();
                            InputMethodManager imm = (InputMethodManager) getSystemService
                                    (Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(reportComment, InputMethodManager.SHOW_FORCED);//
                        } else {
                            reportComment.clearFocus();
                        }
                    }
                });

        reportComment.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                reportComment.setFocusable(true);
                reportComment.setFocusableInTouchMode(true);
                return false;
            }
        });*/

}
