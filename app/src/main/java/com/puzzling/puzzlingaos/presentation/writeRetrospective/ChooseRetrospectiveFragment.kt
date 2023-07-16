package com.puzzling.puzzlingaos.presentation.writeRetrospective

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BottomSheetDialogFragment
import com.puzzling.puzzlingaos.databinding.FragmentChooseRetrospectiveBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChooseRetrospectiveFragment :
    BottomSheetDialogFragment<FragmentChooseRetrospectiveBinding>(R.layout.fragment_choose_retrospective) {
    private val viewModel by activityViewModels<WriteRetrospectiveViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickRadioButton()
    }

    private fun clickReviewItem(reviewType: String) {
        viewModel.setSelectedReviewTypeText(reviewType)
        viewLifecycleOwner.lifecycleScope.launch {
            delay(100)
            dismiss()
        }
    }

    private fun clickRadioButton() {
        binding.rgChooseMain.setOnCheckedChangeListener { group, checkedId ->
            val selectedReviewType = when (checkedId) {
                R.id.rbtn_choose_first -> "TIL"
                R.id.rbtn_choose_second -> "5F"
                R.id.rbtn_choose_third -> "AAR"
                else -> "TIL"
            }
            clickReviewItem(selectedReviewType)
            Log.d(
                "write",
                "selectedReviewType :: $selectedReviewType",
            )
        }
    }
}
