package uz.project.labsconnect.user.main.search

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import uz.project.labsconnect.R
import uz.project.labsconnect.databinding.FragmentSearchBinding
import uz.project.labsconnect.user.main.equipment.EquipmentDetailActivity
import uz.project.labsconnect.user.main.organization.OrganizationActivity
import uz.project.labsconnect.user.main.search.bottom_sheet_dialog.FilterBottomSheetDialog
import uz.project.labsconnect.user.main.search.intro.adapter.category.SearchCategoryAdapter
import uz.project.labsconnect.user.main.search.intro.adapter.recomendation.RecommendedAdapter
import uz.project.labsconnect.user.main.search.intro.adapter.topic.SearchTopicAdapter
import uz.project.labsconnect.user.main.search.search_item.adapter.equipments.SearchResultEquipmentsAdapter
import uz.project.labsconnect.user.main.search.search_item.view_model.SearchResultViewModel
import uz.project.labsconnect.user.main.search.view_model.SearchViewModel

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    //    Adapters
    private var labsAdapter: RecommendedAdapter? = null
    private var equipmentsAdapter: SearchResultEquipmentsAdapter? = null

    //    ViewModel
    private val viewModelResult: SearchResultViewModel by viewModels()

    //    Adapter
    private var categoryAdapter: SearchCategoryAdapter? = null
    private var topicAdapter: SearchTopicAdapter? = null
    private var recommendedAdapter: RecommendedAdapter? = null

    //    ViewModel
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        statusBarColorChange()
        checkSearchText()
        val text = binding.searchInputTextView.editText?.text.toString().orEmpty()
        searchTextCheck(text)

//        Result search
        labsInitList()
        equipmentsInitList()
        bindResultObservers()

//        Intro search
        categoryInitList()
        topicsInitList()
        recommendationInitList()
        bindObservers()

        binding.filterButton.setOnClickListener {

            val modalBottomSheet = FilterBottomSheetDialog()
            modalBottomSheet.show(childFragmentManager, FilterBottomSheetDialog.TAG)
        }

    }

    private fun statusBarColorChange() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            requireActivity().window.statusBarColor =
                ContextCompat.getColor(requireContext(), R.color.statusBarColor);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decor: View = requireActivity().window.decorView
            var flags = decor.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            decor.systemUiVisibility = flags
        }

    }

    private fun checkSearchText() {
        binding.searchInputTextView.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called before the text is changed.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // This method is called whenever the text is changed.
                val newText = s.toString()
                viewModelResult.searchOrganization(newText)
                viewModelResult.searchEquipment(newText)
                searchTextCheck(newText)
                // Do something with the new text.
            }

            override fun afterTextChanged(s: Editable?) {
                // This method is called after the text has changed.
            }
        })
    }

    private fun searchTextCheck(text: String) {

        if (text.isEmpty()) {
            binding.introSearch.visibility = View.VISIBLE
            binding.resultSearch.visibility = View.GONE
        } else {
            binding.introSearch.visibility = View.GONE
            binding.resultSearch.visibility = View.VISIBLE
        }

    }

    private fun labsInitList() {
        labsAdapter = RecommendedAdapter() {

            val intent = Intent(activity, OrganizationActivity::class.java)
            intent.putExtra("key", getSearchOrganizationId(it).toString())
            startActivity(intent)

            Log.d("checkAdapter", "Clicked position=$it")
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

        equipmentsAdapter = SearchResultEquipmentsAdapter() {
            Log.d("checkAdapter", "Clicked position=$it")

            val intent = Intent(activity, EquipmentDetailActivity::class.java)
            intent.putExtra("key", getEquipmentId(it).toString())
            startActivity(intent)

        }

        with(binding.equipmentsRecycler) {
            val linear = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.VERTICAL
            }

            adapter = equipmentsAdapter
            layoutManager = linear
            setHasFixedSize(true)
        }
    }

    private fun topicsInitList() {
        topicAdapter = SearchTopicAdapter() {
            Log.d("checkAdapter", "Clicked position=$it")
        }

        with(binding.topicRecycler) {
            val linear = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)
            adapter = topicAdapter
            layoutManager = linear
            setHasFixedSize(true)
        }
    }

    private fun categoryInitList() {
        categoryAdapter = SearchCategoryAdapter() {
            Log.d("checkAdapter", "Clicked position=$it")
        }

        with(binding.searchCategoryRecycler) {
            val linear = LinearLayoutManager(requireContext())
            adapter = categoryAdapter
            layoutManager = linear
            setHasFixedSize(true)
        }
    }

    private fun recommendationInitList() {
        recommendedAdapter = RecommendedAdapter() {

            val intent = Intent(activity, OrganizationActivity::class.java)

            intent.putExtra("key", getRecommendedOrganizationId(it).toString())

            startActivity(intent)
            Log.d("checkAdapter", "Clicked position=$it, id=${getRecommendedOrganizationId(it)}")
        }

        with(binding.recommendedRecycler) {
            val linear = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }

            adapter = recommendedAdapter
            layoutManager = linear
            setHasFixedSize(true)
        }
    }

    private fun bindObservers() {
        viewModel.getCategory()
        viewModel.getTopic()
        viewModel.getRecommended()

        viewModel.category.observe(viewLifecycleOwner) {
            categoryAdapter?.items = it
        }

        viewModel.topic.observe(viewLifecycleOwner) {
            topicAdapter?.items = it
        }

        viewModel.recommended.observe(viewLifecycleOwner) {
            recommendedAdapter?.items = it
        }
    }

    @SuppressLint("SetTextI18n")
    private fun bindResultObservers() {
        viewModelResult.getAllData()

        viewModelResult.equipments.observe(viewLifecycleOwner) {

            Log.d("checkAdapter", "List: $it")
//                equipmentsAdapter?.items = it
            equipmentsAdapter?.submitList(it.take(20))
            equipmentsAdapter?.notifyDataSetChanged()

            binding.orgResultsCount.text =
                "${labsAdapter?.itemCount!! + equipmentsAdapter?.itemCount!!} Results"
        }

        viewModelResult.labs.observe(viewLifecycleOwner) {
            labsAdapter?.items = it
            equipmentsAdapter?.notifyDataSetChanged()
            binding.orgResultsCount.text =
                "${labsAdapter?.itemCount!! + equipmentsAdapter?.itemCount!!} Results"
        }


    }

    private fun getRecommendedOrganizationId(position: Int): Int {
        Log.d("checkPosition","${viewModel.getOrganizationIdByPosition(position)}")
        return viewModel.getOrganizationIdByPosition(position)
    }

    private fun getSearchOrganizationId(position: Int): Int {
        Log.d("checkPosition","${viewModel.getOrganizationIdByPosition(position)}")
        return viewModelResult.getOrganizationIdByPosition(position)
    }

    private fun getEquipmentId(position: Int) : Int{
        return viewModelResult.getEquipmentIdByPosition(position)
    }

}