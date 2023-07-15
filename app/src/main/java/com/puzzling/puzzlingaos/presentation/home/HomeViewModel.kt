package com.puzzling.puzzlingaos.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private val _reviewCycleList = MutableLiveData<List<String>>()
    val reviewCycleList: List<String>
        get() = requireNotNull(_reviewCycleList.value)

    private val _reviewCycleText = MutableLiveData<String>()
    val reviewCycleText: String
        get() = requireNotNull("")

    private val _isCardVisible = MutableLiveData(false)
    val isCardVisible: LiveData<Boolean>
        get() = _isCardVisible

    init {
        _reviewCycleList.value = listOf("월", "수", "금")
        _reviewCycleText.value = _reviewCycleList.value?.joinToString(separator = ",")
    }
}
