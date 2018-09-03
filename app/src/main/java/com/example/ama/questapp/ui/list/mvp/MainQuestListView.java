package com.example.ama.questapp.ui.list.mvp;

import com.example.ama.questapp.repo.model.PatternWithStatus;
import com.example.ama.questapp.ui.base.QuestView;
import com.example.ama.questapp.ui.base.ViewState;

import java.util.List;

public interface MainQuestListView extends QuestView {
    void render(ViewState<List<PatternWithStatus>> viewState);
}
