package com.example.ama.questapp.presentation.list.adapter.vh;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ama.questapp.data.db.model.pojo.PatternWithStatus;


public abstract class BaseQuestViewHolder extends RecyclerView.ViewHolder {

    BaseQuestViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(PatternWithStatus item);
}
