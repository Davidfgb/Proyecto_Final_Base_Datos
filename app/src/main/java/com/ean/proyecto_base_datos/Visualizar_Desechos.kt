package com.ean.proyecto_base_datos

import android.content.ContentValues
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.checkerframework.common.subtyping.qual.Bottom
import java.text.SimpleDateFormat
import java.util.*

class Visualizar_Desechos : AppCompatActivity() {

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visualizar_desechos)

        val bundle = intent.extras
        val id_gmail = bundle?.getString("correo")
        val mostrar = findViewById<Button>(R.id.bn_visu_mostrar)
        val volver = findViewById<Button>(R.id.bn_visu_volver)

        val mostrar_datos = findViewById<TextView>(R.id.text_visu_motrar_datos)


        val id = id_gmail.toString()

        val datos = mostrar_datos.text.toString()

        val sdf = SimpleDateFormat("M")
        val mes_i = sdf.format(Date())

        val aaa = SimpleDateFormat("yyyy")
        val fecha_id = aaa.format(Date())



        mostrar.setOnClickListener {
            try {
                // Create a reference to the cities collection
                val citiesRef = db.collection("desechos")
                // Create a query against the collection.
                

                val docRef = db.collection("desechos").document(id).collection(fecha_id).document(mes_i)
                docRef.get()
                    .addOnSuccessListener { document ->
                        if (document != null) {
                            Log.d(ContentValues.TAG, "DocumentSnapshot data: ${document.data}")
                            mostrar_datos.setText("${document.data}")
                        } else {
                            Log.d(ContentValues.TAG, "No such document")
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.d(ContentValues.TAG, "get failed with ", exception)
                    }
            }
            catch (e: Exception) {
                Toast.makeText(baseContext, "Ese mes o año todavia no a sido ingresado", Toast.LENGTH_SHORT).show()

            }

        }






    }
}