package com.puzzling.puzzlingaos.presentation.writeRetrospective

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.puzzling.puzzlingaos.presentation.register.RegisterViewModel

class WriteRetrospectiveViewModel : ViewModel() {
    private val registerRegex = RegisterViewModel.REGISTER_REGEX.toRegex()

    val question1 = MutableLiveData<String>()
    val question2 = MutableLiveData<String>()
    val question3 = MutableLiveData<String>()

    fun validTextBox(textBox: String): Boolean {
        return textBox.matches(registerRegex)
    }

    var isValidquestion1: MutableLiveData<Boolean> = MutableLiveData(true)
    var isValidquestion2: MutableLiveData<Boolean> = MutableLiveData(true)
    var isValidquestion3: MutableLiveData<Boolean> = MutableLiveData(true)

    fun validRegister(textBox: String): Boolean {
        return textBox.matches(registerRegex) && !textBox.isNullOrBlank()
    }

    fun isValid(): Boolean {
        return validRegister(question1.value.orEmpty()) &&
            validRegister(question2.value.orEmpty()) &&
            validRegister(question3.value.orEmpty())
    }

    val isEnabledRegister = MediatorLiveData<Boolean>().apply {
        addSource(question1) { value = isValid() }
        addSource(question2) { value = isValid() }
        addSource(question3) { value = isValid() }
    }
    var isBtnEnabled: MutableLiveData<Boolean> = MutableLiveData(false)

    fun checkBtnEnabled() {
        isBtnEnabled.value = isValid()
    }

    companion object {
        const val WRITE_REGEX =
            "/([\\u2700-\\u27BF]|[\\uE000-\\uF8FF]|\\uD83C[\\uDC00-\\uDFFF]|\\uD83D[\\uDC00-\\uDFFF]|[\\u2011-\\u26FF]|\\uD83E[\\uDD10-\\uDDFF])/g"
    }
}
// 특수 문자 가능
// 이모지 사용시 빨간 라인 뜸
