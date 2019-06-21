package com.example.ama.questapp.presentation.list.adapter

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ama.questapp.R
import com.example.ama.questapp.data.db.model.QuestType
import com.example.ama.questapp.data.db.model.UserTask
import com.example.ama.questapp.data.db.model.pojo.UserTaskWithPattern
import com.example.ama.questapp.utils.goneIf
import kotlinx.android.synthetic.main.li_quest.view.*

class ActiveQuestsAdapter(private val listener: ((UserTask) -> Unit))
    : ListAdapter<UserTaskWithPattern, ActiveQuestsAdapter.ActiveQuestViewHolder>(ActiveQuestsDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActiveQuestViewHolder =
            ActiveQuestViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.li_quest, parent, false))

    override fun onBindViewHolder(holder: ActiveQuestViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ActiveQuestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: UserTaskWithPattern) {
            itemView.tvName.text = item.pattern.questName
            itemView.tvDescription.text = item.pattern.questDescription
            itemView.tvResult.text = itemView.resources.getString(
                    R.string.main_quest_list_progress_target_counter,
                    item.userTask.taskProgress,
                    item.userTask.taskTarget)

            val isCompleted = item.userTask.isCompleted == true

            itemView.pbProgress.goneIf(isCompleted)
            itemView.pbProgress.max = item.userTask.taskTarget?.toInt() ?: 0
            itemView.pbProgress.progress = item.userTask.taskProgress?.toInt() ?: 0

            val imageRes = when {
                isCompleted -> R.drawable.ic_quest_done
                item.pattern.questType == QuestType.COUNT -> R.drawable.ic_quest_counter
                else -> R.drawable.ic_quest_one_shot
            }
            itemView.ibIcon.setImageResource(imageRes)

            if (!isCompleted) {
                itemView.ibIcon.setOnClickListener { listener.invoke(getItem(adapterPosition).userTask) }
            } else {
                itemView.ibIcon.setOnClickListener(null)
            }
        }
    }

    class ActiveQuestsDiffUtils : DiffUtil.ItemCallback<UserTaskWithPattern>() {
        override fun areItemsTheSame(oldItem: UserTaskWithPattern, newItem: UserTaskWithPattern) =
                oldItem.userTask.taskId == newItem.userTask.taskId

        override fun areContentsTheSame(oldItem: UserTaskWithPattern, newItem: UserTaskWithPattern) =
                oldItem == newItem
    }
}