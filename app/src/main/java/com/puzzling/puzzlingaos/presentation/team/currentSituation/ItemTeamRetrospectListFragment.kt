package com.puzzling.puzzlingaos.presentation.team.currentSituation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseFragment
import com.puzzling.puzzlingaos.databinding.FragmentItemTeamRetrospectListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemTeamRetrospectListFragment(
    private val dayPosition: Int,
) : BaseFragment<FragmentItemTeamRetrospectListBinding>(R.layout.fragment_item_team_retrospect_list) {

    private lateinit var viewModel: TeamRetroViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            requireActivity(),
        )[TeamRetroViewModel::class.java]

        val day = viewModel.week[dayPosition]

        val retroItem = viewModel.teamRetrospectList.value

        if (retroItem != null) {
            for (item in retroItem) {
                if (day == item.reviewDay) {
                    viewModel.testItemList(viewModel.teamRetrospectList, day)
                    binding.rcvTeamRetrospectList.adapter = viewModel.teamRetrospectMultiList.value?.let {
                        RetrospectListAdapter(
                            it,
                        )
                    }
                }
            }
        }
    }
}
