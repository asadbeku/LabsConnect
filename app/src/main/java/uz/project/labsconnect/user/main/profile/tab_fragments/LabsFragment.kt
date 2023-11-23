package uz.project.labsconnect.user.main.profile.tab_fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.project.labsconnect.R
import uz.project.labsconnect.user.main.home.adapter.labs.LabsAdapter
import uz.project.labsconnect.user.main.profile.tab_fragments.view_model.LabsViewModel

class LabsFragment : Fragment(R.layout.fragment_labs) {

    private var labsAdapter: LabsAdapter? = null
    private val viewModel: LabsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler = view.findViewById<RecyclerView>(R.id.fragmentLabsRecycler)

        initList(recycler)
        bindObserver()
    }

    private fun initList(recycler: RecyclerView) {

        labsAdapter = LabsAdapter(){
            Log.d("checkAdapter","Clicked position=$it")
        }

        with(recycler) {
            val linear = LinearLayoutManager(requireContext())

            adapter = labsAdapter
            layoutManager = linear
            setHasFixedSize(true)

        }
    }

    private fun bindObserver() {
        viewModel.getLabs()

        viewModel.labs.observe(viewLifecycleOwner) {
            labsAdapter?.items = it
        }
    }

}