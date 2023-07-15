package com.puzzling.puzzlingaos.presentation.home.team

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.puzzling.puzzlingaos.databinding.ItemTeampuzzleBoardBinding
import com.puzzling.puzzlingaos.domain.entity.PuzzleBoard
import com.puzzling.puzzlingaos.util.ItemDiffCallback
import com.puzzling.puzzlingaos.util.loadImage

class TeamPuzzleBoardAdapter :
    ListAdapter<PuzzleBoard, TeamPuzzleBoardAdapter.TeamPuzzleBoardViewHolder>(
        ItemDiffCallback<PuzzleBoard>(
            onContentsTheSame = { old, new -> old == new },
            onItemsTheSame = { old, new -> old == new },
        ),
    ) {
    class TeamPuzzleBoardViewHolder(private val binding: ItemTeampuzzleBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: PuzzleBoard) = with(binding) {
            tvTeampuzzleDate.text = data.date
            ivTeampuzzleBoard.loadImage(binding.root.context.getDrawable(data.boardImage))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamPuzzleBoardViewHolder {
        val binding =
            ItemTeampuzzleBoardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamPuzzleBoardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamPuzzleBoardViewHolder, position: Int) {
        holder.onBind(
            getItem(position) as PuzzleBoard,
        )
    }
}
