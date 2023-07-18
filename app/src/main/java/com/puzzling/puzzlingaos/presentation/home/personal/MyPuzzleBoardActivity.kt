package com.puzzling.puzzlingaos.presentation.home.personal

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseActivity
import com.puzzling.puzzlingaos.databinding.ActivityMyPuzzleBoardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPuzzleBoardActivity :
    BaseActivity<ActivityMyPuzzleBoardBinding>(R.layout.activity_my_puzzle_board) {
    private val viewModel by viewModels<MyPuzzleBoardViewModel>()
    private var _myPuzzleBoardAdapter: MyPuzzleBoardAdapter? = null
    private val myPuzzleBoardAdapter
        get() = requireNotNull(_myPuzzleBoardAdapter) { "adapter is null !!" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setMyPuzzleBoardAdapter()
    }

    private fun setMyPuzzleBoardAdapter() {
        _myPuzzleBoardAdapter = MyPuzzleBoardAdapter()
        _myPuzzleBoardAdapter?.submitList(viewModel.puzzleBoardList)
        binding.rcvMypuzzleBoard.also {
            it.adapter = _myPuzzleBoardAdapter
        }
    }
}
