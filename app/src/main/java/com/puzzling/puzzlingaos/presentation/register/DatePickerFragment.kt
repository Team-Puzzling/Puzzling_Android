package com.puzzling.puzzlingaos.presentation.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.puzzling.puzzlingaos.databinding.FragmentDatePickerBinding
import com.puzzling.puzzlingaos.util.ViewModelFactory

class DatePickerFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentDatePickerBinding? = null
    private val binding: FragmentDatePickerBinding
        get() = requireNotNull(_binding) { "앗! binding이 NUll이 아니다" }

    private val viewModel: RegisterViewModel by viewModels { ViewModelFactory(requireActivity()) }

    private lateinit var onDateSelectedListener: OnDateSelectedListener

    interface OnDateSelectedListener {
        fun onDateSelected(year: Int, month: Int, dayOfMonth: Int)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDatePickerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickButton()
    }

    private fun clickButton() {
        binding.btDatePicker.setOnClickListener {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
