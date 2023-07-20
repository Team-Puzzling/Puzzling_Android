package com.puzzling.puzzlingaos.presentation.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseActivity
import com.puzzling.puzzlingaos.databinding.ActivityRegisterBinding
import com.puzzling.puzzlingaos.presentation.main.MainActivity
import com.puzzling.puzzlingaos.presentation.onboarding.ChooseJoinRegisterActivity
import com.puzzling.puzzlingaos.presentation.register.projectCode.ProjectCodeDialogFragment
import com.puzzling.puzzlingaos.util.UserInfo.POST_MEMBER_ID
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class RegisterActivity : BaseActivity<ActivityRegisterBinding>(R.layout.activity_register) {

    private val viewModel by viewModels<RegisterViewModel>()

    private lateinit var dayCycleAdapter: RetrospectWeekCycleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dayCycleAdapter = RetrospectWeekCycleAdapter(viewModel)
        binding.viewModel = viewModel

        pickedDate()
        clickDayCyclePicker()
        textBoxListener(viewModel.projectName)
        textBoxListener(viewModel.projectExplanation)
        textBoxListener(viewModel.role)
        textBoxListener(viewModel.nickName)
        canBtnClick()
        clickRegisterBtn()
        getProjectCode()
        clickBackBtn()
    }

    private fun pickedDate() {
        binding.layoutRegisterDate.setOnClickListener {
            val bottomSheetFragment = DatePickerFragment()
            bottomSheetFragment.setOnDateSelectedListener(object :
                DatePickerFragment.OnDateSelectedListener {
                override fun onDateSelected(year: Int, month: Int, dayOfMonth: Int) {
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(year, month, dayOfMonth)
                    val formattedDateTextBox = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(selectedDate.time)
                    var formattedDateRegister = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(selectedDate.time)
                    binding.tvDateDropDown.text = formattedDateTextBox
                    binding.tvDateDropDown.setTextAppearance(R.style.Kor_Body2_Bold)
                    viewModel.projectStartDate.value = formattedDateRegister
                }
            })

            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }
    }

    private fun clickDayCyclePicker() {
        binding.rcvRetrospectWeekCycle.adapter = RetrospectWeekCycleAdapter(viewModel)
        binding.rcvRetrospectWeekCycle.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        dayCycleAdapter.setOnDayClickListener { response ->
            viewModel.dayArray = dayCycleAdapter.selectedRetrospectDayArray
        }
        viewModel.isDateCycleSelected.value = dayCycleAdapter.sortedSelectedRetrospectDayArray
    }

    private fun textBoxListener(textBox: MutableLiveData<String>) {
        textBox.observe(this) { textBoxString ->
            viewModel.let { viewModel ->
                if (!viewModel.validTextBox(textBoxString)) {
                    when (textBox) {
                        viewModel.projectName -> {
                            binding.layoutProjectName.error = ERROR_MESSAGE
                            viewModel.isValidProjectName.value = false }
                        viewModel.projectExplanation -> {
                            binding.layoutExplanation.error = ERROR_MESSAGE
                            viewModel.isValidProjectExplanation.value = false }
                        viewModel.role -> {
                            binding.layoutRole.error = ERROR_MESSAGE
                            viewModel.isValidRole.value = false }
                        viewModel.nickName -> {
                            binding.layoutNickName.error = ERROR_MESSAGE
                            viewModel.isValidNickName.value = false }
                    }
                } else {
                    when (textBox) {
                        viewModel.projectName -> {
                            binding.layoutProjectName.error = null
                            viewModel.isValidProjectName.value = true
                        }
                        viewModel.projectExplanation -> {
                            binding.layoutExplanation.error = null
                            viewModel.isValidProjectExplanation.value = true
                        }
                        viewModel.role -> {
                            binding.layoutRole.error = null
                            viewModel.isValidRole.value = true
                        }
                        viewModel.nickName -> {
                            binding.layoutNickName.error = null
                            viewModel.isValidNickName.value = true
                        }
                    }
                }
            }
        }
    }

    private fun canBtnClick() {
        viewModel.isEnabledRegister.observe(this@RegisterActivity) {
            viewModel.checkBtnEnabled()
        }
    }

    private fun clickRegisterBtn() {
        binding.btnRegister.setOnClickListener {
            viewModel.isDateCycleSelected.value?.let { it1 ->
                viewModel.doProjectRegister(
                    POST_MEMBER_ID,
                    viewModel.projectName.value.toString(),
                    viewModel.projectExplanation.value.toString(),
                    viewModel.projectStartDate.value.toString(),
                    viewModel.role.value.toString(),
                    viewModel.nickName.value.toString(),
                    it1,
                )
            }
        }
    }

    private fun getProjectCode() {
        viewModel.registerResultBool.observe(this) {
            Log.d("projectName: ", "${viewModel.projectName.value}")
            Log.d("projectIntro: ", "${viewModel.projectExplanation.value}")
            Log.d("projectStartDate: ", "${viewModel.projectStartDate.value}")
            Log.d("role: ", "${viewModel.role.value}")
            Log.d("nickName: ", "${viewModel.nickName.value}")
            Log.d("DateCycle: ", "${viewModel.isDateCycleSelected.value}")
            showDialog("${viewModel.projectCode.value}")
            Log.d("projectCode: ", "${viewModel.projectCode.value}")
        }
    }

    private fun showDialog(code: String) {
        val registerDialog by lazy { ProjectCodeDialogFragment(code) }
        registerDialog.show(supportFragmentManager, TAG_REGISTER_DIALOG)
        Log.d("dialog: ", "dialog")
    }

    private fun clickBackBtn() {
        binding.btnToolbarClose.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }

    companion object {
        const val ERROR_MESSAGE = "특수문자, 이모지를 사용할 수 없어요"
        const val TAG_REGISTER_DIALOG = "Register dialog"
    }
}
