package com.example.ama.questapp.ui.list.adapter;

import android.view.View;
import android.widget.ProgressBar;

import com.example.ama.questapp.R;
import com.example.ama.questapp.repo.model.UserQuest;
import com.example.ama.questapp.repo.model.pojo.PatternWithStatus;

import io.reactivex.subjects.PublishSubject;

class ViewHolderQuestNotCompleted extends BaseQuestViewHolder {
    private PublishSubject<UserQuest> onDoneClickListener;

    ViewHolderQuestNotCompleted(View itemView, PublishSubject<UserQuest> onDoneClickListener) {
        super(itemView);
        this.onDoneClickListener = onDoneClickListener;
    }

    @Override
    void bind(final PatternWithStatus item) {
        super.bind(item);
        itemView.findViewById(R.id.btn_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDoneClickListener.onNext(item.getUserQuest());
            }
        });
        ProgressBar progressBar = itemView.findViewById(R.id.pb_quest_progress);
        progressBar.setMax(item.getUserQuest().getTaskTarget());
        progressBar.setProgress(item.getUserQuest().getTaskProgress());
    }
}
