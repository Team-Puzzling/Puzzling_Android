package com.puzzling.puzzlingaos.presentation.home.team

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puzzling.puzzlingaos.domain.entity.TeamPuzzleBoard
import com.puzzling.puzzlingaos.domain.entity.TeamRanking
import com.puzzling.puzzlingaos.domain.repository.TeamDashBoardRepository
import com.puzzling.puzzlingaos.domain.usecase.teamdashboard.GetTeamPuzzleUseCase
import com.puzzling.puzzlingaos.domain.usecase.teamdashboard.GetTeamRankingUseCase
import com.puzzling.puzzlingaos.util.UserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// TODO Team puzzle data get 조회 로직 수정
@HiltViewModel
class TeamDashBoardViewModel @Inject constructor(
    private val repository: TeamDashBoardRepository,
    private val getTeamPuzzleUseCase: GetTeamPuzzleUseCase,
    private val getTeamRankingUseCase: GetTeamRankingUseCase,
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

    val firstProjectId = MutableLiveData<Int>()

//    init {
//        getTeamPuzzleData()
//        getTeamPuzzleBoard()
//        getTeamRanking()
//    }

    fun getTeamPuzzleData(projectId: Int) = viewModelScope.launch {
        getTeamPuzzleUseCase(projectId, UserInfo.TODAY)
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

    fun getTeamPuzzleBoard(projectId: Int) = viewModelScope.launch {
        repository.getTeamPuzzleBoard(projectId, UserInfo.TODAY)
            .onSuccess { response ->
                _teamPuzzleBoardList.value = response

                val teamPuzzles: List<TeamPuzzleBoard> =
                    _teamPuzzleBoardList.value!!
                _teamReviewDate.value = teamPuzzles.map {
                    it.reviewDate?.substringOrNull(5)?.replace("-", "/") ?: ""
                }
                _reviewMemberPercent.value = teamPuzzles.map { it.reviewMemberPercent ?: "" }
                _teamPuzzleImage.value = teamPuzzles.map { it.puzzleAssetName }
            }
            .onFailure {
                Log.d("team", "getTeamPuzzleBoard() Fail:: $it")
            }
    }

    fun getTeamRanking(projectId: Int) = viewModelScope.launch {
        getTeamRankingUseCase(projectId).onSuccess { response ->
            _teamRankingList.value = response
            val teamRanks: List<TeamRanking> =
                _teamRankingList.value!!
            _memberRank.value = teamRanks.map { it.memberRank }
            _memberNickname.value = teamRanks.map { it.memberNickname }
            _memberRole.value = teamRanks.map { it.memberRole }
            _memberPuzzleCount.value = teamRanks.map { it.memberPuzzleCount }

            _memberNickname.value = truncateMemberNicknameList(_memberNickname.value ?: emptyList())
            _memberRole.value = truncateMemberRoleList(_memberRole.value ?: emptyList())

            Log.d("team", "getTeamRanking() success:: $response")
            Log.d("team", "_memberRank:: ${_memberRank.value}")
            Log.d("team", "_memberNickname:: ${_memberNickname.value}")
            Log.d("team", "_memberRole:: ${_memberRole.value}")
            Log.d("team", "_memberPuzzleCount:: ${_memberPuzzleCount.value}")
        }.onFailure {
            Log.d("team", "getTeamRanking() Fail:: $it")
        }
    }

    private fun truncateMemberNicknameList(nicknameList: List<String>): List<String> {
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

    private fun truncateMemberRoleList(nicknameList: List<String>): List<String> {
        val maxLength = 10
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

    private fun String.substringOrNull(startIndex: Int): String? {
        if (startIndex >= length) {
            return null
        }
        return substring(startIndex)
    }
}
