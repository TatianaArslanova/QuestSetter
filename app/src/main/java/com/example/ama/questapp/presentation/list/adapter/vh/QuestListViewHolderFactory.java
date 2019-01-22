package com.example.ama.questapp.presentation.list.adapter.vh;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.ama.questapp.R;
import com.example.ama.questapp.presentation.list.adapter.OnCompleteQuestClickListener;
import com.example.ama.questapp.presentation.list.di.MainQuestListScope;

import javax.inject.Inject;

@MainQuestListScope
public class QuestListViewHolderFactory {
    public static final int COMPLETED_QUEST = 0;
    public static final int NOT_COMPLETED_QUEST = 1;

    private OnCompleteQuestClickListener listener;

    @Inject
    public QuestListViewHolderFactory(OnCompleteQuestClickListener listener) {
        this.listener = listener;
    }

    public BaseQuestViewHolder createViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case NOT_COMPLETED_QUEST:
                return createNotCompletedVH(parent, inflater, listener);
            case COMPLETED_QUEST:
                return createCompletedVH(parent, inflater);
            default:
                throw new IllegalArgumentException("Unknown viewType");
        }
    }

    private BaseQuestViewHolder createCompletedVH(ViewGroup parent,
                                                  LayoutInflater inflater) {
        return new ViewHolderQuestCompleted(inflater.inflate(
                R.layout.li_quest_completed,
                parent, false));
    }

    private ViewHolderQuestNotCompleted createNotCompletedVH(ViewGroup parent,
                                                             LayoutInflater inflater,
                                                             OnCompleteQuestClickListener listener) {
        return new ViewHolderQuestNotCompleted(inflater.inflate(
                R.layout.li_quest_new,
                parent, false), listener);
    }
}
