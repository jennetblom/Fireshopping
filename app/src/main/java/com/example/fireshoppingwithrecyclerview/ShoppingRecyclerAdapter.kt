package com.example.fireshoppingwithrecyclerview

import DataManager
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ShoppingRecyclerAdapter(val context: Context,val shoppingItems : List<ShoppingItem>) :

    RecyclerView.Adapter<ShoppingRecyclerAdapter.ViewHolder>(){


    val layoutInflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = layoutInflater.inflate(R.layout.list_item,parent,false)

        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return shoppingItems.size
    }
    fun removeShoppingItem(position: Int){
        DataManager.shoppingItems.removeAt(position)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shoppingItem = shoppingItems[position]

        holder.nameTextView.text = shoppingItem.name
        holder.priceTextView.text = shoppingItem.price.toString() + " kr"
        holder.doneButton.isChecked = shoppingItem.done
        holder.shoppingItemPosition = position

    }

    inner class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        val nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)
        val priceTextView = itemView.findViewById<TextView>(R.id.priceTextView)
        val doneButton = itemView.findViewById<CheckBox>(R.id.checkBox)
        val deleteButton = itemView.findViewById<ImageButton>(R.id.deleteButton)

        var shoppingItemPosition = 0

        init {
            itemView.setOnClickListener {
                val intent = Intent(context,CreateAndEditShoppingItemActivity::class.java)
                intent.putExtra(SHOPPINGITEM_POSITION_KEY,shoppingItemPosition)
                context.startActivity(intent)
            }
            doneButton.setOnClickListener {
                DataManager.shoppingItems[shoppingItemPosition].done = doneButton.isChecked

            }
            deleteButton.setOnClickListener {
                removeShoppingItem(shoppingItemPosition)
            }
        }

    }
}