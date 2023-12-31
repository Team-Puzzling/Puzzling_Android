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
import com.puzzling.puzzlingaos.presentation.main.MainActivity
import com.puzzling.puzzlingaos.util.showToast
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
                    binding.root.showToast("저장 완료!") // Show the Toast message
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
                    "TIL" -> {
                        viewModel.projectId.observe(viewLifecycleOwner) {
                            Log.d("til", "$it")
                            viewModel.postReviewTIL(it)
                        }
                    }
                    "5F" -> {
                        viewModel.projectId.observe(viewLifecycleOwner) {
                            Log.d("5f", "$it")
                            viewModel.postReview5F(it)
                        }
                    }
                    "AAR" -> {
                        viewModel.projectId.observe(viewLifecycleOwner) {
                            Log.d("aar", "$it")
                            viewModel.postReviewAAR(it)
                        }
                    }
                }
                // TODO toast 성공하면 주석 제거하기
                activity?.let {
                    val intent = Intent(context, MainActivity::class.java)
                    intent.putExtra("homeProjectId", viewModel.projectId.value)
                    intent.putExtra("Title", viewModel.projectName.value)
                    Log.d("register", "viewModel.projectId.value::: ${viewModel.projectId.value}")
                    launchDetailRetroActivity.launch(intent)
                }
                // TODO 프로젝트 넘기기
            }
            btnRegisterDialogBottom.setOnClickListener {
                dialog?.dismiss()
            }
        }
    }

//    private fun showSnackbar() {
//        CustomSnackbar.makeSnackbar(binding.root, "저장 완료!")
//    }
}
