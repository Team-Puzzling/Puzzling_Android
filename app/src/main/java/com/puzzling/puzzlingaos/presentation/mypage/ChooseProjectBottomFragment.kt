package com.puzzling.puzzlingaos.presentation.mypage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BottomSheetDialogFragment
import com.puzzling.puzzlingaos.databinding.FragmentBottomChooseProjectBinding
import com.puzzling.puzzlingaos.domain.entity.Project
import com.puzzling.puzzlingaos.presentation.mypage.adapter.ChooseProjectAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChooseProjectBottomFragment :
    BottomSheetDialogFragment<FragmentBottomChooseProjectBinding>(R.layout.fragment_bottom_choose_project) {

    private val viewModel by activityViewModels<MyRetrospectViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        val chooseProjectAdapter = ChooseProjectAdapter(::clickProjectItem)

        viewModel.currentProject.observe(this) {
            chooseProjectAdapter.currentProject = it.projectName
        }

        binding.rcvMyRetroChooseProject.adapter = chooseProjectAdapter
        viewModel.responseProjectList.observe(this) {
            chooseProjectAdapter.submitList(it)
        }
    }

    private fun clickProjectItem(currentProject: Project) {
        viewModel.setCurrentProject(currentProject)
        viewLifecycleOwner.lifecycleScope.launch {
            delay(100)
            dismiss()
        }
    }
}
