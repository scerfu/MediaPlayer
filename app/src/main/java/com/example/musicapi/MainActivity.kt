package com.example.musicapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.musicapi.view.Communicator
import com.example.musicapi.view.DisplayFragment
import com.example.musicapi.view.SearchFragment

class MainActivity : AppCompatActivity(), Communicator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_search, SearchFragment())
            .commit()
    }

    override fun sendDataToSearch(musicTerm: String) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_display,
        DisplayFragment.newInstance(musicTerm)).addToBackStack(null).commit()
    }
}