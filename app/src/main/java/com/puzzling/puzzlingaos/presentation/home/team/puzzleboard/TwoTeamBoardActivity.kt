package com.puzzling.puzzlingaos.presentation.home.team.puzzleboard

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseActivity
import com.puzzling.puzzlingaos.databinding.ActivityTwoTeamBoardBinding
import com.puzzling.puzzlingaos.presentation.home.team.TeamDashBoardViewModel
import com.puzzling.puzzlingaos.presentation.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TwoTeamBoardActivity :
    BaseActivity<ActivityTwoTeamBoardBinding>(R.layout.activity_two_team_board) {
    private val viewModel by viewModels<TeamDashBoardViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clickBackBtn()
    }

    private fun clickBackBtn() {
        binding.btnWriteBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("homeProjectId", viewModel.firstProjectId.value)
            startActivity(intent)
        }
    }
}
