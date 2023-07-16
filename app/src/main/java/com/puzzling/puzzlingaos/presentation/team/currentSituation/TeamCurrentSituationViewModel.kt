package com.puzzling.puzzlingaos.presentation.team.currentSituation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.puzzling.puzzlingaos.data.repository.TeamCurrentSituationRepositoryImpl

class TeamCurrentSituationViewModel(private val teamCurrentSituationRepositoryImpl: TeamCurrentSituationRepositoryImpl) : ViewModel() {

    var isWeekRetrospectColor: MutableLiveData<Boolean> = MutableLiveData(true)
    var doRetrospectNull: MutableLiveData<Boolean> = MutableLiveData(true)

    fun weekTeamCurrentRound() {}


}
