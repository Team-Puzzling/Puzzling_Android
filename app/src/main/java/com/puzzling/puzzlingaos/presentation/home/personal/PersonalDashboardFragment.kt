package com.puzzling.puzzlingaos.presentation.home.personal

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseFragment
import com.puzzling.puzzlingaos.databinding.FragmentPersonalDashboardBinding

class PersonalDashboardFragment :
    BaseFragment<FragmentPersonalDashboardBinding>(R.layout.fragment_personal_dashboard) {
    private val viewModel by viewModels<PersonalDashboardViewModel>()

    private var _actionPlanAdapter: ActionPlanListAdapter? = null
    private val actionPlanAdapter
        get() = requireNotNull(_actionPlanAdapter) { "adapter is null !!" }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActionPlanAdapter()
    }

    private fun setActionPlanAdapter() {
        _actionPlanAdapter = ActionPlanListAdapter()
        _actionPlanAdapter?.submitList(viewModel.actionPlanList)
        binding.rcvPersonalView.also {
            it.adapter = _actionPlanAdapter
        }
    }
}
