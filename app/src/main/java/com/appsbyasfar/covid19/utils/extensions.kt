package com.appsbyasfar.covid19.utils

import android.R
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.graphics.ColorUtils


fun View.changeColorTo(colorTo:Int){

    val colorFrom = when (this) {
        is CardView -> this.cardBackgroundColor.defaultColor
        is TextView -> this.currentTextColor
        else -> {
            val background: Drawable = this.background
            if (background is ColorDrawable) background.color
            else Color.TRANSPARENT
        }
    }

    val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
    colorAnimation.duration = 750 // milliseconds

    colorAnimation.addUpdateListener { animator ->
        when (this) {
            is CardView -> this.setCardBackgroundColor(animator.animatedValue as Int)
            is TextView -> this.setTextColor(animator.animatedValue as Int)
            else -> this.setBackgroundColor(animator.animatedValue as Int)
        }
    }

    colorAnimation.start()

}

fun TextView.startDrawable(id: Int){
    if(id==-1){
        this.setCompoundDrawables(null, null, null, null)
    }else {
        val img = context.resources.getDrawable(id)
        img.setBounds(0, 0, 60, 60)
        this.setCompoundDrawables(img, null, null, null)
    }
}

fun Int.withAlpha(alpha: Int): Int{
    return ColorUtils.setAlphaComponent(this,alpha)
}