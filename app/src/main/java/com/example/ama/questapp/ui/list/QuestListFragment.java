package com.example.ama.questapp.ui.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.ama.questapp.R;
import com.example.ama.questapp.repo.model.PatternWithStatus;
import com.example.ama.questapp.repo.model.UserTask;
import com.example.ama.questapp.ui.base.ViewState;
import com.example.ama.questapp.ui.list.adapter.MainQuestListAdapter;
import com.example.ama.questapp.ui.list.adapter.OnDoneClickListener;
import com.example.ama.questapp.ui.list.mvp.MainQuestListView;

import java.util.List;

public class QuestListFragment extends Fragment implements MainQuestListView {
    private ProgressBar progressBar;
    private RecyclerView rvMain;
    private MainQuestListAdapter adapter;

    public static QuestListFragment newInstance() {
        return new QuestListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_quest_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initUI(view);
        super.onViewCreated(view, savedInstanceState);
    }

    private void loading(boolean loading) {
        progressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
        rvMain.setVisibility(!loading ? View.VISIBLE : View.GONE);
    }

    private void initUI(View view) {
        progressBar = view.findViewById(R.id.pb_loading);
        rvMain = view.findViewById(R.id.rv_main_quest_list);
        adapter = new MainQuestListAdapter(new OnDoneClickListener() {
            @Override
            public void onDoneClick(UserTask status) {

            }
        });
        rvMain.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMain.setAdapter(adapter);
    }

    @Override
    public void render(ViewState<List<PatternWithStatus>> viewState) {

    }
}
