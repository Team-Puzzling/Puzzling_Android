package com.puzzling.puzzlingaos.presentation.writeRetrospective

import Write5fFragment
import WriteTilFragment
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseActivity
import com.puzzling.puzzlingaos.databinding.ActivityWriteRetrospectiveBinding

class WriteRetrospectiveActivity :
    BaseActivity<ActivityWriteRetrospectiveBinding>(R.layout.activity_write_retrospective) {
    private val viewModel by viewModels<WriteRetrospectiveViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        initFragment()
        clickBtn()
        handleSelectedReviewType()
    }

    private fun initFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_write_container)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_write_container, Write5fFragment()).commit()
        }
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

    private fun showDialog() {
        val registerDialog by lazy { RegisterDialogFragment() }
        registerDialog.show(supportFragmentManager, TAG_REGISTER_DIALOG)
    }

    private fun handleSelectedReviewType() {
        viewModel.selectedReviewType.observe(this) { reviewType ->
            binding.tvWriteChip.text = reviewType
            Log.d("write", "review type:: $reviewType")
            when (reviewType) {
                "TIL" -> {
                    clearTilQuestionText()
                    replaceFragment(WriteTilFragment())
                }
                "5F" -> {
                    clear5fQuestionText()
                    replaceFragment(Write5fFragment())
                }
                "AAR" -> {
                    clearAarQuestionText()
                    replaceFragment(WriteAarFragment())
                }
            }
        }
    }

    private fun clearTilQuestionText() {
        viewModel.tilQuestion1.value = ""
        viewModel.tilQuestion2.value = ""
        viewModel.tilQuestion3.value = ""
    }

    private fun clear5fQuestionText() {
        viewModel.question5f1.value = ""
        viewModel.question5f2.value = ""
        viewModel.question5f3.value = ""
        viewModel.question5f4.value = ""
        viewModel.question5f5.value = ""
    }

    private fun clearAarQuestionText() {
        viewModel.aarQuestion1.value = ""
        viewModel.aarQuestion2.value = ""
        viewModel.aarQuestion3.value = ""
        viewModel.aarQuestion4.value = ""
        viewModel.aarQuestion5.value = ""
    }

    private fun checkBtnEnabled() {
        viewModel.isInputEnabled.value = when (viewModel.selectedReviewType.value) {
            "TIL" -> viewModel.isTilValid()
            "5F" -> viewModel.is5fValid()
            "AAR" -> viewModel.isAarValid()
            else -> false
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.fcv_write_container, fragment)
        }
    }

    companion object {
        const val TAG_REGISTER_DIALOG = "Register dialog"
    }
}
