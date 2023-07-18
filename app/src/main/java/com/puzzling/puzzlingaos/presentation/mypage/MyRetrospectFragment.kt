package com.puzzling.puzzlingaos.presentation.mypage

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseFragment
import com.puzzling.puzzlingaos.data.model.response.ResponseMyRetroListDto
import com.puzzling.puzzlingaos.databinding.FragmentMyRetrospectBinding
import com.puzzling.puzzlingaos.presentation.mypage.adapter.MyRetroContentAdapter
import com.puzzling.puzzlingaos.presentation.mypage.adapter.MyRetroTitleAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyRetrospectFragment :
    BaseFragment<FragmentMyRetrospectBinding>(R.layout.fragment_my_retrospect) {

    private lateinit var viewModel: MyRetrospectViewModel

    private val dummyItemList = mutableListOf(
        ResponseMyRetroListDto.ReviewData(23, "2023-07-12", "여기는 20글자 정도 노출되고,,"),
        ResponseMyRetroListDto.ReviewData(
            12,
            "2023-07-24",
            "유저가 회고 템플릿에서 가장 첫 번째 인풋창에 입력한 값 노출.. ",
        ),
        ResponseMyRetroListDto.ReviewData(5, "2023-07-27", "여기는18글자정도노출되고나머지부분..."),
        ResponseMyRetroListDto.ReviewData(7, "2023-07-5", "여기는18글자정도노출되고나머지부분..."),
        ResponseMyRetroListDto.ReviewData(8, "2023-08-02", "여기는18글자정도노출되고나머지부분..."),
        ResponseMyRetroListDto.ReviewData(10, "2023-06-22", "여기는18글자정도노출되고나머지부분..."),
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MyRetrospectViewModel::class.java]
        viewModel.getMyProjectReview()
        initToolbar()
        initAdapter()
    }

    private fun initToolbar() {
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.tbMyRetrospectMain)
        activity.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }
        binding.tbMyRetrospectMain.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun initAdapter() {
        val myRetroContentAdapter = MyRetroContentAdapter()
        val myRetroTitleAdapter = MyRetroTitleAdapter("Piickle")

        viewModel.responseReveiew.observe(this) {
            myRetroContentAdapter.submitList(it)
        }

        myRetroTitleAdapter.setOnItemClickListener(object :
            MyRetroTitleAdapter.OnItemClickListener {
            override fun onItemClick(v: View, data: String, pos: Int) {
                val chooseProjectBottomFragment = ChooseProjectBottomFragment()
                chooseProjectBottomFragment.show(requireActivity().supportFragmentManager, "show")
            }
        })
        val concatAdapter = ConcatAdapter(myRetroTitleAdapter, myRetroContentAdapter)

        with(binding.rcvMyRetroMain) {
            adapter = concatAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}
