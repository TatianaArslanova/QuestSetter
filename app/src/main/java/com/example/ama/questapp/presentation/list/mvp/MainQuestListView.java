package com.example.ama.questapp.presentation.list.mvp;

import com.example.ama.questapp.data.db.model.UserQuest;
import com.example.ama.questapp.data.db.model.pojo.PatternWithStatus;
import com.example.ama.questapp.presentation.base.QuestView;
import com.example.ama.questapp.presentation.base.ViewState;

import java.util.List;

import io.reactivex.subjects.PublishSubject;

public interface MainQuestListView extends QuestView {
    void render(ViewState<List<PatternWithStatus>> viewState);

    PublishSubject<UserQuest> completeQuestIntent();
}
