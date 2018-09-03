package com.example.ama.questapp.ui.list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ama.questapp.R;
import com.example.ama.questapp.repo.model.PatternWithStatus;

public class ViewHolderQuestCompleted extends RecyclerView.ViewHolder {
    ViewHolderQuestCompleted(View itemView) {
        super(itemView);
    }

    void bind(PatternWithStatus item) {
        ((TextView) itemView.findViewById(R.id.tv_quest_name)).setText(item.getPattern().getQuestName());
        ((TextView) itemView.findViewById(R.id.tv_quest_description)).setText(item.getPattern().getQuestDescription());
    }
}
