package com.puzzling.puzzlingaos.presentation.main.invitationCode

import android.os.Bundle
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseActivity
import com.puzzling.puzzlingaos.databinding.ActivityInvitationCodeBinding

class InvitationCodeActivity :
    BaseActivity<ActivityInvitationCodeBinding>(R.layout.activity_invitation_code) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fc_invitation)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fc_invitation, InputCodeFragment()).commit()
        }
    }
}
