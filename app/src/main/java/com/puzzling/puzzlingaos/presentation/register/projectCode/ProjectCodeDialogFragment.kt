package com.puzzling.puzzlingaos.presentation.register.projectCode

import android.os.Bundle
import android.view.View
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseDialogFragment
import com.puzzling.puzzlingaos.databinding.FragmentProjectcodeDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProjectCodeDialogFragment : BaseDialogFragment<FragmentProjectcodeDialogBinding>(R.layout.fragment_projectcode_dialog) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialogCancelable()
        clickBtn()
    }

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
}
