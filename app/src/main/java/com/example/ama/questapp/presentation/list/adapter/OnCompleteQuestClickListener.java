package com.example.ama.questapp.presentation.list.adapter;

import com.example.ama.questapp.data.db.model.UserQuest;

public interface OnCompleteQuestClickListener {
    void tryToCompleteQuest(UserQuest userQuest);
}
