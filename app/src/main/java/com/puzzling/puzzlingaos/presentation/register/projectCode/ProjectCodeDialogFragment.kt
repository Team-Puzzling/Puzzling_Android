package com.puzzling.puzzlingaos.presentation.register.projectCode

import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseDialogFragment
import com.puzzling.puzzlingaos.databinding.FragmentProjectcodeDialogBinding
import com.puzzling.puzzlingaos.presentation.main.MainActivity
import com.puzzling.puzzlingaos.presentation.register.RegisterViewModel
import com.puzzling.puzzlingaos.util.getClipboardManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProjectCodeDialogFragment(private val code: String) :
    BaseDialogFragment<FragmentProjectcodeDialogBinding>(R.layout.fragment_projectcode_dialog) {
    private val viewModel by activityViewModels<RegisterViewModel>()

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
                val intent = Intent(requireActivity(), MainActivity::class.java)
                intent.putExtra("homeProjectId", viewModel.projectId.value)
                Log.d("main", "RegisterViewModel.projectId ::: ${viewModel.projectId.value}") // 잘뜸
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
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
