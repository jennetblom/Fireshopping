package com.example.fireshoppingwithrecyclerview

import DataManager
import android.os.Build.VERSION_CODES.P
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

const val SHOPPINGITEM_POSITION_KEY = "SHOPPING_POSITION"
const val POSITION_NOT_SET = -1

class CreateAndEditShoppingItemActivity : AppCompatActivity() {

    lateinit var nameEditText : EditText
    lateinit var priceEditText : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_and_edit_shopping_item)

        nameEditText = findViewById(R.id.nameEditText)
        priceEditText = findViewById(R.id.priceEditText)

        val shoppingPosition = intent.getIntExtra(SHOPPINGITEM_POSITION_KEY, POSITION_NOT_SET)

        val saveButton  = findViewById<Button>(R.id.saveButton)
        if(shoppingPosition!= POSITION_NOT_SET){

            displayShopping(shoppingPosition)
            saveButton.setOnClickListener { editShoppingItem(shoppingPosition) }
        }

        else {

            saveButton.setOnClickListener {

                addNewShoppingItem()
            }
        }
    }
    fun displayShopping(position: Int){
        val shoppingItem = DataManager.shoppingItems[position]

        nameEditText.setText(shoppingItem.name)
        priceEditText.setText(shoppingItem.price.toString())

    }
    fun editShoppingItem(position: Int){
        DataManager.shoppingItems[position].name = nameEditText.text.toString()
        DataManager.shoppingItems[position].price = priceEditText.text.toString().toInt()

        finish()


    }
    fun addNewShoppingItem(){
        val name = nameEditText.text.toString()
        val priceString = priceEditText.text.toString()

        val price : Int = try{
            priceString.toInt()
        }catch (e:NumberFormatException){
            Toast.makeText(this,"Skriv ett pris",Toast.LENGTH_SHORT).show()

            return
        }
        val shoppingItem = ShoppingItem(name,price)
        DataManager.shoppingItems.add(shoppingItem)
        finish()
    }
}