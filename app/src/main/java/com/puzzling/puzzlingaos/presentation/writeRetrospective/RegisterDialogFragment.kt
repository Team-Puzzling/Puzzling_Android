package com.puzzling.puzzlingaos.presentation.writeRetrospective

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseDialogFragment
import com.puzzling.puzzlingaos.databinding.FragmentRegisterDialogBinding
import com.puzzling.puzzlingaos.presentation.detailRetrospect.DetailRetroActivity
import com.puzzling.puzzlingaos.util.CustomSnackbar
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

    private val launchDetailRetroActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                if (data?.getBooleanExtra(DetailRetroActivity.EXTRA_RESULT_SAVED, false) == true) {
                    showSnackbar()
                }
            }
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
                activity?.let {
                    val intent = Intent(context, DetailRetroActivity::class.java)
                    startActivity(intent)
                }
                // TODO 프로젝트 넘기기
            }
            btnRegisterDialogBottom.setOnClickListener {
                dialog?.dismiss()
            }
        }
    }

    private fun showSnackbar() {
        CustomSnackbar.makeSnackbar(binding.root, "저장 완료!")
    }
}
