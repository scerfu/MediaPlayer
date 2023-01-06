package com.example.musicapi.view.adapter

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.musicapi.view.DisplayFragment
import com.example.musicapi.view.SearchFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> DisplayFragment()
            1 -> DisplayFragment()
            2 -> DisplayFragment()
            else -> {throw Resources.NotFoundException("Item not found")}
        }
    }

}