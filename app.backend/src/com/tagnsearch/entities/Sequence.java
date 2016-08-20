package com.tagnsearch.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by JS on 8/20/16.
 */

@Document(indexName = "tagnsearch", type = "sequences", indexStoreType = "memory", shards = 1, replicas = 0, refreshInterval = "-1")
public class Sequence {

    @Id
    private String name;

    private long value;

    public Sequence() {
    }

    public Sequence(final String name, final long value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public long getValue() {
        return value;
    }

    public void setValue(final long value) {
        this.value = value;
    }
}
