package com.example.ama.questapp.presentation.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ama.questapp.R;
import com.example.ama.questapp.data.db.model.UserTask;
import com.example.ama.questapp.data.db.model.pojo.UserTaskWithPattern;
import com.example.ama.questapp.presentation.MainActivity;
import com.example.ama.questapp.presentation.base.ViewState;
import com.example.ama.questapp.presentation.list.adapter.MainQuestListAdapter;
import com.example.ama.questapp.presentation.list.mvp.QuestMainListContract;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class QuestListFragment extends DaggerFragment implements QuestMainListContract.View {
    @Inject
    QuestMainListContract.Presenter presenter;
    @Inject
    MainQuestListAdapter adapter;

    private ProgressBar progressBar;
    private RecyclerView rvMain;
    private TextView tvEmptyMessage;
    private FloatingActionButton floatingActionButton;

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

    @Override
    public void onStart() {
        presenter.attachView(this);
        super.onStart();
    }

    @Override
    public void onStop() {
        presenter.detachView();
        super.onStop();
    }

    private void initUI(View view) {
        progressBar = view.findViewById(R.id.pb_loading);
        tvEmptyMessage = view.findViewById(R.id.tv_empty_message);
        floatingActionButton = view.findViewById(R.id.add_quest);
        rvMain = view.findViewById(R.id.rv_main_quest_list);
        rvMain.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMain.setAdapter(adapter);
        floatingActionButton.setOnClickListener(v -> {
            if (getActivity() != null) {
                ((MainActivity) getActivity()).openAddQuestScreen();
            }
        });
    }

    @Override
    public void render(ViewState<List<UserTaskWithPattern>> viewState) {
        loading(viewState.isLoading());
        error(viewState.isError());
        data(viewState.getData());
        emptyMessage(!(viewState.isLoading() || viewState.isError() || viewState.isData()));
    }

    @Override
    public void tryToCompleteQuest(UserTask userTask) {
        presenter.tryToCompleteQuest(userTask);
    }

    private void loading(boolean loading) {
        progressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
        rvMain.setVisibility(!loading ? View.VISIBLE : View.GONE);
    }

    private void error(boolean show) {
        //TODO: error message
        if (show) Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
    }

    private void data(List<UserTaskWithPattern> data) {
        if (data != null) adapter.setItems(data);
    }

    private void emptyMessage(boolean show) {
        tvEmptyMessage.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
