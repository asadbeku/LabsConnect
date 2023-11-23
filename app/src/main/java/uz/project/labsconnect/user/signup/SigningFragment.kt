package uz.project.labsconnect.user.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import uz.project.labsconnect.R
import uz.project.labsconnect.databinding.FragmentSigninBinding

class SigningFragment : Fragment() {

    private var _binding: FragmentSigninBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSigninBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextConfigure()

        binding.signupTextButton.setOnClickListener {
            findNavController().navigate(R.id.action_signingFragment_to_signupInputPhoneFragment)
        }

        binding.loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_signingFragment_to_homeFragment)
        }

    }

    private fun editTextConfigure(){
        binding.phoneNumber.editText?.setOnClickListener {
            binding.phoneNumber.editText?.hint = ""
        }
    }
}