package com.example.aos_tracker.ui.armySelect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.aos_tracker.R
import com.example.aos_tracker.TrackerActivityViewModel
import com.google.android.material.snackbar.Snackbar

class ArmySelectFragment : Fragment(){
    private val viewModel: TrackerActivityViewModel by viewModels( ownerProducer = {requireParentFragment()})

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_army, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var armyList = view.findViewById<ListView>(R.id.army_list)

        armyList.setOnItemClickListener {parent, view, position, id ->
            viewModel.army = armyList.getItemAtPosition(position).toString()
            Snackbar.make(parent, viewModel.army + " Selected", Snackbar.LENGTH_SHORT).show()
        }
    }
}