package com.example.ama.questapp.presentation.base

class ViewStateFactory<T> {

    fun empty() = ViewState<T>(false, null, null)

    fun loading() = ViewState<T>(true, null, null)

    fun hasData(data: T) = ViewState<T>(false, data, null)

    fun error(error: Throwable) = ViewState<T>(false, null, error)
}