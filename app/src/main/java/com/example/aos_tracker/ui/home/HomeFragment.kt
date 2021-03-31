package com.example.aos_tracker.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.aos_tracker.R
import com.example.aos_tracker.ui.home.HomeViewModel


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_vp_inc).setOnClickListener {
            val showCountTextView = view.findViewById<TextView>(R.id.editTextNumber_vp2)
            var currentCount = showCountTextView.text.toString().toInt()
            currentCount++
            showCountTextView.text = currentCount.toString()
        }
        view.findViewById<Button>(R.id.button_cp_inc).setOnClickListener {
            val showCountTextView = view.findViewById<TextView>(R.id.editTextNumber_cp)
            var currentCount = showCountTextView.text.toString().toInt()
            currentCount++
            showCountTextView.text = currentCount.toString()
        }
        view.findViewById<Button>(R.id.button_vp_dec).setOnClickListener {
            val showCountTextView = view.findViewById<TextView>(R.id.editTextNumber_vp2)
            var currentCount = showCountTextView.text.toString().toInt()
            currentCount = (currentCount - 1).coerceAtLeast(0)
            showCountTextView.text = currentCount.toString()
        }
        view.findViewById<Button>(R.id.button_cp_dec).setOnClickListener {
            val showCountTextView = view.findViewById<TextView>(R.id.editTextNumber_cp)
            var currentCount = showCountTextView.text.toString().toInt()
            currentCount = (currentCount - 1).coerceAtLeast(0)
            showCountTextView.text = currentCount.toString()
        }
    }
}