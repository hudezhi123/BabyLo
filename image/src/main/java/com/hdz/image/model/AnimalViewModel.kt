package com.hdz.image.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hdz.base.http.ApiServiceManager
import kotlinx.coroutines.launch


class AnimalViewModel:ViewModel(){
   suspend fun animalList() {
        ApiServiceManager.getApi().getAnimalList()
    }

    private fun launch(block: suspend () -> Unit, error: suspend (Throwable) -> Unit) = viewModelScope.launch {
        try {
            block()
        } catch (e: Throwable) {
            error(e)
        }
    }
}