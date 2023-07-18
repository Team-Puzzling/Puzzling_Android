package com.puzzling.puzzlingaos.presentation.home.team

import android.os.Bundle
import androidx.activity.viewModels
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseActivity
import com.puzzling.puzzlingaos.databinding.ActivityTeamPuzzleBoardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamPuzzleBoardActivity :
    BaseActivity<ActivityTeamPuzzleBoardBinding>(R.layout.activity_team_puzzle_board) {
    private val viewModel by viewModels<TeamPuzzleBoardViewModel>()
    private var _teamPuzzleBoardAdapter: TeamPuzzleBoardAdapter? = null
    private val teamPuzzleBoardAdapter
        get() = requireNotNull(_teamPuzzleBoardAdapter) { "adapter is null !!" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTeamPuzzleBoardAdapter()
    }

    private fun setTeamPuzzleBoardAdapter() {
        _teamPuzzleBoardAdapter = TeamPuzzleBoardAdapter()
        _teamPuzzleBoardAdapter?.submitList(viewModel.puzzleBoardList)
        binding.rcvTeampuzzleBoard.also {
            it.adapter = _teamPuzzleBoardAdapter
        }
    }
}
