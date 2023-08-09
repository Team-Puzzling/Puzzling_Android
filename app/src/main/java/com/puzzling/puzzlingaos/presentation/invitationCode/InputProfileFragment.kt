package com.puzzling.puzzlingaos.presentation.invitationCode

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseFragment
import com.puzzling.puzzlingaos.databinding.FragmentInputProfileBinding
import com.puzzling.puzzlingaos.presentation.home.HomeViewModel
import com.puzzling.puzzlingaos.presentation.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InputProfileFragment :
    BaseFragment<FragmentInputProfileBinding>(R.layout.fragment_input_profile) {

    private val viewModel by activityViewModels<InvitationCodeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isJoinProjectSuccess()

        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel
    }

    private fun isJoinProjectSuccess() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.isProfileSuccess.collect {
                    if (it != null && it) {
                        val intent = Intent(activity, MainActivity::class.java)
                        intent.putExtra("homeProjectId", viewModel.codeResponse.value?.projectId)
                        intent.putExtra("Title", viewModel.codeResponse.value?.projectName)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}
