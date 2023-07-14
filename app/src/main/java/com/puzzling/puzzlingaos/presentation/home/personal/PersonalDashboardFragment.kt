package com.puzzling.puzzlingaos.presentation.home.personal

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseFragment
import com.puzzling.puzzlingaos.databinding.FragmentPersonalDashboardBinding
import com.puzzling.puzzlingaos.presentation.writeRetrospective.WriteRetrospectiveActivity

class PersonalDashboardFragment :
    BaseFragment<FragmentPersonalDashboardBinding>(R.layout.fragment_personal_dashboard) {
    private val viewModel by viewModels<PersonalDashboardViewModel>()

    private var _actionPlanAdapter: ActionPlanListAdapter? = null
    private val actionPlanAdapter
        get() = requireNotNull(_actionPlanAdapter) { "adapter is null !!" }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActionPlanAdapter()
        clickBottomBtn()
        clickPuzzleBoardBtn()
    }

    private fun setActionPlanAdapter() {
        _actionPlanAdapter = ActionPlanListAdapter()
        _actionPlanAdapter?.submitList(viewModel.actionPlanList)
        binding.rcvPersonalView.also {
            it.adapter = _actionPlanAdapter
        }
    }

    private fun clickBottomBtn() {
        binding.clPersonalBottomBtn.setOnClickListener {
            activity?.let {
                val intent = Intent(context, WriteRetrospectiveActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun clickPuzzleBoardBtn() {
        binding.clPersonalTopBackground.setOnClickListener {
            // 내 퍼즐판 보러가기
            val fragment = MyPuzzleBoardFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fcv_home_container, fragment)
            fragmentTransaction.addToBackStack(null) // Optional: Add to back stack for fragment navigation
            fragmentTransaction.commit()
        }
    }
}
