package com.example.aos_tracker.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.aos_tracker.R
import com.example.aos_tracker.TrackerActivityViewModel

// This fragment is for the UI elements used to track the Rune mechanic used by some armies.
class RunePageFragment : Fragment() {

    private val viewModel: TrackerActivityViewModel by viewModels( ownerProducer = {requireParentFragment()})

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_runes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Variables for each check box
        var furyChkBx = view.findViewById<CheckBox>(R.id.checkBox_rune_fury)
        var heatChkBx = view.findViewById<CheckBox>(R.id.checkBox_rune_heat)
        var steelChkBx = view.findViewById<CheckBox>(R.id.checkBox_rune_steel)
        var determinationChkBx = view.findViewById<CheckBox>(R.id.checkBox_rune_determination)
        var zealChkBx = view.findViewById<CheckBox>(R.id.checkBox_rune_zeal)
        var farsightChkBx = view.findViewById<CheckBox>(R.id.checkBox_rune_farsight)

        // pre load the current values from teh view model into the appropriate check boxes
        furyChkBx.isChecked = viewModel.runeFury
        heatChkBx.isChecked = viewModel.runeHeat
        steelChkBx.isChecked = viewModel.runeSteel
        determinationChkBx.isChecked = viewModel.runeDetermination
        zealChkBx.isChecked = viewModel.runeZeal
        farsightChkBx.isChecked = viewModel.runeFarsight

        // onClick listeners for each checkbox to update the isChecked status in the view model.
        furyChkBx.setOnClickListener {
            viewModel.runeFury = furyChkBx.isChecked
        }

        heatChkBx.setOnClickListener {
            viewModel.runeHeat = heatChkBx.isChecked
        }

        steelChkBx.setOnClickListener {
            viewModel.runeSteel = steelChkBx.isChecked
        }

        determinationChkBx.setOnClickListener {
            viewModel.runeDetermination = determinationChkBx.isChecked
        }

        zealChkBx.setOnClickListener {
            viewModel.runeZeal = determinationChkBx.isChecked
        }

        farsightChkBx.setOnClickListener {
            viewModel.runeFarsight = farsightChkBx.isChecked
        }

    }
}