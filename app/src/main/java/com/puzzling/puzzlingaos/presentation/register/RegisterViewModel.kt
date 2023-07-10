package com.puzzling.puzzlingaos.presentation.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.puzzling.puzzlingaos.data.repository.RegisterRepositoryImpl

class RegisterViewModel(private val registerRepositoryImpl: RegisterRepositoryImpl) : ViewModel() {

    private val _isDateCycleSelected: MutableLiveData<Boolean> = MutableLiveData()
    var isDateCycleSelected: LiveData<Boolean> = _isDateCycleSelected

    private val _isEnabledRegister: MutableLiveData<Boolean> = MutableLiveData(false)
    var isEnabledRegister: LiveData<Boolean> = _isEnabledRegister

    val projectName = MutableLiveData<String>()
    val projectExplanation = MutableLiveData<String>()
    var projectStartDate = String()
    val role = MutableLiveData<String>()
    val nickName = MutableLiveData<String>()
}
