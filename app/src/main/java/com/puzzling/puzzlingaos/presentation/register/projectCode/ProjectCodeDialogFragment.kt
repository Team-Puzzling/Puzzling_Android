package com.puzzling.puzzlingaos.presentation.register.projectCode

import android.content.ClipData
import android.os.Bundle
import android.view.View
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseDialogFragment
import com.puzzling.puzzlingaos.databinding.FragmentProjectcodeDialogBinding
import com.puzzling.puzzlingaos.util.getClipboardManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProjectCodeDialogFragment(private val code: String) : BaseDialogFragment<FragmentProjectcodeDialogBinding>(R.layout.fragment_projectcode_dialog) {

    private lateinit var clip: ClipData

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvRegisterDialogDesc.text = code
        dialogCancelable()
        clickBtn()
    }

    private fun dialogCancelable() {
        dialog?.setCancelable(false)
    }

    private fun clickBtn() {
        with(binding) {
            btnRegisterDialogTop.setOnClickListener {
                copyText()
            }
            btnRegisterDialogMiddle.setOnClickListener { }
            btnRegisterDialogBottom.setOnClickListener {
                dialog?.dismiss()
            }
        }
    }

    private fun copyText() {
        val clipboard = requireContext().getClipboardManager()
        // 일반 텍스트 복사
        clip = ClipData.newPlainText("CLIPBOARD_TEXT", code)
        // ClipData 개체를 클립보드에 넣음
        clipboard.setPrimaryClip(clip)
    }
}
