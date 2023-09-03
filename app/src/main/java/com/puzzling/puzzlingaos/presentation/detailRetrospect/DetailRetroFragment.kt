package com.puzzling.puzzlingaos.presentation.detailRetrospect

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
// import androidx.recyclerview.widget.DividerItemDecoration
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseFragment
import com.puzzling.puzzlingaos.databinding.FragmentDetailRetroBinding
import com.puzzling.puzzlingaos.util.DividerItemDecoration
import com.puzzling.puzzlingaos.util.ViewModelFactory

class DetailRetroFragment(
    private val position: Int,
) :
    BaseFragment<FragmentDetailRetroBinding>(R.layout.fragment_detail_retro) {

    private val viewModel by activityViewModels<DetailRetroViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val day = viewModel.week[position]
        val dataList = viewModel.detailRetroList.value

        val detailRetroAdapter = DetailRetroAdapter()
        // binding.rcvDetailRetroMain.addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        binding.rcvDetailRetroMain.addItemDecoration(
            com.puzzling.puzzlingaos.util.DividerItemDecoration(
                R.color.gray_300,
                32,
            ),
        )

        if (dataList != null) {
            for (data in dataList) {
                if (day == data.reviewDay) {
                    binding.rcvDetailRetroMain.adapter = detailRetroAdapter
                    detailRetroAdapter.submitList(data.content)
                }
            }
        }
    }
}
