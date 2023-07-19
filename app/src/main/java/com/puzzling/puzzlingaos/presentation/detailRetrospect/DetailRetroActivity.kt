package com.puzzling.puzzlingaos.presentation.detailRetrospect

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseActivity
import com.puzzling.puzzlingaos.databinding.ActivityDetailRetroBinding
import com.puzzling.puzzlingaos.presentation.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class DetailRetroActivity :
    BaseActivity<ActivityDetailRetroBinding>(R.layout.activity_detail_retro) {

    private val viewModel by viewModels<DetailRetroViewModel>()

    private val tabTitle = getWeekDatesWithToday()

    var num = mutableListOf(0, 0, 0, 0, 0, 0, 0)

    companion object {
        const val BLACK_TEXT = 1
        const val BG_BLUE_100 = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clickToolbarBtnBack()

        binding.tvDetailRetroTitle.text = intent.getStringExtra("Title")

        viewModel.getDetailRetro()

        viewModel.detailRetroList.observe(this) { contents ->

            Log.d("오류", "contents : $contents")

            if (contents != null) {
                for (day in contents) {
                    if (day.reviewId != null) {
                        num[viewModel.week.indexOf(day.reviewDay)] = BG_BLUE_100
                    } else {
                        num[viewModel.week.indexOf(day.reviewDay)] = BLACK_TEXT
                    }
                }
            }
            setItemBg()
        }

        binding.viewPager.adapter = WeekTapAdapter(this, num)
        TabLayoutMediator(binding.tlDetailRetroDate, binding.viewPager) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }

    private fun setItemBg() {
        for (i in 0..6) {
            Log.d("오류", "$num")
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

    private fun getWeekDatesWithToday(): List<String> {
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

    private fun clickToolbarBtnBack() {
        setSupportActionBar(binding.tbDetailRetroMain)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.ibDetailRetroClose.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }
}
