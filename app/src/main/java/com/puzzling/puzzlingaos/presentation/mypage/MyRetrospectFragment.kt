package com.puzzling.puzzlingaos.presentation.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseFragment
import com.puzzling.puzzlingaos.databinding.FragmentMyRetrospectBinding
import com.puzzling.puzzlingaos.domain.entity.ProjectReview
import com.puzzling.puzzlingaos.presentation.detailRetrospect.DetailRetroActivity
import com.puzzling.puzzlingaos.presentation.mypage.adapter.MyRetroContentAdapter
import com.puzzling.puzzlingaos.presentation.mypage.adapter.MyRetroTitleAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyRetrospectFragment :
    BaseFragment<FragmentMyRetrospectBinding>(R.layout.fragment_my_retrospect) {

    private lateinit var viewModel: MyRetrospectViewModel
    private var currentTitle = "piikle"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MyRetrospectViewModel::class.java]
        viewModel.currentProject.observe(this) {
            Log.d("myPage 회고 뷰", "$it")
            viewModel.getMyProjectReview(it.projectId)
        }
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
        val myRetroTitleAdapter = MyRetroTitleAdapter()

        viewModel.currentProject.observe(viewLifecycleOwner) {
            myRetroTitleAdapter.projectName = it.projectName
            myRetroContentAdapter.notifyDataSetChanged()
        }

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

        myRetroContentAdapter.setOnItemClickListener(object :
            MyRetroContentAdapter.OnItemClickListener {
            override fun onItemClick(v: View, item: ProjectReview, pos: Int) {
                val intent = Intent(activity, DetailRetroActivity::class.java)
                intent.putExtra("Title", viewModel.currentProject.value?.projectName)
                intent.putExtra("homeProjectId", viewModel.currentProject.value?.projectId)
                startActivity(intent)
            }
        })
        val concatAdapter = ConcatAdapter(myRetroTitleAdapter, myRetroContentAdapter)

        with(binding.rcvMyRetroMain) {
            adapter = concatAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}
