package com.example.ama.questapp.presentation.list.adapter.vh;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ama.questapp.R;
import com.example.ama.questapp.data.db.model.pojo.PatternWithStatus;
import com.example.ama.questapp.presentation.list.adapter.OnCompleteQuestClickListener;

/***
 * View holder using additional layout elements towards {@link BaseQuestViewHolder}.
 * Created for displaying quests marked as uncompleted
 */

public class ViewHolderQuestNotCompleted extends BaseQuestViewHolder {
    private OnCompleteQuestClickListener listener;

    ViewHolderQuestNotCompleted(View itemView, OnCompleteQuestClickListener listener) {
        super(itemView);
        this.listener = listener;
    }

    @Override
    public void bind(final PatternWithStatus item) {
        ((TextView) itemView.findViewById(R.id.tv_quest_name))
                .setText(item.getPattern().getQuestName());
        ((TextView) itemView.findViewById(R.id.tv_quest_description))
                .setText(item.getPattern().getQuestDescription());
        ((TextView) itemView.findViewById(R.id.tv_target_progress))
                .setText(itemView.getResources().getString(
                        R.string.main_quest_list_progress_target_counter,
                        item.getUserQuest().getTaskProgress(),
                        item.getUserQuest().getTaskTarget()));
        itemView.findViewById(R.id.btn_done)
                .setOnClickListener(v -> listener.tryToCompleteQuest(item.getUserQuest()));
        ProgressBar progressBar = itemView.findViewById(R.id.pb_quest_progress);
        progressBar.setMax(item.getUserQuest().getTaskTarget());
        progressBar.setProgress(item.getUserQuest().getTaskProgress());
    }
}
