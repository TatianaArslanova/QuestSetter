package com.example.ama.questapp.repo.model;

public enum QuestType {
    ONCE(0),
    COUNT(1),
    TIME(2),
    AUTO_COUNT(3);

    private int type;

    QuestType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
