package com.puzzling.puzzlingaos.presentation.home.mypage

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseFragment
import com.puzzling.puzzlingaos.data.model.response.ResponseMyPageProjectDto
import com.puzzling.puzzlingaos.databinding.FragmentMyPageBinding
import com.puzzling.puzzlingaos.presentation.home.mypage.adapter.MyProjectBottomAdapter
import com.puzzling.puzzlingaos.presentation.home.mypage.adapter.MyProjectAdapter
import com.puzzling.puzzlingaos.presentation.home.mypage.adapter.MyProjectTopAdapter

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
    }

    private fun initAdapter() {
        val myProjectAdapter = MyProjectAdapter()
        val concatAdapter = ConcatAdapter(MyProjectTopAdapter("지니"), myProjectAdapter, MyProjectBottomAdapter())

        with(binding) {
            rcvMyPageMain.adapter = concatAdapter
            rcvMyPageMain.layoutManager = LinearLayoutManager(activity)
        }
        myProjectAdapter.submitList(dummyItemList)
    }
}
