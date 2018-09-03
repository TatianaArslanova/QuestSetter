package com.example.ama.questapp.ui.list.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.ama.questapp.R;
import com.example.ama.questapp.repo.model.PatternWithStatus;

import java.util.ArrayList;
import java.util.List;

public class MainQuestListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int COMPLETED_QUEST = 0;
    private static final int NOT_COMPLETED_QUEST = 1;

    private OnDoneClickListener listener;
    private List<PatternWithStatus> items = new ArrayList<>();

    public MainQuestListAdapter(OnDoneClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getStatus().isCompleted() ? COMPLETED_QUEST : NOT_COMPLETED_QUEST;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case COMPLETED_QUEST:
                return new ViewHolderQuestCompleted(
                        LayoutInflater.from(parent.getContext()).inflate(
                                R.layout.main_quest_list_rv_item_completed,
                                parent, false));
        }
        return new ViewHolderQuestNotCompleted(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.main_quest_list_rv_item_not_completed,
                        parent, false), listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderQuestCompleted) {
            ((ViewHolderQuestCompleted) holder).bind(items.get(position));
        } else if (holder instanceof ViewHolderQuestNotCompleted) {
            ((ViewHolderQuestNotCompleted) holder).bind(items.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<PatternWithStatus> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }
}
