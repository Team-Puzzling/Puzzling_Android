package com.puzzling.puzzlingaos.presentation.home.personal

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

    init {
        getMyPuzzleData()
        getMyPuzzleData()
    }

    private fun getMyPuzzleData() = viewModelScope.launch {
        repository.getMyPuzzleBoard(1, 11, "2023-07-05").onSuccess { response ->
            _myNickname.value = response.myPuzzle.nickname
            _mypuzzleCount.value = response.myPuzzle.puzzleCount
            _isReviewDay.value = response.isReviewDay
            _hasTodayReview.value = response.hasTodayReview
        }
    }

//    private fun getActionPlan() = viewModelScope.launch {
//        repository.getActionPlan(1, 11).onSuccess {
//        }
//    }

    val actionPlanList: List<ActionPlan> =
        listOf(
            ActionPlan("여기에는 글이 계속 작성되다가 작성되다가 작성되다가 작성되다가 이쯤 되면 끊기게 돼...", "7월 3일"),
            ActionPlan("여기에는 글이 계속 작성되다가 작성되다가 작성되다가 작성되다가 이쯤 되면 끊기게 돼...", "7월 4일"),
            ActionPlan("여기에는 글이 계속 작성되다가 작성되다가 작성되다가 작성되다가 이쯤 되면 끊기게 돼...", "7월 5일"),
            ActionPlan("여기에는 글이 계속 작성되다가 작성되다가 작성되다가 작성되다가 이쯤 되면 끊기게 돼...", "7월 6일"),
            ActionPlan("여기에는 글이 계속 작성되다가 작성되다가 작성되다가 작성되다가 이쯤 되면 끊기게 돼...", "7월 7일"),
        )

    val _bottomButtonText = MutableLiveData<String>()
    val bottomButtonText: String
        get() = _bottomButtonText.value ?: "회고 작성일이 아니에요"
}
