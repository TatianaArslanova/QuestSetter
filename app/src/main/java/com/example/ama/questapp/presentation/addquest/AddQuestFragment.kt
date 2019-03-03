package com.example.ama.questapp.presentation.addquest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ama.questapp.R
import dagger.android.support.DaggerFragment

class AddQuestFragment : DaggerFragment() {

    companion object {
        fun newInstance() = AddQuestFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_add_quest, container, false)


}