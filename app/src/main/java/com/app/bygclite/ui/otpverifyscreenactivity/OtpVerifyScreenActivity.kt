package com.app.bygclite.ui.otpverifyscreenactivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.app.bygclite.databinding.ActivityOtpVerifyScreenBinding
import com.app.bygclite.firebasemodel.UserModel
import com.app.bygclite.ui.createuser.CreateUserActivity
import com.app.bygclite.utils.Constants
import com.app.bygclite.utils.hide
import com.app.bygclite.utils.show
import com.app.bygclite.utils.showToast
import com.app.bygclite.viewmodels.OtpVerifyScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import im.getsocial.sdk.GetSocial
import im.getsocial.sdk.GetSocialError
import im.getsocial.sdk.communities.ConflictUser
import im.getsocial.sdk.communities.CurrentUser
import im.getsocial.sdk.communities.Identity
import im.getsocial.sdk.communities.UserId.currentUser
import im.getsocial.sdk.communities.UserUpdate
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class OtpVerifyScreenActivity : AppCompatActivity(), View.OnClickListener {

    private val binding: ActivityOtpVerifyScreenBinding by viewBinding()
    private val otpVerifyScreenViewModel: OtpVerifyScreenViewModel by viewModels()

    private var mobileNumber = ""
    private var isCountDownStart = false

    private var otpNumbers =
        arrayOf("223355", "405405", "111113", "680569", "555333", "778343", "225533")
    private var userId = ""

    private lateinit var currentUserModel: UserModel
    private lateinit var currentUser:CurrentUser


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.listener = this
        binding.viewModel = otpVerifyScreenViewModel

        val intent = intent
        if (intent != null && intent.extras != null) {
            val bundle = intent.extras

            if (bundle!!.containsKey("mobile_number")) {
                mobileNumber = bundle.getString("mobile_number", "")
            }
        }

        initViews()
    }

    private fun initViews() {
        binding.textPhoneNumber.text = mobileNumber
        startCountDown()
        isCountDownStart = true

        otpVerifyScreenViewModel.userModelList.observe(this, Observer {

            if (it.isEmpty()) {
                Intent(this, CreateUserActivity::class.java).apply {
                    this.putExtra("mobile_number", mobileNumber)
                    startActivity(this)
                    overridePendingTransition(0, 0)
                }
            } else {
                otpVerifyScreenViewModel.validateUserData(it, mobileNumber)
            }

        })


        otpVerifyScreenViewModel.notDetect.observe(this, Observer {
            if (it == "404") {
                Intent(this, CreateUserActivity::class.java).apply {
                    startActivity(this)
                    overridePendingTransition(0, 0)
                }
            }
        })

        otpVerifyScreenViewModel.UserData.observe(this, Observer {

            currentUserModel=it
            userId = it.id.toString()

            showToast(userId)

            otpVerifyScreenViewModel.calculateAccessToken(
                Constants.PROVIDER_ID,
                it.id ?: "",
                mobileNumber
            )

        })

        otpVerifyScreenViewModel.calculateAccessToken.observe(this, Observer {

            val identity = Identity.custom(Constants.PROVIDER_ID, userId, it)
            currentUser = GetSocial.getCurrentUser()

            currentUser.addIdentity(identity, {
                showToast("User created successfully")

                val batchUpdate = UserUpdate()
                    .updateDisplayName(currentUserModel.fullname)
                    .setPublicProperty("email",currentUserModel.fullname)
                    .setPublicProperty("mobile",currentUserModel.mobileNumber)

                GetSocial.getCurrentUser().updateDetails(batchUpdate, {
                    Log.d("CurrentUser", "User details were successfully updated")
                }, { error ->
                    Log.d("CurrentUser", "Failed to update user details, error: ${error}")
                })

            }, { conflictUser: ConflictUser ->
            }, { error: GetSocialError ->
            })


        })

    }

    override fun onClick(view: View?) {

        when (view) {
            binding.imageBack -> {
                onBackPressed()
            }

            binding.btnSubmit -> {

                if (otpNumbers.contains(binding.otpView.otp.toString().trim())) {
                    binding.textOtpErrorText.hide()
                    binding.otpView.showSuccess()

                    /* Intent(this, CreateUserActivity::class.java).apply {
                         startActivity(this)
                         overridePendingTransition(0,0)
                     }*/

                    otpVerifyScreenViewModel.fetchUserLists()

                } else {
                    binding.textOtpErrorText.show()
                    binding.otpView.showError()
                }
            }

            binding.textOtpCountDown -> {
                if (!isCountDownStart) {
                    isCountDownStart = true
                    startCountDown()
                }
            }

            binding.textChange -> {
                onBackPressed()
            }
        }

    }

    override fun onBackPressed() {
        finish()
        overridePendingTransition(0, 0)
    }

    private fun startCountDown() {

        binding.appLoader.show()
        //for generating otp
        Handler(Looper.getMainLooper()).postDelayed({
            binding.otpView.setOTP(generateOtp())
        }, 5000)

        val timer = object : CountDownTimer(30000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                binding.textOtpText.text = "Fetching OTP"
                binding.textOtpCountDown.text = "00:${
                    String.format(
                        "%02d",
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
                    )
                }"
            }

            override fun onFinish() {
                isCountDownStart = false
                binding.appLoader.hide()
                binding.textOtpText.text = "Did'nt receive OTP?"
                binding.textOtpCountDown.text = "Resend"
            }
        }
        timer.start()
    }

    private fun generateOtp(): String {
        return otpNumbers[(0..6).random()]
    }

}
