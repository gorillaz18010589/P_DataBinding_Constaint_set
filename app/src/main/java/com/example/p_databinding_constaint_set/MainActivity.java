package com.example.p_databinding_constaint_set;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;

import com.example.p_databinding_constaint_set.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;
    private View[] views = new View[2];
    private int count = 0;
    private int count2 = 0;
    private int count3 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        init();
    }

    private void init() {
        views = new View[]{
                activityMainBinding.conBar1,
                activityMainBinding.conBar2,
                activityMainBinding.conBar3
        };
        for (View view : views) {
            view.setOnClickListener(onClickListener);
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.conBar1:
                    if (count == 0) {
                        //如果conTbar 0 就展開自己並且關閉其他的
                        Log.v("hank", "conBar1 -> count =0:");
                        count++;
                        showThisBar(views, R.id.conBar1, 0.3f, R.drawable.ic_launcher_background);
                        //其他bar可能有點一次載點我們這所以歸0其他的控制
                        count2 = 0;
                        count3 = 0;
                    } else if (count == 1) {
                        //如果cont 1 就全部關閉
                        Log.v("hank", "conBar1 -> count =1:");
                        noShowBars(views, 0.1f, R.drawable.ic_launcher_background);
                        //如果點到這關閉,就全部改成0再點一次大家都可以開
                        count2 = 0;
                        count3 = 0;
                        count = 0;
                    }
                    break;
                case R.id.conBar2:
                    if (count2 == 0) {
                        Log.v("hank", "conBar2 -> count2 = 0:");
                        count2++;
                        showThisBar(views, R.id.conBar2, 0.3f, R.drawable.ic_launcher_background);
                        count = 0;
                        count3 = 0;
                    } else if (count2 == 1) {
                        Log.v("hank", "conBar2 -> count2 = 1");
                        noShowBars(views, 0.1f, R.drawable.ic_launcher_background);
                        count = 0;
                        count2 = 0;
                        count3 = 0;
                    }
                    break;
                case R.id.conBar3:
                    if (count3 == 0) {
                        Log.v("hank", "conBar3 -> count3 = 0");
                        count3++;
                        showThisBar(views, R.id.conBar3, 0.3f, R.drawable.ic_launcher_background);
                        count = 0;
                        count2 = 0;
                    } else if (count3 == 1) {
                        Log.v("hank", "conBar3 -> count3 = 1");
                        noShowBars(views, 0.1f, R.drawable.ic_launcher_background);
                        count = 0;
                        count2 = 0;
                        count3 = 0;
                    }
                    break;
            }

        }
    };


    //當點擊自己的bar,我這個bar要展開,其他bar要關閉
    private void showThisBar(View[] views, int conViewId, float height, int imageId) {
        showBar(conViewId, height, imageId);
        noShowThisBar(views, conViewId);
    }

    //只有自己的bar不關閉,其他要關閉
    private void noShowThisBar(View[] views, int conViewId) {
        for (View view : views) {
            if (view.getId() != conViewId) {
                noShowBar(view.getId(), 0.1f, R.drawable.ic_launcher_background);
            }
        }
    }


    //展示bar
    private void showBar(int conViewId, float height, int imageId) {
        Log.v("hank", "showBar" + "toogle:");
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(activityMainBinding.ccontainer);
        constraintSet.constrainPercentHeight(conViewId, height);
        constraintSet.applyTo(activityMainBinding.ccontainer);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            TransitionManager.beginDelayedTransition(activityMainBinding.ccontainer);
        }
        activityMainBinding.img1.setImageDrawable(getResources().getDrawable(imageId));
    }

    //全部的bar都關閉
    private void noShowBars(View[] views, float height, int imageId) {
        for (View view : views) {
            noShowBar(view.getId(), height, imageId);
        }
    }


    //不要顯指定的bar
    private void noShowBar(int conViewId, float height, int imageId) {
        Log.v("hank", "noShowBar");
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(activityMainBinding.ccontainer);
        constraintSet.constrainPercentHeight(conViewId, height);
        constraintSet.applyTo(activityMainBinding.ccontainer);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            TransitionManager.beginDelayedTransition(activityMainBinding.ccontainer);
        }
        activityMainBinding.img1.setImageDrawable(getResources().getDrawable(imageId));
    }


    public void toPage2(View view) {
        startActivity(new Intent(MainActivity.this,TestOBJActivity.class));

    }
}
