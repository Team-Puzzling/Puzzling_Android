package com.puzzling.puzzlingaos.presentation.onboarding

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.model.OAuthToken
import com.puzzling.puzzlingaos.data.datasource.local.LocalDataSource
import com.puzzling.puzzlingaos.domain.repository.AuthRepository
import com.puzzling.puzzlingaos.domain.usecase.onboarding.GetLocalTokenUseCase
import com.puzzling.puzzlingaos.domain.usecase.onboarding.GetRemoteTokenUseCase
import com.puzzling.puzzlingaos.domain.usecase.onboarding.GetUserUseCase
import com.puzzling.puzzlingaos.domain.usecase.onboarding.PostLocalTokenUseCase
import com.puzzling.puzzlingaos.util.KakaoLoginCallback
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val postLocalTokenUseCase: PostLocalTokenUseCase,
    private val getLocalTokenUseCase: GetLocalTokenUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val getRemoteTokenUseCase: GetRemoteTokenUseCase,
    private val authRepository: AuthRepository,
) :
    ViewModel() {
    private val _isKakaoLogin = MutableStateFlow(false)
    val isKakaoLogin = _isKakaoLogin.asStateFlow()

    private val _isAlreadyLogin = MutableLiveData<Boolean>()
    val isAlreadyLogin: LiveData<Boolean> get() = _isAlreadyLogin

    val kakaoLoginCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        KakaoLoginCallback {
            _isKakaoLogin.value = true
            Log.d("LoginViewModel", "카카오 로그인 set 토큰 $token")
            LocalDataSource.setAccessToken("$token")
            viewModelScope.launch {
                postLocalTokenUseCase.invoke(it)
                Log.d("LoginViewModel", "카카오 로그인 get 토큰 ${getLocalTokenUseCase.invoke()}")
            }
        }.handleResult(token, error)
    }

    fun login(socialPlatform: String) = viewModelScope.launch {
        Log.d("LoginActivity", "로그인 함수 호출")
        authRepository.login("KAKAO")
    }

    fun checkIsAlreadyLogin() = viewModelScope.launch {
        var userInfo = getUserUseCase.invoke()
        _isAlreadyLogin.value = !userInfo.name.isNullOrBlank()
        Log.d("userInfo", "$userInfo")
    }

    fun getToken() = viewModelScope.launch {
        getRemoteTokenUseCase.invoke()
    }
}
