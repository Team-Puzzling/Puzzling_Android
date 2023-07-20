package com.puzzling.puzzlingaos.presentation.home.team

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseFragment
import com.puzzling.puzzlingaos.databinding.FragmentTeamDashboardBinding
import com.puzzling.puzzlingaos.presentation.home.team.puzzleboard.OneTeamBoardActivity
import com.puzzling.puzzlingaos.presentation.home.team.puzzleboard.ThreeTeamBoardActivity
import com.puzzling.puzzlingaos.presentation.home.team.puzzleboard.TwoTeamBoardActivity
import com.puzzling.puzzlingaos.presentation.team.currentSituation.MjTestActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamDashboardFragment :
    BaseFragment<FragmentTeamDashboardBinding>(R.layout.fragment_team_dashboard) {
    private val viewModel by viewModels<TeamDashBoardViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        clickTeamPuzzleBoardBtn()
    }

    private fun clickTeamPuzzleBoardBtn() {
        // TODO 팀원 현황
        binding.clTeamMain1.setOnClickListener {
            activity?.let {
                val intent = Intent(context, MjTestActivity::class.java)
                startActivity(intent)
            }
            binding.clTeamTopBackground.setOnClickListener {
                Log.d(
                    "team",
                    "viewModel.puzzleBoardCount.value::: ${viewModel.teamPuzzleBoardCount.value}",
                )
                when (viewModel.teamPuzzleBoardCount.value) {
                    1 -> activity?.let {
                        val intent = Intent(context, OneTeamBoardActivity::class.java)
                        Log.d("team", "1")
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
