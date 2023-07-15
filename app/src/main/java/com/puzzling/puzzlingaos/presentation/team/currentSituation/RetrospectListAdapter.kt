package com.puzzling.puzzlingaos.presentation.team.currentSituation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puzzling.puzzlingaos.databinding.ItemTeamDoDontRetrospectListBinding
import com.puzzling.puzzlingaos.databinding.ItemTeamDoRetrospectTextBinding
import com.puzzling.puzzlingaos.databinding.ItemTeamDontRetrospectTextBinding
import com.puzzling.puzzlingaos.domain.entity.TeamRetrospectList
import com.puzzling.puzzlingaos.domain.entity.TeamRetrospectList.Companion.DONT_RETROSPECT_TEXT
import com.puzzling.puzzlingaos.domain.entity.TeamRetrospectList.Companion.DO_RETROSPECT_TEXT
import com.puzzling.puzzlingaos.domain.entity.TeamRetrospectList.Companion.RETROSPECT_LIST

class RetrospectListAdapter(private val viewModel: TeamCurrentSituationViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private lateinit var doRetrospectTextBinding: ItemTeamDoRetrospectTextBinding
    private lateinit var dontRetrospectTextBinding: ItemTeamDontRetrospectTextBinding
    private lateinit var retrospectListBinding: ItemTeamDoDontRetrospectListBinding

    // private var itemList = mutableListOf<TeamRetrospectList>()

    private val itemList = mutableListOf<TeamRetrospectList>(
        TeamRetrospectList(0, null, null, null),
        TeamRetrospectList(2, null, "닉네임최대10자보이...", "역할도최대10자고이후.."),
        TeamRetrospectList(2, null, "이솝트", "안드로이드개발자"),
        TeamRetrospectList(2, null, "김솝트", "디자이너"),
        TeamRetrospectList(2, null, "한솝트", "기획자"),
        TeamRetrospectList(1, null, null, null),
        TeamRetrospectList(2, null, "닉네임최대10자보이...", "역할도최대10자고이후.."),
        TeamRetrospectList(2, null, "이솝트", "안드로이드개발자"),
        TeamRetrospectList(2, null, "김솝트", "디자이너"),
        TeamRetrospectList(2, null, "한솝트", "기획자"),
    )

    inner class DoRetrospectTextViewHolder(val binding: ItemTeamDoRetrospectTextBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TeamRetrospectList) {
            // binding.tvItemRetrospectText.text = item.titleText
            binding.tvItemRetrospectText.text
        }
    }
    inner class DontRetrospectTextViewHolder(val binding: ItemTeamDontRetrospectTextBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TeamRetrospectList) {
            // binding.tvItemRetrospectText.text = item.titleText
            binding.tvItemRetrospectText.text
        }
    }
    inner class RetrospectListViewHolder(val binding: ItemTeamDoDontRetrospectListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TeamRetrospectList) {
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
            RETROSPECT_LIST -> {
                retrospectListBinding = ItemTeamDoDontRetrospectListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return RetrospectListViewHolder(retrospectListBinding)
            }
            else -> {
                retrospectListBinding = ItemTeamDoDontRetrospectListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return RetrospectListViewHolder(retrospectListBinding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (itemList[position].type) {
            DO_RETROSPECT_TEXT -> {
                (holder as DoRetrospectTextViewHolder).bind(itemList[position])
                holder.setIsRecyclable(false)
            }
            DONT_RETROSPECT_TEXT -> {
                (holder as DontRetrospectTextViewHolder).bind(itemList[position])
                holder.setIsRecyclable(false)
            }
            RETROSPECT_LIST -> {
                (holder as RetrospectListViewHolder).bind(itemList[position])
                holder.setIsRecyclable(false)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return itemList[position].type
    }

    override fun getItemCount(): Int = itemList.size

//    fun setItemList(newItemList: MutableList<TeamRetrospectList>) {
//        itemList = newItemList
//    }
}
