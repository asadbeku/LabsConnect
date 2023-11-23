package uz.project.labsconnect.user.main.profile.container.completed_research

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import uz.project.labsconnect.R
import uz.project.labsconnect.databinding.FragmentProfileQaBinding
import uz.project.labsconnect.databinding.FragmentProfileResearchBinding
import uz.project.labsconnect.user.main.profile.container.completed_research.adapter.ResearchesAdapter
import uz.project.labsconnect.user.main.profile.container.completed_research.view_model.ResearchProfileViewModel
import uz.project.labsconnect.user.main.profile.container.qa.adapter.QAAdapter
import uz.project.labsconnect.user.main.profile.container.qa.view_model.QAViewModel

class ResearchProfileFragment : Fragment(R.layout.fragment_profile_research) {

    //    Binding
    private var _binding: FragmentProfileResearchBinding? = null
    private val binding get() = _binding!!

    //    Adapter
    private var researchesAdapter: ResearchesAdapter? = null

    //    ViewModel
    private val viewModel: ResearchProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileResearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initList()
        bindObserver()
    }

    private fun initList() {
        researchesAdapter = ResearchesAdapter()

        with(binding.researchRecycler) {
            val linear = LinearLayoutManager(requireContext())
            adapter = researchesAdapter
            layoutManager = linear
            setHasFixedSize(true)
        }
    }

    private fun bindObserver() {
        viewModel.getResearchesList()
        viewModel.researches.observe(viewLifecycleOwner) {
            researchesAdapter?.items = it
        }
    }

}