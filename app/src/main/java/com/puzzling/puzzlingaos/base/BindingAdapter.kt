package com.puzzling.puzzlingaos.base

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.databinding.BindingAdapter
import coil.load
import com.puzzling.puzzlingaos.R

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

@BindingAdapter("app:checkedReviewType")
fun setCheckedReviewType(radioButton: AppCompatRadioButton, reviewType: String) {
    val checkedId = when (reviewType) {
        "TIL" -> R.id.rbtn_choose_first
        "5F" -> R.id.rbtn_choose_second
        "AAR" -> R.id.rbtn_choose_third
        else -> R.id.rbtn_choose_first
    }
    radioButton.isChecked = radioButton.id == checkedId
}
