package com.puzzling.puzzlingaos.presentation.detailRetrospect

import android.app.ActionBar.LayoutParams
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseActivity
import com.puzzling.puzzlingaos.databinding.ActivityDetailRetroBinding

class DetailRetroActivity :
    BaseActivity<ActivityDetailRetroBinding>(R.layout.activity_detail_retro) {

    private val tabTitle = arrayListOf(
        "5",
        "6",
        "7",
        "8",
        "9",
        "10",
        "11",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewPager.adapter = WeekTapAdapter(this)
        TabLayoutMediator(binding.tlDetailRetroDate, binding.viewPager) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()

        for (i in 0..7) {
            val textView = LayoutInflater.from(this).inflate(R.layout.tab_title, null) as ConstraintLayout
            // textView.layoutParams = ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            binding.tlDetailRetroDate.getTabAt(i)?.customView = textView
        }
    }
}
