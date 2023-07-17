package com.puzzling.puzzlingaos.presentation.team.currentSituation

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseFragment
import com.puzzling.puzzlingaos.databinding.FragmentTeamCurrentSituationBinding
import com.puzzling.puzzlingaos.util.ViewModelFactory
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class TeamCurrentSituationFragment : BaseFragment<FragmentTeamCurrentSituationBinding>(R.layout.fragment_team_current_situation) {

    private val viewModel: TeamCurrentSituationViewModel by viewModels { ViewModelFactory(requireContext()) }

    @RequiresApi(Build.VERSION_CODES.O)
    private val teamTabTitle = getWeekDates()
    var num = mutableListOf(0, 0, 0, 0, 0, 0, 0)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

        viewModel.getTeamRetrospectList()
        // viewModel.testItemList(viewModel.teamRetrospectList, LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))

        viewModel.teamRetrospectList.observe(this) { contents ->
            Log.d("오류", "contents : $contents")
            contents.forEach { day ->
                day.reviewDay?.let { reviewDay ->
                    if (day.reviewWriters != null) {
                        num[viewModel.week.indexOf(day.reviewDay)] = BLUE_100
                    } else {
                        num[viewModel.week.indexOf(day.reviewDay)] = BLACK_TEXT
                    }
                }
            }
            setBackGround()
        }

        binding.viewPagerTeamRetrospectList.adapter = RetrospectThisWeekAdapter(this, num)
        TabLayoutMediator(binding.layoutTeamTabDate, binding.viewPagerTeamRetrospectList) { tab, position ->
            tab.text = teamTabTitle[position]
        }.attach()
    }

    private fun setBackGround() {
        for (i in 0..6) {
            Log.d("오류", "$num")
            when (num[i]) {
                BLUE_100 -> {
                    val tapItem = LayoutInflater.from(requireContext())
                        .inflate(R.layout.tab_text_color_blue100, null) as ConstraintLayout
                    binding.layoutTeamTabDate.getTabAt(i)?.customView = tapItem
                }
                BLACK_TEXT -> {
                    val tapItem =
                        LayoutInflater.from(requireContext())
                            .inflate(R.layout.tab_title_text_black, null) as ConstraintLayout
                    binding.layoutTeamTabDate.getTabAt(i)?.customView = tapItem
                }
                else -> {
                    val tapItem =
                        LayoutInflater.from(requireContext())
                            .inflate(R.layout.tab_title, null) as ConstraintLayout
                    binding.layoutTeamTabDate.getTabAt(i)?.customView = tapItem
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getWeekDates(): List<String> {
        val today = LocalDate.now()
        val startOfWeek = today.with(DayOfWeek.MONDAY) // 해당 주의 시작일
        val endOfWeek = today.with(DayOfWeek.SUNDAY) // 해당 주의 종료일

        val dates = mutableListOf<String>()
        var currentDate = startOfWeek

        val dateFormatter = DateTimeFormatter.ofPattern("dd")

        while (!currentDate.isAfter(endOfWeek)) {
            val formattedDate = currentDate.format(dateFormatter)
            dates.add(formattedDate)
            currentDate = currentDate.plusDays(1)
        }

        return dates
    }

    companion object {
        const val BLACK_TEXT = 1
        const val BLUE_100 = 2
    }
}
