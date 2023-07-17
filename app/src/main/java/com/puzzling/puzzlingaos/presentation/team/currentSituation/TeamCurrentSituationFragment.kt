package com.puzzling.puzzlingaos.presentation.team.currentSituation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseFragment
import com.puzzling.puzzlingaos.databinding.FragmentTeamCurrentSituationBinding
import com.puzzling.puzzlingaos.domain.entity.TeamRetrospectList
import com.puzzling.puzzlingaos.domain.entity.TeamRetrospectMultiList
import com.puzzling.puzzlingaos.util.ViewModelFactory

class TeamCurrentSituationFragment : BaseFragment<FragmentTeamCurrentSituationBinding>(R.layout.fragment_team_current_situation) {

    private val viewModel: TeamCurrentSituationViewModel by viewModels { ViewModelFactory(requireContext()) }
    private lateinit var retrospectThisWeekAdapter: RetrospectThisWeekAdapter
    private lateinit var retrospectListAdapter: RetrospectListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        retrospectThisWeekAdapter = RetrospectThisWeekAdapter(viewModel)
        retrospectListAdapter = RetrospectListAdapter(viewModel)
        binding.viewModel = viewModel

        // makeRetrospectThisWeekAdapter()
        // makeRetrospectListAdapter()
    }

//    private fun makeRetrospectListAdapter() {
//        val itemList = mutableListOf<TeamRetrospectList>(
//            TeamRetrospectList(
//                "일요일",
//                "2023-07-16",
//                arrayListOf(TeamRetrospectList.ReviewWriterList("박솝트", "ios"), TeamRetrospectList.ReviewWriterList("김솝트", "서버"), TeamRetrospectList.ReviewWriterList("한솝트", "서버")),
//                arrayListOf(TeamRetrospectList.NonReviewWriterList("한솝트", "디자인"), TeamRetrospectList.NonReviewWriterList("이솝트", "안드로이드")),
//            ),
//            TeamRetrospectList(
//                "일요일",
//                "2023-07-16",
//                null,
//                arrayListOf(TeamRetrospectList.NonReviewWriterList("한솝트", "디자인"), TeamRetrospectList.NonReviewWriterList("이솝트", "안드로이드")),
//            ),
//            TeamRetrospectList(
//                "일요일",
//                "2023-07-16",
//                arrayListOf(TeamRetrospectList.ReviewWriterList("박솝트", "ios"), TeamRetrospectList.ReviewWriterList("김솝트", "서버"), TeamRetrospectList.ReviewWriterList("한솝트", "서버")),
//                null,
//            ),
//        )
//
//        setItemList(itemList)
//
//        binding.rcvTeamRetrospectList.adapter = retrospectListAdapter
//    }

//    @SuppressLint("NotifyDataSetChanged")
//    fun setItemList(itemList: MutableList<TeamRetrospectList>) {
//        retrospectListAdapter.adapterItemList.clear()
//
//        if (itemList.isNotEmpty()) {
//            if (itemList[0].reviewWriters?.isNotEmpty() == true) {
//                retrospectListAdapter.adapterItemList.add(TeamRetrospectMultiList(0, null, null))
//                for (i: Int in 0 until (itemList[0].reviewWriters?.size ?: 0)) {
//                    retrospectListAdapter.adapterItemList.add(
//                        TeamRetrospectMultiList(
//                            1,
//                            itemList[0].reviewWriters?.get(i)?.memberNickname,
//                            itemList[0].reviewWriters?.get(i)?.memberRole,
//                        ),
//                    )
//                }
//            }
//
//            if (itemList[0].nonReviewWriters?.isNotEmpty() == true) {
//                retrospectListAdapter.adapterItemList.add(TeamRetrospectMultiList(2, null, null))
//                for (i: Int in 0 until (itemList[0].nonReviewWriters?.size ?: 0)) {
//                    retrospectListAdapter.adapterItemList.add(
//                        TeamRetrospectMultiList(
//                            3,
//                            itemList[0].nonReviewWriters?.get(i)?.memberNickname,
//                            itemList[0].nonReviewWriters?.get(i)?.memberRole,
//                        ),
//                    )
//                }
//            }
//
//            retrospectListAdapter.notifyDataSetChanged()
//        }
//    }

//    private fun makeRetrospectThisWeekAdapter() {
//        binding.rcvTeamThisWeek.adapter = RetrospectThisWeekAdapter(viewModel)
//    }
}
