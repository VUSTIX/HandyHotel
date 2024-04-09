package com.example.handyhotel.ui.hotels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HotelsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is hotels Fragment"
    }
    val text: LiveData<String> = _text
}