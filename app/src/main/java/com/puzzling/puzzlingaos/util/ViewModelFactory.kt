package com.puzzling.puzzlingaos.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.puzzling.puzzlingaos.data.service.KakaoLoginService
import com.puzzling.puzzlingaos.presentation.detailRetrospect.DetailRetroViewModel
import com.puzzling.puzzlingaos.presentation.invitationCode.InvitationCodeViewModel
import com.puzzling.puzzlingaos.presentation.onboarding.LoginViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(InvitationCodeViewModel::class.java) -> {
                InvitationCodeViewModel(context) as T
            }

            modelClass.isAssignableFrom(DetailRetroViewModel::class.java) -> {
                DetailRetroViewModel(context) as T
            }

            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                val repository = KakaoLoginService(context)
                LoginViewModel(repository) as T
            }

            else -> {
                throw java.lang.IllegalArgumentException("Unknown ViewModel")
            }
        }
    }
}
