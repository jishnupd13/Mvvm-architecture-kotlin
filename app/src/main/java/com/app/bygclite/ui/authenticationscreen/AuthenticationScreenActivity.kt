package com.app.bygclite.ui.authenticationscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.viewbinding.library.activity.viewBinding
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.app.bygclite.R
import com.app.bygclite.databinding.ActivityAuthenticationScreenBinding
import com.app.bygclite.ui.otpverifyscreenactivity.OtpVerifyScreenActivity
import com.app.bygclite.utils.hide
import com.app.bygclite.utils.show
import com.app.bygclite.viewmodels.AuthenticationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenticationScreenActivity : AppCompatActivity(), View.OnClickListener {

    private val binding: ActivityAuthenticationScreenBinding by viewBinding()
    private val authenticationViewModel: AuthenticationViewModel by viewModels()

    private var doubleBackToExitPressedOnce = false
    private var onBackPressTimeDelay = 4000L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.listener = this
        binding.viewModel = authenticationViewModel
    }

    override fun onClick(view: View?) {

        when (view) {
            binding.btnSubmit -> {
                if (validate()) {
                    binding.editTextMobileNumber.background =
                        ContextCompat.getDrawable(this, R.drawable.bg_grey_with_black_border)
                    binding.textMobileErrorText.hide()

                    Intent(this, OtpVerifyScreenActivity::class.java).apply {
                        this.putExtra(
                            "mobile_number",
                            binding.editTextMobileNumber.text.toString().trim()
                        )
                        startActivity(this)
                        overridePendingTransition(0, 0)
                    }

                } else {
                    binding.editTextMobileNumber.background =
                        ContextCompat.getDrawable(this, R.drawable.bg_grey_with_red_border)
                    binding.textMobileErrorText.show()
                }
            }
        }

    }

    private fun validate(): Boolean {

        if (binding.editTextMobileNumber.text.toString().trim().length != 10) {
            return false
        }
        return true
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finish()
            overridePendingTransition(0, 0)
        } else {
            Toast.makeText(this, "Please click back again to exit", Toast.LENGTH_SHORT).show()
        }
        this.doubleBackToExitPressedOnce = true
        Handler(Looper.getMainLooper()).postDelayed({}, onBackPressTimeDelay)
    }

}