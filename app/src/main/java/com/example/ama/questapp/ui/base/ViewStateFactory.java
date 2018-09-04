package com.example.ama.questapp.ui.base;

public class ViewStateFactory<T> {

    public ViewState<T> empty() {
        return new ViewState<>(false, null, null);
    }

    public ViewState<T> loading() {
        return new ViewState<>(true, null, null);
    }

    public ViewState<T> hasData(T data) {
        return new ViewState<>(false, data, null);
    }

    public ViewState<T> error(Throwable error) {
        return new ViewState<>(false, null, error);
    }
}