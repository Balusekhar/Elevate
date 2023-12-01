package com.example.elevate

import android.content.Intent
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
import com.google.firebase.auth.FirebaseUser

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val currentUser : FirebaseUser? = auth.currentUser

        if (currentUser!=null){
            startActivity(Intent(requireContext(),FitnessActivity::class.java))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()



        binding.signinButton.setOnClickListener {
            val email = binding.signupEmail.text.toString()
            val password = binding.signupPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(requireContext(), "Fill all the Details", Toast.LENGTH_SHORT).show()
            }else{
                auth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener {
                        if (it.isSuccessful){
                            Toast.makeText(requireContext(), "Sign In Successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(requireContext(),FitnessActivity::class.java))
                        }else{
                            Toast.makeText(requireContext(), "Sign In Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        //Create a New Account
        binding.createAccount.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }
    }

}