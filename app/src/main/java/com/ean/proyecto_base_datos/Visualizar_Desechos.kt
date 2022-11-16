package com.ean.proyecto_base_datos

import android.content.ContentValues
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class Visualizar_Desechos : AppCompatActivity() {

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visualizar_desechos)

        val bundle = intent.extras
        val id_gmail = bundle?.getString("correo")






        val collect = id_gmail.toString()


        val sdf = SimpleDateFormat("M/yyyy")
        val currentDate = sdf.format(Date())


        Toast.makeText(baseContext, collect, Toast.LENGTH_SHORT).show()


        val total = findViewById<TextView>(R.id.editText_visu_total)

        //total.setText(documento)

        
        val docRef = db.collection(collect).document(currentDate)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(ContentValues.TAG, "DocumentSnapshot data: ${document.data}")
                    total.setText("${document.data}")
                } else {
                    Log.d(ContentValues.TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }







    }
}