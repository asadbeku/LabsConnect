package uz.project.labsconnect.user.signup

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.project.labsconnect.R
import uz.project.labsconnect.databinding.FragmentLaunchScreenBinding
import uz.project.labsconnect.databinding.FragmentSignupInputPhoneBinding

class SignupInputPhoneFragment : Fragment() {

    private var _binding: FragmentSignupInputPhoneBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupInputPhoneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signupButton.setOnClickListener {
            findNavController().navigate(R.id.action_signupInputPhoneFragment_to_signupConformCodeFragment)
        }

        binding.loginTextView.setOnClickListener {
            findNavController().navigate(R.id.action_signupInputPhoneFragment_to_signingFragment)
        }

    }
}