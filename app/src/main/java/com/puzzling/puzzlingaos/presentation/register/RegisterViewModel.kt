package com.puzzling.puzzlingaos.presentation.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puzzling.puzzlingaos.data.model.request.RequestProjectRegisterDto
import com.puzzling.puzzlingaos.data.model.response.ResponseProjectRegisterDto
import com.puzzling.puzzlingaos.data.repository.ProjectRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val projectRepositoryImpl: ProjectRepositoryImpl,
) : ViewModel() {

    private val _registerResult: MutableLiveData<ResponseProjectRegisterDto> = MutableLiveData()
    val registerResult: LiveData<ResponseProjectRegisterDto> = _registerResult

    private val registerRegex = REGISTER_REGEX.toRegex()

    val projectName = MutableLiveData<String>()
    val projectExplanation = MutableLiveData<String>()
    val projectStartDate = MutableLiveData<String>()
    val role = MutableLiveData<String>()
    val nickName = MutableLiveData<String>()
    var dayArray = ArrayList<String>()
    val isDateCycleSelected = MutableLiveData<ArrayList<String>>()

    // editText 확인용
    fun validTextBox(textBox: String): Boolean {
        return textBox.matches(registerRegex)
    }
    var isValidProjectName: MutableLiveData<Boolean> = MutableLiveData(true)
    var isValidProjectExplanation: MutableLiveData<Boolean> = MutableLiveData(true)
    var isValidRole: MutableLiveData<Boolean> = MutableLiveData(true)
    var isValidNickName: MutableLiveData<Boolean> = MutableLiveData(true)

    // 등록용
    fun validRegister(textBox: String): Boolean {
        return textBox.matches(registerRegex) && !textBox.isNullOrBlank()
    }

    fun isValid(): Boolean {
        return validRegister(projectName.value.orEmpty()) &&
            validRegister(projectExplanation.value.orEmpty()) &&
            validRegister(role.value.orEmpty()) &&
            validRegister(nickName.value.orEmpty()) &&
            !projectStartDate.value.isNullOrBlank() &&
            isDateCycleSelected.value.orEmpty().isNotEmpty()
    }

    val isEnabledRegister = MediatorLiveData<Boolean>().apply {
        addSource(projectName) { value = isValid() }
        addSource(projectExplanation) { value = isValid() }
        addSource(role) { value = isValid() }
        addSource(nickName) { value = isValid() }
        addSource(projectStartDate) { value = isValid() }
        addSource(isDateCycleSelected) { value = isValid() }
    }
    var isBtnEnabled: MutableLiveData<Boolean> = MutableLiveData(false)

    fun checkBtnEnabled() {
        isBtnEnabled.value = isValid()
    }

    fun doProjectRegister(
        id: Int,
        projectName: String,
        projectIntro: String,
        startDate: String,
        role: String,
        nickName: String,
        dateCycle: ArrayList<String>,
    ) {
        viewModelScope.launch {
            projectRepositoryImpl.projectRegister(id, RequestProjectRegisterDto(projectName, projectIntro, startDate, role, nickName, dateCycle)).onSuccess { response ->
                _registerResult.value = response
            }.onFailure { error ->
                Log.d("register: ", "register 실패")
            }
        }
    }

    fun checkNickname(nickName: String) {
    }

    companion object {
        const val REGISTER_REGEX = "^[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9 \\\\s]*$"
    }
}
