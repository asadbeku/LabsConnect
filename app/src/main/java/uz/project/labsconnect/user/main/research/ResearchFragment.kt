package uz.project.labsconnect.user.main.research

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import uz.project.labsconnect.R
import uz.project.labsconnect.databinding.FragmentResearchBinding
import uz.project.labsconnect.user.main.research.adapter.SimilarResearchesAdapter
import uz.project.labsconnect.user.main.research.view_model.ResearchViewModel
import uz.project.labsconnect.user.main.types.MyResearchType

class ResearchFragment : Fragment(R.layout.fragment_research) {

    //    Binding
    private var _binding: FragmentResearchBinding? = null
    private val binding get() = _binding!!

    //    Adapter
    private var similarResearchAdapter: SimilarResearchesAdapter? = null

    //    ViewModel
    private val viewModel: ResearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initList()
        bindObserver()
        statusChangeResearchItem()
        statusBarColorChange()
    }

    private fun initList() {
        similarResearchAdapter = SimilarResearchesAdapter(){
            Log.d("checkAdapter","Clicked position=$it")
        }

        with(binding.similarRecycler) {
            val linear = LinearLayoutManager(requireContext())
            adapter = similarResearchAdapter
            layoutManager = linear
            setHasFixedSize(true)
        }
    }

    private fun bindObserver() {
        val research: MyResearchType? = viewModel.getMyResearch.value

        viewModel.getMyResearch()

        binding.generateResearches.setOnClickListener {
            if (research == null) {
                viewModel.getSimilarResearches()
                initList()
            } else {
                val myIntent = Intent(requireContext(), AddMyResearchActivity::class.java)
//                myIntent.putExtra("key", value)
                startActivity(myIntent)
            }

        }



        viewModel.similarResearches.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.similarRecycler.visibility = View.GONE
                binding.progressBarRecycler.visibility = View.VISIBLE
            } else {
                binding.similarRecycler.visibility = View.VISIBLE
                binding.progressBarRecycler.visibility = View.GONE

                similarResearchAdapter?.items = it
            }
        }

        viewModel.updatingState.observe(viewLifecycleOwner, ::updatingState)
        viewModel.updatingStateSimilar.observe(viewLifecycleOwner, ::updatingStateRecycler)
    }

    private fun updatingState(isUpdating: Boolean) {



        binding.progressBar.isVisible = isUpdating
        binding.mainNestedScroll.isVisible = !isUpdating

        viewModel.getMyResearch.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.researchName.text = it.titleResearch
                binding.researchCategory.text = it.categoryName
                binding.researchSummary.text = it.summary
                binding.researchAuthors.text = it.authors
            } else {
                binding.mainNestedScroll.visibility = View.GONE
                binding.emptyTextView.visibility = View.VISIBLE

                binding.generateResearches.text = "Add research"
                binding.generateResearches.setIconResource(R.drawable.add_icon)
            }

        }
    }

    private fun updatingStateRecycler(isUpdating: Boolean) {
        binding.progressBarRecycler.isVisible = isUpdating
        binding.similarRecycler.isVisible = !isUpdating
    }


    private fun statusChangeResearchItem() {

        var itemStatus = false

        binding.researchDetailContainer.visibility = View.GONE

        binding.mainResearchItem.setOnClickListener {
            if (itemStatus) {
                itemStatus = false
                binding.researchDetailContainer.visibility = View.GONE
            } else {
                itemStatus = true
                binding.researchDetailContainer.visibility = View.VISIBLE
            }
        }
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