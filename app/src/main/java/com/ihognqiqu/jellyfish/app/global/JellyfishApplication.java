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
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import com.ihognqiqu.jellyfish.app.R;
import com.ihognqiqu.jellyfish.app.ui.MainActivity;

/**
 * Global settings.
 *
 * Created by zhenguo on 2015/7/8.
 */
public class JellyfishApplication extends Application {

    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;
    private ImageView imageView;

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

        layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        layoutParams.format = PixelFormat.RGBA_8888;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        layoutParams.width = 120;
        layoutParams.height = 120;
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
    }




}
