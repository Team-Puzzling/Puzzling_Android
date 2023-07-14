package com.puzzling.puzzlingaos.presentation.home.personal

import androidx.lifecycle.ViewModel
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.domain.entity.PuzzleBoard

class MyPuzzleBoardViewModel : ViewModel() {
    val puzzleBoardList = listOf<PuzzleBoard>(
        PuzzleBoard("2023.07.03. ~ 2023.07.18.", R.drawable.img_myboard1),
        PuzzleBoard("2023.06.14. ~ 2023.06.29.", R.drawable.img_myboard2),
        PuzzleBoard("2023.05.04. ~ 2023.06.12.", R.drawable.img_myboard3),
        PuzzleBoard("2023.05.04. ~ 2023.06.12.", R.drawable.img_myboard4),

    )
}
