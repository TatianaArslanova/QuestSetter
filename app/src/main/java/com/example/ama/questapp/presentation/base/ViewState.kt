package com.example.ama.questapp.presentation.base

data class ViewState<T>(
        val loading: Boolean?,
        val data: T?,
        val error: Throwable?
)
