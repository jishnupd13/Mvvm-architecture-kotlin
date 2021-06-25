package com.app.bygclite.ui.fragmentactivity

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.bygclite.R
import com.app.bygclite.databinding.FragmentFirstBinding
import com.app.bygclite.listeners.OnItemClickListener
import com.app.bygclite.models.Name
import com.app.bygclite.models.TestApiNestedModel
import com.app.bygclite.ui.adapters.nestedadapter.TestNestedAdapter
import com.app.bygclite.utils.showToast
import com.app.bygclite.viewmodels.FragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class FirstFragment : Fragment(R.layout.fragment_first), View.OnClickListener, OnItemClickListener {


    private val firstBinding: FragmentFirstBinding by viewBinding()
    private val fragmentViewModel: FragmentViewModel by viewModels()

    private lateinit var testNestedAdapter: TestNestedAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstBinding.viewModel = fragmentViewModel
        firstBinding.listener = this
        testNestedAdapter = TestNestedAdapter(this)
        initViews()
    }

    private fun initViews() {

        firstBinding.postNestedRecyclerView.adapter = testNestedAdapter
        testNestedAdapter.differ.submitList(setUpList())

    }

    override fun onClick(view: View?) {

    }

    override fun onItemClick(key: String, item: Any) {

        when (key) {
            "root" -> {
                val data = item as TestApiNestedModel
                Timber.e("<<<<<<<<<<<<<<<<<<<<< root >>>>>>>>>>>>>>>>>>>>>>>")
                val currentList = testNestedAdapter.differ.currentList
                val position = currentList.indexOf(data)

                if (position >= 0) {
                    currentList[position].isVisible = currentList[position].isVisible != true
                    testNestedAdapter.notifyDataSetChanged()
                }
            }
            "name" -> {
                val data = item as Name
                data.userName?.let { showToast(it) }
            }
        }
    }


    private fun setUpList(): ArrayList<TestApiNestedModel> {
        val list = ArrayList<TestApiNestedModel>()
        val userNameList = ArrayList<Name>()
        userNameList.add(Name("Jishnu P Dileep"))
        list.add(TestApiNestedModel("Hello", 1, "Hello World", 1, userNameList, false))
        list.add(TestApiNestedModel("Hello", 2, "Hello World", 1, userNameList, false))
        list.add(TestApiNestedModel("Hello", 3, "Hello World", 1, userNameList, false))
        return list
    }
}