package com.example.ama.questapp.presentation.list.adapter;

import com.example.ama.questapp.data.db.model.UserTask;

public interface OnCompleteQuestClickListener {
    void tryToCompleteQuest(UserTask userTask);
}
