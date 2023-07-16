package com.puzzling.puzzlingaos.presentation.detailRetrospect

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseActivity
import com.puzzling.puzzlingaos.data.model.response.ResponseDetailRetroDto
import com.puzzling.puzzlingaos.databinding.ActivityDetailRetroBinding
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DetailRetroActivity :
    BaseActivity<ActivityDetailRetroBinding>(R.layout.activity_detail_retro) {

    private val tabTitle = getWeekDatesWithToday()

    val week = listOf(
        "월",
        "화",
        "수",
        "목",
        "금",
        "토",
        "일",
    )

    var num = mutableListOf(0, 0, 0, 0, 0, 0, 0)

    companion object {
        const val BLACK_TEXT = 1
        const val BG_BLUE_100 = 2
    }

    private val dummyList = mutableListOf<ResponseDetailRetroDto>(
        ResponseDetailRetroDto(null, "월", "2023-07-10", null),
        ResponseDetailRetroDto(
            4,
            "화",
            "2023-07-11",
            listOf(
                ResponseDetailRetroDto.Content("잘한점...", "잘한점 어쩌구...."),
                ResponseDetailRetroDto.Content("아쉬운점...", "아쉬운점 어쩌구...."),
                ResponseDetailRetroDto.Content("배운점...", "배운점 어쩌구...."),
            ),
        ),
        ResponseDetailRetroDto(
            9,
            "목",
            "2023-07-13",
            listOf(
                ResponseDetailRetroDto.Content("잘한점...", "잘한점 어쩌구...."),
                ResponseDetailRetroDto.Content("아쉬운점...", "아쉬운점 어쩌구...."),
                ResponseDetailRetroDto.Content("배운점...", "배운점 어쩌구...."),
            ),
        ),
        ResponseDetailRetroDto(null, "금", "2023-07-10", null),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewPager.adapter = WeekTapAdapter(this)
        TabLayoutMediator(binding.tlDetailRetroDate, binding.viewPager) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()

        for (day in dummyList) {
            if (day.reviewId != null) {
                num[week.indexOf(day.reviewDay)] = BG_BLUE_100
            } else {
                Log.d("회고 주기", "day : $day")
                Log.d("회고 주기", "${day.reviewDay}")
                Log.d("회고 주기", "${week.indexOf(day.reviewDay)}")
                num[week.indexOf(day.reviewDay)] = BLACK_TEXT
            }
        }

        for (i in 0..6) {
            when (num[i]) {
                BG_BLUE_100 -> {
                    val tapItem = LayoutInflater.from(this)
                        .inflate(R.layout.tab_text_color_blue100, null) as ConstraintLayout
                    binding.tlDetailRetroDate.getTabAt(i)?.customView =
                        tapItem
                }
                BLACK_TEXT -> {
                    val tapItem =
                        LayoutInflater.from(this)
                            .inflate(R.layout.tab_title_text_black, null) as ConstraintLayout
                    binding.tlDetailRetroDate.getTabAt(i)?.customView =
                        tapItem
                }
                else -> {
                    val tapItem =
                        LayoutInflater.from(this)
                            .inflate(R.layout.tab_title, null) as ConstraintLayout
                    binding.tlDetailRetroDate.getTabAt(i)?.customView = tapItem
                }
            }
        }
    }

    fun getWeekDatesWithToday(): List<String> {
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
}
