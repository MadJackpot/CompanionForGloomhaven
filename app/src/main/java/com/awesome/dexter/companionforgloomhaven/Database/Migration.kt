package com.awesome.dexter.companionforgloomhaven.Database

import io.realm.DynamicRealm
import io.realm.RealmMigration

/**
 * Created by Dan on 7/23/2017.
 */

class Migration : RealmMigration {
    override fun migrate(realm: DynamicRealm?, oldVersion: Long, newVersion: Long) {
        if (realm == null)
            return

        var schema = realm.schema
    }

    override fun equals(other: Any?): Boolean {
        return other is Migration
    }

}