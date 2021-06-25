package com.app.bygclite.viewmodels

import androidx.lifecycle.ViewModel
import com.app.bygclite.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel   @Inject constructor(
    private val mainRepository: AppRepository
) : ViewModel() {
}