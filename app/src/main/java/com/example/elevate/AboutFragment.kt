package com.example.elevate

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.elevate.databinding.FragmentAboutBinding
import com.example.elevate.databinding.FragmentHomeBinding


class AboutFragment : Fragment() {

    private lateinit var binding : FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.feedbackButton.setOnClickListener {

            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf("jagan@gmail.com"))
                putExtra(Intent.EXTRA_SUBJECT, "Feedback for Elevate App")
            }

            // Check if there's an app that can handle this intent
            if (emailIntent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(emailIntent)
            }

        }
    }


}