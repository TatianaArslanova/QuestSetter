package com.example.ama.questapp.presentation;

import android.os.Bundle;

import com.example.ama.questapp.R;
import com.example.ama.questapp.presentation.addquest.AddQuestFragment;
import com.example.ama.questapp.presentation.list.QuestListFragment;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(
                            R.id.fl_container_main_list,
                            QuestListFragment.newInstance(),
                            QuestListFragment.class.getSimpleName())
                    .commit();
        }
    }

    public void openAddQuestScreen() {
        getSupportFragmentManager().beginTransaction()
                .replace(
                        R.id.fl_container_main_list,
                        AddQuestFragment.Companion.newInstance(),
                        AddQuestFragment.class.getSimpleName())
                .addToBackStack(AddQuestFragment.class.getSimpleName())
                .commit();
    }
}
