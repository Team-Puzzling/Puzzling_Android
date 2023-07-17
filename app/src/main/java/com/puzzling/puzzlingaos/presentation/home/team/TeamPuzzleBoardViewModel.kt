package com.puzzling.puzzlingaos.presentation.home.team

import androidx.lifecycle.ViewModel
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.domain.entity.PuzzleBoard

class TeamPuzzleBoardViewModel : ViewModel() {
    val puzzleBoardList = listOf(
        PuzzleBoard("2023.07.03. ~ 2023.07.18.", R.drawable.img_teamboard1),
        PuzzleBoard("2023.06.14. ~ 2023.06.29.", R.drawable.img_teamboard2),
        PuzzleBoard("2023.05.04. ~ 2023.06.12.", R.drawable.img_teamboard3),
        PuzzleBoard("2023.05.04. ~ 2023.06.12.", R.drawable.img_teamboard4),

    )
}
