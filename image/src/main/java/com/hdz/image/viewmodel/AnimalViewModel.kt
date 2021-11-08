package com.hdz.image.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hdz.base.bean.Animal
import com.hdz.base.http.ApiServiceManager
import com.hdz.base.http.BaseJson
import com.hdz.base.http.bean.ListBean
import com.hdz.base.util.L
import kotlinx.coroutines.async


class AnimalViewModel : ViewModel() {

    val animalList = MutableLiveData<BaseJson<MutableList<Animal>>>()

    fun animalList() {
        launch(
            {
                val result = async { ApiServiceManager.getApi().getAnimalList() }
                animalList.value = result.await()
            },
            {

            }
        )

    }

}