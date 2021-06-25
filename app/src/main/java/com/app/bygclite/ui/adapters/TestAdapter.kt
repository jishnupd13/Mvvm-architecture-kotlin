package com.app.bygclite.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.bygclite.R
import com.app.bygclite.databinding.ItemTestViewBinding
import com.app.bygclite.listeners.OnItemClickListener
import com.app.bygclite.models.TestApiResponseModel


/** Created by Jishnu P Dileep on 26-05-2021 */
class TestAdapter(val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<TestAdapter.TestViewHolder>() {

    inner class TestViewHolder(binding: ItemTestViewBinding) : RecyclerView.ViewHolder(binding.root){
        val itemBinding=binding
    }

    private val differCallback = object : DiffUtil.ItemCallback<TestApiResponseModel>() {
        override fun areItemsTheSame(
            oldItem: TestApiResponseModel,
            newItem: TestApiResponseModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: TestApiResponseModel,
            newItem: TestApiResponseModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    fun submitList(list: List<TestApiResponseModel>){
        differ.submitList(list)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {

        val itemTestViewBinding: ItemTestViewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_test_view, parent, false
        )
        return TestViewHolder(itemTestViewBinding)
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        val data=differ.currentList[position]
        val binding=holder.itemBinding
        binding.item=data
        binding.listner=onItemClickListener
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}