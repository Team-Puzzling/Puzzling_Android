package com.puzzling.puzzlingaos.presentation.home.personal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseFragment
import com.puzzling.puzzlingaos.databinding.FragmentPersonalDashboardBinding
import com.puzzling.puzzlingaos.presentation.detailRetrospect.DetailRetroActivity
import com.puzzling.puzzlingaos.presentation.home.HomeViewModel
import com.puzzling.puzzlingaos.presentation.home.personal.puzzleboard.OnePuzzleBoardActivity
import com.puzzling.puzzlingaos.presentation.home.personal.puzzleboard.ThreePuzzleBoardActivity
import com.puzzling.puzzlingaos.presentation.home.personal.puzzleboard.TwoPuzzleBoardActivity
import com.puzzling.puzzlingaos.presentation.home.team.TeamDashBoardViewModel
import com.puzzling.puzzlingaos.presentation.team.currentSituation.TeamRetroActivity
import com.puzzling.puzzlingaos.presentation.writeRetrospective.WriteRetrospectiveActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonalDashboardFragment :
    BaseFragment<FragmentPersonalDashboardBinding>(R.layout.fragment_personal_dashboard) {
    private val viewModel by activityViewModels<PersonalDashboardViewModel>()
    private val teamViewModel by activityViewModels<TeamDashBoardViewModel>()
    private val homeViewModel by activityViewModels<HomeViewModel>()

    private var _actionPlanAdapter: ActionPlanListAdapter? = null
    private val actionPlanAdapter
        get() = requireNotNull(_actionPlanAdapter) { "adapter is null !!" }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        setActionPlanAdapter()
        clickBottomBtn()
        clickMyPuzzleBoardBtn()
        clickPuzzlePiece()
        setBottomBtnBackgroundColor()
        observeProjectId()
        homeViewModel.selectedProjectId.observe(this) {
            viewModel.getMyPuzzleData(it)
            viewModel.getMyPuzzleBoard(it)
        }
        setVisibleAdapter()
    }

    private fun observeProjectId() {
        viewModel.firstProjectId.observe(this) {
            Log.d("personal", "firstProjectId:: $it")
            viewModel.getMyPuzzleData(it)
            viewModel.getMyPuzzleBoard(it)
            viewModel.getActionPlan(it)
            viewModel.getPreviousTemplate(it)
        }
    }

//    private fun intentToWriteActivity() {
//        val intent = Intent(requireActivity(), WriteRetrospectiveActivity::class.java)
//        viewModel.firstProjectId.observe(this) {
//            Log.d("개인", "$it")
//            intent.putExtra("homeProjectId", it)
////            startActivity(intent)
//        }
//    }

    private fun putExtraProjectId() {
        activity?.let {
            val intent = Intent(context, TeamRetroActivity::class.java)
            intent.putExtra("Team", viewModel.myNickname.value)
            startActivity(intent)
        }
    }

    private fun setActionPlanAdapter() {
        _actionPlanAdapter = ActionPlanListAdapter()
//        _actionPlanAdapter?.submitList(viewModel.actionPlanList.value)
        viewModel.actionPlanList.observe(this) {
            binding.rcvPersonalView.adapter = _actionPlanAdapter
            _actionPlanAdapter!!.submitList(it)
        }
    }

    private fun clickBottomBtn() {
        binding.clPersonalBottomBtn.setOnClickListener {
            viewModel.firstProjectId.observe(this) {
                val intent = Intent(activity, WriteRetrospectiveActivity::class.java)
                intent.putExtra("homeProjectId", it)
                Log.d("homeProjectId", "$it")
                startActivity(intent)
            }
//            val intent = Intent(context, WriteRetrospectiveActivity::class.java)
//            intent.putExtra("Title", teamViewModel.myNickname.value)
//            startActivity(intent)
        }
    }

    private fun clickMyPuzzleBoardBtn() {
        binding.clPersonalTopBackground.setOnClickListener {
            Log.d(
                "personal",
                "viewModel.puzzleBoardCount.value::: ${viewModel.puzzleBoardCount.value}",
            )
            when (viewModel.puzzleBoardCount.value) {
                1 -> activity?.let {
                    val intent = Intent(context, OnePuzzleBoardActivity::class.java)
                    startActivity(intent)
                }
                2 -> activity?.let {
                    val intent = Intent(context, TwoPuzzleBoardActivity::class.java)
                    startActivity(intent)
                }
                3 -> activity?.let {
                    val intent = Intent(context, ThreePuzzleBoardActivity::class.java)
                    startActivity(intent)
                }
            }
            //            activity?.let {
            //                val intent = Intent(context, MyPuzzleBoardActivity::class.java)
            //                startActivity(intent)
            //            }
        }
    }

    private fun clickPuzzlePiece() {
        Log.d("personal", "review ID:::: ${viewModel.myReviewId.value}")
        // TODO 각 퍼즐 조각 클릭 -> 각 날짜의 회고 상세조회로 넘어가는 로직 추가
        val teamLayouts = listOf(
            binding.clPersonalMain1,
            binding.clPersonalMain2,
            binding.clPersonalMain3,
            binding.clPersonalMain4,
            binding.clPersonalMain5,
            binding.clPersonalMain6,
            binding.clPersonalMain7,
            binding.clPersonalMain8,
            binding.clPersonalMain9,
            binding.clPersonalMain10,
            binding.clPersonalMain11,
            binding.clPersonalMain12,
            binding.clPersonalMain13,
            binding.clPersonalMain14,
            binding.clPersonalMain15,
        )
        teamLayouts.forEach { layout ->
            layout.setOnClickListener {
                teamViewModel.myNickname.observe(this) {
                    val intent = Intent(context, DetailRetroActivity::class.java)
//                    intent.putExtra("projectId", )
                    intent.putExtra("Title", teamViewModel.myNickname.value)
                    startActivity(intent)
                }
            }
        }
    }

    private fun setBottomBtnBackgroundColor() {
        viewModel.isReviewDay.observe(this) {
            if (it == true) { // 회고하는날
                if (viewModel.hasTodayReview.value == true) {
                    binding.tvPersonalBottomTitle.text = "회고 작성하기"
                    binding.clPersonalBottomBtn.setBackgroundResource(R.drawable.rect_gray400_fill_16)
                    binding.clPersonalBottomBtn.isClickable = true
                } else {
                    binding.tvPersonalBottomTitle.text = "회고 작성하기"
                    binding.clPersonalBottomBtn.setBackgroundResource(R.drawable.rect_blue400_fill_radius_16)
                    binding.clPersonalBottomBtn.isClickable = true
                }
            } else {
                binding.tvPersonalBottomTitle.text = "회고 작성일이 아니에요"
                binding.clPersonalBottomBtn.setBackgroundResource(R.drawable.rect_gray400_fill_16)
                binding.clPersonalBottomBtn.isClickable = true
            }
        }
    }

    private fun setVisibleAdapter() {
        viewModel.actionPlanList.observe(this) {
            if (it != null && it.isEmpty()) {
                binding.clActionplanNone.visibility = View.VISIBLE
                binding.rcvPersonalView.visibility = View.INVISIBLE
            } else {
                binding.rcvPersonalView.visibility = View.VISIBLE
                binding.clActionplanNone.visibility = View.INVISIBLE
            }
        }
    }
}
