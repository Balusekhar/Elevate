package com.example.elevate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.elevate.databinding.FragmentHomeBinding
import com.example.elevate.databinding.FragmentTrainersBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class TrainersFragment : Fragment() {

    private lateinit var binding: FragmentTrainersBinding
    private lateinit var dbRef: DatabaseReference
    private lateinit var showTrainerAdapter: ShowTrainerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrainersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showTrainerAdapter = ShowTrainerAdapter(emptyList())
        binding.showTrainerRecyclerView.adapter = showTrainerAdapter

        getClassDataFromFirebase()

    }

    private fun getClassDataFromFirebase() {

        dbRef = FirebaseDatabase.getInstance().reference.child("Trainers")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val trainerList = mutableListOf<TrainerDetails>()

                // Iterate through the snapshots and convert them to ClassDetails objects
                for (classSnapshot in snapshot.children) {
                    val trainerDetails = classSnapshot.getValue(TrainerDetails::class.java)
                    trainerDetails?.let { trainerList.add(it) }
                }

                // Update the adapter with the new data
                showTrainerAdapter.updateData(trainerList)

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Retrieve Fail", Toast.LENGTH_SHORT).show()
            }

        })
    }

}