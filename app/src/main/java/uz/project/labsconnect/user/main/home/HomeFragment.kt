package uz.project.labsconnect.user.main.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import uz.project.labsconnect.R
import uz.project.labsconnect.databinding.FragmentHomeBinding
import uz.project.labsconnect.user.main.equipment.EquipmentDetailActivity
import uz.project.labsconnect.user.main.home.adapter.category.CategoryAdapter
import uz.project.labsconnect.user.main.home.adapter.equipments.EquipmentsAdapter
import uz.project.labsconnect.user.main.search.search_item.adapter.equipments.SearchResultEquipmentsAdapter
import uz.project.labsconnect.user.main.home.adapter.labs.LabsAdapter
import uz.project.labsconnect.user.main.home.view_model.HomeViewModel
import uz.project.labsconnect.user.main.organization.OrganizationActivity

class HomeFragment : Fragment() {

    //  Binding
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    //    Adapters
    private var categoryAdapter: CategoryAdapter? = null
    private var labsAdapter: LabsAdapter? = null
    private var equipmentsAdapter: EquipmentsAdapter? = null

    //    ViewModel
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireActivity(), R.color.colorPrimary)
        requireActivity().window.decorView.systemUiVisibility = 0
        initListCategory()
        initListLabs()
        initListEquipments()

        categoryObservers()
    }

    private fun initListCategory() {
        val cTime = System.currentTimeMillis()
        categoryAdapter = CategoryAdapter() {
            Log.d("checkAdapter", "Clicked position=$it")
        }

        with(binding.categoryRecycler) {
            val linear = GridLayoutManager(requireContext(), 2)
            adapter = categoryAdapter
            layoutManager = linear
            setHasFixedSize(true)
        }

        Log.d("checkTime", "recycler Time: ${System.currentTimeMillis() - cTime}")
    }

    private fun initListLabs() {
        labsAdapter = LabsAdapter() {
            Log.d("checkAdapter", "Clicked position=$it")

            val intent = Intent(activity, OrganizationActivity::class.java)
            intent.putExtra("key", getLabsId(it).toString())
            startActivity(intent)

        }

        with(binding.labsRecycler) {
            val linear = GridLayoutManager(requireContext(), 1)
            adapter = labsAdapter
            layoutManager = linear
            setHasFixedSize(true)
        }
    }

    private fun initListEquipments() {
        equipmentsAdapter = EquipmentsAdapter() {
            Log.d("checkAdapter", "Clicked position=$it")

            val intent = Intent(activity, EquipmentDetailActivity::class.java)
            intent.putExtra("key", getEquipmentId(it).toString())
            startActivity(intent)
        }

        with(binding.equipmentsRecycler) {
            val lManager =
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
            adapter = equipmentsAdapter
            layoutManager = lManager
            setHasFixedSize(true)
        }
    }

    private fun categoryObservers() {
        viewModel.getCategory()
        viewModel.getLabs()
        viewModel.getPopularEquipments()
        viewModel.getOurNumbers()

        viewModel.category.observe(viewLifecycleOwner) {
            categoryAdapter?.items = it
        }

        viewModel.labs.observe(viewLifecycleOwner) {
            labsAdapter?.items = it
            viewModel.getPopularEquipments()
        }

        viewModel.equipments.observe(viewLifecycleOwner) {
            equipmentsAdapter?.items = it
        }

        viewModel.ourNumbersLabs.observe(viewLifecycleOwner) {
            binding.orgSizeTextView.text = it.toString()
        }

        viewModel.ourNumbersEquipments.observe(viewLifecycleOwner) {
            binding.equipmentSizeTextView.text = it.toString()
        }

    }

    private fun getEquipmentId(position: Int): Int {
        return viewModel.getEquipmentIdByPosition(position)
    }

    private fun getLabsId(position: Int): Int {
        return viewModel.getOrganizationIdByPosition(position)
    }

}