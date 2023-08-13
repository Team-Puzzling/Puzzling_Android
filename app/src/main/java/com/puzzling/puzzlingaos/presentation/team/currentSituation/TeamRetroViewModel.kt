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

    private val _teamRetrospectList = MutableLiveData<ArrayList<ResponseTeamReviewListDto.Data.MemberReviews>>().apply { value = arrayListOf() }
    val teamRetrospectList: LiveData<ArrayList<ResponseTeamReviewListDto.Data.MemberReviews>> get() = _teamRetrospectList

    private val _teamRetrospectMultiList = MutableLiveData<ArrayList<TeamReviewMultiList>>()
    val teamRetrospectMultiList: LiveData<ArrayList<TeamReviewMultiList>> get() = _teamRetrospectMultiList

    private val test = listOf(teamRetrospectMultiList)

    val week = listOf("월", "화", "수", "목", "금", "토", "일")

    var itemRetroList = arrayListOf<ResponseTeamReviewListDto.Data.MemberReviews>()

    fun getTeamRetrospectList(
        id: Int,
        startOfWeek: String,
        endOfWeek: String,
    ) {
        viewModelScope.launch {
            repositoryImpl.getTeamRetroList(id, startOfWeek, endOfWeek).onSuccess { response ->
                //
                // itemRetroList = response.toTeamRetroArray()!!
                for (i in 0 until response.data.size) {
                    itemRetroList = response.data[0].toTeamRetroArray()!!
                }

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

    fun testItemList(itemList: LiveData<ArrayList<ResponseTeamReviewListDto.Data.MemberReviews>>, day: String) {
        var intDay: Int = itemRetroList.indexOfFirst { it.reviewDay == day }

        teamRetrospectMultiList.value?.clear()

        if (itemList.value?.isNotEmpty() == true) {
            if (itemList.value?.get(intDay)?.reviewWriters?.isNotEmpty() == true) {
                teamRetrospectMultiList.value?.add(
                    TeamReviewMultiList(
                        0,
                        null,
                        null,
                        null,
                        null,
                    ),
                )
                for (i in 0 until (itemList.value?.get(intDay)?.reviewWriters!!.size ?: 0)) {
                    teamRetrospectMultiList.value?.add(
                        TeamReviewMultiList(
                            1,
                            itemList.value?.get(intDay)?.reviewDay,
                            itemList.value?.get(intDay)?.reviewDate,
                            itemList.value?.get(intDay)?.reviewWriters?.get(i)?.memberNickname,
                            itemList.value?.get(intDay)?.reviewWriters?.get(i)?.memberRole,
                        ),
                    )
                }
            }

            if (itemList.value?.get(intDay)?.nonReviewWriters?.isNotEmpty() == true) {
                teamRetrospectMultiList.value?.add(
                    TeamReviewMultiList(
                        2,
                        null,
                        null,
                        null,
                        null,
                    ),
                )
                for (i: Int in 0 until (itemList.value?.get(intDay)?.nonReviewWriters?.size ?: 0)) {
                    teamRetrospectMultiList.value?.add(
                        TeamReviewMultiList(
                            3,
                            itemList.value?.get(intDay)?.reviewDay,
                            itemList.value?.get(intDay)?.reviewDate,
                            itemList.value?.get(intDay)?.nonReviewWriters?.get(i)?.memberNickname,
                            itemList.value?.get(intDay)?.nonReviewWriters?.get(i)?.memberRole,
                        ),
                    )
                }
            }
        }
        Log.d("ItemTeamRetrospectListFragment: ", "${teamRetrospectMultiList.value}")
    }
}
