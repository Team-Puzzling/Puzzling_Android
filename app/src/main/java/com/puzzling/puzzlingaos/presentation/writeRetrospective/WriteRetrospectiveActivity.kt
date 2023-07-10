package com.puzzling.puzzlingaos.presentation.writeRetrospective

import android.os.Bundle
import android.util.Log
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseActivity
import com.puzzling.puzzlingaos.databinding.ActivityWriteRetrospectiveBinding

class WriteRetrospectiveActivity :
    BaseActivity<ActivityWriteRetrospectiveBinding>(R.layout.activity_write_retrospective) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clickBtn()
    }

    private fun clickBtn() {
        binding.clWriteChip.setOnClickListener {
            Log.d("write", "회고 선택 바텀시트 생겨야함")
            val chooseRetrospectiveFragment = ChooseRetrospectiveFragment()
            chooseRetrospectiveFragment.show(supportFragmentManager, "show")

//            val bottomSheetView =
//                layoutInflater.inflate(R.layout.fragment_choose_retrospective, null)
//            val bottomSheetDialog = BottomSheetDialog(this)
//            bottomSheetDialog.setContentView(bottomSheetView)
        }

        binding.tvWriteRegister.setOnClickListener {
            Log.d("write", "저장 다이얼로그 표시함")
        }
    }
}
