package com.puzzling.puzzlingaos.presentation.home.personal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.puzzling.puzzlingaos.databinding.ItemMypuzzleBoardBinding
import com.puzzling.puzzlingaos.domain.entity.PuzzleBoard
import com.puzzling.puzzlingaos.util.ItemDiffCallback
import com.puzzling.puzzlingaos.util.loadImage

class MyPuzzleBoardAdapter : ListAdapter<PuzzleBoard, MyPuzzleBoardAdapter.MyPuzzleBoardViewHolder>(
    ItemDiffCallback<PuzzleBoard>(
        onContentsTheSame = { old, new -> old == new },
        onItemsTheSame = { old, new -> old == new },
    ),
) {
    class MyPuzzleBoardViewHolder(private val binding: ItemMypuzzleBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: PuzzleBoard) = with(binding) {
            tvMypuzzleDate.text = data.date
            ivMyuzzleBoard.loadImage(binding.root.context.getDrawable(data.boardImage))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPuzzleBoardViewHolder {
        val binding =
            ItemMypuzzleBoardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyPuzzleBoardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyPuzzleBoardViewHolder, position: Int) {
        holder.onBind(
            getItem(position) as PuzzleBoard,
        )
    }
}
