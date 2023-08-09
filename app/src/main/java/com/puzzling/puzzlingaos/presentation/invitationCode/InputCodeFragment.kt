package com.puzzling.puzzlingaos.presentation.invitationCode

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseFragment
import com.puzzling.puzzlingaos.databinding.FragmentInputCodeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InputCodeFragment : BaseFragment<FragmentInputCodeBinding>(R.layout.fragment_input_code) {

    private val viewModel by activityViewModels<InvitationCodeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        isCodeSuccess()
    }

    private fun isCodeSuccess() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.isCodeSuccess.collect {
                    if (it != null && it) {
                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(R.id.fcv_invitation_main, InputProfileFragment())
                            .commit()
                    }
                }
            }
        }
    }
}
