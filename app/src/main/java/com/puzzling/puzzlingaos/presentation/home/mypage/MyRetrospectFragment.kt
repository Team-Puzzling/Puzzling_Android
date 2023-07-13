package com.puzzling.puzzlingaos.presentation.home.mypage

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseFragment
import com.puzzling.puzzlingaos.data.model.response.ResponseMyRetroListDto
import com.puzzling.puzzlingaos.databinding.FragmentMyRetrospectBinding
import com.puzzling.puzzlingaos.presentation.home.mypage.adapter.MyProjectTopAdapter
import com.puzzling.puzzlingaos.presentation.home.mypage.adapter.MyRetroAdapter

class MyRetrospectFragment :
    BaseFragment<FragmentMyRetrospectBinding>(R.layout.fragment_my_retrospect) {

    private val dummyItemList = mutableListOf(
        ResponseMyRetroListDto(23, "2023-07-12", "여기는 20글자 정도 노출되고,,"),
        ResponseMyRetroListDto(12, "2023-07-24", "유저가 회고 템플릿에서 가장 첫 번째 인풋창에 입력한 값 노출.. "),
        ResponseMyRetroListDto(5, "2023-07-27", "여기는18글자정도노출되고나머지부분..."),
        ResponseMyRetroListDto(7, "2023-07-5", "여기는18글자정도노출되고나머지부분..."),
        ResponseMyRetroListDto(8, "2023-08-02", "여기는18글자정도노출되고나머지부분..."),
        ResponseMyRetroListDto(10, "2023-06-22", "여기는18글자정도노출되고나머지부분..."),
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fcv_main_container, MyPageFragment())
                    .commit()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun initAdapter() {
        val myRetroAdapter = MyRetroAdapter()
        val concatAdapter = ConcatAdapter(MyProjectTopAdapter("Piickle"), myRetroAdapter)

        with(binding) {
            rcvMyRetroMain.adapter = concatAdapter
            rcvMyRetroMain.layoutManager = LinearLayoutManager(activity)
        }
        myRetroAdapter.submitList(dummyItemList)
    }
}
