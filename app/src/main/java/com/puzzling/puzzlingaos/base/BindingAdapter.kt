package com.puzzling.puzzlingaos.base

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("image")
fun ImageView.setImage(imageUrl: String) {
    this.load(imageUrl)
}

@BindingAdapter("spanTextColor")
fun setTextColor(view: TextView, inputText: String?) {
    val isConditionMet = inputText == "Your Condition"
    view.setTextColor(if (isConditionMet) Color.BLUE else Color.BLACK)
}
// TODO span color 이용해서 텍스트 컬러 처리
