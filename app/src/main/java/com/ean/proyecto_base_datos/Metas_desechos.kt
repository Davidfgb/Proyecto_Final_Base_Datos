package com.ean.proyecto_base_datos

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class Metas_desechos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_metas_desechos)

        val db = Firebase.firestore

        val bundle = intent.extras
        val id_gmail = bundle?.getString("correo")
        val id = id_gmail.toString()

        val boton_registar = findViewById<Button>(R.id.bn_mta_registrar)
        val boton_volver = findViewById<Button>(R.id.bn_mta_volver)
        val inertes = findViewById<TextView>(R.id.editText_mta_inertes)
        val urbanos = findViewById<TextView>(R.id.editText_mta_urbanos)
        val peligrosos = findViewById<TextView>(R.id.editText_mta_peligrosos)
        val otros = findViewById<TextView>(R.id.editText_mta_otros)

        val desechos_list =  arrayListOf<Pair<String,Int>>()

        val sdf = SimpleDateFormat("M/yyyy")
        val currentDate = sdf.format(Date())

        boton_registar.setOnClickListener {
            try {

                val desecho_inertes = inertes.text.toString()
                val desecho_urbanos = urbanos.text.toString()
                val desecho_peligrosos = peligrosos.text.toString()
                val desecho_otros = otros.text.toString()


                if (desecho_inertes.isEmpty() || desecho_peligrosos.isEmpty() || desecho_urbanos.isEmpty() || desecho_otros.isEmpty()) {
                    Toast.makeText(baseContext, "Campos vacios", Toast.LENGTH_SHORT).show()
                } else {
                    val desechos = hashMapOf(
                         currentDate to desechos_list,
                    )
                    db.collection(id).document(currentDate).set(desechos)
                        .addOnSuccessListener {
                            Log.d(
                                ContentValues.TAG,
                                "DocumentSnapshot successfully written!"
                            )
                        }
                        .addOnFailureListener { e ->
                            Log.w(
                                ContentValues.TAG,
                                "Error writing document",
                                e
                            )
                        }
                    Toast.makeText(baseContext, "Informacion Registarda", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {

            }
        }





    }
}