package com.puzzling.puzzlingaos.presentation.invitationCode

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseActivity
import com.puzzling.puzzlingaos.databinding.ActivityInvitationCodeBinding

class InvitationCodeActivity :
    BaseActivity<ActivityInvitationCodeBinding>(R.layout.activity_invitation_code) {

    private val viewModel by viewModels<InvitationCodeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initFragment()

        binding.vm = viewModel
        binding.lifecycleOwner = this@InvitationCodeActivity
    }

    private fun initFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_invitation_main)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_invitation_main, InputCodeFragment()).commit()
        }
    }
}
