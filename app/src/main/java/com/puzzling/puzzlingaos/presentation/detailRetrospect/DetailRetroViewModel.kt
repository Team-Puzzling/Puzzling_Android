package com.puzzling.puzzlingaos.presentation.detailRetrospect

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puzzling.puzzlingaos.data.model.response.ResponseDetailRetroDto
import com.puzzling.puzzlingaos.domain.repository.MyPageRepository
import com.puzzling.puzzlingaos.util.UserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class DetailRetroViewModel @Inject constructor(private val repository: MyPageRepository) :
    ViewModel() {
    private val _detailRetroList = MutableLiveData<List<ResponseDetailRetroDto.Data.DetailReviewData>?>()
    val detailRetroList: LiveData<List<ResponseDetailRetroDto.Data.DetailReviewData>?> get() = _detailRetroList

    val week = listOf(
        "월",
        "화",
        "수",
        "목",
        "금",
        "토",
        "일",
    )

    val today = LocalDate.now()
    val startOfWeek = today.with(DayOfWeek.MONDAY) // 해당 주의 시작일
    val endOfWeek = today.with(DayOfWeek.SUNDAY) // 해당 주의 종료일

    val projectId = MutableLiveData<Int>()
    val projectName = MutableLiveData<String>()

    fun getDetailRetro(projectId: Int) = viewModelScope.launch {
        kotlin.runCatching {
            Log.d("상세회고조회", "startOfWeek:: $startOfWeek")
            Log.d("상세회고조회", "endOfWeek:: $endOfWeek")
            repository.getMyDetailReview(
                UserInfo.GET_MEMBER_ID,
                projectId,
                startOfWeek.toString(),
                endOfWeek.toString(),
            )
        }.onSuccess { response ->
            _detailRetroList.value = response.data?.reviews
            Log.d("상세회고조회", "response:: $response")
        }.onFailure {
            Log.d("상세회고조회", "fail:: $it")
        }
    }
}
