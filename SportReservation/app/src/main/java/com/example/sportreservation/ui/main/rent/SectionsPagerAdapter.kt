package com.example.sportreservation.ui.main.rent

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.sportreservation.ui.main.rent.equipment.EquipmentFragment
import com.example.sportreservation.ui.main.rent.referee.RefereeFragment

class SectionsPagerAdapter(activity: RentFragment) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = RefereeFragment()
            1 -> fragment = EquipmentFragment()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int = 2
}