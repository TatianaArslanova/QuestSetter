package com.example.ama.questapp.ui.list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ama.questapp.R;
import com.example.ama.questapp.repo.model.pojo.PatternWithStatus;

class BaseQuestViewHolder extends RecyclerView.ViewHolder {
    BaseQuestViewHolder(View itemView) {
        super(itemView);
    }

    void bind(PatternWithStatus item) {
        ((TextView) itemView.findViewById(R.id.tv_quest_name))
                .setText(item.getPattern().getQuestName());
        ((TextView) itemView.findViewById(R.id.tv_quest_description))
                .setText(item.getPattern().getQuestDescription());
        ((TextView) itemView.findViewById(R.id.tv_target_progress))
                .setText(itemView.getResources().getString(
                        R.string.main_quest_list_progress_target_counter,
                        item.getUserQuest().getTaskProgress(),
                        item.getUserQuest().getTaskTarget()));
    }
}
