package com.example.aos_tracker

import androidx.lifecycle.ViewModel

class TrackerActivityViewModel : ViewModel() {
    // below are the variables for the generic trackable elements
    var army = "GA: Death"
    var battleplan = 0
    var realm = 0
    var aux1 = 0
    var aux2 = 0
    var vp = 0
    var cp = 0
    var nr = 0
    var aux1_complete = false
    var aux2_complete =  false

    // below are the boolean values for the Fyreslayers runes page
    var runeFury = false
    var runeHeat = false
    var runeSteel = false
    var runeDetermination = false
    var runeZeal = false
    var runeFarsight = false

}