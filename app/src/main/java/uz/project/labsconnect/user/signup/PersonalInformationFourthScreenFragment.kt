package uz.project.labsconnect.user.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.project.labsconnect.R
import uz.project.labsconnect.databinding.FragmentPersonalInformationFourthScreenBinding
import uz.project.labsconnect.databinding.FragmentPersonalInformationThirdScreenBinding

class PersonalInformationFourthScreenFragment : Fragment() {


    private var _binding: FragmentPersonalInformationFourthScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonalInformationFourthScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.conformButton.setOnClickListener {
            findNavController().navigate(R.id.action_personalInformationFourthScreenFragment_to_homeFragment)
        }

    }

}