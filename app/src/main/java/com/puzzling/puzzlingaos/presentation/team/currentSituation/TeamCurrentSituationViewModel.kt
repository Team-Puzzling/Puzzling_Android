package com.puzzling.puzzlingaos.presentation.team.currentSituation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TeamCurrentSituationViewModel() : ViewModel() {

    var isWeekRetrospectColor: MutableLiveData<Boolean> = MutableLiveData(true)
    var doRetrospectNull: MutableLiveData<Boolean> = MutableLiveData(true)

    fun weekTeamCurrentRound() {}
}
