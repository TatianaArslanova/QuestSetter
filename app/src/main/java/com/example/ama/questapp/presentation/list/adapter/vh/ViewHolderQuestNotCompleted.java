package com.example.ama.questapp.presentation.list.adapter.vh;

import android.view.View;
import android.widget.ImageButton;
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
        ((TextView) itemView.findViewById(R.id.tv_name))
                .setText(item.getPattern().getQuestName());
        ((TextView) itemView.findViewById(R.id.tv_description))
                .setText(item.getPattern().getQuestDescription());
        ((TextView) itemView.findViewById(R.id.tv_result))
                .setText(itemView.getResources().getString(
                        R.string.main_quest_list_progress_target_counter,
                        item.getUserQuest().getTaskProgress(),
                        item.getUserQuest().getTaskTarget()));
        ImageButton iconBtn = itemView.findViewById(R.id.ib_icon);
        iconBtn.setOnClickListener(v -> listener.tryToCompleteQuest(item.getUserQuest()));
        switch (item.getPattern().getQuestType()) {
            case ONCE:
                iconBtn.setImageResource(R.drawable.ic_quest_one_shot);
                break;
            case COUNT:
                iconBtn.setImageResource(R.drawable.ic_quest_counter);
                break;
        }
        ProgressBar progressBar = itemView.findViewById(R.id.pb_progress);
        progressBar.setMax(item.getUserQuest().getTaskTarget());
        progressBar.setProgress(item.getUserQuest().getTaskProgress());
    }
}
