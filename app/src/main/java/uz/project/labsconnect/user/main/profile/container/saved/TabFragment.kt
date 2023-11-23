package uz.project.labsconnect.user.main.profile.container.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import uz.project.labsconnect.databinding.FragmentTabBinding
import uz.project.labsconnect.user.main.profile.tab_fragments.TabsAdapter

class TabFragment : Fragment() {

    private var _binding: FragmentTabBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindTabLayout()
    }

    private fun bindTabLayout() {
        val tabNames: Array<String> = arrayOf("Labs", "Equipments")
        val viewPager2 = binding.tabContainer

        viewPager2.adapter = TabsAdapter(requireActivity())

        TabLayoutMediator(binding.tabLayout, viewPager2) { tab, position ->
            tab.text = tabNames[position]
        }.attach()
    }

}