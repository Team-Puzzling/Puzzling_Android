package com.puzzling.puzzlingaos.presentation.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.puzzling.puzzlingaos.data.repository.RegisterRepositoryImpl

class RegisterViewModel(private val registerRepositoryImpl: RegisterRepositoryImpl) : ViewModel() {

//    private val _isEnabledRegister: MutableLiveData<Boolean> = MutableLiveData(false)
//    var isEnabledRegister: LiveData<Boolean> = _isEnabledRegister

    private val registerRegex = REGISTER_REGEX.toRegex()

    val projectName = MutableLiveData<String>()
    val projectExplanation = MutableLiveData<String>()
    var projectStartDate = String()
    val role = MutableLiveData<String>()
    val nickName = MutableLiveData<String>()
    var isDateCycleSelected = ArrayList<String>()

    fun isValid(): Boolean {
        return validRegister(projectName.value.orEmpty()) &&
            validRegister(projectExplanation.value.orEmpty()) &&
            validRegister(role.value.orEmpty()) &&
            validRegister(nickName.value.orEmpty()) &&
            projectStartDate.isNotBlank() &&
            isDateCycleSelected.size > 0
    }

    val isEnabledRegister = MutableLiveData<Boolean>().apply { isValid() }

    // 등록용
    fun validRegister(textBox: String): Boolean {
        return textBox.matches(registerRegex) && textBox.isNotBlank()
    }

    // editText 확인용
    fun validTextBox(textBox: String): Boolean {
        return textBox.matches(registerRegex)
    }

    val isValidProjectName: Boolean = projectName.value.orEmpty().matches(registerRegex) || projectName.value.isNullOrBlank()

    companion object {
        const val REGISTER_REGEX = "^[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9\\\\s]{0,}$"
    }
}
