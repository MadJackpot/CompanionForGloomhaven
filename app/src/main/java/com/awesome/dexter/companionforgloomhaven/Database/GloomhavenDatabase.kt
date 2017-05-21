package com.awesome.dexter.companionforgloomhaven.Database

import com.raizlabs.android.dbflow.annotation.Database

/**
 * Created by Dan on 5/20/2017.
 */
const val NAME: String = "GloomhavenDatabase"
const val VERSION: Int = 1

@Database(name = NAME, version = VERSION)
class GloomhavenDatabase() {
    val name = NAME
    val version = VERSION
}

