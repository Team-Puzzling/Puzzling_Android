package com.puzzling.puzzlingaos.presentation.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseActivity
import com.puzzling.puzzlingaos.databinding.ActivityChooseJoinRegisterBinding
import com.puzzling.puzzlingaos.presentation.invitationCode.InvitationCodeActivity
import com.puzzling.puzzlingaos.presentation.register.RegisterActivity

class ChooseJoinRegisterActivity :
    BaseActivity<ActivityChooseJoinRegisterBinding>(R.layout.activity_choose_join_register) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clickRegisterProject()
        clickJoinProject()
    }

    private fun clickJoinProject() {
        binding.linearOnBoardingJoin.setOnClickListener {
            val intent = Intent(this, InvitationCodeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }

    private fun clickRegisterProject() {
        binding.linearOnBoardingRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }
}
