package com.puzzling.puzzlingaos.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import coil.load
import coil.transform.RoundedCornersTransformation
import com.google.android.material.snackbar.Snackbar
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.databinding.SnackbarCustomBinding

fun ImageView.loadImage(image: Drawable?) {
    load(image) {
        placeholder(R.drawable.ic_puzzling_logo)
            .transformations(RoundedCornersTransformation(20f, 20f, 20f, 20f))
            .error(R.drawable.ic_launcher_background)
    }
}

fun Context.showCustomSnackBar(message: String, container: View?) {
    val binding = container?.let { SnackbarCustomBinding.bind(it) }
    val snackBar = container?.let { Snackbar.make(it, "", Snackbar.LENGTH_LONG) }
    snackBar?.apply {
        (view as ViewGroup).addView(binding?.root)
        binding?.tvSnackbar!!.text = message
        show()
    }
}

fun View.showToast(message: String) {
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT)
        .show()
}
