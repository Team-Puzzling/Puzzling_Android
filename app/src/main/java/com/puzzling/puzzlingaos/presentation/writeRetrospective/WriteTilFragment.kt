package com.puzzling.puzzlingaos.presentation.writeRetrospective

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseFragment
import com.puzzling.puzzlingaos.databinding.FragmentWriteTilBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WriteTilFragment :
    BaseFragment<FragmentWriteTilBinding>(R.layout.fragment_write_til) {
    private val viewModel by activityViewModels<WriteReviewViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        textInputListener(viewModel.tilQuestion1)
        textInputListener(viewModel.tilQuestion1)
        textInputListener(viewModel.tilQuestion1)
        validBtnEnabled()
    }

    private fun textInputListener(textBox: MutableLiveData<String>) {
        textBox.observe(this) { textBoxString ->
            viewModel.let { viewModel ->
                if (!viewModel.validTextBox(textBoxString)) {
                    when (textBox) {
                        viewModel.tilQuestion1 -> {
                            binding.edtWriteFirstMain.error = ERROR_MESSAGE
                            viewModel.isValidTilquestion1.value = false
                        }
                        viewModel.tilQuestion2 -> {
                            binding.edtWriteSecondMain.error = ERROR_MESSAGE
                            viewModel.isValidTilquestion2.value = false
                        }
                        viewModel.tilQuestion3 -> {
                            binding.edtWriteThirdMain.error = ERROR_MESSAGE
                            viewModel.isValidTilquestion3.value = false
                        }
                    }
                } else {
                    when (textBox) {
                        viewModel.tilQuestion1 -> {
                            binding.edtWriteFirstMain.error = null
                            viewModel.isValidTilquestion1.value = true
                        }
                        viewModel.tilQuestion2 -> {
                            binding.edtWriteSecondMain.error = null
                            viewModel.isValidTilquestion2.value = true
                        }
                        viewModel.tilQuestion3 -> {
                            binding.edtWriteThirdMain.error = null
                            viewModel.isValidTilquestion3.value = true
                        }
                    }
                }
            }
        }
    }

    private fun validBtnEnabled() {
        viewModel.isEnabledTilRegister.observe(this) {
            viewModel.checkBtnEnabled()
        }
    }

    companion object {
        const val ERROR_MESSAGE = "이모지를 사용할 수 없어요"
    }
}
