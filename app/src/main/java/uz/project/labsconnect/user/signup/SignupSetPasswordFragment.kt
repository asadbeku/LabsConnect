package uz.project.labsconnect.user.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.project.labsconnect.R
import uz.project.labsconnect.databinding.FragmentLaunchScreenBinding
import uz.project.labsconnect.databinding.FragmentSignupSetPasswordBinding

class SignupSetPasswordFragment : Fragment() {


    private var _binding: FragmentSignupSetPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupSetPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.continueButton.setOnClickListener {
            findNavController().navigate(R.id.action_signupSetPasswordFragment_to_personalInformationFirstScreenFragment)
        }

    }
}