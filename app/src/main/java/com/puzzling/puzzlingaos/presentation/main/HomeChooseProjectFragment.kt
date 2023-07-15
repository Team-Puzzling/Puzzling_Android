package com.puzzling.puzzlingaos.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BottomSheetDialogFragment
import com.puzzling.puzzlingaos.data.model.response.ResponseMyPageProjectDto
import com.puzzling.puzzlingaos.databinding.FragmentHomeChooseProjectBinding
import com.puzzling.puzzlingaos.presentation.invitationCode.InvitationCodeActivity
import com.puzzling.puzzlingaos.presentation.register.RegisterActivity

class HomeChooseProjectFragment :
    BottomSheetDialogFragment<FragmentHomeChooseProjectBinding>(R.layout.fragment_home_choose_project) {

    private val dummyItemList = mutableListOf<ResponseMyPageProjectDto>(
        ResponseMyPageProjectDto("Piickle", "2023-07-03", 2),
        ResponseMyPageProjectDto("HARA", "2023-07-28", 3),
        ResponseMyPageProjectDto("낫투두", "2023-07-12", 4),
        ResponseMyPageProjectDto("PEEKABOOK", "2023-07-20", 5),
        ResponseMyPageProjectDto("ZOOC", "2023-06-25", 9),
//        ResponseMyPageProjectDto("킵고잇", "2023-06-25", 8),
//        ResponseMyPageProjectDto("하", "2023-06-25", 8),
//        ResponseMyPageProjectDto(
//            "흠",
//            "2023-06-25",
//            8,
//
//        ),
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBottomSheetAdapter()
        clickBottomBtn()
    }

    private fun setBottomSheetAdapter() {
        val chooseProjectAdapter = HomeChooseProjectAdapter("PEEKABOOK")
        binding.rcvHomeChooseProject.adapter = chooseProjectAdapter
        chooseProjectAdapter.setItemList(dummyItemList)
    }

    private fun clickBottomBtn() {
        with(binding) {
            clHomeChooseProject.setOnClickListener {
                activity?.let {
                    val intent = Intent(context, RegisterActivity::class.java)
                    startActivity(intent)
                }
            }
            clHomeJoinProject.setOnClickListener {
                activity?.let {
                    val intent = Intent(context, InvitationCodeActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}
