package com.example.aos_tracker.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {


     var battleplan = 0
     var realm = 0
     var aux1 = 0
     var aux2 = 0
     var vp = 0
     var cp = 0
     var aux1_complete = false
     var aux2_complete = false

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text


}