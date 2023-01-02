package com.example.codelab5_crickbuzz.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


class ViewPagerAdapter(fm : FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm,lifecycle) {

    private val list=ArrayList<Fragment>()

    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        Log.e("main adapter","${position.toString()}")
        return list[position]
    }

    /**
     * adding fragment in the list
     */
    fun addFragment(fragment: Fragment)
    {
        list.add(fragment)
    }



}