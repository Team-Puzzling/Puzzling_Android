package com.puzzling.puzzlingaos.presentation.writeRetrospective

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BottomSheetDialogFragment
import com.puzzling.puzzlingaos.databinding.FragmentChooseRetrospectiveBinding

class ChooseRetrospectiveFragment :
    BottomSheetDialogFragment<FragmentChooseRetrospectiveBinding>(R.layout.fragment_choose_retrospective) {
    private val viewModel by activityViewModels<WriteRetrospectiveViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickItemButton()
    }

    private fun clickItemButton() {
        with(binding) {
            clChooseFirst.setOnClickListener {
                clickReviewItem("TIL")
            }
            clChooseSecond.setOnClickListener {
                clickReviewItem("5F")
            }
            clChooseThird.setOnClickListener {
                clickReviewItem("AAR")
            }
        }
    }

    private fun clickReviewItem(reviewType: String) {
        viewModel.setSelectedReviewTypeText(reviewType)
        dismiss()
    }
}
