package com.example.ama.questapp.presentation.list.adapter.vh;

import android.view.View;
import android.widget.TextView;

import com.example.ama.questapp.R;
import com.example.ama.questapp.data.db.model.pojo.UserTaskWithPattern;

/***
 * View holder uses primary layout elements. Created for displaying quests marked as completed
 */

public class ViewHolderQuestCompleted extends BaseQuestViewHolder {

    ViewHolderQuestCompleted(View itemView) {
        super(itemView);
    }

    public void bind(UserTaskWithPattern item) {
        ((TextView) itemView.findViewById(R.id.tv_name))
                .setText(item.getPattern().getQuestName());
        ((TextView) itemView.findViewById(R.id.tv_description))
                .setText(item.getPattern().getQuestDescription());
        ((TextView) itemView.findViewById(R.id.tv_result))
                .setText(itemView.getResources().getString(
                        R.string.main_quest_list_progress_target_counter,
                        item.getUserTask().getTaskProgress(),
                        item.getUserTask().getTaskTarget()));
    }
}
