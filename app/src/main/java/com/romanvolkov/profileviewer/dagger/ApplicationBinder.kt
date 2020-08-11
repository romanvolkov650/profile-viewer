package com.romanvolkov.profileviewer.dagger

import androidx.lifecycle.ViewModelProviders
import com.romanvolkov.profileviewer.main.view.MainActivity
import com.romanvolkov.profileviewer.main.viewmodel.IMainActivityViewModel
import com.romanvolkov.profileviewer.main.viewmodel.MainActivityViewModel
import com.romanvolkov.profileviewer.model.repository.IMainRepository
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class ApplicationBinder {

    @ContributesAndroidInjector(modules = [ActivityViewModelModule::class])
    abstract fun mainActivity(): MainActivity
}

@Module
class ActivityViewModelModule {
    @Provides
    fun iMainVM(
        activity: MainActivity,
        repository: IMainRepository
    ): IMainActivityViewModel =
        ViewModelProviders.of(
            activity, AppViewModelFactory.forInstance {
                MainActivityViewModel(repository)
            }
        ).get(MainActivityViewModel::class.java)
}