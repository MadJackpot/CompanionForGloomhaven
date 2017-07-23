package com.awesome.dexter.companionforgloomhaven.Activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.awesome.dexter.companionforgloomhaven.Adapters.CharacterAdapter
import com.awesome.dexter.companionforgloomhaven.Characters.Character
import com.awesome.dexter.companionforgloomhaven.Characters.Race
import com.awesome.dexter.companionforgloomhaven.Characters.getDisplayString
import com.awesome.dexter.companionforgloomhaven.Database.CharacterQueries
import com.awesome.dexter.companionforgloomhaven.Database.Migration
import com.awesome.dexter.companionforgloomhaven.R.id.deleteAllCharacters
import com.awesome.dexter.companionforgloomhaven.R.layout.activity_main
import com.awesome.dexter.companionforgloomhaven.R.layout.dialog_new_character
import com.awesome.dexter.companionforgloomhaven.R.menu.main_menu
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_new_character.view.*
import kotlin.properties.Delegates

val CHARACTER_KEY: String = "CHARKEY"

class MainActivity : AppCompatActivity() {
    private var realm: Realm by Delegates.notNull()
    var characters = mutableListOf<Character>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)
        Realm.init(this)

        val config = RealmConfiguration.Builder()
                .name("DBConfig")
                .schemaVersion(0)
                .migration(Migration())
                .build()
        Realm.setDefaultConfiguration(config)
        realm = Realm.getDefaultInstance()

        title = "Gloomhaven Companion"
        setSupportActionBar(mainToolbar)

        characters = CharacterQueries.GetCharacters()

        if (characters.isEmpty()) {
            arrayOf(Character("Jonathan", Race.HumanScoundrel),
                Character("Dan", Race.SavvasCragheart),
                Character("Michele", Race.QuatrylTinkerer),
                Character("Kevin", Race.VermlingMindthief),
                Character("Michelle", Race.OrchidSpellweaver),
                Character("Ryan", Race.InoxBrute)).forEach{
                    characters.add(CharacterQueries.CreateNewCharacter(it.name, it.race))
                    }
        }

        CharacterListView.onItemClickListener = AdapterView.OnItemClickListener{
            _, _, pos, _->
            val charActivity = Intent(this, CharacterActivity::class.java)
            charActivity.putExtra(CHARACTER_KEY, characters[pos].id)
            startActivity(charActivity)
            UpdateCharacterList()
        }

        attachCharacterAddListener()
        UpdateCharacterList()
    }

    fun attachCharacterAddListener() {
        addCharacterButton.setOnClickListener {
            var builder = AlertDialog.Builder(this)
            builder.setTitle("Add Character")
                    .setCancelable(true)
            var newCharSetup = LayoutInflater.from(this).inflate(dialog_new_character, null)
            val raceStrings = Race.values().map{ it.getDisplayString() }
            var characterArray = ArrayAdapter(this, android.R.layout.simple_spinner_item, raceStrings)
            newCharSetup.raceSpinner.adapter = characterArray
            builder.setView(newCharSetup)
            builder.setPositiveButton("OK", {
                _, _ ->
                    characters.add(CharacterQueries.CreateNewCharacter(newCharSetup.newCharacterName.text.toString(),
                            newCharSetup.raceSpinner.selectedItemPosition))
                UpdateCharacterList()
            })

            var alert = builder.create()
            alert.show()

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (menu != null)
            MenuInflater(this).inflate(main_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item == null)
            return true

        when (item.itemId) {
            deleteAllCharacters -> {
                realm.executeTransaction{ characters.forEach{ it.deleteFromRealm() } }
                characters.clear()
                UpdateCharacterList()
            }
        }

        return true
    }

    private fun UpdateCharacterList(){
        CharacterListView.adapter = CharacterAdapter(this, android.R.layout.simple_list_item_2, characters)
    }
}
