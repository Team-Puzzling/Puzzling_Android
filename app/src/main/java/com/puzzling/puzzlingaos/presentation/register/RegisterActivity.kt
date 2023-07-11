package com.puzzling.puzzlingaos.presentation.register

import android.os.Bundle
import androidx.activity.viewModels
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


        dayCycleAdapter = DayCycleAdapter(this)
        binding.viewModel = viewModel

        clickDatePicker()
        clickDayCyclePicker()
        isDateSelected()
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
                viewModel.projectStartDate = formattedDate
            }
        })

        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }

    private fun clickDayCyclePicker() {
        binding.rvDayCycle.adapter = DayCycleAdapter(this)
        binding.rvDayCycle.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        dayCycleAdapter.setOnDayClickListener { response ->

        }
    }

    private fun isDateSelected() {
    }
}
