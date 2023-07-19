package com.puzzling.puzzlingaos.presentation.team.currentSituation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puzzling.puzzlingaos.data.model.response.ResponseTeamRetroListDto
import com.puzzling.puzzlingaos.domain.entity.TeamRetrospectList
import com.puzzling.puzzlingaos.domain.entity.TeamRetrospectMultiList
import com.puzzling.puzzlingaos.domain.repository.TeamRetroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class TeamCurrentSituationViewModel @Inject constructor(
    private val repository: TeamRetroRepository
    ): ViewModel() {

    var isWeekRetrospectColor: MutableLiveData<Boolean> = MutableLiveData(true)

//    private val _teamRetrospectList = MutableLiveData<ArrayList<TeamRetrospectList>>().apply { value = arrayListOf() }
//    val teamRetrospectList: LiveData<ArrayList<TeamRetrospectList>> get() = _teamRetrospectList

    private val _teamRetrospectList = MutableLiveData<ArrayList<ResponseTeamRetroListDto.Data>>().apply { value = arrayListOf() }
    val teamRetrospectList: LiveData<ArrayList<ResponseTeamRetroListDto.Data>> get() = _teamRetrospectList

    private val _teamRetrospectMultiList = MutableLiveData<ArrayList<TeamRetrospectMultiList>>()
    val teamRetrospectMultiList: LiveData<ArrayList<TeamRetrospectMultiList>> get() = _teamRetrospectMultiList

    val week = listOf("월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일")

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
var itemRetroList = arrayListOf<ResponseTeamRetroListDto.Data>()

//    private var _itemRetroList: MutableLiveData<ArrayList<TeamRetrospectList>> = MutableLiveData(null)
//    val itemRetroList: LiveData<ArrayList<TeamRetrospectList>> get() = _itemRetroList

//    fun getTeamRetrospectList() = viewModelScope.launch {
//        _teamRetrospectList.value = itemRetroList
//        _teamRetrospectMultiList.apply { value = arrayListOf() }
//    }

    val today = LocalDate.now()
    val dateFormatter = DateTimeFormatter.ofPattern("dd")
    val startOfWeek = today.with(DayOfWeek.MONDAY).format(dateFormatter) // 해당 주의 시작일
    val endOfWeek = today.with(DayOfWeek.SUNDAY).format(dateFormatter) // 해당 주의 종료일
    fun getTeamRetrospectList() = viewModelScope.launch {
        repository.getTeamRetroList(2, startOfWeek, endOfWeek).onSuccess { response ->
            itemRetroList = response.toTeamRetroArray()!!
            _teamRetrospectList.value = itemRetroList
            // _teamRetrospectList.apply { value = ArrayList(response.toTeamRetro()) }
            Log.d("viewModel._teamRetrospectList: ", "${_teamRetrospectList.value}")
            _teamRetrospectMultiList.apply { value = arrayListOf() }
            Log.d("viewModel._teamRetrospectList: ", "${_teamRetrospectMultiList.value}")
        }.onFailure { error ->
            Log.d("팀원 현황 viewModel: ", "연결 실패")
        }
    }
}
