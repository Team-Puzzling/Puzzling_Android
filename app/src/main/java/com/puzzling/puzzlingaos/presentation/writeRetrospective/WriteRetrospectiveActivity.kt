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
        with(binding) {
            clWriteChip.setOnClickListener {
                val chooseRetrospectiveFragment = ChooseRetrospectiveFragment()
                chooseRetrospectiveFragment.show(supportFragmentManager, "show")
            }
            tvWriteRegister.setOnClickListener {
                Log.d("write", "저장 다이얼로그 표시함")
                showDialog()
            }
            btnWriteBack.setOnClickListener {
                // 뒤로 가기 로직
            }
        }
    }

    private fun showDialog() {
        val registerDialog by lazy { RegisterDialogFragment() }
        registerDialog.show(supportFragmentManager, TAG_REGISTER_DIALOG)
    }

    companion object {
        const val TAG_REGISTER_DIALOG = "REGISTER_DIALOG"
    }
}
