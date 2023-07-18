package com.puzzling.puzzlingaos.presentation.writeRetrospective

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WriteRetrospectiveViewModel : ViewModel() {
    private val registerRegex = WRITE_REGEX.toRegex()

    val tilQuestion1 = MutableLiveData<String>()
    val tilQuestion2 = MutableLiveData<String>()
    val tilQuestion3 = MutableLiveData<String>()
    var isValidTilquestion1: MutableLiveData<Boolean> = MutableLiveData(true)
    var isValidTilquestion2: MutableLiveData<Boolean> = MutableLiveData(true)
    var isValidTilquestion3: MutableLiveData<Boolean> = MutableLiveData(true)

    val question5f1 = MutableLiveData<String>()
    val question5f2 = MutableLiveData<String>()
    val question5f3 = MutableLiveData<String>()
    val question5f4 = MutableLiveData<String>()
    val question5f5 = MutableLiveData<String>()
    var isValid5fquestion1: MutableLiveData<Boolean> = MutableLiveData(true)
    var isValid5fquestion2: MutableLiveData<Boolean> = MutableLiveData(true)
    var isValid5fquestion3: MutableLiveData<Boolean> = MutableLiveData(true)
    var isValid5fquestion4: MutableLiveData<Boolean> = MutableLiveData(true)
    var isValid5fquestion5: MutableLiveData<Boolean> = MutableLiveData(true)

    val aarQuestion1 = MutableLiveData<String>()
    val aarQuestion2 = MutableLiveData<String>()
    val aarQuestion3 = MutableLiveData<String>()
    val aarQuestion4 = MutableLiveData<String>()
    val aarQuestion5 = MutableLiveData<String>()
    var isValidAarquestion1: MutableLiveData<Boolean> = MutableLiveData(true)
    var isValidAarquestion2: MutableLiveData<Boolean> = MutableLiveData(true)
    var isValidAarquestion3: MutableLiveData<Boolean> = MutableLiveData(true)
    var isValidAarquestion4: MutableLiveData<Boolean> = MutableLiveData(true)
    var isValidAarquestion5: MutableLiveData<Boolean> = MutableLiveData(true)

    private val _isReviewTypeSelected = MutableLiveData(false)
    val isReviewTypeSelected: LiveData<Boolean>
        get() = _isReviewTypeSelected

    private val _selectedReviewType = MutableLiveData("TIL")
    val selectedReviewType: LiveData<String>
        get() = _selectedReviewType

    private val _selectedChipText = MutableLiveData<String>()
    val selectedChipText: LiveData<String>
        get() = _selectedChipText

    fun setSelectedChipText(chipText: String) {
        _selectedChipText.value = chipText
    }

    fun validTextBox(textBox: String): Boolean {
        return textBox.matches(registerRegex)
    }

    fun validRegister(textBox: String): Boolean {
        return textBox.matches(registerRegex) && !textBox.isNullOrBlank()
    }

    fun isTilValid(): Boolean {
        return validRegister(tilQuestion1.value.orEmpty()) &&
            validRegister(tilQuestion2.value.orEmpty()) &&
            validRegister(tilQuestion3.value.orEmpty())
    }

    fun is5fValid(): Boolean {
        return validRegister(question5f1.value.orEmpty()) &&
            validRegister(question5f2.value.orEmpty()) &&
            validRegister(question5f3.value.orEmpty()) &&
            validRegister(question5f4.value.orEmpty()) &&
            validRegister(question5f5.value.orEmpty())
    }

    fun isAarValid(): Boolean {
        return validRegister(aarQuestion1.value.orEmpty()) &&
            validRegister(aarQuestion2.value.orEmpty()) &&
            validRegister(aarQuestion3.value.orEmpty()) &&
            validRegister(aarQuestion4.value.orEmpty()) &&
            validRegister(aarQuestion5.value.orEmpty())
    }

    val isEnabledTilRegister = MediatorLiveData<Boolean>().apply {
        addSource(tilQuestion1) { value = isTilValid() }
        addSource(tilQuestion2) { value = isTilValid() }
        addSource(tilQuestion3) { value = isTilValid() }
    }

    val isEnabled5fRegister = MediatorLiveData<Boolean>().apply {
        addSource(question5f1) { value = is5fValid() }
        addSource(question5f2) { value = is5fValid() }
        addSource(question5f3) { value = is5fValid() }
        addSource(question5f4) { value = is5fValid() }
        addSource(question5f5) { value = is5fValid() }
    }

    val isEnabledAarRegister = MediatorLiveData<Boolean>().apply {
        addSource(aarQuestion1) { value = isAarValid() }
        addSource(aarQuestion2) { value = isAarValid() }
        addSource(aarQuestion3) { value = isAarValid() }
        addSource(aarQuestion4) { value = isAarValid() }
        addSource(aarQuestion5) { value = isAarValid() }
    }

    var isInputEnabled: MutableLiveData<Boolean> = MutableLiveData(false)

    fun checkBtnEnabled() {
        isInputEnabled.value = when (_selectedReviewType.value) {
            "TIL" -> isTilValid()
            "5F" -> is5fValid()
            "AAR" -> isAarValid()
            else -> false
        }
    }

    fun setSelectedReviewTypeText(reviewType: String) {
        _selectedReviewType.value = reviewType
        Log.d("write", "_selectedReviewType.value :: ${_selectedReviewType.value}")
        _isReviewTypeSelected.value = true
    }

    companion object {
        const val WRITE_REGEX =
            "^[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9 \\\\\\\\s!@#\$%^&*()-_=+\\\\[{\\\\]}\\\\|;:'\",.<>/?]*\$"
    }
}
