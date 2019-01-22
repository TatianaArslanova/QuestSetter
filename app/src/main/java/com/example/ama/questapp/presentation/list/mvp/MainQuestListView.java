package com.example.ama.questapp.presentation.list.mvp;

import com.example.ama.questapp.data.db.model.UserTask;
import com.example.ama.questapp.data.db.model.pojo.PatternWithStatus;
import com.example.ama.questapp.presentation.base.QuestView;
import com.example.ama.questapp.presentation.base.ViewState;

import java.util.List;

public interface MainQuestListView extends QuestView {
    void render(ViewState<List<PatternWithStatus>> viewState);

    void tryToCompleteQuest(UserTask userTask);
}
