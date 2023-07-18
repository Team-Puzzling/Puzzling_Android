package com.puzzling.puzzlingaos.presentation.onboarding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.kakao.sdk.common.util.Utility
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseActivity
import com.puzzling.puzzlingaos.databinding.ActivityLoginBinding
import com.puzzling.puzzlingaos.util.ViewModelFactory
import kotlinx.coroutines.launch

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val viewModel: LoginViewModel by viewModels { ViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKakaoLogin()
        isKakaoLoginSuccess()
    }

    private fun startKakaoLogin() {
        binding.ibLoginKakao.setOnClickListener {
            viewModel.kakaoLogin()
        }
    }

    private fun isKakaoLoginSuccess() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.isKakaoLogin.collect { isLoginSuccess ->
                    if (isLoginSuccess) {
                        val intent =
                            Intent(this@LoginActivity, ChooseJoinRegisterActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }
    }
}
