package com.ean.proyecto_base_datos

import android.content.ContentValues
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Visualizar_Desechos : AppCompatActivity() {

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visualizar_desechos)

        val bundle = intent.extras
        val id_gmail = bundle?.getString("correo")

        val bundle2 = intent.extras
        val año_desecho = bundle2?.getString("document")



        val collect = id_gmail.toString()
        val documento = año_desecho.toString()



        val total = findViewById<TextView>(R.id.editText_visu_total)

        
        val docRef = db.collection(collect).document(documento)
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