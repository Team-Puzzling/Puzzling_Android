package com.puzzling.puzzlingaos.presentation.team.currentSituation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseActivity
import com.puzzling.puzzlingaos.databinding.ActivityTeamRetroBinding
import com.puzzling.puzzlingaos.presentation.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class TeamRetroActivity : BaseActivity<ActivityTeamRetroBinding>(R.layout.activity_team_retro) {

    private val viewModel by viewModels<TeamRetroViewModel>()

    @RequiresApi(Build.VERSION_CODES.O)
    private val teamTabTitle = getWeekDates()
    var num = mutableListOf(0, 0, 0, 0, 0, 0, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding.tvDetailRetroTitle.text = intent.getStringExtra("Title")


        binding.viewModel = viewModel
        binding.tvTeamProjectName.text = intent.getStringExtra("Team")
        binding.tvTeamCurrentYearmonth.text = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy년 MM월"))

        viewModel.getTeamRetrospectList(
            1,
            startOfWeek = LocalDate.now().with(DayOfWeek.MONDAY).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
            endOfWeek = LocalDate.now().with(DayOfWeek.SUNDAY).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
        )

        viewModel.teamRetrospectList.observe(this) { contents ->
            Log.d("오류", "contents : $contents")
            if (contents.isNotEmpty()) {
                contents.forEach { day ->
                    day.reviewDay?.let { reviewDay ->
                        if (day.reviewWriters != null) {
                            num[viewModel.week.indexOf(day.reviewDay)] = BLUE_100
                        } else {
                            num[viewModel.week.indexOf(day.reviewDay)] = BLACK_TEXT
                        }
                    }
                    // setBackGround()
                }
                setBackGround()
            }
        }

        binding.viewPagerTeamRetrospectList.adapter = RetrospectThisWeekAdapter(this, num)
        TabLayoutMediator(binding.layoutTeamTabDate, binding.viewPagerTeamRetrospectList) { tab, position ->
            tab.text = teamTabTitle[position]
        }.attach()

        clickBackBtn()
    }

    private fun clickBackBtn() {
        binding.btnTeamCurrentBack.setOnClickListener {
//            setSupportActionBar(binding.tbTeamCurrent)
//            supportActionBar!!.setDisplayHomeAsUpEnabled(false)
//            supportActionBar?.setDisplayShowTitleEnabled(false)
//
//            val intent = Intent(this, MainActivity::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
//            startActivity(intent)
            finish()
        }
    }

    private fun setBackGround() {
        for (i in 0..6) {
            Log.d("오류", "$num")
            when (num[i]) {
                BLUE_100 -> {
                    val tapItem = LayoutInflater.from(this)
                        .inflate(R.layout.tab_text_color_blue100, null) as ConstraintLayout
                    binding.layoutTeamTabDate.getTabAt(i)?.customView = tapItem
                }
                BLACK_TEXT -> {
                    val tapItem =
                        LayoutInflater.from(this)
                            .inflate(R.layout.tab_title_text_black, null) as ConstraintLayout
                    binding.layoutTeamTabDate.getTabAt(i)?.customView = tapItem
                }
                else -> {
                    val tapItem =
                        LayoutInflater.from(this)
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
