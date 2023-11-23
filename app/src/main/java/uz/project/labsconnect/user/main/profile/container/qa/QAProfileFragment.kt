package uz.project.labsconnect.user.main.profile.container.qa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import uz.project.labsconnect.R
import uz.project.labsconnect.databinding.FragmentProfileQaBinding
import uz.project.labsconnect.databinding.FragmentQaBinding
import uz.project.labsconnect.user.main.profile.container.qa.adapter.QAAdapter
import uz.project.labsconnect.user.main.profile.container.qa.view_model.QAViewModel

class QAProfileFragment : Fragment(R.layout.fragment_profile_qa) {

    //    Binding
    private var _binding: FragmentProfileQaBinding? = null
    private val binding get() = _binding!!

    //    Adapter
    private var qaAdapter: QAAdapter? = null

    //    ViewModel
    private val viewModel: QAViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileQaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initList()
        bindObserver()

    }

    private fun initList() {
        qaAdapter = QAAdapter()

        with(binding.qaRecycler) {
            val linear = LinearLayoutManager(requireContext())
            adapter = qaAdapter
            layoutManager = linear
            setHasFixedSize(true)
        }
    }

    private fun bindObserver() {
        viewModel.getQAList()
        viewModel.qaList.observe(viewLifecycleOwner) {
            qaAdapter?.items = it
        }
    }

}