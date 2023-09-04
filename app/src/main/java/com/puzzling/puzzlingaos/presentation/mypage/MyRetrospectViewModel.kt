package com.puzzling.puzzlingaos.presentation.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puzzling.puzzlingaos.domain.entity.Project
import com.puzzling.puzzlingaos.domain.entity.ProjectReview
import com.puzzling.puzzlingaos.domain.entity.ReviewCycle
import com.puzzling.puzzlingaos.domain.repository.MyBoardRepository
import com.puzzling.puzzlingaos.domain.repository.MyPageRepository
import com.puzzling.puzzlingaos.domain.repository.ProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyRetrospectViewModel @Inject constructor(
    private val myPageRepository: MyPageRepository,
    private val myBoardRepository: MyBoardRepository,
    private val projectRepository: ProjectRepository,
) :
    ViewModel() {

    private val _responseReview = MutableLiveData<List<ProjectReview>?>()
    val responseReveiew: LiveData<List<ProjectReview>?> get() = _responseReview

    private var _responseProjectList: MutableLiveData<List<Project>> = MutableLiveData()
    val responseProjectList: LiveData<List<Project>>
        get() = _responseProjectList

    private val _currentProject = MutableLiveData<Project>()
    val currentProject: LiveData<Project> get() = _currentProject

    private val _retroWeek = MutableLiveData<ReviewCycle?>()
    val retroWeek: LiveData<ReviewCycle?> get() = _retroWeek

    fun getMyProjectReview(selectedProjectId: Int) = viewModelScope.launch {
        myPageRepository.getMyProjectReview(1, selectedProjectId).onSuccess { response ->
            _responseReview.value = response
            Log.d("MyProjectRetro", "$response")
        }.onFailure {
            Log.d("MyProjectRetro", "$it")
        }
    }

    fun setCurrentProject(currentProject: Project) {
        _currentProject.value = currentProject
        Log.d("viewModel 클릭 이벤트", "$currentProject")
    }

    fun getMyProjectList() = viewModelScope.launch {
        kotlin.runCatching {
            myBoardRepository.getProceedingProject(1).onSuccess { response ->
                _responseProjectList.value = response
            }.onFailure {
                Log.d("MyProjectRetro", "$it")
            }
        }
    }
    // 2425350570

    fun getProjectWeekCycle(projectId: Int) = viewModelScope.launch {
        projectRepository.getProjectWeekCycle(projectId).onSuccess { response ->
            _retroWeek.value = response
            Log.d("회고 주기", "$response")
            Log.d("회고 주기", "${response.projectReviewCycle}")
        }.onFailure {
            Log.d("회고 주기", "$it")
        }
    }
}
