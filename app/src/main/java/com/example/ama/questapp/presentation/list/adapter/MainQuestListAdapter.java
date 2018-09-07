package com.example.ama.questapp.presentation.list.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.ama.questapp.R;
import com.example.ama.questapp.data.db.model.UserQuest;
import com.example.ama.questapp.data.db.model.pojo.PatternWithStatus;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.PublishSubject;

/***
 * Adapter for main quest recycler view
 */

public class MainQuestListAdapter extends RecyclerView.Adapter<BaseQuestViewHolder> {

    private static final int COMPLETED_QUEST = 0;
    private static final int NOT_COMPLETED_QUEST = 1;

    private List<PatternWithStatus> items = new ArrayList<>();
    private PublishSubject<UserQuest> onDoneClicklistener = PublishSubject.create();

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getUserQuest().isCompleted() ? COMPLETED_QUEST : NOT_COMPLETED_QUEST;
    }

    @NonNull
    @Override
    public BaseQuestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case NOT_COMPLETED_QUEST:
                return new ViewHolderQuestNotCompleted(inflater.inflate(
                        R.layout.main_quest_list_rv_item_not_completed,
                        parent, false), onDoneClicklistener);
            default:
                return new BaseQuestViewHolder(inflater.inflate(
                        R.layout.main_quest_list_rv_item_completed,
                        parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseQuestViewHolder holder, int position) {
        holder.bind(items.get(position));
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

    public PublishSubject<UserQuest> getOnDoneClicklistener() {
        return onDoneClicklistener;
    }
}
