package com.cc.downunder.datastore;

import com.google.cloud.datastore.*;

/**
 * @author Jessica Cui
 * @project downunder
 * @date 2020-04-30
 */

/**
 * A snippet for Google Cloud Datastore showing how to create an entity.
 */
public class CreateEntity {
    Datastore datastore;
    String key, capital;
    int population;

    public CreateEntity() {
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