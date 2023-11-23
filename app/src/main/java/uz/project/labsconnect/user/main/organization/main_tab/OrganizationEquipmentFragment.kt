package uz.project.labsconnect.user.main.organization.main_tab

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import uz.project.labsconnect.R
import uz.project.labsconnect.databinding.FragmentEquipmentBinding
import uz.project.labsconnect.user.main.organization.view_model.OrganizationViewModel
import uz.project.labsconnect.user.main.search.search_item.adapter.equipments.SearchResultEquipmentsAdapter

class OrganizationEquipmentFragment : Fragment(R.layout.fragment_equipment) {

    private var _binding: FragmentEquipmentBinding? = null
    private val binding get() = _binding!!

    //    Adapters
    private var equipmentsAdapter: SearchResultEquipmentsAdapter? = null
    private val viewModel: OrganizationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEquipmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        equipmentsInitList()
        bindObserver()

    }

    private fun equipmentsInitList() {

        equipmentsAdapter = SearchResultEquipmentsAdapter() {
            Log.d("checkAdapter", "Clicked position=$it")
        }

        with(binding.fragmentEquipmentRecycler) {
            val linear = LinearLayoutManager(requireContext())

            adapter = equipmentsAdapter
            layoutManager = linear
            setHasFixedSize(true)
        }

    }

    private fun bindObserver() {
        viewModel.getOrganizationInfo(100)
        viewModel.equipmentList.observe(viewLifecycleOwner) {
            equipmentsAdapter?.items = it
        }
    }

}