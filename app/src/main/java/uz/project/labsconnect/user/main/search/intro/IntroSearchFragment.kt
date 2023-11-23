package uz.project.labsconnect.user.main.search.intro

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import uz.project.labsconnect.R
import uz.project.labsconnect.databinding.FragmentSearchIntroBinding
import uz.project.labsconnect.user.main.home.adapter.category.CategoryAdapter
import uz.project.labsconnect.user.main.search.intro.adapter.category.SearchCategoryAdapter
import uz.project.labsconnect.user.main.search.intro.adapter.recomendation.RecommendedAdapter
import uz.project.labsconnect.user.main.search.intro.adapter.topic.SearchTopicAdapter
import uz.project.labsconnect.user.main.search.view_model.SearchViewModel

class IntroSearchFragment : Fragment(R.layout.fragment_search_intro) {

    //    Binding
    private var _binding: FragmentSearchIntroBinding? = null
    private val binding get() = _binding!!

    //    Adapter
    private var categoryAdapter: SearchCategoryAdapter? = null
    private var topicAdapter: SearchTopicAdapter? = null
    private var recommendedAdapter: RecommendedAdapter? = null

    //    ViewModel
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchIntroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryInitList()
        topicsInitList()
        recommendationInitList()

        bindObservers()
    }

    private fun topicsInitList() {
        topicAdapter = SearchTopicAdapter(){
            Log.d("checkAdapter","Clicked position=$it")
        }

        with(binding.topicRecycler) {
            val linear = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)
            adapter = topicAdapter
            layoutManager = linear
            setHasFixedSize(true)
        }
    }

    private fun categoryInitList() {
        categoryAdapter = SearchCategoryAdapter(){
            Log.d("checkAdapter","Clicked position=$it")
        }

        with(binding.searchCategoryRecycler) {
            val linear = LinearLayoutManager(requireContext())
            adapter = categoryAdapter
            layoutManager = linear
            setHasFixedSize(true)
        }
    }

    private fun recommendationInitList() {
        recommendedAdapter = RecommendedAdapter(){
            Log.d("checkAdapter","Clicked position=$it")
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

}