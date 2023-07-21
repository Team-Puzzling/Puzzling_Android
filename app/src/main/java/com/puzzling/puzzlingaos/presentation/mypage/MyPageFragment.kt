package com.puzzling.puzzlingaos.presentation.mypage

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseFragment
import com.puzzling.puzzlingaos.data.model.response.ResponseMyPageProjectDto
import com.puzzling.puzzlingaos.databinding.FragmentMyPageBinding
import com.puzzling.puzzlingaos.domain.entity.Project
import com.puzzling.puzzlingaos.presentation.home.HomeFragment
import com.puzzling.puzzlingaos.presentation.home.HomeViewModel
import com.puzzling.puzzlingaos.presentation.home.personal.PersonalDashboardViewModel
import com.puzzling.puzzlingaos.presentation.mypage.adapter.MyProjectBottomAdapter
import com.puzzling.puzzlingaos.presentation.mypage.adapter.MyProjectContentAdapter
import com.puzzling.puzzlingaos.presentation.mypage.adapter.MyProjectTitleAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    private lateinit var viewModel: MyRetrospectViewModel
    private val personalViewModel by activityViewModels<PersonalDashboardViewModel>()
    private val homeViewModel by activityViewModels<HomeViewModel>()

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
        viewModel = ViewModelProvider(requireActivity())[MyRetrospectViewModel::class.java]
        viewModel.getMyProjectList()
        homeViewModel.selectedProjectId.observe(this) {
            viewModel.getProjectWeekCycle(it)
        }
        initAdapter()
        showPopupMessage()

        getPopUpContent()
    }

    private fun getPopUpContent() {
        viewModel.retroWeek.observe(this) {
            val reviewCycleText = "매주 ${it?.projectReviewCycle} \n회고를 작성해주세요"

            // SpannableString 생성
            val spannableString = SpannableString(reviewCycleText)

            // "${it?.projectReviewCycle}" 부분의 텍스트 색상 변경
            val startIndex = reviewCycleText.indexOf(it?.projectReviewCycle ?: "")
            val endIndex = startIndex + (it?.projectReviewCycle?.length ?: 0)
            if (startIndex in 0 until endIndex) {
                spannableString.setSpan(
                    ForegroundColorSpan(
                        ContextCompat.getColor(
                            requireActivity(),
                            R.color.blue_400,
                        ),
                    ), // 원하는 색상으로 변경
                    startIndex,
                    endIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE,
                )
            }

            // SpannableString을 TextView에 설정
            binding.tvMypagePopupContent.text = spannableString
            binding.tvMypagePopupTitle.text = it?.projectName
        }
    }

    private fun initAdapter() {
        val myProjectContentAdapter = MyProjectContentAdapter()
        val myProjectTitleAdapter = MyProjectTitleAdapter()
        myProjectTitleAdapter.submitList(listOf(personalViewModel.myNickname.value ?: "김민주"))
        val concatAdapter =
            ConcatAdapter(
                myProjectTitleAdapter,
                myProjectContentAdapter,
                MyProjectBottomAdapter(),
            )

        with(binding) {
            rcvMyPageMain.adapter = concatAdapter
            rcvMyPageMain.layoutManager = LinearLayoutManager(activity)
        }

        viewModel.responseProjectList.observe(this) {
            myProjectContentAdapter.submitList(it)
        }

        myProjectContentAdapter.setOnItemClickListener(object :
            MyProjectContentAdapter.OnItemClickListener {
            override fun onItemClick(btn: Int, data: Project, pos: Int) {
                viewModel.setCurrentProject(data.projectName)
                if (btn == 0) {
                    homeViewModel.projectNameSetter(data.projectName)
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fcv_main_container, HomeFragment())
                        .addToBackStack(null)
                        .commit()
                } else {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fcv_main_container, MyRetrospectFragment())
                        .addToBackStack(null)
                        .commit()
                }
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
