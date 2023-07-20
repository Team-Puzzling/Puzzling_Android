package com.puzzling.puzzlingaos.presentation.home.team

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.puzzling.puzzlingaos.domain.entity.TeamPuzzleBoard
import com.puzzling.puzzlingaos.domain.entity.TeamRanking
import com.puzzling.puzzlingaos.domain.repository.TeamReviewRepository
import com.puzzling.puzzlingaos.util.UserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamDashBoardViewModel @Inject constructor(
    private val repository: TeamReviewRepository,
) : ViewModel() {
    private val _myNickname = MutableLiveData<String>()
    val myNickname: LiveData<String> get() = _myNickname
    private val _myPuzzleCount = MutableLiveData<Int>()
    val myPuzzleCount: LiveData<Int> get() = _myPuzzleCount
    private val _isReviewDay = MutableLiveData<Boolean>()
    val isReviewDay: LiveData<Boolean> get() = _isReviewDay
    private val _hasTodayReview = MutableLiveData<Boolean>()
    val hasTodayReview: LiveData<Boolean> get() = _hasTodayReview
    private val _teamPuzzleBoardCount = MutableLiveData<Int>()
    val teamPuzzleBoardCount: LiveData<Int> get() = _teamPuzzleBoardCount

    private var _teamPuzzleBoardList: MutableLiveData<List<TeamPuzzleBoard>> = MutableLiveData()
    val teamPuzzleBoardList: LiveData<List<TeamPuzzleBoard>>
        get() = _teamPuzzleBoardList

    private val _teamPuzzleImage = MutableLiveData<List<String>>()
    val teamPuzzleImage: LiveData<List<String>> get() = _teamPuzzleImage

    private val _teamReviewDate = MutableLiveData<List<String>>()
    val teamReviewDate: LiveData<List<String>> get() = _teamReviewDate

    private val _reviewMemberPercent = MutableLiveData<List<String>>()
    val reviewMemberPercent: LiveData<List<String>> get() = _reviewMemberPercent

    private val _isSuccess = MutableLiveData(false)
    val isSuccess: LiveData<Boolean> get() = _isSuccess

    private var _teamRankingList: MutableLiveData<List<TeamRanking>> = MutableLiveData(null)
    val teamRankingList: LiveData<List<TeamRanking>>
        get() = _teamRankingList

    private val _memberRank = MutableLiveData<List<Int>>()
    val memberRank: LiveData<List<Int>> get() = _memberRank

    private val _memberNickname = MutableLiveData<List<String>>()
    val memberNickname: LiveData<List<String>> get() = _memberNickname

    private val _memberRole = MutableLiveData<List<String>>()
    val memberRole: LiveData<List<String>> get() = _memberRole

    private val _memberPuzzleCount = MutableLiveData<List<Int>>()
    val memberPuzzleCount: LiveData<List<Int>> get() = _memberPuzzleCount

    init {
        getTeamPuzzleData()
        getTeamPuzzleBoard()
        getTeamRanking()
    }

    private fun getTeamPuzzleData() = viewModelScope.launch {
        repository.getTeamPuzzle(UserInfo.PROJECT_ID, UserInfo.TODAY)
            .onSuccess { response ->
                _isSuccess.value = true
                Log.d("team", "getTeamPuzzleData() success:: $response")
                _myNickname.value = response.data.myPuzzle.nickname
                _myPuzzleCount.value = response.data.myPuzzle.puzzleCount
                _isReviewDay.value = response.data.isReviewDay
                _hasTodayReview.value = response.data.hasTodayReview
                _teamPuzzleBoardCount.value = response.data.teamPuzzleBoardCount
                Log.d("team", "myNickname success:: ${_myNickname.value}")
                Log.d("team", "_teamPuzzleBoardCount success:: ${_teamPuzzleBoardCount.value}")
                Log.d("team", "hasTodayReview success:: ${_hasTodayReview.value}")
            }
            .onFailure {
                Log.d("team", "getTeamPuzzleData() Fail:: $it")
            }
    }

    private fun getTeamPuzzleBoard() = viewModelScope.launch {
        repository.getTeamPuzzleBoard(UserInfo.PROJECT_ID, UserInfo.TODAY)
            .onSuccess { response ->
                _teamPuzzleBoardList.value = response
                Log.d("team", "getTeamPuzzleBoard() success:: $response")

                val teamPuzzles: List<TeamPuzzleBoard> =
                    _teamPuzzleBoardList.value!!
                _teamReviewDate.value = teamPuzzles.map {
                    it.reviewDate?.substringOrNull(5)?.replace("-", "/") ?: ""
                }
                _reviewMemberPercent.value = teamPuzzles.map { it.reviewMemberPercent ?: "" }
                _teamPuzzleImage.value = teamPuzzles.map { it.puzzleAssetName }
                Log.d("team", "_teamReviewDate:: ${_teamReviewDate.value}")
                Log.d("team", "_reviewMemberPercent:: ${_reviewMemberPercent.value}")
                Log.d("team", "_teamPuzzleImage:: ${_teamPuzzleImage.value}")
            }
            .onFailure {
                Log.d("team", "getTeamPuzzleBoard() Fail:: $it")
            }
    }

    private fun getTeamRanking() = viewModelScope.launch {
        repository.getTeamRanking(UserInfo.PROJECT_ID).onSuccess { response ->
            _teamRankingList.value = response
            val teamRanks: List<TeamRanking> =
                _teamRankingList.value!!
            _memberRank.value = teamRanks.map { it.memberRank }
            _memberNickname.value = teamRanks.map { it.memberNickname }
            _memberRole.value = teamRanks.map { it.memberRole }
            _memberPuzzleCount.value = teamRanks.map { it.memberPuzzleCount ?: -1 }

            _memberNickname.value = truncateMemberNicknameList(_memberNickname.value ?: emptyList())

            Log.d("team", "getTeamRanking() success:: $response")
            Log.d("team", "_memberRank:: ${_memberRank.value}")
            Log.d("team", "_memberNickname:: ${_memberNickname.value}")
            Log.d("team", "_memberRole:: ${_memberRole.value}")
            Log.d("team", "_memberPuzzleCount:: ${_memberPuzzleCount.value}")
        }.onFailure {
            Log.d("team", "getTeamRanking() Fail:: $it")
        }
    }

    fun truncateMemberNicknameList(nicknameList: List<String>): List<String> {
        val maxLength = 4
        val truncatedList = mutableListOf<String>()

        for (nickname in nicknameList) {
            val truncatedNickname = if (nickname.length <= maxLength) {
                nickname
            } else {
                nickname.substring(0, maxLength - 1) + "..."
            }
            truncatedList.add(truncatedNickname)
        }

        return truncatedList
    }

    fun String.substringOrNull(startIndex: Int): String? {
        if (startIndex >= length) {
            return null
        }
        return substring(startIndex)
    }
}
