package com.example.ama.questapp.ui.list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ama.questapp.R;
import com.example.ama.questapp.repo.model.UserQuest;
import com.example.ama.questapp.repo.model.pojo.PatternWithStatus;

import io.reactivex.subjects.PublishSubject;

public class ViewHolderQuestNotCompleted extends RecyclerView.ViewHolder {
    private PublishSubject<UserQuest> onDoneClickListener;

    ViewHolderQuestNotCompleted(View itemView, PublishSubject<UserQuest> onDoneClickListener) {
        super(itemView);
        this.onDoneClickListener = onDoneClickListener;
    }

    void bind(final PatternWithStatus item) {
        ((TextView) itemView.findViewById(R.id.tv_quest_name))
                .setText(item.getPattern().getQuestName());
        ((TextView) itemView.findViewById(R.id.tv_quest_description))
                .setText(item.getPattern().getQuestDescription());
        itemView.findViewById(R.id.btn_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDoneClickListener.onNext(item.getUserQuest());
            }
        });
        ((ProgressBar) itemView.findViewById(R.id.pb_quest_progress))
                .setMax(item.getUserQuest().getTaskTarget());
        ((ProgressBar) itemView.findViewById(R.id.pb_quest_progress))
                .setProgress(item.getUserQuest().getTaskProgress());
    }
}
