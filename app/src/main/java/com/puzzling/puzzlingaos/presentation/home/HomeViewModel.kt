package com.puzzling.puzzlingaos.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puzzling.puzzlingaos.data.model.response.ResponseMyPageProjectDto
import com.puzzling.puzzlingaos.data.model.response.ResponseProjectRetroWeekDto
import com.puzzling.puzzlingaos.domain.entity.Project
import com.puzzling.puzzlingaos.domain.repository.MyBoardRepository
import com.puzzling.puzzlingaos.domain.repository.ProjectRepository
import com.puzzling.puzzlingaos.util.UserInfo
import com.puzzling.puzzlingaos.util.UserInfo.GET_PROJECT_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MyBoardRepository,
    private val projectRepository: ProjectRepository,
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

    private val _selectedProjectName = MutableLiveData("찌릿")
    val selectedProjectName: LiveData<String>
        get() = _selectedProjectName

    private val _selectedProjectId = MutableLiveData<Int>(GET_PROJECT_ID)
    val selectedProjectId: LiveData<Int>
        get() = _selectedProjectId

    private val _projectId = MutableLiveData<List<Int>>()
    val projectId: LiveData<List<Int>>
        get() = _projectId

    private val _projectName = MutableLiveData<List<String>>()
    val projectName: LiveData<List<String>>
        get() = _projectName

    private val _retroWeek = MutableLiveData<ResponseProjectRetroWeekDto.ProjectCycle?>()
    val retroWeek: LiveData<ResponseProjectRetroWeekDto.ProjectCycle?> get() = _retroWeek

    val firstProjectId = MutableLiveData<Int>()

    init {
        _reviewCycleList.value = listOf("월", "수", "금")
        _reviewCycleText.value = _reviewCycleList.value?.joinToString(separator = ",")
        getProjectList()
    }

    private fun getProjectList() = viewModelScope.launch {
        repository.getProceedingProject(UserInfo.MEMBER_ID).onSuccess { response ->
            Log.d("home", "getProjectList() success:: $response")
            _projectList.value = response
            val projects: List<Project> = _projectList.value!!
            _projectId.value = projects.map {
                it.projectId
            }
            _projectName.value = projects.map {
                it.projectName
            }
            Log.d("home", "_projectId:: ${_projectId.value}")
            Log.d("home", "_projectName:: ${_projectName.value}")
        }.onFailure {
            Log.d("home", "getProjectList() Fail:: $it")
        }
    }

    fun setSelectedProjectText(projectName: String) {
        _selectedProjectName.value = projectName
        Log.d("home", "_selectedProjectName.value :: ${_selectedProjectName.value}")
        _isProjectNameSelected.value = true
        _selectedProjectId.value = findProjectIdByProjectName(projectName)
        Log.d("home", "_selectedProjectId.value :: ${_selectedProjectId.value}")
    }

    private fun findProjectIdByProjectName(projectName: String): Int? {
        val projects: List<Project> = _projectList.value ?: return null
        val selectedProject = projects.find { it.projectName == projectName }
        return selectedProject?.projectId
    }

    fun getProjectWeekCycle(projectId: Int) = viewModelScope.launch {
        kotlin.runCatching {
            projectRepository.getProjectWeekCycle(projectId)
        }.onSuccess { response ->
            _retroWeek.value = response.data
            Log.d("회고 주기", "$response")
            Log.d("회고 주기", "${response.data?.projectReviewCycle}")
        }.onFailure {
            Log.d("회고 주기", "$it")
        }
    }

    fun projectNameSetter(currentProject: String) {
        _selectedProjectName.value = currentProject
    }
}
