package com.example.aos_tracker.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import android.widget.AdapterView.OnItemSelectedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.aos_tracker.R
import com.example.aos_tracker.TrackerActivityViewModel
import com.example.aos_tracker.ui.home.HomeViewModel


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
        var battleplanSpinner = view.findViewById<Spinner>(R.id.spinner_battleplans)
        var realmSpinner = view.findViewById<Spinner>(R.id.spinner_realm)
        var firstAuxSpinner = view.findViewById<Spinner>(R.id.spinner_aux1)
        var secondAuxSpinner = view.findViewById<Spinner>(R.id.spinner_aux2)
        var cpTextView = view.findViewById<TextView>(R.id.editTextNumber_cp)
        var vpTextView = view.findViewById<TextView>(R.id.editTextNumber_vp2)
        var firstAuxChkbx = view.findViewById<CheckBox>(R.id.checkBox_aux_1)
        var secondAuxChkbx = view.findViewById<CheckBox>(R.id.checkBox_aux_2)

        battleplanSpinner.setSelection(viewModel.battleplan)
        realmSpinner.setSelection(viewModel.realm)
        firstAuxSpinner.setSelection(viewModel.aux1)
        secondAuxSpinner.setSelection(viewModel.aux2)
        cpTextView.text = viewModel.cp.toString()
        vpTextView.text = viewModel.vp.toString()
        firstAuxChkbx.isChecked = viewModel.aux1_complete
        secondAuxChkbx.isChecked = viewModel.aux2_complete

        battleplanSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                viewModel.battleplan = battleplanSpinner.selectedItemPosition
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        realmSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                viewModel.realm = realmSpinner.selectedItemPosition
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        firstAuxSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                viewModel.aux1 = firstAuxSpinner.selectedItemPosition
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        secondAuxSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                viewModel.aux2 = secondAuxSpinner.selectedItemPosition
                Log.i("ViewModel","value: " + viewModel.aux2)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        firstAuxChkbx.setOnClickListener{
            viewModel.aux1_complete = firstAuxChkbx.isChecked
        }

        secondAuxChkbx.setOnClickListener {
            viewModel.aux2_complete = secondAuxChkbx.isChecked
            Log.i("ViewModel","value: " + viewModel.aux2_complete)
        }

        view.findViewById<Button>(R.id.button_vp_inc).setOnClickListener {
            var currentCount = vpTextView.text.toString().toInt()
            currentCount++
            viewModel.vp = currentCount
            Log.i("ViewModel","value: " + viewModel.vp)
            vpTextView.text = currentCount.toString()
        }
        view.findViewById<Button>(R.id.button_cp_inc).setOnClickListener {
            var currentCount = cpTextView.text.toString().toInt()
            currentCount++
            viewModel.cp = currentCount
            cpTextView.text = currentCount.toString()
        }
        view.findViewById<Button>(R.id.button_vp_dec).setOnClickListener {
            var currentCount = vpTextView.text.toString().toInt()
            currentCount = (currentCount - 1).coerceAtLeast(0)
            viewModel.vp = currentCount
            vpTextView.text = currentCount.toString()
        }
        view.findViewById<Button>(R.id.button_cp_dec).setOnClickListener {
            var currentCount = cpTextView.text.toString().toInt()
            currentCount = (currentCount - 1).coerceAtLeast(0)
            viewModel.cp = currentCount
            cpTextView.text = currentCount.toString()
        }
    }


}


