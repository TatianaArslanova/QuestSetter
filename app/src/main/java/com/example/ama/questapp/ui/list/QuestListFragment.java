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
import android.widget.TextView;
import android.widget.Toast;

import com.example.ama.questapp.QuestApp;
import com.example.ama.questapp.R;
import com.example.ama.questapp.repo.model.PatternWithStatus;
import com.example.ama.questapp.repo.model.UserTask;
import com.example.ama.questapp.ui.base.ViewState;
import com.example.ama.questapp.ui.list.adapter.MainQuestListAdapter;
import com.example.ama.questapp.ui.list.adapter.OnDoneClickListener;
import com.example.ama.questapp.ui.list.mvp.MainQuestListView;
import com.example.ama.questapp.ui.list.mvp.QuestMainListPresenter;

import java.util.List;

import javax.inject.Inject;

public class QuestListFragment extends Fragment implements MainQuestListView {
    @Inject
    QuestMainListPresenter presenter;

    private ProgressBar progressBar;
    private RecyclerView rvMain;
    private TextView tvEmptyMessage;
    private MainQuestListAdapter adapter;

    public static QuestListFragment newInstance() {
        return new QuestListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        QuestApp.getInstance().getComponent().inject(this);
        super.onCreate(savedInstanceState);
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
        presenter.loadData();
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
        loading(viewState.isLoading());
        error(viewState.isError());
        data(viewState.getData());
        emptyMessage(!(viewState.isLoading() || viewState.isError() || viewState.isData()));
    }

    private void loading(boolean loading) {
        progressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
        rvMain.setVisibility(!loading ? View.VISIBLE : View.GONE);
    }

    private void error(boolean show) {
        //TODO: error message
        if (show) Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
    }

    private void data(List<PatternWithStatus> data) {
        if (data != null) adapter.setItems(data);
    }

    private void emptyMessage(boolean show) {
        tvEmptyMessage.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
