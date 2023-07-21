// package com.puzzling.puzzlingaos.util
//
// import android.view.View
// import androidx.core.content.ContextCompat
// import androidx.databinding.DataBindingUtil
// import com.google.android.material.snackbar.Snackbar
// import com.puzzling.puzzlingaos.R
// import com.puzzling.puzzlingaos.databinding.SnackbarCustomBinding
//
// class CustomSnackbar(view: View, private val message: String) {
//
//    companion object {
//
//        fun make(view: View, message: String) = CustomSnackbar(view, message)
//    }
//
//    private val context = view.context
//    private val snackbar = Snackbar.make(view, "", 5000)
//    private val snackbarLayout = snackbar.view as Snackbar.SnackbarLayout
//
//    private val inflater = SnackbarCustomBinding.from(context)
//    private val snackbarBinding: SnackbarCustomBinding =
//        DataBindingUtil.inflate(inflater, R.layout.snackbar_custom, null, false)
//
//    init {
//        initView()
//        initData()
//    }
//
//    private fun initView() {
//        with(snackbarLayout) {
//            removeAllViews()
//            setPadding(0, 0, 0, 0)
//            setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
//            addView(snackbarBinding.root, 0)
//        }
//    }
//
//    private fun initData() {
//        snackbarBinding.tvSnackbar.text = "저장 완료!"
//    }
//
//    fun show() {
//        snackbar.show()
//    }
// }
