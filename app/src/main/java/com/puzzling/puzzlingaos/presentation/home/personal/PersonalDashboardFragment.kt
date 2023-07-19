package com.puzzling.puzzlingaos.presentation.home.personal

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseFragment
import com.puzzling.puzzlingaos.databinding.FragmentPersonalDashboardBinding
import com.puzzling.puzzlingaos.presentation.writeRetrospective.WriteRetrospectiveActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonalDashboardFragment :
    BaseFragment<FragmentPersonalDashboardBinding>(R.layout.fragment_personal_dashboard) {
    private val viewModel by viewModels<PersonalDashboardViewModel>()

    private var _actionPlanAdapter: ActionPlanListAdapter? = null
    private val actionPlanAdapter
        get() = requireNotNull(_actionPlanAdapter) { "adapter is null !!" }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        setActionPlanAdapter()
        clickBottomBtn()
        clickMyPuzzleBoardBtn()
        clickPuzzlePiece()
    }

    private fun setActionPlanAdapter() {
        _actionPlanAdapter = ActionPlanListAdapter()
//        _actionPlanAdapter?.submitList(viewModel.actionPlanList.value)
        viewModel.actionPlanList.observe(this) {
            binding.rcvPersonalView.adapter = _actionPlanAdapter
            _actionPlanAdapter!!.submitList(it)
        }
    }

    private fun setPuzzlePiece() {
    }

    private fun clickBottomBtn() {
        binding.clPersonalBottomBtn.setOnClickListener {
            val intent = Intent(context, WriteRetrospectiveActivity::class.java)
            startActivity(intent)
        }
    }

    private fun clickMyPuzzleBoardBtn() {
        binding.clPersonalTopBackground.setOnClickListener {
            activity?.let {
                val intent = Intent(context, MyPuzzleBoardActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun clickPuzzlePiece() {
        with(binding) {
            // TODO 각 퍼즐 조각 클릭 -> 각 날짜의 회고 상세조회로 넘어가는 로직 추가
            clPersonalMain1.setOnClickListener {
            }
        }
    }
}
