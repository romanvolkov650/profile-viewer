package com.romanvolkov.profileviewer.main.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.romanvolkov.profileviewer.BaseViewModel
import com.romanvolkov.profileviewer.IViewModel
import com.romanvolkov.profileviewer.Resource
import com.romanvolkov.profileviewer.model.entity.Profile
import com.romanvolkov.profileviewer.model.repository.IMainRepository
import com.romanvolkov.worldnews.addTo
import io.reactivex.schedulers.Schedulers

interface IMainActivityViewModel : IViewModel {
    val state: LiveData<List<Profile>>
    fun load()
}

class MainActivityViewModel(private val repository: IMainRepository) :
    IMainActivityViewModel,
    BaseViewModel() {

    init {
        load()
    }

    override val state: MutableLiveData<List<Profile>> = MutableLiveData()

    override fun load() {
        repository
            .getProfiles(20)
            .subscribeOn(Schedulers.io())
            .subscribe { resource ->
                when (resource) {
                    is Resource.Data -> {
                        state.postValue(resource.data.results)
                    }
                    is Resource.Error -> {
                        Log.d("123", resource.error.localizedMessage)
                    }
                }

            }
            .addTo(disposables)
    }
}