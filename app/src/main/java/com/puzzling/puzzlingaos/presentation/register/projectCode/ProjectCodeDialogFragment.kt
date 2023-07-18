package com.puzzling.puzzlingaos.presentation.register.projectCode

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseDialogFragment
import com.puzzling.puzzlingaos.databinding.FragmentProjectcodeDialogBinding
import com.puzzling.puzzlingaos.presentation.register.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProjectCodeDialogFragment(private val code: String) : BaseDialogFragment<FragmentProjectcodeDialogBinding>(R.layout.fragment_projectcode_dialog) {

    private val viewModel by viewModels<RegisterViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvRegisterDialogDesc.text = code
        dialogCancelable()
        clickBtn()
    }

//    private fun putProjectCode(data: String) {
//        binding.tvRegisterDialogDesc.text = data.projectCode
//    }

//    private fun projectCode() {
//        val data: ResponseProjectRegisterDto.ProjectCode
//        binding.tvRegisterDialogDesc.text = data.projectCode
//    }

    private fun dialogCancelable() {
        dialog?.setCancelable(false)
    }

    private fun clickBtn() {
        with(binding) {
            btnRegisterDialogTop.setOnClickListener { }
            btnRegisterDialogMiddle.setOnClickListener { }
            btnRegisterDialogBottom.setOnClickListener {
                dialog?.dismiss()
            }
        }
    }

    companion object {
        const val TAG_REGISTER_DIALOG = "Register dialog"
    }
}
