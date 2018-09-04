package com.example.ama.questapp.ui.list.mvp;

import com.example.ama.questapp.repo.model.PatternWithStatus;
import com.example.ama.questapp.repo.model.UserQuest;
import com.example.ama.questapp.ui.base.QuestView;
import com.example.ama.questapp.ui.base.ViewState;

import java.util.List;

import io.reactivex.subjects.PublishSubject;

public interface MainQuestListView extends QuestView {
    void render(ViewState<List<PatternWithStatus>> viewState);

    PublishSubject<UserQuest> completeQuestIntent();
}
