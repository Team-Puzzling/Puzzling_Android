package com.puzzling.puzzlingaos.presentation.home.personal

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.puzzling.puzzlingaos.domain.entity.ActionPlan

class PersonalDashboardViewModel : ViewModel() {
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
