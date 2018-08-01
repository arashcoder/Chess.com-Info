package com.chess.personal.my.ui.injection.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.chess.personal.my.presentation.BrowseBookmarkedPlayersViewModel
import com.chess.personal.my.presentation.BrowsePlayersViewModel
import com.chess.personal.my.presentation.HomeViewModel
import com.chess.personal.my.ui.injection.ViewModelFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BrowsePlayersViewModel::class)
    abstract fun bindBrowseProjectsViewModel(viewModel: BrowsePlayersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BrowseBookmarkedPlayersViewModel::class)
    abstract fun bindBrowseBookmarkedProjectsViewModel(
            viewModel: BrowseBookmarkedPlayersViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)