package com.puzzling.puzzlingaos.presentation.home.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puzzling.puzzlingaos.databinding.ItemBottomLogoutBinding
import com.puzzling.puzzlingaos.databinding.ItemMypageNicknameBinding

class BottomAdapter() :
    RecyclerView.Adapter<BottomAdapter.BottomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomViewHolder {
        val binding: ItemBottomLogoutBinding =
            ItemBottomLogoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BottomViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: BottomViewHolder, position: Int) {
    }

    class BottomViewHolder(private val binding: ItemBottomLogoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}
