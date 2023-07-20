package com.puzzling.puzzlingaos.presentation.writeRetrospective

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseActivity
import com.puzzling.puzzlingaos.databinding.ActivityWriteRetrospectiveBinding
import com.puzzling.puzzlingaos.presentation.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WriteRetrospectiveActivity :
    BaseActivity<ActivityWriteRetrospectiveBinding>(R.layout.activity_write_retrospective) {
    private val viewModel by viewModels<WriteReviewViewModel>()
    private var reviewType: String = ""

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
                val chooseReviewFragment = ChooseReviewFragment()
                chooseReviewFragment.show(supportFragmentManager, "show")
            }
            tvWriteRegister.setOnClickListener {
                if (viewModel.isInputEnabled.value == true) {
                    showDialog()
                }
            }
            btnWriteBack.setOnClickListener {
                val intent = Intent(this@WriteRetrospectiveActivity, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun showDialog() {
        val registerDialog by lazy { RegisterDialogFragment() }
        registerDialog.show(supportFragmentManager, TAG_REGISTER_DIALOG)
    }

    private fun handleSelectedReviewType() {
        viewModel.selectedReviewType.observe(this) { type ->
            binding.tvWriteChip.text = type
            reviewType = type
            Log.d("write", "review type:: $reviewType")
            when (type) {
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

    //    private fun clearTilQuestionText() {
//        viewModel.tilQuestion1.value = ""
//        viewModel.tilQuestion2.value = ""
//        viewModel.tilQuestion3.value = ""
//    }
//
//    private fun clear5fQuestionText() {
//        viewModel.question5f1.value = ""
//        viewModel.question5f2.value = ""
//        viewModel.question5f3.value = ""
//        viewModel.question5f4.value = ""
//        viewModel.question5f5.value = ""
//    }
//
//    private fun clearAarQuestionText() {
//        viewModel.aarQuestion1.value = ""
//        viewModel.aarQuestion2.value = ""
//        viewModel.aarQuestion3.value = ""
//        viewModel.aarQuestion4.value = ""
//        viewModel.aarQuestion5.value = ""
//    }

    private fun clearText(vararg text: String?): Array<String?> {
        return Array(text.size) { "" }
    }

    private fun clearTilQuestionText() {
        val clearedText = clearText(
            viewModel.tilQuestion1.value,
            viewModel.tilQuestion2.value,
            viewModel.tilQuestion3.value,
        )
        viewModel.tilQuestion1.value = clearedText[0]
        viewModel.tilQuestion2.value = clearedText[1]
        viewModel.tilQuestion3.value = clearedText[2]
    }

    private fun clear5fQuestionText() {
        val clearedText = clearText(
            viewModel.question5f1.value,
            viewModel.question5f2.value,
            viewModel.question5f3.value,
            viewModel.question5f4.value,
            viewModel.question5f5.value,
        )
        viewModel.question5f1.value = clearedText[0]
        viewModel.question5f2.value = clearedText[1]
        viewModel.question5f3.value = clearedText[2]
        viewModel.question5f4.value = clearedText[3]
        viewModel.question5f5.value = clearedText[4]
    }

    private fun clearAarQuestionText() {
        val clearedText = clearText(
            viewModel.aarQuestion1.value,
            viewModel.aarQuestion2.value,
            viewModel.aarQuestion3.value,
            viewModel.aarQuestion4.value,
            viewModel.aarQuestion5.value,
        )
        viewModel.aarQuestion1.value = clearedText[0]
        viewModel.aarQuestion2.value = clearedText[1]
        viewModel.aarQuestion3.value = clearedText[2]
        viewModel.aarQuestion4.value = clearedText[3]
        viewModel.aarQuestion5.value = clearedText[4]
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
