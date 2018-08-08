package com.chess.personal.my.ui.injection.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.chess.personal.my.presentation.*
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
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindBSearchViewModel(viewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PlayerHomeViewModel::class)
    abstract fun bindPlayerHomeViewModel(
            viewModel: PlayerHomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PlayerProfileViewModel::class)
    abstract fun bindPlayerProfileViewModel(
            viewModel: PlayerProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PlayerAllGamesViewModel::class)
    abstract fun bindPlayerAllGamesViewModel(
            viewModel: PlayerAllGamesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PlayerMonthlyGamesViewModel::class)
    abstract fun bindPlayerMonthlyGamesViewModel(
            viewModel: PlayerMonthlyGamesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ClubProfileViewModel::class)
    abstract fun bindClubProfileViewModel(
            viewModel: ClubProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ClubMembersViewModel::class)
    abstract fun bindClubMembersViewModel(
            viewModel: ClubMembersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ClubHomeViewModel::class)
    abstract fun bindClubHomeViewModel(
            viewModel: ClubHomeViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)