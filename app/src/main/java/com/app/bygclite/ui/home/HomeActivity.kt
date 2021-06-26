package com.app.bygclite.ui.home

import android.os.Bundle
import android.view.View
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.app.bygclite.R
import com.app.bygclite.databinding.ActivityHomeBinding
import com.app.bygclite.utils.showToast
import com.app.bygclite.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), View.OnClickListener {

    private val binding: ActivityHomeBinding by viewBinding()
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.listener = this
    }

    override fun onClick(v: View?) {
        when (v) {

            binding.navHead.textAddPost -> {
                showToast("addPost")
            }

            binding.navHead.textTopics -> {
                showToast("topics")
            }

            binding.navHead.textUserAccount -> {
                showToast("user account")
            }

            binding.navHead.textLogOut -> {
                showToast("logout")
            }
        }
    }

    fun openDrawer() {
        binding.drawerLayout.openDrawer(GravityCompat.START);
    }

    fun closeDrawer() {
        binding.drawerLayout.closeDrawer(GravityCompat.START);
    }

    override fun onBackPressed() {
        val layout = binding.drawerLayout
        if (layout.isDrawerOpen(GravityCompat.START)) {
            layout.closeDrawer(GravityCompat.START)
        } else {
            finish()
            overridePendingTransition(0, 0)
        }
    }

  /*  private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }*/
}