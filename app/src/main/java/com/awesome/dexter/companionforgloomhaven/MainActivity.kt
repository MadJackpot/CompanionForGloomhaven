package com.awesome.dexter.companionforgloomhaven

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.awesome.dexter.companionforgloomhaven.characters.*
import com.awesome.dexter.companionforgloomhaven.Adapters.CharacterAdapter

class MainActivity : AppCompatActivity() {
    val items: Array<BaseCharacter> = arrayOf<BaseCharacter>(
            HumanScoundrel("Jonathan"),
            InoxBrute("Ryan"),
            OrchidSpellweaver("Michelle"),
            QuatrylTinkerer("Kevin"),
            SavvasCragheartCharacter("Dan"),
            VermlingMindthief("Michele")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listview = findViewById(R.id.CharacterListView) as ListView
        val adapter: CharacterAdapter = CharacterAdapter(this, android.R.layout.simple_list_item_2, items)
        listview.adapter = adapter
    }
}
