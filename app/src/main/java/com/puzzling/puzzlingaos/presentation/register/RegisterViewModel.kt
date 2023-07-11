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
        return valid(projectName.value.orEmpty()) &&
            valid(projectExplanation.value.orEmpty()) &&
            valid(role.value.orEmpty()) &&
            valid(nickName.value.orEmpty()) &&
            projectStartDate.isNotBlank() &&
            isDateCycleSelected.size > 0
    }

    val isEnabledRegister = MutableLiveData<Boolean>().apply { isValid() }

    fun valid(textBox: String): Boolean {
        return textBox.matches(registerRegex) && textBox.isNotBlank()
    }

    companion object {
        const val REGISTER_REGEX = "^[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9\\\\s]{1,}$"
    }
}
