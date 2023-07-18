package com.puzzling.puzzlingaos.presentation.team.currentSituation

import android.os.Bundle
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseActivity
import com.puzzling.puzzlingaos.databinding.ActivityMjTestBinding

class MjTestActivity : BaseActivity<ActivityMjTestBinding>(R.layout.activity_mj_test) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fcv_team_current, TeamCurrentSituationFragment())
            .commit()
    }
}
