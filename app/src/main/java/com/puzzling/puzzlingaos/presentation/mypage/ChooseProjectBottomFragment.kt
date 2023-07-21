package com.puzzling.puzzlingaos.presentation.mypage

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BottomSheetDialogFragment
import com.puzzling.puzzlingaos.data.model.response.ResponseMyPageProjectDto
import com.puzzling.puzzlingaos.databinding.FragmentBottomChooseProjectBinding
import com.puzzling.puzzlingaos.domain.entity.Project
import com.puzzling.puzzlingaos.presentation.mypage.adapter.ChooseProjectAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChooseProjectBottomFragment :
    BottomSheetDialogFragment<FragmentBottomChooseProjectBinding>(R.layout.fragment_bottom_choose_project) {

    private lateinit var viewModel: MyRetrospectViewModel

    private val dummyItemList = mutableListOf<ResponseMyPageProjectDto>(
        ResponseMyPageProjectDto("Piickle", "2023-07-03", 2),
        ResponseMyPageProjectDto("HARA", "2023-07-28", 3),
        ResponseMyPageProjectDto("낫투두", "2023-07-12", 4),
        ResponseMyPageProjectDto("PEEKABOOK", "2023-07-20", 5),
        ResponseMyPageProjectDto("킵고잇", "2023-06-25", 8),
        ResponseMyPageProjectDto("킵고잇", "2023-06-25", 8),
        ResponseMyPageProjectDto("Piickle", "2023-07-03", 2),
        ResponseMyPageProjectDto("HARA", "2023-07-28", 3),
        ResponseMyPageProjectDto("낫투두", "2023-07-12", 4),
        ResponseMyPageProjectDto("PEEKABOOK", "2023-07-20", 5),
        ResponseMyPageProjectDto("킵고잇", "2023-06-25", 8),
        ResponseMyPageProjectDto("킵고잇", "2023-06-25", 8),
        ResponseMyPageProjectDto("Piickle", "2023-07-03", 2),
        ResponseMyPageProjectDto("HARA", "2023-07-28", 3),
        ResponseMyPageProjectDto("낫투두", "2023-07-12", 4),
        ResponseMyPageProjectDto("PEEKABOOK", "2023-07-20", 5),
        ResponseMyPageProjectDto("킵고잇", "2023-06-25", 8),
        ResponseMyPageProjectDto("킵고잇", "2023-06-25", 8),
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MyRetrospectViewModel::class.java]
        initAdapter()
    }

    private fun initAdapter() {
        val chooseProjectAdapter = ChooseProjectAdapter(::clickProjectItem)

        viewModel.currentProject.observe(this) {
            chooseProjectAdapter.currentProject = it.projectName
        }

        binding.rcvMyRetroChooseProject.adapter = chooseProjectAdapter
        viewModel.responseProjectList.observe(this) {
            chooseProjectAdapter.setItemList(it)
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
