package com.puzzling.puzzlingaos.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
//            modelClass.isAssignableFrom(InvitationCodeViewModel::class.java) -> {
//                InvitationCodeViewModel(context) as T
//            }

//            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
//                val repository = KakaoAuthService(context)
//                LoginViewModel(repository) as T
//            }

            else -> {
                throw java.lang.IllegalArgumentException("Unknown ViewModel")
            }
        }
    }
}
