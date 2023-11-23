package uz.project.labsconnect.user.main.profile.tab_fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.project.labsconnect.R
import uz.project.labsconnect.user.main.home.adapter.equipments.EquipmentsAdapter
import uz.project.labsconnect.user.main.profile.tab_fragments.view_model.EquipmentsViewModel

class EquipmentFragment: Fragment(R.layout.fragment_equipment) {

    private var equipmentsAdapter: EquipmentsAdapter? = null
    private val viewModel: EquipmentsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler = view.findViewById<RecyclerView>(R.id.fragmentEquipmentRecycler)

        initList(recycler)
        bindObserver()
    }

    private fun initList(recycler: RecyclerView) {

        equipmentsAdapter = EquipmentsAdapter(){
            Log.d("checkAdapter","Clicked position=$it")
        }

        with(recycler) {
            val linear = LinearLayoutManager(requireContext())

            adapter = equipmentsAdapter
            layoutManager = linear
            setHasFixedSize(true)

        }
    }

    private fun bindObserver() {
        viewModel.getEquipments()

        viewModel.equipments.observe(viewLifecycleOwner) {
            equipmentsAdapter?.items = it
        }
    }



}