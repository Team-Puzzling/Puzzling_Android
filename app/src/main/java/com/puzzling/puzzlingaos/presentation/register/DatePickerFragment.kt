package com.puzzling.puzzlingaos.presentation.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BottomSheetDialogFragment
import com.puzzling.puzzlingaos.databinding.FragmentDatePickerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DatePickerFragment : BottomSheetDialogFragment<FragmentDatePickerBinding>(R.layout.fragment_date_picker) {

    private val viewModel by viewModels<RegisterViewModel>()

    private lateinit var onDateSelectedListener: OnDateSelectedListener

    interface OnDateSelectedListener {
        fun onDateSelected(year: Int, month: Int, dayOfMonth: Int)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickDatePicker()
    }

    private fun clickDatePicker() {
        binding.btnDatePicker.setOnClickListener {
            sendDate()
            dialog?.dismiss()
        }
    }

    private fun sendDate() {
        val datePicker = binding.datePickerActions
        val year = datePicker.year
        val month = datePicker.month
        val dayOfMonth = datePicker.dayOfMonth
        onDateSelectedListener.onDateSelected(year, month, dayOfMonth)
        viewModel.projectStartDate.value = "$year-${month + 1}-$dayOfMonth"
    }

    fun setOnDateSelectedListener(listener: OnDateSelectedListener) {
        onDateSelectedListener = listener
    }
}
