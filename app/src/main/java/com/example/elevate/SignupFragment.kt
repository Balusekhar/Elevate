package com.example.elevate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.elevate.databinding.FragmentLoginBinding
import com.example.elevate.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth


class SignupFragment : Fragment() {
    
    private lateinit var binding: FragmentSignupBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        binding.signupButton.setOnClickListener {
            val email = binding.signupEmail.text.toString()
            val password = binding.signupPassword.text.toString()
            val confirmPassword = binding.confirmPassword.text.toString()

            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
                Toast.makeText(requireContext(), "Fill all the Details", Toast.LENGTH_SHORT).show()
            }else if(password != confirmPassword){
                Toast.makeText(requireContext(), "Both the Passwords must be same", Toast.LENGTH_SHORT).show()
            }else{
                auth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener {
                        if (it.isSuccessful){
                            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
                            Toast.makeText(requireContext(), "Registration Successful", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(requireContext(), "Registration Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }

}