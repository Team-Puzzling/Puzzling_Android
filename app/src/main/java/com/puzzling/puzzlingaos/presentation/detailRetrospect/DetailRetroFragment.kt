package com.puzzling.puzzlingaos.presentation.detailRetrospect

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseFragment
import com.puzzling.puzzlingaos.databinding.FragmentDetailRetroBinding
import com.puzzling.puzzlingaos.util.ViewModelFactory

class DetailRetroFragment(
    private val position: Int,
) :
    BaseFragment<FragmentDetailRetroBinding>(R.layout.fragment_detail_retro) {

    private lateinit var viewModel: DetailRetroViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory(requireContext()),
        )[DetailRetroViewModel::class.java]

        val day = viewModel.week[position]
        val dataList = viewModel.detailRetroList.value

        if (dataList != null) {
            for (data in dataList) {
                if (day == data.reviewDay) {
                    binding.tvTest.text = data?.contents?.get(1)?.title
                }
            }
        }
    }
}
