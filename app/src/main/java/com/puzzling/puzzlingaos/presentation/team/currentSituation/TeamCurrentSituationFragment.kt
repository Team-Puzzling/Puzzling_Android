package com.puzzling.puzzlingaos.presentation.team.currentSituation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseFragment
import com.puzzling.puzzlingaos.databinding.FragmentTeamCurrentSituationBinding
import com.puzzling.puzzlingaos.util.ViewModelFactory

class TeamCurrentSituationFragment : BaseFragment<FragmentTeamCurrentSituationBinding>(R.layout.fragment_team_current_situation) {

    private val viewModel: TeamCurrentSituationViewModel by viewModels { ViewModelFactory(requireContext()) }
    private lateinit var retrospectListAdapter: RetrospectListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        retrospectListAdapter = RetrospectListAdapter(viewModel)
        binding.viewModel = viewModel

        makeRetrospectListAdpater()
    }

    private fun makeRetrospectListAdpater() {
        binding.rcvTeamRetrospectList.adapter = RetrospectListAdapter(viewModel)
        // retrospectListAdapter.setItemList()
    }
}
