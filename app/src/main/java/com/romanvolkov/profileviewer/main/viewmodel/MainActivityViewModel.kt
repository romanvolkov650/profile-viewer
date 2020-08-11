package com.romanvolkov.profileviewer.main.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jakewharton.rxrelay2.BehaviorRelay
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

    private val items: BehaviorRelay<List<Profile>> = BehaviorRelay.createDefault(listOf())
    override val state: MutableLiveData<List<Profile>> = MutableLiveData()

    init {
        load()
        items
            .observeOn(Schedulers.computation())
            .subscribe { state.postValue(it) }
            .addTo(disposables)
    }

    override fun load() {
        repository
            .getProfiles(20)
            .subscribeOn(Schedulers.io())
            .subscribe { resource ->
                when (resource) {
                    is Resource.Data -> {
                        val list = items.value?.toMutableList() ?: ArrayList()
                        list.addAll(resource.data.results)
                        items.accept(list)
                    }
                    is Resource.Error -> {
                        Log.d("123", resource.error.localizedMessage)
                    }
                }

            }
            .addTo(disposables)
    }
}