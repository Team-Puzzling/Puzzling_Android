package com.puzzling.puzzlingaos.presentation.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puzzling.puzzlingaos.data.model.response.ResponseMyRetroListDto
import com.puzzling.puzzlingaos.domain.entity.Project
import com.puzzling.puzzlingaos.domain.repository.MyBoardRepository
import com.puzzling.puzzlingaos.domain.repository.MyPageRepository
import com.puzzling.puzzlingaos.domain.repository.ProjectRepository
import com.puzzling.puzzlingaos.util.UserInfo.GET_MEMBER_ID
import com.puzzling.puzzlingaos.util.UserInfo.GET_PROJECT_ID
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

    private val _currentProject = MutableLiveData<String>("프로젝트 이름")
    val currentProject: LiveData<String> get() = _currentProject

    private val _retroWeek = MutableLiveData<String>()
    val retroWeek: LiveData<String> get() = _retroWeek

    init {
        getMyProjectReview()
    }

    fun getMyProjectReview() = viewModelScope.launch {
        kotlin.runCatching {
            myPageRepository.getMyProjectReview(GET_MEMBER_ID, GET_PROJECT_ID)
        }.onSuccess { response ->
            _responseReview.value = response.data
            Log.d("MyProjectRetro", "$response")
        }.onFailure {
            Log.d("MyProjectRetro", "$it")
        }
    }

    fun setCurrentProject(currentProject: String) {
        _currentProject.value = currentProject
        Log.d("viewModel 클릭 이벤트", "$currentProject")
    }

    fun getMyProjectList() = viewModelScope.launch {
        kotlin.runCatching {
            myBoardRepository.getProceedingProject(GET_MEMBER_ID).onSuccess { response ->
                _responseProjectList.value = response
            }.onFailure {
                Log.d("MyProjectRetro", "$it")
            }
        }
    }

    fun getProjectWeekCycle() = viewModelScope.launch {
        kotlin.runCatching {
            projectRepository.getProjectWeekCycle(GET_PROJECT_ID)
        }.onSuccess { response ->
            _retroWeek.value = response.data?.projectReviewCycle
            Log.d("회고 주기", "$response")
            Log.d("회고 주기", "${response.data?.projectReviewCycle}")
        }.onFailure {
            Log.d("회고 주기", "$it")
        }
    }
}
