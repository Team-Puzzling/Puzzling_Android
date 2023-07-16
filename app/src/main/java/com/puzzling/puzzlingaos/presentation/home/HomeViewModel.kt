package com.puzzling.puzzlingaos.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.puzzling.puzzlingaos.data.model.response.ResponseMyPageProjectDto

class HomeViewModel : ViewModel() {

    val projectItemList = mutableListOf<ResponseMyPageProjectDto>(
        ResponseMyPageProjectDto("Piickle", "2023-07-03", 2),
        ResponseMyPageProjectDto("HARA", "2023-07-28", 3),
        ResponseMyPageProjectDto("낫투두", "2023-07-12", 4),
        ResponseMyPageProjectDto("PEEKABOOK", "2023-07-20", 5),
        ResponseMyPageProjectDto("ZOOC", "2023-06-25", 9),
    )

    private val _reviewCycleList = MutableLiveData<List<String>>()
    val reviewCycleList: List<String>
        get() = requireNotNull(_reviewCycleList.value)

    private val _reviewCycleText = MutableLiveData<String>()
    val reviewCycleText: String
        get() = requireNotNull("")

    private val _isCardVisible = MutableLiveData(false)
    val isCardVisible: LiveData<Boolean>
        get() = _isCardVisible

    var selectedProject: ResponseMyPageProjectDto? = null

    private val _projectItemSelected = MutableLiveData<Boolean>()
    val projectItemSelected: LiveData<Boolean>
        get() = _projectItemSelected

    private val _isProjectNameSelected = MutableLiveData(false)
    val isProjectNameSelected: LiveData<Boolean>
        get() = _isProjectNameSelected

    private val _selectedProjectName = MutableLiveData("PUZZLING")
    val selectedProjectName: LiveData<String>
        get() = _selectedProjectName

    init {
        _reviewCycleList.value = listOf("월", "수", "금")
        _reviewCycleText.value = _reviewCycleList.value?.joinToString(separator = ",")
    }

    fun setSelectedProjectText(projectName: String) {
        _selectedProjectName.value = projectName
        Log.d("home", "_selectedProjectName.value :: ${_selectedProjectName.value}")
        _isProjectNameSelected.value = true
    }
}
