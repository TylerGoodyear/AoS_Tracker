package com.example.aos_tracker.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.aos_tracker.R
import com.example.aos_tracker.TrackerActivityViewModel

// This fragment contains the UI elements for tracking the generic and some basic army exclusive scores and resources.
class HomeFragment : Fragment() {

    private val viewModel: TrackerActivityViewModel by viewModels( ownerProducer = {requireParentFragment()})

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Variables for UI elements
        val battleplanSpinner = view.findViewById<Spinner>(R.id.spinner_battleplans)
        val firstAuxSpinner = view.findViewById<Spinner>(R.id.spinner_aux1)
        val secondAuxSpinner = view.findViewById<Spinner>(R.id.spinner_aux2)
        val cpTextView = view.findViewById<TextView>(R.id.editTextNumber_cp)
        val vpTextView = view.findViewById<TextView>(R.id.editTextNumber_vp)
        val nrTextView = view.findViewById<TextView>(R.id.editTextNumber_nr)
        val optionalResources = view.findViewById<Group>(R.id.numeric_resources)
        val armyResourceLabel = view.findViewById<TextView>(R.id.textView_numeric_resource)
        val cpLabel = view.findViewById<TextView>(R.id.textView_cp)
        val army = viewModel.army

        //Pre loading values into applicable UI elements
        battleplanSpinner.setSelection(viewModel.battleplan)
        firstAuxSpinner.setSelection(viewModel.aux1)
        secondAuxSpinner.setSelection(viewModel.aux2)
        cpTextView.text = viewModel.cp.toString()
        vpTextView.text = viewModel.vp.toString()
        nrTextView.text = viewModel.nr.toString()

        //Bonereapers replaces the command point mechanic with their own mechanic, so if bonereapers is selected this will change the command points label to match
        if (army == "Ossiarch Bonereapers") {
            cpLabel.text = armyRules.Ossiarch_Bonereapers.rule
        } else {
            cpLabel.text = "Command Points"
        }

        checkArmy(army, optionalResources, armyResourceLabel)

        // Loads the newly selected battleplan into the view model
        battleplanSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                viewModel.battleplan = battleplanSpinner.selectedItemPosition
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }



        // Loads the newly selected auxiliary into the view model
        firstAuxSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                viewModel.aux1 = firstAuxSpinner.selectedItemPosition
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        // Loads the newly selected second auxiliary into the view model
        secondAuxSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                viewModel.aux2 = secondAuxSpinner.selectedItemPosition
                Log.i("ViewModel","value: " + viewModel.aux2)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }



        // when the increment button is clicked. gets the current value of the victory point counter, increments it by 1 and updates the value in the counter and view model
        view.findViewById<Button>(R.id.button_vp_inc).setOnClickListener {
            var currentCount = vpTextView.text.toString().toInt()
            currentCount++
            viewModel.vp = currentCount
            Log.i("ViewModel","value: " + viewModel.vp)
            vpTextView.text = currentCount.toString()
        }

        // same as above but for the command point counter
        view.findViewById<Button>(R.id.button_cp_inc).setOnClickListener {
            var currentCount = cpTextView.text.toString().toInt()
            currentCount++
            viewModel.cp = currentCount
            cpTextView.text = currentCount.toString()
        }

        // same as above but for the tertiary counter
        view.findViewById<Button>(R.id.button_nr_inc).setOnClickListener {
            var currentCount = nrTextView.text.toString().toInt()
            currentCount++
            viewModel.nr = currentCount
            nrTextView.text = currentCount.toString()
        }

        // When the decrement button is clicked, gets the current value of the vp counter and decrements it by 1 to a minimum of 0 and updates the value in the counter and view model.
        view.findViewById<Button>(R.id.button_vp_dec).setOnClickListener {
            var currentCount = vpTextView.text.toString().toInt()
            currentCount = (currentCount - 1).coerceAtLeast(0)
            viewModel.vp = currentCount
            vpTextView.text = currentCount.toString()
        }

        // same as above but for the command point counter
        view.findViewById<Button>(R.id.button_cp_dec).setOnClickListener {
            var currentCount = cpTextView.text.toString().toInt()
            currentCount = (currentCount - 1).coerceAtLeast(0)
            viewModel.cp = currentCount
            cpTextView.text = currentCount.toString()
        }

        // same as above but for the tertiary resource counter
        view.findViewById<Button>(R.id.button_nr_dec).setOnClickListener {
            var currentCount = nrTextView.text.toString().toInt()
            currentCount = (currentCount - 1).coerceAtLeast(0)
            viewModel.nr = currentCount
            nrTextView.text = currentCount.toString()
        }
    }

    //enumerator for armies with a third numeric resource and the name of said resource
    enum class armyRules(val rule: String) {
        Big_Waaagh("Waaagh Points"),
        Slaanesh("Depravity Points"),
        Khorne("Blood Tithe Points"),
        Tzeentch("Sorcery Points"),
        Nurgle("Corruption Points"),
        Beasts_of_Chaos("Primordial Points"),
        Ossiarch_Bonereapers("RDP"),
        Seraphon("Conjuration Points")
    }

    /**
     *  Checks if any army that uses a third numeric resource is selected, if true makes the third resource's UI elements visible and changes labels to reflect the specific resource.
     *  @param army The name of the currently selected army
     *  @param group The group of UI elements associated with the tertiary resource
     *  @param resourceLabel The label for the tertiary resource, the text will be changed to reflec the needed resource.
     */
    fun checkArmy(army: String, group: View, resourceLabel: TextView)  {
        when(army) {
            "Big Waaagh", "Slaanesh", "Khorne", "Tzeentch", "Nurgle", "Beasts of Chaos", "Seraphon" -> group.visibility = View.VISIBLE
            else ->  group.visibility = View.GONE
        }
        when(army){
            "Big Waaagh" -> resourceLabel.text = armyRules.Big_Waaagh.rule
            "Slaanesh" -> resourceLabel.text = armyRules.Slaanesh.rule
            "Khorne" -> resourceLabel.text = armyRules.Khorne.rule
            "Tzeentch" -> resourceLabel.text = armyRules.Tzeentch.rule
            "Nurgle" -> resourceLabel.text = armyRules.Nurgle.rule
            "Beasts of Chaos" -> resourceLabel.text = armyRules.Beasts_of_Chaos.rule
            "Seraphon" -> resourceLabel.text = armyRules.Seraphon.rule
        }
    }

}


