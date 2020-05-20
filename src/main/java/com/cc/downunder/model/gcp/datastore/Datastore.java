package com.cc.downunder.model.gcp.datastore;

import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;

/**
 * @author Jessica Cui
 * @project downunder
 * @date 2020-04-30
 */

/**
 * A snippet for Google Cloud Datastore showing how to create an entity.
 */
public class Datastore {
    com.google.cloud.datastore.Datastore datastore;
    String key, capital;
    int population;

    public Datastore() {
        datastore = DatastoreOptions.getDefaultInstance().getService();
//            this.key = key;
//            this.capital = capital;
//            this.population = population;
    }

    public void createStateEntity(String keyName, String capital, int population) {
        KeyFactory keyFactory = datastore.newKeyFactory().setKind("State");
        Key key = keyFactory.newKey(keyName);
        Entity entity =
                Entity.newBuilder(key)
                        .set("capital", capital)
                        .set("population", population)
                        .build();
        datastore.put(entity);
    }

}