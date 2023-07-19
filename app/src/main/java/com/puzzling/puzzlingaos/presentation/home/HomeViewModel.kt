package com.puzzling.puzzlingaos.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puzzling.puzzlingaos.data.model.response.ResponseMyPageProjectDto
import com.puzzling.puzzlingaos.domain.entity.Project
import com.puzzling.puzzlingaos.domain.repository.MyBoardRepository
import com.puzzling.puzzlingaos.domain.repository.ProjectRepository
import com.puzzling.puzzlingaos.util.UserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MyBoardRepository,
    private val projectRepository: ProjectRepository
) : ViewModel() {

    private var _projectList: MutableLiveData<List<Project>> = MutableLiveData()
    val projectList: LiveData<List<Project>>
        get() = _projectList

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

    private val _retroWeek = MutableLiveData<String>()
    val retroWeek: LiveData<String> get() = _retroWeek

    init {
        _reviewCycleList.value = listOf("월", "수", "금")
        _reviewCycleText.value = _reviewCycleList.value?.joinToString(separator = ",")
        getProjectList()
    }

    private fun getProjectList() = viewModelScope.launch {
        repository.getProceedingProject(UserInfo.MEMBER_ID).onSuccess { response ->
            Log.d("home", "getProjectList() success:: $response")
            _projectList.value = response
        }.onFailure {
            Log.d("home", "getProjectList() Fail:: $it")
        }
    }

    fun setSelectedProjectText(projectName: String) {
        _selectedProjectName.value = projectName
        Log.d("home", "_selectedProjectName.value :: ${_selectedProjectName.value}")
        _isProjectNameSelected.value = true
    }

    fun getProjectWeekCycle() = viewModelScope.launch {
        kotlin.runCatching {
            projectRepository.getProjectWeekCycle(UserInfo.PROJECT_ID)
        }.onSuccess { response ->
            _retroWeek.value = response.data?.projectReviewCycle
            Log.d("회고 주기", "$response")
        }.onFailure {
            Log.d("회고 주기", "$it")
        }
    }
}
