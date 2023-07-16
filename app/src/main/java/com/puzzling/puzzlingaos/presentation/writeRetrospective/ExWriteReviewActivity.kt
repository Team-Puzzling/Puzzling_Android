// package com.puzzling.puzzlingaos.presentation.writeRetrospective
//
// import Write5fFragment
// import WriteTilFragment
// import android.os.Bundle
// import android.util.Log
// import androidx.activity.viewModels
// import androidx.fragment.app.Fragment
// import androidx.fragment.app.commit
// import com.puzzling.puzzlingaos.R
// import com.puzzling.puzzlingaos.base.BaseActivity
// import com.puzzling.puzzlingaos.databinding.ActivityExWriteReviewBinding
//
// class ExWriteReviewActivity :
//    BaseActivity<ActivityExWriteReviewBinding>(R.layout.activity_ex_write_review) {
//    private val viewModel by viewModels<ExWriteReviewViewModel>()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding.vm = viewModel
//        initFragment()
//        clickBtn()
//        handleSelectedReviewType()
//    }
//
//    private fun clickBtn() {
//        with(binding) {
//            clWriteChip.setOnClickListener {
//                val chooseRetrospectiveFragment = ChooseRetrospectiveFragment()
//                chooseRetrospectiveFragment.show(supportFragmentManager, "show")
//            }
//            tvWriteRegister.setOnClickListener {
//                if (viewModel.isInputEnabled.value == true) {
//                    showDialog()
//                }
//            }
//            btnWriteBack.setOnClickListener {
//                // 뒤로 가기 로직
//            }
//        }
//    }
//
//    private fun showDialog() {
//        val registerDialog by lazy { RegisterDialogFragment() }
//        registerDialog.show(supportFragmentManager, WriteRetrospectiveActivity.TAG_REGISTER_DIALOG)
//    }
//
//    private fun handleSelectedReviewType() {
//        viewModel.selectedReviewType.observe(this) { reviewType ->
//            binding.tvWriteChip.text = reviewType
//            Log.d("write", "review type:: $reviewType")
//            when (reviewType) {
//                "TIL" -> replaceFragment(WriteTilFragment())
//                "5F" -> replaceFragment(Write5fFragment())
//                // TODO 다른 회고 타입도 추가해줘야함
//            }
//        }
//    }
//
//    private fun replaceFragment(fragment: Fragment) {
//        supportFragmentManager.commit {
//            replace(R.id.fcv_write_container, fragment)
//        }
//    }
//
//    private fun initFragment() {
//        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_write_container)
//        if (currentFragment == null) {
//            supportFragmentManager.beginTransaction()
//                .add(R.id.fcv_write_container, Write5fFragment()).commit()
//        }
//    }
// }
