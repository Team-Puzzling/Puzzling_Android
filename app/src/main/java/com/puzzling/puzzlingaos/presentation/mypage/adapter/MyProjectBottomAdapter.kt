package com.puzzling.puzzlingaos.presentation.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puzzling.puzzlingaos.databinding.ItemBottomLogoutBinding

class MyProjectBottomAdapter() :
    RecyclerView.Adapter<MyProjectBottomAdapter.MyProjectBottomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyProjectBottomViewHolder {
        val binding: ItemBottomLogoutBinding =
            ItemBottomLogoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyProjectBottomViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: MyProjectBottomViewHolder, position: Int) {
    }

    class MyProjectBottomViewHolder(private val binding: ItemBottomLogoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}
