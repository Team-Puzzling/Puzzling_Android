package com.puzzling.puzzlingaos.presentation.main.invitationCode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class InvitationCodeViewModel : ViewModel() {

    val inputCode = MutableStateFlow("")
    val inputCodeLength = inputCode.map { inputCode ->
        inputCode.length.toString()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), "")

    private val _isCodeSuccess = MutableLiveData<Boolean>()
    val isCodeSuccess: LiveData<Boolean> get() = _isCodeSuccess
}
