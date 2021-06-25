package com.app.bygclite.viewmodels

import androidx.lifecycle.ViewModel
import com.app.bygclite.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/** Created by Jishnu P Dileep on 28-05-2021 */
@HiltViewModel
class FragmentViewModel @Inject constructor(
    private val mainRepository: AppRepository
) : ViewModel() {

}