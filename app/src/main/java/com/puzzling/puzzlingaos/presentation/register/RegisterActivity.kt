package com.puzzling.puzzlingaos.presentation.register

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseActivity
import com.puzzling.puzzlingaos.databinding.ActivityRegisterBinding
import com.puzzling.puzzlingaos.util.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(R.layout.activity_register) {

    private val viewModel: RegisterViewModel by viewModels { ViewModelFactory(this) }

    private lateinit var dayCycleAdapter: RetrospectWeekCycleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dayCycleAdapter = RetrospectWeekCycleAdapter(viewModel)
        binding.viewModel = viewModel

        pickedDate()
        clickDayCyclePicker()
        getDay()
        textBoxListener(viewModel.projectName)
        textBoxListener(viewModel.projectExplanation)
        textBoxListener(viewModel.role)
        textBoxListener(viewModel.nickName)
        canBtnClick()
    }

    private fun pickedDate() {
        binding.btnDateDropDown.setOnClickListener {
            val bottomSheetFragment = DatePickerFragment()
            bottomSheetFragment.setOnDateSelectedListener(object :
                DatePickerFragment.OnDateSelectedListener {
                override fun onDateSelected(year: Int, month: Int, dayOfMonth: Int) {
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(year, month, dayOfMonth)
                    val formattedDateTextBox = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(selectedDate.time)
                    var formattedDateRegister = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(selectedDate.time)
                    binding.tvDateDropDown.text = formattedDateTextBox
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
    }

    private fun getDay() {
        viewModel.isDateCycleSelected.value = viewModel.dayArray
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

    companion object {
        const val ERROR_MESSAGE = "특수문자, 이모지를 사용할 수 없어요"
    }
}
