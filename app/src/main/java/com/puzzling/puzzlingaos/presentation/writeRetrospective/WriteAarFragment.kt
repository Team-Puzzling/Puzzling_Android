package com.puzzling.puzzlingaos.presentation.writeRetrospective

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseFragment
import com.puzzling.puzzlingaos.databinding.FragmentWriteAarBinding

class WriteAarFragment :
    BaseFragment<FragmentWriteAarBinding>(R.layout.fragment_write_aar) {
    private val viewModel by activityViewModels<WriteRetrospectiveViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        textInputListener(viewModel.aarQuestion1)
        textInputListener(viewModel.aarQuestion2)
        textInputListener(viewModel.aarQuestion3)
        textInputListener(viewModel.aarQuestion4)
        textInputListener(viewModel.aarQuestion5)
        validBtnEnabled()
    }

    private fun textInputListener(textBox: MutableLiveData<String>) {
        textBox.observe(this) { textBoxString ->
            viewModel.let { viewModel ->
                if (!viewModel.validTextBox(textBoxString)) {
                    when (textBox) {
                        viewModel.aarQuestion1 -> {
                            binding.edtWriteFirstMain.error = ERROR_MESSAGE
                            viewModel.isValidAarquestion1.value = false
                        }
                        viewModel.aarQuestion2 -> {
                            binding.edtWriteSecondMain.error = ERROR_MESSAGE
                            viewModel.isValidAarquestion2.value = false
                        }
                        viewModel.aarQuestion3 -> {
                            binding.edtWriteThirdMain.error = ERROR_MESSAGE
                            viewModel.isValidAarquestion3.value = false
                        }
                        viewModel.aarQuestion4 -> {
                            binding.edtWriteFourthMain.error = ERROR_MESSAGE
                            viewModel.isValidAarquestion4.value = false
                        }
                        viewModel.aarQuestion5 -> {
                            binding.edtWriteFifthMain.error = ERROR_MESSAGE
                            viewModel.isValidAarquestion5.value = false
                        }
                    }
                } else {
                    when (textBox) {
                        viewModel.aarQuestion1 -> {
                            binding.edtWriteFirstMain.error = null
                            viewModel.isValidAarquestion1.value = true
                        }
                        viewModel.aarQuestion2 -> {
                            binding.edtWriteSecondMain.error = null
                            viewModel.isValidAarquestion2.value = true
                        }
                        viewModel.aarQuestion3 -> {
                            binding.edtWriteThirdMain.error = null
                            viewModel.isValidAarquestion3.value = true
                        }
                        viewModel.aarQuestion4 -> {
                            binding.edtWriteFourthMain.error = null
                            viewModel.isValidAarquestion4.value = true
                        }
                        viewModel.aarQuestion5 -> {
                            binding.edtWriteFifthMain.error = null
                            viewModel.isValidAarquestion5.value = true
                        }
                    }
                }
            }
        }
    }

    private fun validBtnEnabled() {
        viewModel.isEnabledAarRegister.observe(this) {
            viewModel.checkBtnEnabled()
        }
    }

    companion object {
        const val ERROR_MESSAGE = "이모지를 사용할 수 없어요"
    }
}
