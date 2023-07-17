package com.puzzling.puzzlingaos.presentation.team.currentSituation

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puzzling.puzzlingaos.data.repository.TeamCurrentSituationRepositoryImpl
import com.puzzling.puzzlingaos.domain.entity.TeamRetrospectList
import com.puzzling.puzzlingaos.domain.entity.TeamRetrospectMultiList
import kotlinx.coroutines.launch

class TeamCurrentSituationViewModel(private val teamCurrentSituationRepositoryImpl: TeamCurrentSituationRepositoryImpl) : ViewModel() {

    var isWeekRetrospectColor: MutableLiveData<Boolean> = MutableLiveData(true)

    private val _teamRetrospectList = MutableLiveData<ArrayList<TeamRetrospectList>>().apply { value = arrayListOf() }
    val teamRetrospectList: LiveData<ArrayList<TeamRetrospectList>> get() = _teamRetrospectList

    private val _teamRetrospectMultiList = MutableLiveData<ArrayList<TeamRetrospectMultiList>>()
    val teamRetrospectMultiList: LiveData<ArrayList<TeamRetrospectMultiList>> get() = _teamRetrospectMultiList

    val week = listOf("월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일")

    val itemRetroList = arrayListOf<TeamRetrospectList>(
        TeamRetrospectList(
            "화요일",
            "2023-07-18",
            arrayListOf(TeamRetrospectList.ReviewWriterList("박솝트", "ios"), TeamRetrospectList.ReviewWriterList("김솝트", "서버"), TeamRetrospectList.ReviewWriterList("한솝트", "서버")),
            arrayListOf(TeamRetrospectList.NonReviewWriterList("한솝트", "디자인"), TeamRetrospectList.NonReviewWriterList("이솝트", "안드로이드")),
        ),
        TeamRetrospectList(
            "수요일",
            "2023-07-19",
            null,
            arrayListOf(TeamRetrospectList.NonReviewWriterList("한솝트", "디자인"), TeamRetrospectList.NonReviewWriterList("이솝트", "안드로이드")),
        ),
        TeamRetrospectList(
            "목요일",
            "2023-07-20",
            arrayListOf(TeamRetrospectList.ReviewWriterList("박솝트", "ios"), TeamRetrospectList.ReviewWriterList("김솝트", "서버"), TeamRetrospectList.ReviewWriterList("한솝트", "서버")),
            null,
        ),
    )

    fun getTeamRetrospectList() = viewModelScope.launch {
        _teamRetrospectList.value = itemRetroList
        _teamRetrospectMultiList.apply { value = arrayListOf() }
    }

//    @SuppressLint("NotifyDataSetChanged")
//    fun setItemList(itemList: LiveData<ArrayList<TeamRetrospectList>>) {
//        teamRetrospectMultiList.value?.clear()
//
//        if (itemList.value?.isNotEmpty() == true) {
//            if (itemList.value?.get(0)?.reviewWriters?.isNotEmpty() == true) {
//                teamRetrospectMultiList.value?.add(
//                    TeamRetrospectMultiList(
//                        0,
//                        null,
//                        null,
//                        null,
//                        null,
//                    ),
//                )
//                for (i in 0 until (itemList.value?.get(0)?.reviewWriters!!.size ?: 0)) {
//                    teamRetrospectMultiList.value?.add(
//                        TeamRetrospectMultiList(
//                            1,
//                            itemList.value?.get(0)?.reviewDay,
//                            itemList.value?.get(0)?.reviewDate,
//                            itemList.value?.get(0)?.reviewWriters?.get(i)?.memberNickname,
//                            itemList.value?.get(0)?.reviewWriters?.get(i)?.memberRole,
//                        ),
//                    )
//                }
//            }
//
//            if (itemList.value?.get(0)?.nonReviewWriters?.isNotEmpty() == true) {
//                teamRetrospectMultiList.value?.add(
//                    TeamRetrospectMultiList(
//                        2,
//                        null,
//                        null,
//                        null,
//                        null,
//                    ),
//                )
//                for (i: Int in 0 until (itemList.value?.get(0)?.nonReviewWriters?.size ?: 0)) {
//                    teamRetrospectMultiList.value?.add(
//                        TeamRetrospectMultiList(
//                            3,
//                            itemList.value?.get(0)?.reviewDay,
//                            itemList.value?.get(0)?.reviewDate,
//                            itemList.value?.get(0)?.nonReviewWriters?.get(i)?.memberNickname,
//                            itemList.value?.get(0)?.nonReviewWriters?.get(i)?.memberRole,
//                        ),
//                    )
//                }
//            }
//        }
//    }

//    fun testItemList(itemList: LiveData<ArrayList<TeamRetrospectList>>, day: String) {
//        var intDay: Int = itemRetroList.indexOfFirst { it.reviewDate == day }
//
//        teamRetrospectMultiList.value?.clear()
//
//        if (itemList.value?.isNotEmpty() == true) {
//            if (itemList.value?.get(intDay)?.reviewWriters?.isNotEmpty() == true) {
//                teamRetrospectMultiList.value?.add(
//                    TeamRetrospectMultiList(
//                        0,
//                        null,
//                        null,
//                        null,
//                        null,
//                    ),
//                )
//                for (i in 0 until (itemList.value?.get(intDay)?.reviewWriters!!.size ?: 0)) {
//                    teamRetrospectMultiList.value?.add(
//                        TeamRetrospectMultiList(
//                            1,
//                            itemList.value?.get(intDay)?.reviewDay,
//                            itemList.value?.get(intDay)?.reviewDate,
//                            itemList.value?.get(intDay)?.reviewWriters?.get(i)?.memberNickname,
//                            itemList.value?.get(intDay)?.reviewWriters?.get(i)?.memberRole,
//                        ),
//                    )
//                }
//            }
//
//            if (itemList.value?.get(intDay)?.nonReviewWriters?.isNotEmpty() == true) {
//                teamRetrospectMultiList.value?.add(
//                    TeamRetrospectMultiList(
//                        2,
//                        null,
//                        null,
//                        null,
//                        null,
//                    ),
//                )
//                for (i: Int in 0 until (itemList.value?.get(intDay)?.nonReviewWriters?.size ?: 0)) {
//                    teamRetrospectMultiList.value?.add(
//                        TeamRetrospectMultiList(
//                            3,
//                            itemList.value?.get(intDay)?.reviewDay,
//                            itemList.value?.get(intDay)?.reviewDate,
//                            itemList.value?.get(intDay)?.nonReviewWriters?.get(i)?.memberNickname,
//                            itemList.value?.get(intDay)?.nonReviewWriters?.get(i)?.memberRole,
//                        ),
//                    )
//                }
//            }
//        }
//    }
}
