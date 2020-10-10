package com.c4rex.brikz.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class LeaderViewModel: ViewModel() {

    // LiveData holds state which is observed by the UI
    // (state flows down from ViewModel)
    private val _name = MutableLiveData("")
    val name: LiveData<String> = _name

    // onNameChanged is an event we're defining that the UI can invoke
    // (events flow up from UI)
    fun onNameChanged(newName: String) {
        _name.value = newName
    }

}
