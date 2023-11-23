package uz.project.labsconnect.user.main.organization.main_tab

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import uz.project.labsconnect.utils.SharedPreferencesHelper
import uz.project.labsconnect.databinding.TabAboutFragmentBinding
import uz.project.labsconnect.user.main.organization.view_model.OrganizationViewModel
import uz.project.labsconnect.utils.OnEventSelectedListener

class AboutFragment : Fragment() {

    private var _binding: TabAboutFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: OrganizationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TabAboutFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindData()
    }

    private fun bindData() {
        SharedPreferencesHelper(requireContext()).apply {
            binding.description.text = getDataByKey("description")
            binding.chiefName.text=getDataByKey("chiefName")
            binding.orgEmail.text=getDataByKey("email")
            binding.orgTel.text=getDataByKey("tel")
            binding.address.text=getDataByKey("address")
            binding.website.text=getDataByKey("web")
            Log.d("checkViewModel", "Worked")
        }
    }


}