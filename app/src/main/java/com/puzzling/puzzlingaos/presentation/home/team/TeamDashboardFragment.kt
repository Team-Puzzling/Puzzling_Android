package com.puzzling.puzzlingaos.presentation.home.team

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseFragment
import com.puzzling.puzzlingaos.databinding.FragmentTeamDashboardBinding
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
            activity?.let {
                val intent = Intent(context, TeamPuzzleBoardActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
