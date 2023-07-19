package com.puzzling.puzzlingaos.presentation.team.currentSituation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseFragment
import com.puzzling.puzzlingaos.databinding.FragmentItemTeamRetrospectListBinding
import com.puzzling.puzzlingaos.domain.entity.TeamRetrospectList
import com.puzzling.puzzlingaos.domain.entity.TeamRetrospectMultiList
import com.puzzling.puzzlingaos.util.DividerItemDecoration
import com.puzzling.puzzlingaos.util.ViewModelFactory

class ItemTeamRetrospectListFragment(
    private val dayPosition: Int,
) : BaseFragment<FragmentItemTeamRetrospectListBinding>(R.layout.fragment_item_team_retrospect_list) {

    private lateinit var viewModel: TeamCurrentSituationViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory(requireContext()),
        )[TeamCurrentSituationViewModel::class.java]

        val day = viewModel.week[dayPosition]

        viewModel.getTeamRetrospectList()

        val retroItem = viewModel.teamRetrospectList.value

        binding.rcvTeamRetrospectList.addItemDecoration(
            DividerItemDecoration(R.color.gray_300, 32),
        )

        if (retroItem != null) {
            for (item in retroItem) {
                if (day == item.reviewDay) {
                    testItemList(viewModel.teamRetrospectList, day)
                }
            }
        }
        if (retroItem != null) {
            for (item in retroItem) {
                if (day == item.reviewDay) {
                    binding.rcvTeamRetrospectList.adapter = viewModel.teamRetrospectMultiList.value?.let {
                        RetrospectListAdapter(
                            it,
                        )
                    }
                }
            }
        }
    }

    fun testItemList(itemList: LiveData<ArrayList<TeamRetrospectList>>, day: String) {
        var intDay: Int = viewModel.itemRetroList.indexOfFirst { it.reviewDay == day }
        // var intDay: Int = viewModel.teamRetrospectList.indexOfFirst { it.reviewDay == day }

        viewModel.teamRetrospectMultiList.value?.clear()

        if (itemList.value?.isNotEmpty() == true) {
            if (itemList.value?.get(intDay)?.reviewWriters?.isNotEmpty() == true) {
                viewModel.teamRetrospectMultiList.value?.add(
                    TeamRetrospectMultiList(
                        0,
                        null,
                        null,
                        null,
                        null,
                    ),
                )
                for (i in 0 until (itemList.value?.get(intDay)?.reviewWriters!!.size ?: 0)) {
                    viewModel.teamRetrospectMultiList.value?.add(
                        TeamRetrospectMultiList(
                            1,
                            itemList.value?.get(intDay)?.reviewDay,
                            itemList.value?.get(intDay)?.reviewDate,
                            itemList.value?.get(intDay)?.reviewWriters?.get(i)?.memberNickname,
                            itemList.value?.get(intDay)?.reviewWriters?.get(i)?.memberRole,
                        ),
                    )
                }
            }

            if (itemList.value?.get(intDay)?.nonReviewWriters?.isNotEmpty() == true) {
                viewModel.teamRetrospectMultiList.value?.add(
                    TeamRetrospectMultiList(
                        2,
                        null,
                        null,
                        null,
                        null,
                    ),
                )
                for (i: Int in 0 until (itemList.value?.get(intDay)?.nonReviewWriters?.size ?: 0)) {
                    viewModel.teamRetrospectMultiList.value?.add(
                        TeamRetrospectMultiList(
                            3,
                            itemList.value?.get(intDay)?.reviewDay,
                            itemList.value?.get(intDay)?.reviewDate,
                            itemList.value?.get(intDay)?.nonReviewWriters?.get(i)?.memberNickname,
                            itemList.value?.get(intDay)?.nonReviewWriters?.get(i)?.memberRole,
                        ),
                    )
                }
            }
        }
    }
}
