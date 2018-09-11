package com.example.ama.questapp.presentation.list.mvp;

import com.example.ama.questapp.data.db.model.UserQuest;
import com.example.ama.questapp.presentation.base.Presenter;

public interface MainQuestListPresenter<T extends MainQuestListView> extends Presenter<T> {
    void tryToCompleteQuest(UserQuest userQuest);
}
