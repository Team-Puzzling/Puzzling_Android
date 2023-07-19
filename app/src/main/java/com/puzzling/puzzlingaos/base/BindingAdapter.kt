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

@BindingAdapter("imageName", "defaultImage, requireAll = false")
fun setImageResourceByImageName(
    imageView: ImageView,
    imageName: String?,
    defaultImage: Int?,
) {
    val imageResource = if (imageName != null) {
        val context = imageView.context
        val resourceId = context.resources.getIdentifier(
            imageName,
            "drawable",
            context.packageName,
        )
        resourceId
    } else {
        defaultImage ?: 0
    }

    imageView.setImageResource(imageResource)
}

@BindingAdapter("imageName")
fun setImageResourceByName(view: ImageView, fileName: String?) {
    if (fileName != null) {
        val resourceId = view.context.resources.getIdentifier(
            fileName,
            "drawable",
            view.context.packageName,
        )
        view.setImageResource(resourceId)
    }
}

// @BindingAdapter("imageName", "defaultImage")
// fun setImageResourceByName(view: ImageView, fileName: String?, defaultImage: Int) {
//    val resourceId = if (!fileName.isNullOrEmpty()) {
//        view.context.resources.getIdentifier(
//            fileName,
//            "drawable",
//            view.context.packageName,
//        )
//    } else {
//        defaultImage
//    }
//    view.setImageResource(resourceId)
// }
