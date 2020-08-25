package com.example.p_databinding_constaint_set;

import android.content.Context;
import android.os.Build;
import android.transition.TransitionManager;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

public class ConstraintUtils {
    private ConstraintLayout mContainer;
    private ConstraintSet mConstraintSet;
    private Context mContent;


    public ConstraintUtils(Context context ,ConstraintLayout constraintLayout) {
        this.mContainer = constraintLayout;
        this.mContent = context;
        this.mConstraintSet  = new ConstraintSet();
        this.mConstraintSet.clone(constraintLayout);
    }

    public ConstraintSet setImageIcon(ImageView imageIcon, int drawableId){
        if(mConstraintSet != null){
            imageIcon.setImageDrawable(mContent.getResources().getDrawable(drawableId));
        }
        return mConstraintSet;
    }

    public void setBarHeight(int viewid,float percent){
        if(mConstraintSet != null){
            mConstraintSet.constrainPercentHeight(viewid,percent);
            mConstraintSet.applyTo(mContainer);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                TransitionManager.beginDelayedTransition(mContainer);
            }
        }
    }
}
