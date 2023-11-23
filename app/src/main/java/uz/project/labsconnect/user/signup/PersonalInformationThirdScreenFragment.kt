package uz.project.labsconnect.user.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.project.labsconnect.R
import uz.project.labsconnect.databinding.FragmentPersonalInformationSecondScreenBinding
import uz.project.labsconnect.databinding.FragmentPersonalInformationThirdScreenBinding

class PersonalInformationThirdScreenFragment : Fragment() {

    private var _binding: FragmentPersonalInformationThirdScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonalInformationThirdScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextButton.setOnClickListener {
            findNavController().navigate(R.id.action_personalInformationThirdScreenFragment_to_personalInformationFourthScreenFragment)
        }

    }
}