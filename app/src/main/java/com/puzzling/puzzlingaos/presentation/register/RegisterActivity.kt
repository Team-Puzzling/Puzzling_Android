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

    private lateinit var dayCycleAdapter: DayCycleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dayCycleAdapter = DayCycleAdapter(viewModel)
        binding.viewModel = viewModel

        clickDatePicker()
        clickDayCyclePicker()
        getDay()
        textBoxListener(viewModel.projectName)
        textBoxListener(viewModel.projectExplanation)
        textBoxListener(viewModel.role)
        textBoxListener(viewModel.nickName)
        canBtnClick()
    }

    private fun clickDatePicker() {
        binding.btnDateDropDown.setOnClickListener {
            pickedDate()
        }
    }

    private fun pickedDate() {
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

    private fun clickDayCyclePicker() {
        binding.rvDayCycle.adapter = DayCycleAdapter(viewModel)
        binding.rvDayCycle.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        dayCycleAdapter.setOnDayClickListener { response ->
            viewModel.dayArray = dayCycleAdapter.selectedDayArray
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
                            binding.textLayoutProjectName.error = "특수문자, 이모지를 사용할 수 없어요"
                            viewModel.isValidProjectName.value = false }
                        viewModel.projectExplanation -> {
                            binding.textLayoutIntroduction.error = "특수문자, 이모지를 사용할 수 없어요"
                            viewModel.isValidProjectExplanation.value = false }
                        viewModel.role -> {
                            binding.textLayoutRole.error = "특수문자, 이모지를 사용할 수 없어요"
                            viewModel.isValidRole.value = false }
                        viewModel.nickName -> {
                            binding.textLayoutNickName.error = "특수문자, 이모지를 사용할 수 없어요"
                            viewModel.isValidNickName.value = false }
                    }
                } else {
                    when (textBox) {
                        viewModel.projectName -> {
                            binding.textLayoutProjectName.error = null
                            viewModel.isValidProjectName.value = true
                        }
                        viewModel.projectExplanation -> {
                            binding.textLayoutIntroduction.error = null
                            viewModel.isValidProjectExplanation.value = true
                        }
                        viewModel.role -> {
                            binding.textLayoutRole.error = null
                            viewModel.isValidRole.value = true
                        }
                        viewModel.nickName -> {
                            binding.textLayoutNickName.error = null
                            viewModel.isValidNickName.value = true
                        }
                    }
                }
            }
        }
    }

    private fun canBtnClick() {
        viewModel.isEnabledRegister.observe(this@RegisterActivity) {
            viewModel.let { viewModel ->
                viewModel.isBtnEnabled.value = viewModel.isValid()
            }
        }
    }
}