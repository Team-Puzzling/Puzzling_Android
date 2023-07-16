package com.puzzling.puzzlingaos.presentation.writeRetrospective

import android.os.Bundle
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
        }
    }

    // 선택한 아이템을 액티비티로 넘긴다. ( 뷰모뎀로 넘겨주면 될듯?)
    // 그럼 액티비티는 프래그먼트를 교체해야겠지?
}
