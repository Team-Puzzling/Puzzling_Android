package com.puzzling.puzzlingaos.presentation.home.team

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseFragment
import com.puzzling.puzzlingaos.databinding.FragmentTeamDashboardBinding
import com.puzzling.puzzlingaos.presentation.home.HomeViewModel
import com.puzzling.puzzlingaos.presentation.home.team.puzzleboard.OneTeamBoardActivity
import com.puzzling.puzzlingaos.presentation.home.team.puzzleboard.ThreeTeamBoardActivity
import com.puzzling.puzzlingaos.presentation.home.team.puzzleboard.TwoTeamBoardActivity
import com.puzzling.puzzlingaos.presentation.team.currentSituation.TeamRetroActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamDashboardFragment :
    BaseFragment<FragmentTeamDashboardBinding>(R.layout.fragment_team_dashboard) {
    private val viewModel by activityViewModels<TeamDashBoardViewModel>()
    private val homeViewModel by activityViewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        clickTeamPuzzleBoardBtn()
        clickPuzzlePiece()
        observeProjectId()
        homeViewModel.selectedProjectId.observe(this) {
            viewModel.getTeamPuzzleData(it)
            viewModel.getTeamPuzzleBoard(it)
            viewModel.getTeamRanking(it)
        }
        setRankingText()
    }

    private fun setRankingText() {
        viewModel.memberPuzzleCount.observe(viewLifecycleOwner) {
            Log.d("memberPuzzleCount", "viewModel.memberPuzzleCount.value?.size:: ${viewModel.memberPuzzleCount.value?.size}")

            if (viewModel.memberPuzzleCount.value?.size == 1) {
                // tv_team_third_ranking_puzzle
                binding.tvTeamThirdRankingPuzzle.text = "?"
                binding.tvTeamSecondRankingPuzzle.text = "?"
            }
        }
    }

    private fun observeProjectId() {
        viewModel.firstProjectId.observe(this) {
            Log.d("team", "firstProjectId:: $it")
            viewModel.getTeamPuzzleData(it)
            viewModel.getTeamPuzzleBoard(it)
            viewModel.getTeamRanking(it)
        }
    }

    private fun clickPuzzlePiece() {
        val teamLayouts = listOf(
            binding.clTeamMain1,
            binding.clTeamMain2,
            binding.clTeamMain3,
            binding.clTeamMain4,
            binding.clTeamMain5,
            binding.clTeamMain6,
            binding.clTeamMain7,
            binding.clTeamMain8,
            binding.clTeamMain9,
            binding.clTeamMain10,
            binding.clTeamMain11,
            binding.clTeamMain12,
            binding.clTeamMain13,
            binding.clTeamMain14,
            binding.clTeamMain15,
        )
        teamLayouts.forEach { layout ->
            layout.setOnClickListener {
                activity?.let {
                    val intent = Intent(context, TeamRetroActivity::class.java)
                    intent.putExtra("Title", viewModel.myNickname.value)
                    startActivity(intent)
                }
            }
        }
    }

    private fun clickTeamPuzzleBoardBtn() {
        // 팀 퍼즐판 보러가기
        binding.clTeamTopBackground.setOnClickListener {
            binding.clTeamTopBackground.setOnClickListener {
                Log.d(
                    "team",
                    "viewModel.puzzleBoardCount.value::: ${viewModel.teamPuzzleBoardCount.value}",
                )
                when (viewModel.teamPuzzleBoardCount.value) {
                    1 -> activity?.let {
                        val intent = Intent(context, OneTeamBoardActivity::class.java)
                        startActivity(intent)
                    }
                    2 -> activity?.let {
                        val intent = Intent(context, TwoTeamBoardActivity::class.java)
                        Log.d("team", "2")
                        startActivity(intent)
                    }
                    3 -> activity?.let {
                        val intent = Intent(context, ThreeTeamBoardActivity::class.java)
                        Log.d("team", "3")
                        startActivity(intent)
                    }
                }
            }
        }
    }
}
