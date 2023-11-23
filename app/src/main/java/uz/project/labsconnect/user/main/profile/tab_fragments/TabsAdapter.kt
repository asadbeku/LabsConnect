package uz.project.labsconnect.user.main.profile.tab_fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.project.labsconnect.user.main.profile.ProfileFragment

class TabsAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
//        val fragment = LabsFragment()
//        fragment.arguments = Bundle().apply {
//            putInt("ARG_OBJECT", position + 1)
//        }
//        return fragment

        return when (position) {
            0 -> LabsFragment()
            else -> EquipmentFragment()
        }
    }
}