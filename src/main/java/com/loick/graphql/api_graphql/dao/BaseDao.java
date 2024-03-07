package com.loick.graphql.api_graphql.dao;

public class BaseDao {
    private int nextId;

    public BaseDao(int nextId) {
        this.nextId = nextId;
    }

    public int useNextId() {
        int currentId = nextId;
        nextId++;
        return currentId;
    }
}
