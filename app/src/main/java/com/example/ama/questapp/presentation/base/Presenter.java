package com.example.ama.questapp.presentation.base;

public interface Presenter<T extends QuestView> {
    void attachView(T view);

    void detachView();

    void loadData();
}
