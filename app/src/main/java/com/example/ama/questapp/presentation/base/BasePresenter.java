package com.example.ama.questapp.presentation.base;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<T extends QuestView> implements Presenter<T> {
    protected T view;
    protected CompositeDisposable disposable;

    @Override
    public void attachView(T view) {
        this.view = view;
        disposable = new CompositeDisposable();
    }

    @Override
    public void detachView() {
        view = null;
        if (disposable != null) {
            disposable.dispose();
            disposable = null;
        }
    }
}
