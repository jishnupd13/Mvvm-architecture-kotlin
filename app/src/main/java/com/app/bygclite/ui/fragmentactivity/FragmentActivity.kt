package com.app.bygclite.ui.fragmentactivity

import android.os.Bundle
import android.view.View
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.app.bygclite.databinding.ActivityFragmentBinding
import com.app.bygclite.networkconnectivity.NetworkConnectivityManager
import com.app.bygclite.viewmodels.FragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import timber.log.Timber

@AndroidEntryPoint
class FragmentActivity : AppCompatActivity(), View.OnClickListener {

    private val fragmentBinding: ActivityFragmentBinding by viewBinding()
    private val fragmentViewModel: FragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentBinding.viewModel = fragmentViewModel
        fragmentBinding.listener = this
        setUpFragment()


        runBlocking {
            val isInternetAvailable = lifecycleScope.async(Dispatchers.IO) {
                NetworkConnectivityManager.hasInternetConnected(this@FragmentActivity)
            }
            val networkCheckingStatus = isInternetAvailable.await()

            if (networkCheckingStatus) {
                Timber.e("network available")
            } else {
                Timber.e("network not available")
            }
        }


    }

    private fun setUpFragment() {
        supportFragmentManager.beginTransaction()
            .replace(fragmentBinding.fragmentContainerView.id, FirstFragment())
            .commit()
    }

    override fun onClick(view: View?) {

    }
}