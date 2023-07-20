package com.puzzling.puzzlingaos.presentation.team.currentSituation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puzzling.puzzlingaos.data.model.response.ResponseTeamReviewListDto
import com.puzzling.puzzlingaos.data.repository.TeamReviewRepositoryImpl
import com.puzzling.puzzlingaos.domain.entity.TeamReviewMultiList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamRetroViewModel @Inject constructor(
    private val repositoryImpl: TeamReviewRepositoryImpl,
) : ViewModel() {

    var isWeekRetrospectColor: MutableLiveData<Boolean> = MutableLiveData(true)

    private val _teamRetrospectList = MutableLiveData<ArrayList<ResponseTeamReviewListDto.Data>>().apply { value = arrayListOf() }
    val teamRetrospectList: LiveData<ArrayList<ResponseTeamReviewListDto.Data>> get() = _teamRetrospectList

    private val _teamRetrospectMultiList = MutableLiveData<ArrayList<TeamReviewMultiList>>()
    val teamRetrospectMultiList: LiveData<ArrayList<TeamReviewMultiList>> get() = _teamRetrospectMultiList

    val week = listOf("월", "화", "수", "목", "금", "토", "일")

    var itemRetroList = arrayListOf<ResponseTeamReviewListDto.Data>()

    fun getTeamRetrospectList(
        id: Int,
        startOfWeek: String,
        endOfWeek: String,
    ) {
        viewModelScope.launch {
            repositoryImpl.getTeamRetroList(id, startOfWeek, endOfWeek).onSuccess { response ->
                itemRetroList = response.toTeamRetroArray()!!
                _teamRetrospectList.value = itemRetroList
                // _teamRetrospectList.apply { value = ArrayList(response.toTeamRetro()) }
                Log.d("viewModel._teamRetrospectList: ", "${_teamRetrospectList.value}")
                _teamRetrospectMultiList.apply { value = arrayListOf() }
                // Log.d("viewModel._teamRetrospectMultiList: ", "${_teamRetrospectMultiList.value}")
            }.onFailure { error ->
                Log.d("팀원 현황 viewModel: ", "연결 실패")
            }
        }
    }
}
