package com.puzzling.puzzlingaos.presentation.writeRetrospective

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseActivity
import com.puzzling.puzzlingaos.databinding.ActivityWriteRetrospectiveBinding

class WriteRetrospectiveActivity :
    BaseActivity<ActivityWriteRetrospectiveBinding>(R.layout.activity_write_retrospective) {
    private val viewModel by viewModels<WriteRetrospectiveViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        clickBtn()
        textInputListener(viewModel.question1)
        textInputListener(viewModel.question2)
        textInputListener(viewModel.question3)
        validBtnEnabled()
        handleSelectedReviewType()
    }

    private fun clickBtn() {
        with(binding) {
            clWriteChip.setOnClickListener {
                val chooseRetrospectiveFragment = ChooseRetrospectiveFragment()
                chooseRetrospectiveFragment.show(supportFragmentManager, "show")
            }
            tvWriteRegister.setOnClickListener {
                if (viewModel.isInputEnabled.value == true) {
                    showDialog()
                }
            }
            btnWriteBack.setOnClickListener {
                // 뒤로 가기 로직
            }
        }
    }

    private fun handleSelectedReviewType() {
        viewModel.selectedReviewType.observe(this) { reviewType ->
            binding.tvWriteChip.text = reviewType
        }
    }

    private fun showDialog() {
        val registerDialog by lazy { RegisterDialogFragment() }
        registerDialog.show(supportFragmentManager, TAG_REGISTER_DIALOG)
    }

    private fun textInputListener(textBox: MutableLiveData<String>) {
        textBox.observe(this) { textBoxString ->
            viewModel.let { viewModel ->
                if (!viewModel.validTextBox(textBoxString)) {
                    when (textBox) {
                        viewModel.question1 -> {
                            binding.edtWriteFirstMain.error = ERROR_MESSAGE
                            viewModel.isValidquestion1.value = false
                        }
                        viewModel.question2 -> {
                            binding.edtWriteSecondMain.error = ERROR_MESSAGE
                            viewModel.isValidquestion2.value = false
                        }
                        viewModel.question3 -> {
                            binding.edtWriteThirdMain.error = ERROR_MESSAGE
                            viewModel.isValidquestion3.value = false
                        }
                    }
                } else {
                    when (textBox) {
                        viewModel.question1 -> {
                            binding.edtWriteFirstMain.error = null
                            viewModel.isValidquestion1.value = true
                        }
                        viewModel.question2 -> {
                            binding.edtWriteSecondMain.error = null
                            viewModel.isValidquestion2.value = true
                        }
                        viewModel.question3 -> {
                            binding.edtWriteThirdMain.error = null
                            viewModel.isValidquestion3.value = true
                        }
                    }
                }
            }
        }
    }

    private fun validBtnEnabled() {
        viewModel.isEnabledRegister.observe(this) {
            viewModel.checkBtnEnabled()
        }
    }

    companion object {
        const val TAG_REGISTER_DIALOG = "REGISTER_DIALOG"
        const val ERROR_MESSAGE = "이모지를 사용할 수 없어요"
    }
}
