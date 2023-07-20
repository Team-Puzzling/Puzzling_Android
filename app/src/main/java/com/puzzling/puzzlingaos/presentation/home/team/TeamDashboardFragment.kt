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
                    startActivity(intent)
                }
                3 -> activity?.let {
                    val intent = Intent(context, ThreeTeamBoardActivity::class.java)
                    startActivity(intent)
                }
            }
//            activity?.let {
//                val intent = Intent(context, TeamPuzzleBoardActivity::class.java)
//                startActivity(intent)
//            }
        }
    }
}
