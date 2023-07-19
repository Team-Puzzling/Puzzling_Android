package com.puzzling.puzzlingaos.presentation.writeRetrospective

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseDialogFragment
import com.puzzling.puzzlingaos.databinding.FragmentRegisterDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterDialogFragment :
    BaseDialogFragment<FragmentRegisterDialogBinding>(R.layout.fragment_register_dialog) {
    private val viewModel by activityViewModels<WriteReviewViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDialogCancelable()
        clickBtn()
    }

    private fun initDialogCancelable() {
        dialog?.setCancelable(false)
    }

    private fun clickBtn() {
        with(binding) {
            btnRegisterDialogTop.setOnClickListener {
                Log.d("dialog", "register 버튼 누름")
                when (viewModel.selectedReviewType.value) {
                    "TIL" -> viewModel.postReviewTIL()
                    "5F" -> viewModel.postReview5F()
                    "AAR" -> viewModel.postReviewAAR()
                }
                // TODO detailRetroActivity 넘어갈때
                // 프로젝트 이름을 넘겨줌
            }
            btnRegisterDialogBottom.setOnClickListener {
                dialog?.dismiss()
            }
        }
    }
}
