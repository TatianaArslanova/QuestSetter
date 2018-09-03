package com.example.ama.questapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ama.questapp.R;
import com.example.ama.questapp.ui.list.QuestListFragment;

public class MainActivity extends AppCompatActivity {

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
}
