package com.puzzling.puzzlingaos.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BottomSheetDialogFragment
import com.puzzling.puzzlingaos.databinding.FragmentHomeChooseProjectBinding
import com.puzzling.puzzlingaos.presentation.home.HomeViewModel
import com.puzzling.puzzlingaos.presentation.invitationCode.InvitationCodeActivity
import com.puzzling.puzzlingaos.presentation.register.RegisterActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeChooseProjectFragment :
    BottomSheetDialogFragment<FragmentHomeChooseProjectBinding>(R.layout.fragment_home_choose_project) {
    private val viewModel by activityViewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBottomSheetAdapter()
        clickBottomBtn()
    }

    private fun initBottomSheetAdapter() {
        val chooseProjectAdapter = HomeChooseProjectAdapter(::clickProjectItem)

        binding.rcvHomeChooseProject.adapter = chooseProjectAdapter
        chooseProjectAdapter.submitList(viewModel.projectList.value)
    }

    private fun clickBottomBtn() {
        with(binding) {
            clHomeChooseProject.setOnClickListener {
                activity?.let {
                    val intent = Intent(context, RegisterActivity::class.java)
                    startActivity(intent)
                }
                dismiss()
            }
            clHomeJoinProject.setOnClickListener {
                activity?.let {
                    val intent = Intent(context, InvitationCodeActivity::class.java)
                    startActivity(intent)
                }
                dismiss()
            }
        }
    }

    private fun clickProjectItem(projectName: String) {
        viewModel.setSelectedProjectText(projectName)
        viewLifecycleOwner.lifecycleScope.launch {
            delay(100)
            dismiss()
        }
    }
}
