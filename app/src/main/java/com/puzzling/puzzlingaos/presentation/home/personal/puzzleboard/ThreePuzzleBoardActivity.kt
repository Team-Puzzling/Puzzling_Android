package com.puzzling.puzzlingaos.presentation.home.personal.puzzleboard

import android.content.Intent
import android.os.Bundle
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseActivity
import com.puzzling.puzzlingaos.databinding.ActivityThreePuzzleBoardBinding
import com.puzzling.puzzlingaos.presentation.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThreePuzzleBoardActivity :
    BaseActivity<ActivityThreePuzzleBoardBinding>(R.layout.activity_three_puzzle_board) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clickBackBtn()
    }

    private fun clickBackBtn() {
        binding.btnWriteBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
