package uz.project.labsconnect.user.main.profile

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import uz.project.labsconnect.R
import uz.project.labsconnect.databinding.FragmentProfileBinding
import uz.project.labsconnect.user.main.profile.container.completed_research.ResearchProfileFragment
import uz.project.labsconnect.user.main.profile.container.qa.QAProfileFragment
import uz.project.labsconnect.user.main.profile.container.saved.TabFragment
import uz.project.labsconnect.user.main.profile.tab_fragments.EquipmentFragment
import uz.project.labsconnect.user.main.profile.tab_fragments.LabsFragment
import uz.project.labsconnect.user.main.profile.tab_fragments.TabsAdapter


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        statusBarColorChange()


        changeFragments()

    }

    private fun changeFragments() {
        replaceContainer(TabFragment())

        binding.QAContainer.setOnClickListener {
            replaceContainer(QAProfileFragment())
        }
        binding.ResearchContainer.setOnClickListener {
            replaceContainer(ResearchProfileFragment())
        }
        binding.savedContainer.setOnClickListener {
            replaceContainer(TabFragment())
        }
    }

    private fun replaceContainer(fragment: Fragment) {
        childFragmentManager.beginTransaction().replace(R.id.mainNavContainer, fragment).commit()
    }

    private fun statusBarColorChange() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            requireActivity().window.statusBarColor =
                ContextCompat.getColor(requireContext(), R.color.white);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decor: View = requireActivity().window.decorView
            var flags = decor.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            decor.systemUiVisibility = flags
        }
    }


}