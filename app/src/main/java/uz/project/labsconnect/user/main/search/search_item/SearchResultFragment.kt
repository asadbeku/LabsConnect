package uz.project.labsconnect.user.main.search.search_item

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import uz.project.labsconnect.R
import uz.project.labsconnect.databinding.FragmentSearchResultsBinding
import uz.project.labsconnect.user.main.search.intro.adapter.recomendation.RecommendedAdapter
import uz.project.labsconnect.user.main.search.search_item.adapter.equipments.SearchResultEquipmentsAdapter
import uz.project.labsconnect.user.main.search.search_item.view_model.SearchResultViewModel
import uz.project.labsconnect.user.main.search.view_model.SearchViewModel

class SearchResultFragment() : Fragment(R.layout.fragment_search_results) {
    private val query: String=""
    //  Binding
    private var _binding: FragmentSearchResultsBinding? = null
    private val binding get() = _binding!!

    //    Adapters
    private var labsAdapter: RecommendedAdapter? = null
    private var equipmentsAdapter: SearchResultEquipmentsAdapter? = null

    //    ViewModel
    private val viewModel: SearchResultViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchResultsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        labsInitList()
        equipmentsInitList()

        bindObservers()
    }

    private fun labsInitList() {
        labsAdapter = RecommendedAdapter(){
            Log.d("checkAdapter","Clicked position=$it")
        }

        with(binding.labsRecycler) {
            val linear = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }

            adapter = labsAdapter
            layoutManager = linear
            setHasFixedSize(true)
        }
    }

    private fun equipmentsInitList() {

        equipmentsAdapter = SearchResultEquipmentsAdapter(){
            Log.d("checkAdapter","Clicked position=$it")
        }

        with(binding.equipmentsRecycler){
            val linear = LinearLayoutManager(requireContext())

            adapter = equipmentsAdapter
            layoutManager = linear
            setHasFixedSize(true)
        }

    }

    private fun bindObservers() {


        viewModel.labs.observe(viewLifecycleOwner){
            labsAdapter?.items= it
        }

        viewModel.equipments.observe(viewLifecycleOwner){
            equipmentsAdapter?.items = it
        }
    }

}