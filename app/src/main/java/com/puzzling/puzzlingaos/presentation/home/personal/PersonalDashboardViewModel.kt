package com.puzzling.puzzlingaos.presentation.home.personal

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puzzling.puzzlingaos.domain.entity.ActionPlan
import com.puzzling.puzzlingaos.domain.repository.MyBoardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonalDashboardViewModel @Inject constructor(
    private val repository: MyBoardRepository,
) : ViewModel() {
    private val _myNickname = MutableLiveData<String>()
    val myNickname: LiveData<String> get() = _myNickname
    private val _mypuzzleCount = MutableLiveData<Int>()
    val mypuzzleCount: LiveData<Int> get() = _mypuzzleCount
    private val _isReviewDay = MutableLiveData<Boolean>()
    val isReviewDay: LiveData<Boolean> get() = _isReviewDay
    private val _hasTodayReview = MutableLiveData<Boolean>()
    val hasTodayReview: LiveData<Boolean> get() = _hasTodayReview

    private var _actionPlanList: MutableLiveData<List<ActionPlan>> = MutableLiveData()
    val actionPlanList: LiveData<List<ActionPlan>>
        get() = _actionPlanList

    init {
        getMyPuzzleData()
        getMyPuzzleData()
        getActionPlan()
    }

    private fun getMyPuzzleData() = viewModelScope.launch {
        repository.getMyPuzzleBoard(1, 11, "2023-07-05")
            .onSuccess { response ->
//                Timber.tag("personal").d("Success,%s", response)
                Log.d("personal", "getMyPuzzleData() success:: $response")
                _myNickname.value = response.data.toPuzzle().nickname
                _mypuzzleCount.value = response.data.toPuzzle().puzzleCount
                _isReviewDay.value = response.data.isReviewDay
                _hasTodayReview.value = response.data.hasTodayReview
            }
            .onFailure {
//                Timber.tag("personal").d("Fail!!" + it)
                Log.d("personal", "getMyPuzzleData() Fail:: $it")
            }
    }

    private fun getActionPlan() = viewModelScope.launch {
        repository.getActionPlan(1, 15).onSuccess { response ->
            Log.d("personal", "getActionPlan() success:: $response")
            _actionPlanList.value = response
        }.onFailure {
            Log.d("personal", "getActionPlan() Fail:: $it")
        }
    }

//    val actionPlanList: List<ActionPlan> =
//        listOf(
//            ActionPlan("여기에는 글이 계속 작성되다가 작성되다가 작성되다가 작성되다가 이쯤 되면 끊기게 돼...", "7월 3일"),
//            ActionPlan("여기에는 글이 계속 작성되다가 작성되다가 작성되다가 작성되다가 이쯤 되면 끊기게 돼...", "7월 4일"),
//            ActionPlan("여기에는 글이 계속 작성되다가 작성되다가 작성되다가 작성되다가 이쯤 되면 끊기게 돼...", "7월 5일"),
//            ActionPlan("여기에는 글이 계속 작성되다가 작성되다가 작성되다가 작성되다가 이쯤 되면 끊기게 돼...", "7월 6일"),
//            ActionPlan("여기에는 글이 계속 작성되다가 작성되다가 작성되다가 작성되다가 이쯤 되면 끊기게 돼...", "7월 7일"),
//        )

    val _bottomButtonText = MutableLiveData<String>()
    val bottomButtonText: String
        get() = _bottomButtonText.value ?: "회고 작성일이 아니에요"
}
