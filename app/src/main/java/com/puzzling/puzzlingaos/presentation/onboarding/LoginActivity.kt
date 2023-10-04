package com.puzzling.puzzlingaos.presentation.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseActivity
import com.puzzling.puzzlingaos.data.service.KakaoAuthService
import com.puzzling.puzzlingaos.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    @Inject
    lateinit var kakakoAuthService: KakaoAuthService

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKakaoLogin()
        isKakaoLoginSuccess()
    }

    private fun startKakaoLogin() {
        binding.ibLoginKakao.setOnClickListener {
            kakakoAuthService.startKakaoLogin(viewModel.kakaoLoginCallback)
        }
    }

    private fun isKakaoLoginSuccess() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isKakaoLogin.collect { isLoginSuccess ->
                    if (isLoginSuccess) {
                        val intent =
                            Intent(this@LoginActivity, ChooseJoinRegisterActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        finish()
                        delay(100)
                        viewModel.login("KAKAO")
                    }
                }
            }
        }
    }
}
