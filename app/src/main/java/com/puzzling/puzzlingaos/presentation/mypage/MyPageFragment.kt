package com.puzzling.puzzlingaos.presentation.mypage

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseFragment
import com.puzzling.puzzlingaos.data.model.response.ResponseMyPageProjectDto
import com.puzzling.puzzlingaos.databinding.FragmentMyPageBinding
import com.puzzling.puzzlingaos.presentation.mypage.adapter.MyProjectBottomAdapter
import com.puzzling.puzzlingaos.presentation.mypage.adapter.MyProjectContentAdapter
import com.puzzling.puzzlingaos.presentation.mypage.adapter.MyProjectTitleAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    private val dummyItemList = mutableListOf<ResponseMyPageProjectDto>(
        ResponseMyPageProjectDto("Piickle", "2023-07-03", 2),
        ResponseMyPageProjectDto("HARA", "2023-07-28", 3),
        ResponseMyPageProjectDto("낫투두", "2023-07-12", 4),
        ResponseMyPageProjectDto("PEEKABOOK", "2023-07-20", 5),
        ResponseMyPageProjectDto("킵고잇", "2023-06-25", 8),
        ResponseMyPageProjectDto("킵고잇", "2023-06-25", 8),
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        showPopupMessage()
    }

    private fun initAdapter() {
        val myProjectContentAdapter = MyProjectContentAdapter()
        val concatAdapter =
            ConcatAdapter(
                MyProjectTitleAdapter("지니"),
                myProjectContentAdapter,
                MyProjectBottomAdapter(),
            )

        with(binding) {
            rcvMyPageMain.adapter = concatAdapter
            rcvMyPageMain.layoutManager = LinearLayoutManager(activity)
        }
        myProjectContentAdapter.submitList(dummyItemList)

        myProjectContentAdapter.setOnItemClickListener(object :
            MyProjectContentAdapter.OnItemClickListener {
            override fun onItemClick(v: View, data: ResponseMyPageProjectDto, pos: Int) {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fcv_main_container, MyRetrospectFragment()).addToBackStack(null)
                    .commit()
            }
        })
    }

    private fun showPopupMessage() {
        binding.btnHomeNotification.setOnClickListener {
            val isCardVisible = binding.cvMypagePopup.isVisible
            binding.cvMypagePopup.isVisible = !isCardVisible
        }
    }
}
