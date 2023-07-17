package com.puzzling.puzzlingaos.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.puzzling.puzzlingaos.R

fun ImageView.loadImage(image: Drawable?) {
    load(image) {
        placeholder(R.drawable.ic_puzzling_logo)
            .transformations(RoundedCornersTransformation(20f, 20f, 20f, 20f))
            .error(R.drawable.ic_launcher_background)
    }
}
