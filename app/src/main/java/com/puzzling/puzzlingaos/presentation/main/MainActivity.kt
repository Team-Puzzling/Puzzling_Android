package com.puzzling.puzzlingaos.presentation.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseActivity
import com.puzzling.puzzlingaos.databinding.ActivityMainBinding
import com.puzzling.puzzlingaos.presentation.home.HomeFragment
import com.puzzling.puzzlingaos.presentation.home.personal.PersonalDashboardViewModel
import com.puzzling.puzzlingaos.presentation.home.team.TeamDashBoardViewModel
import com.puzzling.puzzlingaos.presentation.mypage.MyPageFragment
import com.puzzling.puzzlingaos.presentation.writeRetrospective.WriteRetrospectiveActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    //    private val viewModel by viewModels<HomeViewModel>()
    private val viewModel by viewModels<PersonalDashboardViewModel>()
    private val teamViewModel by viewModels<TeamDashBoardViewModel>()

    var projectId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        initTabLayout()
        clickBottomNavItem()
        if (intent != null) {
            projectId = intent.getIntExtra("homeProjectId", -1)
            viewModel.firstProjectId.value = projectId
            teamViewModel.firstProjectId.value = projectId
            viewModel.firstProjectId.observe(this) {
            }
            Log.d("main", "viewModel.firstProjectId  :: ${viewModel.firstProjectId.value}")
            Log.d("main", "teamViewModel.firstProjectId  :: ${teamViewModel.firstProjectId.value}")
        }
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
