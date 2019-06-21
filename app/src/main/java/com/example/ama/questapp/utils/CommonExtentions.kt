package com.example.ama.questapp.utils

import android.view.View
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.addTo(compositeDisposable: CompositeDisposable) = compositeDisposable.add(this)

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.visibleIf(visible: Boolean) {
    if (visible) visible() else gone()
}

fun View.goneIf(gone: Boolean) {
    if (gone) gone() else visible()
}

fun View.invisibleIf(invisible: Boolean) {
    if (invisible) invisible() else visible()
}