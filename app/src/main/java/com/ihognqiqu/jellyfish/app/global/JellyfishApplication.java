/*
 *  Copyright 2015 Zhenguo Jin (jingle1267@163.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.ihognqiqu.jellyfish.app.global;

import android.app.Application;
import android.graphics.PixelFormat;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.ImageView;
import com.ihognqiqu.jellyfish.app.R;
import com.ihognqiqu.jellyfish.app.ui.activity.MainActivity;
import com.ihognqiqu.jellyfish.app.ui.listener.GestureListener;
import com.ihognqiqu.jellyfish.app.utils.DisplayUtil;

/**
 * Global settings.
 *
 * Created by zhenguo on 2015/7/8.
 */
public class JellyfishApplication extends Application {

    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;
    private ImageView imageView;
    private GestureDetector gestureDetector;

    private int width, height;
    private int viewWidth = 87, viewHeight = 87;
    private int statusBarHeight = 0;
    private float rawX, rawY;
    private float x, y;

    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }

    private void init() {
        windowManager = (WindowManager) getApplicationContext()
                .getSystemService(WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int widgetSize = displayMetrics.widthPixels / 6;

        layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        layoutParams.format = PixelFormat.RGBA_8888;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        layoutParams.width = widgetSize; // 120
        layoutParams.height = widgetSize; // 120
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.x = 0;
        layoutParams.y = 0;

        imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(R.mipmap.ic_launcher);
        if (imageView.getParent() == null) {
            windowManager.addView(imageView, layoutParams);
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.launch(v.getContext());
            }
        });

        statusBarHeight = DisplayUtil.getStatusBarHeight(getApplicationContext());
        viewWidth = DisplayUtil.dip2px(getApplicationContext(), 87 / 2);
        viewHeight = DisplayUtil.dip2px(getApplicationContext(), 87 / 2);
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;

        gestureDetector = new GestureDetector(this, new GestureListener(getApplicationContext()));
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                rawX = event.getRawX();
                rawY = event.getRawY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x = event.getX();
                        y = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        updatePosition();
                        break;
                    case MotionEvent.ACTION_UP:
                        updatePosition();
                        // if (SettingHelper.getAutoAlign(getApplicationContext())) {
                            autoMove();
                        // }
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                    case MotionEvent.ACTION_OUTSIDE:
                        break;
                    default:
                        break;
                }
                return gestureDetector.onTouchEvent(event);
            }

            private void updatePosition() {
                layoutParams.x = (int) rawX - width / 2 - (int) x + viewWidth / 2;
                layoutParams.y = (int) rawY - height / 2 - (int) y + viewHeight / 2
                        - statusBarHeight / 2;

                windowManager.updateViewLayout(imageView, layoutParams);
            }

            private void autoMove() {
                while (true) {
                    if (layoutParams.x <= 0 && layoutParams.x > -width / 2 + 5) {
                        layoutParams.x = layoutParams.x - 5;
                        windowManager.updateViewLayout(imageView, layoutParams);
                    } else if (layoutParams.x > 0 && layoutParams.x < width / 2 - 5) {
                        layoutParams.x = layoutParams.x + 5;
                        windowManager.updateViewLayout(imageView, layoutParams);
                    } else {
                        break;
                    }
                }
            }

        });
    }




}
