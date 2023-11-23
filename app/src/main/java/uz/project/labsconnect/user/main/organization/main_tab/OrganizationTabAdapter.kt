package uz.project.labsconnect.user.main.organization.main_tab

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.project.labsconnect.user.main.organization.OrganizationActivity

class OrganizationTabAdapter(fragment: OrganizationActivity) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> AboutFragment()
            1 -> OrganizationEquipmentFragment()
            2 -> QAFragment()
            else -> AboutFragment()
        }
    }
}