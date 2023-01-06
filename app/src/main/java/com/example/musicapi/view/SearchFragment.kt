package com.example.musicapi.view

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.musicapi.R
import com.example.musicapi.databinding.FragmentSearchBinding
import com.example.musicapi.view.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

private const val TAG = "SearchFragment"
class SearchFragment: Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var communicator: Communicator
    private lateinit var musicTerm: String
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2

    override fun onAttach(context: Context) {
        super.onAttach(context)
        when(context){
            is Communicator -> communicator = context
            else -> throw Exception("Incorrect Host Activity")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentSearchBinding.inflate(inflater,
        container,false)
        initViews()
        return binding.root
    }

    private fun initViews() {
        tabLayout = binding.root.findViewById(R.id.tab_layout)
        viewPager2 = binding.root.findViewById(R.id.view_pager)
        viewPager2.adapter = ViewPagerAdapter(binding.root.context as FragmentActivity)
        TabLayoutMediator(tabLayout, viewPager2){ tab,index->
            when(index){
                0 -> {
                    tab.text = "Rock"
                    tab.setIcon(R.drawable.ic_music_note)
                    musicTerm = tab.text.toString()
                    sendSearchParams(musicTerm)
                }
                1 -> {
                    tab.text = "Classic"
                    tab.setIcon(R.drawable.ic_music_note)
                }
                2 -> {
                    tab.text = "Pop"
                    tab.setIcon(R.drawable.ic_music_note)
                }
                else -> {throw Resources.NotFoundException("Item not found")}
            }
        }.attach()
        binding.apply {
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab) {
                    viewPager2!!.currentItem = tab.position
                    musicTerm = tab.text.toString()
                    Log.d(TAG, "onTabSelected: $musicTerm")
                    sendSearchParams(musicTerm)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }

            })
        }
    }

    private fun sendSearchParams(musicTerm: String) {
        communicator.sendDataToSearch(musicTerm)
    }
}