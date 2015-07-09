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

package com.ihognqiqu.jellyfish.app.ui.listener;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;
import com.ihognqiqu.jellyfish.app.ui.activity.MainActivity;
import com.ihognqiqu.jellyfish.app.utils.CommonUtils;

/**
 * Custom gesture listener.
 * <p/>
 * Created by rose on 2015/7/9.
 */
public class GestureListener extends GestureDetector.SimpleOnGestureListener {

    private Context context;
    private DisplayMetrics displayMetrics;

    public GestureListener(Context context) {
        this.context = context;
        this.displayMetrics = CommonUtils.getScreenPix(context);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        MainActivity.launch(context);
        return super.onSingleTapConfirmed(e);
    }

    @Override
    public void onLongPress(MotionEvent e) {
        super.onLongPress(e);
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return super.onDoubleTap(e);
    }

}
