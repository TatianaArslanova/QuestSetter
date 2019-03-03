package com.example.ama.questapp.presentation.list.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.ama.questapp.data.db.model.pojo.UserTaskWithPattern;
import com.example.ama.questapp.presentation.list.adapter.vh.BaseQuestViewHolder;
import com.example.ama.questapp.presentation.list.adapter.vh.QuestListViewHolderFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/***
 * Adapter for main quest recycler view
 */

public class MainQuestListAdapter extends RecyclerView.Adapter<BaseQuestViewHolder> {

    private List<UserTaskWithPattern> items = new ArrayList<>();
    private QuestListViewHolderFactory factory;

    @Inject
    public MainQuestListAdapter(QuestListViewHolderFactory factory) {
        this.factory = factory;
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getUserTask().isCompleted() ?
                QuestListViewHolderFactory.COMPLETED_QUEST :
                QuestListViewHolderFactory.NOT_COMPLETED_QUEST;
    }

    @NonNull
    @Override
    public BaseQuestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return factory.createViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseQuestViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<UserTaskWithPattern> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }
}
