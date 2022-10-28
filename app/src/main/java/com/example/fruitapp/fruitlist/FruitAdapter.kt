package com.example.fruitapp.fruitlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fruitapp.databinding.FruitRowBinding
import com.example.fruitapp.models.FruitsItem

class FruitAdapter(private val onClickListener: OnClickListener): ListAdapter<FruitsItem, FruitAdapter.MyViewHolder>(FruitItemDiffCallback()) {
    class OnClickListener(val clickListener: (fruit: FruitsItem) -> Unit){
        //delegate
        fun onClick(fruit: FruitsItem)= clickListener(fruit)
    }


    class MyViewHolder(val fruitRowBinding: FruitRowBinding)
        : RecyclerView.ViewHolder(fruitRowBinding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): FruitAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = FruitRowBinding.inflate(view,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        val fruitList = getItem(position)

        holder.fruitRowBinding.textname.text = fruitList.name
        holder.fruitRowBinding.textfamily.text = fruitList.family
        holder.fruitRowBinding.textgenus.text = fruitList.genus
       holder.fruitRowBinding.textnutritn.text = fruitList.order



        holder.itemView.setOnClickListener{
            onClickListener.onClick(fruitList)

        }
    }
}

/**
 * Extend ListAdapter<entity,ViewHolder>
 *     create DiffUtil
 *     ==
 *     viewHolder: cache the view references
 */

class FruitItemDiffCallback : DiffUtil.ItemCallback<FruitsItem>(){
    override fun areItemsTheSame(oldItem: FruitsItem, newItem: FruitsItem): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: FruitsItem, newItem: FruitsItem): Boolean =
        oldItem== newItem

}
