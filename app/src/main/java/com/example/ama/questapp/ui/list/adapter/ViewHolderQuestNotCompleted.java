package com.example.ama.questapp.ui.list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ama.questapp.R;
import com.example.ama.questapp.repo.model.PatternWithStatus;

public class ViewHolderQuestNotCompleted extends RecyclerView.ViewHolder {
    private OnDoneClickListener listener;

    ViewHolderQuestNotCompleted(View itemView, OnDoneClickListener listener) {
        super(itemView);
        this.listener = listener;
    }

    void bind(final PatternWithStatus item) {
        ((TextView) itemView.findViewById(R.id.tv_quest_name)).setText(item.getPattern().getQuestName());
        ((TextView) itemView.findViewById(R.id.tv_quest_description)).setText(item.getPattern().getQuestDescription());
        itemView.findViewById(R.id.btn_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDoneClick(item.getStatus());
            }
        });
    }
}
