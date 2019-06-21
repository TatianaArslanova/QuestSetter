package com.example.ama.questapp.presentation

import android.os.Bundle
import com.example.ama.questapp.R
import com.example.ama.questapp.presentation.addquest.AddQuestFragment
import com.example.ama.questapp.presentation.list.ActiveQuestsFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        savedInstanceState ?: supportFragmentManager.beginTransaction()
                .replace(R.id.fl_container_main_list,
                        ActiveQuestsFragment.newInstance(),
                        ActiveQuestsFragment::class.java.simpleName)
                .commit()
    }

    fun openAddQuestScreen() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fl_container_main_list,
                        AddQuestFragment.newInstance(),
                        AddQuestFragment::class.java.simpleName)
                .addToBackStack(AddQuestFragment::class.java.simpleName)
                .commit()
    }
}