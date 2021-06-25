package com.app.bygclite.ui.createuser

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.app.bygclite.databinding.ActivityCreateUserBinding
import com.app.bygclite.firebasemodel.UserModel
import com.app.bygclite.ui.authenticationscreen.AuthenticationScreenActivity
import com.app.bygclite.utils.hide
import com.app.bygclite.utils.isEmailValid
import com.app.bygclite.utils.show
import com.app.bygclite.utils.showToast
import com.app.bygclite.viewmodels.CreateUserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateUserActivity : AppCompatActivity(), View.OnClickListener {

    private val binding: ActivityCreateUserBinding by viewBinding()
    private val createUserViewModel: CreateUserViewModel by viewModels()

    private var mobileNumber = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.listener = this
        binding.viewModel = createUserViewModel

        val intent = intent
        if (intent != null && intent.extras != null) {
            val bundle = intent.extras

            if (bundle!!.containsKey("mobile_number")) {
                mobileNumber = bundle.getString("mobile_number", "")
            }
        }

        createUserViewModel.result.observe(this, Observer {

            binding.appLoader.show()
            binding.layoutRootLayout.hide()

            if (it == null) {
                showToast("Account created successfully")
                Intent(this, AuthenticationScreenActivity::class.java).apply {
                    startActivity(this)
                    finishAffinity()
                    overridePendingTransition(0, 0)
                }
            } else {
                showToast("Something went wrong! Please Try again")
            }

        })


    }

    override fun onClick(view: View?) {
        when (view) {

            binding.imageBack->{
                onBackPressed()
            }

            binding.btnCreateUser -> {
                if (validate()) {
                    val user = UserModel(
                        fullname = binding.editTextFullName.text.toString().trim(),
                        userEmail = binding.editTextEmail.text.toString().trim(),
                        userPassword = binding.editTextPassword.text.toString().trim(),
                        mobileNumber = mobileNumber
                    )
                    createUserViewModel.createUser(user)
                    binding.appLoader.show()
                    binding.layoutRootLayout.hide()
                }
            }
        }
    }

    private fun validate(): Boolean {

        if (binding.editTextFullName.text.toString().trim() == "") {
            binding.textInputNameField.isErrorEnabled = true
            binding.textInputNameField.error = "Please enter valid name"
            return false
        } else {
            binding.textInputNameField.isErrorEnabled = false
            binding.textInputNameField.error = null
        }

        if (!isEmailValid(binding.editTextEmail.text.toString().trim())) {
            binding.textInputEmail.isErrorEnabled = true
            binding.textInputEmail.error = "Please enter valid email"
            return false
        } else {
            binding.textInputEmail.isErrorEnabled = false
            binding.textInputEmail.error = null
        }

        if (binding.editTextPassword.text.toString().trim().length < 6) {
            binding.textInputPassword.isErrorEnabled = true
            binding.textInputPassword.error = "Please enter valid password"
            return false
        } else {
            binding.textInputPassword.isErrorEnabled = false
            binding.textInputPassword.error = null
        }

        if (binding.editTextPassword.text.toString()
                .trim() != binding.editTextConfirmPassword.text.toString()
        ) {
            binding.textInputConfirmPassword.isErrorEnabled = true
            binding.textInputConfirmPassword.error = "Confirm password and password must be same"
            return false
        } else {
            binding.textInputConfirmPassword.isErrorEnabled = false
            binding.textInputConfirmPassword.error = null
        }
        return true
    }

    override fun onBackPressed() {
        finish()
        overridePendingTransition(0,0)
    }
}