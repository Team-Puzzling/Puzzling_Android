package com.puzzling.puzzlingaos.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseActivity
import com.puzzling.puzzlingaos.databinding.ActivityMainBinding
import com.puzzling.puzzlingaos.presentation.home.HomeFragment
import com.puzzling.puzzlingaos.presentation.home.mypage.MyPageFragment

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        initTabLayout()
        clickBottomNavItem()
    }

    private fun initTabLayout() {
        supportFragmentManager.findFragmentById(R.id.fcv_main_container)
            ?: navigateTo<HomeFragment>()
    }

    private fun clickBottomNavItem() {
        supportFragmentManager.findFragmentById(R.id.fcv_main_container)
            ?: navigateTo<HomeFragment>()

        binding.bnvMain.setOnItemSelectedListener { menu ->
            when (menu.itemId) {
                R.id.menu_home -> navigateTo<HomeFragment>()
                R.id.menu_mypage -> navigateTo<MyPageFragment>()
            }
            true
        }
    }

    private inline fun <reified T : Fragment> navigateTo() {
        supportFragmentManager.commit {
            replace(R.id.fcv_main_container, T::class.java.newInstance())
        }
    }
}
