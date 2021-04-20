package com.example.aos_tracker.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RunePageViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Runes Used"
    }
    val text: LiveData<String> = _text
}