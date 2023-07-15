package com.puzzling.puzzlingaos.presentation.detailRetrospect

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.TextView
import com.google.android.material.tabs.TabLayoutMediator
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseActivity
import com.puzzling.puzzlingaos.databinding.ActivityDetailRetroBinding
import kotlin.math.roundToInt

class DetailRetroActivity :
    BaseActivity<ActivityDetailRetroBinding>(R.layout.activity_detail_retro) {

    private val tabTitle = mutableMapOf(
        "5" to R.drawable.ic_monday,
        "6" to R.drawable.ic_thuesday,
        "7" to R.drawable.ic_monday,
        "8" to R.drawable.ic_thuesday,
        "9" to R.drawable.ic_monday,
        "10" to R.drawable.ic_thuesday,
        "11" to R.drawable.ic_monday,

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val titles = ArrayList(tabTitle.keys)
        binding.viewPager.adapter = WeekTapAdapter(this)
        TabLayoutMediator(binding.tablayout, binding.viewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()

        tabTitle.values.forEachIndexed { index, imageResId ->
            val textView = LayoutInflater.from(this).inflate(R.layout.tab_title, null) as TextView
            textView.setCompoundDrawablesWithIntrinsicBounds(0, imageResId, 0, 0)
            textView.compoundDrawablePadding = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                4f,
                resources.displayMetrics,
            ).roundToInt()
            binding.tablayout.getTabAt(index)?.customView = textView
        }
    }
}
