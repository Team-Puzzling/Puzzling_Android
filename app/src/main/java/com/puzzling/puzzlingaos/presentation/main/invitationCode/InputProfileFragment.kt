package com.puzzling.puzzlingaos.presentation.main.invitationCode

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseFragment
import com.puzzling.puzzlingaos.databinding.FragmentInputProfileBinding

class InputProfileFragment :
    BaseFragment<FragmentInputProfileBinding>(R.layout.fragment_input_profile) {

    private lateinit var viewModel: InvitationCodeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[InvitationCodeViewModel::class.java]
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel
    }
}
