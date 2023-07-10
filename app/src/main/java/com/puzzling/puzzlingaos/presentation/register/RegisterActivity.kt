package com.puzzling.puzzlingaos.presentation.register

import android.os.Bundle
import androidx.activity.viewModels
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseActivity
import com.puzzling.puzzlingaos.databinding.ActivityRegisterBinding
import com.puzzling.puzzlingaos.util.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(R.layout.activity_register) {

    private val viewModel: RegisterViewModel by viewModels { ViewModelFactory(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel

        clickDatePicker()
        isDateSeleceted()
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
                val formattedDate = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(selectedDate.time)
                binding.tvDateDropDown.text = formattedDate

            }
        })

        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }

    private fun isDateSeleceted() {
    }
}
