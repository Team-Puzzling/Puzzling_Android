package com.puzzling.puzzlingaos.presentation.team.currentSituation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puzzling.puzzlingaos.databinding.ItemTeamDoRetrospectListBinding
import com.puzzling.puzzlingaos.databinding.ItemTeamDoRetrospectTextBinding
import com.puzzling.puzzlingaos.databinding.ItemTeamDontRetrospectListBinding
import com.puzzling.puzzlingaos.databinding.ItemTeamDontRetrospectTextBinding
import com.puzzling.puzzlingaos.domain.entity.TeamRetrospectMultiList
import com.puzzling.puzzlingaos.domain.entity.TeamRetrospectMultiList.Companion.DONT_RETROSPECT_LIST
import com.puzzling.puzzlingaos.domain.entity.TeamRetrospectMultiList.Companion.DONT_RETROSPECT_TEXT
import com.puzzling.puzzlingaos.domain.entity.TeamRetrospectMultiList.Companion.DO_RETROSPECT_LIST
import com.puzzling.puzzlingaos.domain.entity.TeamRetrospectMultiList.Companion.DO_RETROSPECT_TEXT

class RetrospectListAdapter(var adapterItemList: ArrayList<TeamRetrospectMultiList> = ArrayList<TeamRetrospectMultiList>()) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var doRetrospectTextBinding: ItemTeamDoRetrospectTextBinding
    private lateinit var dontRetrospectTextBinding: ItemTeamDontRetrospectTextBinding
    private lateinit var doRetrospectListBinding: ItemTeamDoRetrospectListBinding
    private lateinit var dontRetrospectListBinding: ItemTeamDontRetrospectListBinding

    inner class DoRetrospectTextViewHolder(val binding: ItemTeamDoRetrospectTextBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TeamRetrospectMultiList) {
            binding.tvItemRetrospectText.text
        }
    }
    inner class DontRetrospectTextViewHolder(val binding: ItemTeamDontRetrospectTextBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TeamRetrospectMultiList) {
            binding.tvItemRetrospectText.text
        }
    }
    inner class DoRetrospectListViewHolder(val binding: ItemTeamDoRetrospectListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TeamRetrospectMultiList) {
            binding.tvItemRetrospectNickName.text = item.memberNickname
            binding.tvItemRetrospectRole.text = item.memberRole
        }
    }

    inner class DontRetrospectListViewHolder(val binding: ItemTeamDontRetrospectListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TeamRetrospectMultiList) {
            binding.tvItemRetrospectNickName.text = item.memberNickname
            binding.tvItemRetrospectRole.text = item.memberRole
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            DO_RETROSPECT_TEXT -> {
                doRetrospectTextBinding = ItemTeamDoRetrospectTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return DoRetrospectTextViewHolder(doRetrospectTextBinding)
            }
            DONT_RETROSPECT_TEXT -> {
                dontRetrospectTextBinding = ItemTeamDontRetrospectTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return DontRetrospectTextViewHolder(dontRetrospectTextBinding)
            }
            DO_RETROSPECT_LIST -> {
                doRetrospectListBinding = ItemTeamDoRetrospectListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return DoRetrospectListViewHolder(doRetrospectListBinding)
            }
            else -> {
                dontRetrospectListBinding = ItemTeamDontRetrospectListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return DontRetrospectListViewHolder(dontRetrospectListBinding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (adapterItemList[position].type) {
            DO_RETROSPECT_TEXT -> {
                (holder as DoRetrospectTextViewHolder).bind(adapterItemList[position])
                holder.setIsRecyclable(false)
            }
            DONT_RETROSPECT_TEXT -> {
                (holder as DontRetrospectTextViewHolder).bind(adapterItemList[position])
                holder.setIsRecyclable(false)
            }
            DO_RETROSPECT_LIST -> {
                (holder as DoRetrospectListViewHolder).bind(adapterItemList[position])
                holder.setIsRecyclable(false)
            }
            DONT_RETROSPECT_LIST -> {
                (holder as DontRetrospectListViewHolder).bind(adapterItemList[position])
                holder.setIsRecyclable(false)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return adapterItemList[position].type
    }

    override fun getItemCount(): Int = adapterItemList.size
}
