package com.example.fireshoppingwithrecyclerview

import DataManager
import android.content.Intent
import android.os.Build.VERSION_CODES.Q
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var titleText : TextView
    lateinit var shoppingImage : ImageView
    lateinit var recyclerView : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView =findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = ShoppingRecyclerAdapter(this,DataManager.shoppingItems)
        titleText = findViewById(R.id.titleTextView)
        shoppingImage = findViewById(R.id.shoppingImage)
        shoppingImage.setImageResource(R.drawable.baseline_shopping_cart_24)


        val db = Firebase.firestore
        val item1 = ShoppingItem("Pepparkakor",12)

//        for(item in DataManager.shoppingItems){
//            db.collection("Items").add(item)
//        }
        db.collection("Item").add(item1).addOnFailureListener {
            Log.d("!!!","error : $it")
        }
            .addOnSuccessListener {
                Log.d("!!!","sucess")
            }
        //db.collection("Items").add(DataManager.shoppingItems)
        Log.d("!!!","$item1")

        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fab.setOnClickListener {
            val intent = Intent(this,CreateAndEditShoppingItemActivity::class.java)
            startActivity(intent)
        }


    }
    override fun onResume(){
        super.onResume()

        recyclerView.adapter?.notifyDataSetChanged()
    }
}