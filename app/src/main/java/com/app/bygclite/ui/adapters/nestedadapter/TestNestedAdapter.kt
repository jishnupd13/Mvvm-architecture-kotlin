package com.app.bygclite.ui.adapters.nestedadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.bygclite.R
import com.app.bygclite.databinding.ItemTestNestViewBinding
import com.app.bygclite.listeners.OnItemClickListener
import com.app.bygclite.models.TestApiNestedModel


/** Created by Jishnu P Dileep on 28-05-2021 */
class TestNestedAdapter(val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<TestNestedAdapter.TestNestedViewHolder>() {

    private lateinit var testUserNameAdapter: TestUserNameAdapter

    inner class TestNestedViewHolder(binding: ItemTestNestViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val itemBinding = binding
    }

    private val differCallback = object : DiffUtil.ItemCallback<TestApiNestedModel>() {
        override fun areItemsTheSame(
            oldItem: TestApiNestedModel,
            newItem: TestApiNestedModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: TestApiNestedModel,
            newItem: TestApiNestedModel
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestNestedViewHolder {
        val ItemTestNestViewBinding: ItemTestNestViewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_test_nest_view, parent, false
        )
        return TestNestedViewHolder(ItemTestNestViewBinding)
    }

    override fun onBindViewHolder(holder: TestNestedViewHolder, position: Int) {
        val data = differ.currentList[position]
        val binding = holder.itemBinding
        binding.item = data
        binding.listner= onItemClickListener

        testUserNameAdapter = TestUserNameAdapter(onItemClickListener)
        binding.postNestedRecyclerView.adapter = testUserNameAdapter
        data.Username?.let { testUserNameAdapter.submitList(it) }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<TestApiNestedModel>){
        differ.submitList(list)
    }


}