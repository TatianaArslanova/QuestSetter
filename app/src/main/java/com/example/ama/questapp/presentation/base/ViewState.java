package com.example.ama.questapp.presentation.base;

/***
 * View model class
 * @param <T> type of data that view uses
 */

public class ViewState<T> {
    private final boolean loading;
    private final T data;
    private final Throwable error;

    ViewState(boolean loading, T data, Throwable error) {
        this.loading = loading;
        this.data = data;
        this.error = error;
    }

    public boolean isLoading() {
        return loading;
    }

    public T getData() {
        return data;
    }

    public Throwable getError() {
        return error;
    }

    public boolean isError() {
        return error != null;
    }

    public boolean isData() {
        return data != null;
    }
}
