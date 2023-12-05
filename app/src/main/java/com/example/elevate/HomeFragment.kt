package com.example.elevate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.elevate.databinding.FragmentHomeBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var dbRef: DatabaseReference
    private lateinit var showClassAdapter: ShowClassAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showClassAdapter = ShowClassAdapter(emptyList())
        binding.showClassRecyclerView.adapter = showClassAdapter

        getClassDataFromFirebase()
    }

    private fun getClassDataFromFirebase() {
        dbRef = FirebaseDatabase.getInstance().reference.child("Classes")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val classList = mutableListOf<ClassDetails>()

                // Iterate through the snapshots and convert them to ClassDetails objects
                for (classSnapshot in snapshot.children) {
                    val classDetail = classSnapshot.getValue(ClassDetails::class.java)
                    classDetail?.let { classList.add(it) }
                }

                // Update the adapter with the new data
                showClassAdapter.updateData(classList)

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Retrieve Fail", Toast.LENGTH_SHORT).show()
            }

        })
    }

}