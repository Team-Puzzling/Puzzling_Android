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
class TeamCurrentSituationViewModel @Inject constructor(
    private val repositoryImpl: TeamReviewRepositoryImpl,
) : ViewModel() {

    var isWeekRetrospectColor: MutableLiveData<Boolean> = MutableLiveData(true)

//    private val _teamRetrospectList = MutableLiveData<ArrayList<TeamRetrospectList>>().apply { value = arrayListOf() }
//    val teamRetrospectList: LiveData<ArrayList<TeamRetrospectList>> get() = _teamRetrospectList

    private val _teamRetrospectList = MutableLiveData<ArrayList<ResponseTeamReviewListDto.Data>>().apply { value = arrayListOf() }
    val teamRetrospectList: LiveData<ArrayList<ResponseTeamReviewListDto.Data>> get() = _teamRetrospectList

    private val _teamRetrospectMultiList = MutableLiveData<ArrayList<TeamReviewMultiList>>()
    val teamRetrospectMultiList: LiveData<ArrayList<TeamReviewMultiList>> get() = _teamRetrospectMultiList

    val week = listOf("월", "화", "수", "목", "금", "토", "일")

//    val itemRetroList = arrayListOf<TeamRetrospectList>(
//        TeamRetrospectList(
//            "화요일",
//            "2023-07-18",
//            arrayListOf(TeamRetrospectList.ReviewWriterList("박솝트", "ios"), TeamRetrospectList.ReviewWriterList("김솝트", "서버"), TeamRetrospectList.ReviewWriterList("한솝트", "서버")),
//            arrayListOf(TeamRetrospectList.NonReviewWriterList("한솝트", "디자인"), TeamRetrospectList.NonReviewWriterList("이솝트", "안드로이드")),
//        ),
//        TeamRetrospectList(
//            "수요일",
//            "2023-07-19",
//            null,
//            arrayListOf(TeamRetrospectList.NonReviewWriterList("한솝트", "디자인"), TeamRetrospectList.NonReviewWriterList("이솝트", "안드로이드")),
//        ),
//        TeamRetrospectList(
//            "목요일",
//            "2023-07-20",
//            arrayListOf(TeamRetrospectList.ReviewWriterList("박솝트", "ios"), TeamRetrospectList.ReviewWriterList("김솝트", "서버"), TeamRetrospectList.ReviewWriterList("한솝트", "서버")),
//            null,
//        ),
//
    var itemRetroList = arrayListOf<ResponseTeamReviewListDto.Data>()

//    private var _itemRetroList: MutableLiveData<ArrayList<TeamRetrospectList>> = MutableLiveData(null)
//    val itemRetroList: LiveData<ArrayList<TeamRetrospectList>> get() = _itemRetroList

//    fun getTeamRetrospectList() = viewModelScope.launch {
//        _teamRetrospectList.value = itemRetroList
//        _teamRetrospectMultiList.apply { value = arrayListOf() }
//    }

//    fun getTeamRetrospectList(
//        id: Int,
//        startOfWeek: String,
//        endOfWeek: String,
//    ) {
//        viewModelScope.launch {
//            repositoryImpl.getTeamRetroList(id, startOfWeek, endOfWeek).onSuccess { response ->
//                itemRetroList = response.toTeamRetroArray()!!
//                _teamRetrospectList.value = itemRetroList
//                // _teamRetrospectList.apply { value = ArrayList(response.toTeamRetro()) }
//                Log.d("viewModel._teamRetrospectList: ", "${_teamRetrospectList.value}")
//                _teamRetrospectMultiList.apply { value = arrayListOf() }
//                // Log.d("viewModel._teamRetrospectMultiList: ", "${_teamRetrospectMultiList.value}")
//            }.onFailure { error ->
//                Log.d("팀원 현황 viewModel: ", "연결 실패")
//            }
//        }
//    }

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
