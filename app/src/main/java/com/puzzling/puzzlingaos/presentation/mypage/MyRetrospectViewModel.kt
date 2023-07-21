package com.puzzling.puzzlingaos.presentation.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puzzling.puzzlingaos.data.model.response.ResponseMyRetroListDto
import com.puzzling.puzzlingaos.data.model.response.ResponseProjectRetroWeekDto
import com.puzzling.puzzlingaos.domain.entity.Project
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

    private val _responseReview = MutableLiveData<List<ResponseMyRetroListDto.ReviewData>?>()
    val responseReveiew: LiveData<List<ResponseMyRetroListDto.ReviewData>?> get() = _responseReview

    private var _responseProjectList: MutableLiveData<List<Project>> = MutableLiveData()
    val responseProjectList: LiveData<List<Project>>
        get() = _responseProjectList

    private val _currentProject = MutableLiveData<Project>()
    val currentProject: LiveData<Project> get() = _currentProject

    private val _retroWeek = MutableLiveData<ResponseProjectRetroWeekDto.ProjectCycle?>()
    val retroWeek: LiveData<ResponseProjectRetroWeekDto.ProjectCycle?> get() = _retroWeek

    fun getMyProjectReview(selectedProjectId: Int) = viewModelScope.launch {
        kotlin.runCatching {
            myPageRepository.getMyProjectReview(2, selectedProjectId)
        }.onSuccess { response ->
            _responseReview.value = response.data
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
            myBoardRepository.getProceedingProject(2).onSuccess { response ->
                _responseProjectList.value = response
            }.onFailure {
                Log.d("MyProjectRetro", "$it")
            }
        }
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
}
