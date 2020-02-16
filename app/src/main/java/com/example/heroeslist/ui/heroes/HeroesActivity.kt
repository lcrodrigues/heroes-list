package com.example.heroeslist.ui.heroes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.heroeslist.R
import kotlinx.android.synthetic.main.activity_heroes.*

class HeroesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heroes)

        appToolbar.title = resources.getString(R.string.app_name)
        setSupportActionBar(appToolbar)

    }
}
