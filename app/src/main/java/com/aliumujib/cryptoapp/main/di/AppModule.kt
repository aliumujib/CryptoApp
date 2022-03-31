package com.aliumujib.cryptoapp.main.di

import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.aliumujib.cryptoapp.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
object AppModule {

    @Provides
    fun provideNavController(activity: FragmentActivity): NavController =
        NavHostFragment.findNavController(
            activity.supportFragmentManager.findFragmentById(R.id.mainHostFragment)!!
        )
}
