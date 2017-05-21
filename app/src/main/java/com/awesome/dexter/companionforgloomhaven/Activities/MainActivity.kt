package com.awesome.dexter.companionforgloomhaven.Activities

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.awesome.dexter.companionforgloomhaven.characters.*
import com.awesome.dexter.companionforgloomhaven.Adapters.CharacterAdapter
import com.awesome.dexter.companionforgloomhaven.Database.GloomhavenDatabase
import com.awesome.dexter.companionforgloomhaven.R
import com.awesome.dexter.companionforgloomhaven.R.layout.activity_main
import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.kotlinextensions.*
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
        var characters: List<Character> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        FlowManager.init(this)

        var characters = (select from Character::class).list
        if (characters.isEmpty()) {
            characters.addAll(arrayOf(Character("Jonathan", Race.HumanScoundrel),
                    Character("Dan", Race.SavvasCragheart),
                    Character("Michele", Race.QuatrylTinkerer),
                    Character("Kevin", Race.VermlingMindthief),
                    Character("Michelle", Race.OrchidSpellweaver),
                    Character("Ryan", Race.InoxBrute)))
        }

        val adapter: CharacterAdapter = CharacterAdapter(this, android.R.layout.simple_list_item_2, characters)
        CharacterListView.adapter = adapter
    }

    override fun onDestroy() {
        characters.processInTransactionAsync({it, dbWrapper-> it.save(dbWrapper)}, Transaction.Success{})

        super.onDestroy()
    }
}
