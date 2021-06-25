package com.app.bygclite.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.app.bygclite.databinding.ActivitySplashScreenBinding
import com.app.bygclite.networkconnectivity.NetworkConnectivityManager
import com.app.bygclite.ui.authenticationscreen.AuthenticationScreenActivity
import com.app.bygclite.utils.showToast
import com.app.bygclite.viewmodels.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import im.getsocial.sdk.GetSocial
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity(),View.OnClickListener {

    private val binding: ActivitySplashScreenBinding by viewBinding()
    private val splashViewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        runBlocking {
            val isInternetAvailable = lifecycleScope.async(Dispatchers.IO) {
                NetworkConnectivityManager.hasInternetConnected(this@SplashScreenActivity)
            }
            val networkCheckingStatus = isInternetAvailable.await()

            if (networkCheckingStatus) {
                GetSocial.addOnInitializeListener {
                    Intent(this@SplashScreenActivity,AuthenticationScreenActivity::class.java).apply {
                        startActivity(this)
                        overridePendingTransition(0,0)
                    }
                }
            } else {
                showToast("No Internet")
            }
        }
    }

    override fun onClick(view: View?) {

    }
}