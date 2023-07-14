package com.puzzling.puzzlingaos.presentation.home.personal

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseFragment
import com.puzzling.puzzlingaos.databinding.FragmentMyPuzzleBoardBinding

class MyPuzzleBoardFragment :
    BaseFragment<FragmentMyPuzzleBoardBinding>(R.layout.fragment_my_puzzle_board) {
    private val viewModel by viewModels<MyPuzzleBoardViewModel>()
    private var _myPuzzleBoardAdapter: MyPuzzleBoardAdapter? = null
    private val myPuzzleBoardAdapter
        get() = requireNotNull(_myPuzzleBoardAdapter) { "adapter is null !!" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setmyPuzzleBoardAdapter()
    }

    private fun setmyPuzzleBoardAdapter() {
        _myPuzzleBoardAdapter = MyPuzzleBoardAdapter()
        _myPuzzleBoardAdapter?.submitList(viewModel.puzzleBoardList)
        binding.rcvMypuzzleBoard.also {
            it.adapter = _myPuzzleBoardAdapter
        }
    }
}
