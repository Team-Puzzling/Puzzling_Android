package com.puzzling.puzzlingaos.presentation.home

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResultListener
import com.google.android.material.tabs.TabLayout
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseFragment
import com.puzzling.puzzlingaos.databinding.FragmentHomeBinding
import com.puzzling.puzzlingaos.presentation.home.personal.PersonalDashboardFragment
import com.puzzling.puzzlingaos.presentation.home.team.TeamDashboardFragment
import com.puzzling.puzzlingaos.presentation.main.HomeChooseProjectFragment
import com.puzzling.puzzlingaos.presentation.register.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel by activityViewModels<HomeViewModel>()
    private val registerViewModel by activityViewModels<RegisterViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        observeProjectId()
        initTabItem()
        showProjectBottomSheet()
        clickTabItem()
        showPopupMessage()
        handleSelectedProject()
        getPopupContent()
    }

    private fun getPopupContent() {
        viewModel.selectedProjectId.observe(this) {
            viewModel.getProjectWeekCycle(it)
        }

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
                    ),
                    startIndex,
                    endIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE,
                )
            }

            binding.tvHomePopupContent.text = spannableString
            binding.tvHomePopupTitle.text = it?.projectName
        }
    }

    private fun initTabItem() {
        parentFragmentManager.commit {
            replace(R.id.fcv_home_container, PersonalDashboardFragment())
        }
    }

    private fun clickTabItem() {
        binding.tlHomeDashboard.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val fragment: Fragment = when (tab.position) {
                    0 -> PersonalDashboardFragment()
                    1 -> TeamDashboardFragment()
                    else -> PersonalDashboardFragment()
                }
                parentFragmentManager.commit {
                    replace(R.id.fcv_home_container, fragment)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                // Not used
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // Not used
            }
        })
    }

    private fun showProjectBottomSheet() {
        binding.clHomeProjectMain.setOnClickListener {
            val chooseProjectFragment = HomeChooseProjectFragment()
            chooseProjectFragment.show(parentFragmentManager, "show")
        }
    }

    private fun showPopupMessage() {
        binding.btnHomeNotification.setOnClickListener {
            val isCardVisible = binding.cvHomePopup.isVisible
            binding.cvHomePopup.isVisible = !isCardVisible
        }
    }

    private fun handleSelectedProject() {
        viewModel.isProjectNameSelected.observe(viewLifecycleOwner) {
            Log.d("home", "isProjectSelected: ${viewModel.isProjectNameSelected.value}")
            val projectName = viewModel.selectedProjectName.value
            binding.tvHomeProjectName.text = projectName.toString()
            Log.d("home", "tvHomeProjectName: ${binding.tvHomeProjectName.text}")

            setFragmentResultListener("projectIdKey") { key, bundle ->
                val projectId = bundle.getString("projectId")
                Log.d("home", "Received projectId: $projectId")
                // Now you can use the projectId value as needed
            }
        }
//        setFragmentResultListener("projectIdKey") { key, bundle ->
//            val projectId = bundle.getInt("projectId").toString()
//            Log.d("Received", "Received projectId: $projectId")
//        }
    }

    private fun observeProjectId() {
        registerViewModel.projectId.observe(this) {
            Log.d(
                "home",
                "registerViewModel.projectId::: ${registerViewModel.projectId.value}",
            )
            binding.tvHomeProjectName.text = registerViewModel.projectId.value.toString()
        }
    }
}
