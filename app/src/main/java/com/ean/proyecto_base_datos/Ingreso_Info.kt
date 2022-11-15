package com.ean.proyecto_base_datos

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase



class Ingreso_Info : AppCompatActivity() {



    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingreso_info)


        val boton_guardar = findViewById<Button>(R.id.bn_ingr_guardar)
        val plastico = findViewById<TextView>(R.id.editTex_ingr_info_plastico)

        var id = 0

        val bundle = intent.extras
        val id_gmail = bundle?.getString("correo")










        boton_guardar.setOnClickListener {
            try {
                val desecho_plastico = plastico.text.toString()
                if (desecho_plastico.isEmpty()) {
                    Toast.makeText(baseContext, "Campos vacios", Toast.LENGTH_SHORT).show()
                } else {
                    val desechos = hashMapOf(
                        "Desecho Plastico" to plastico.text.toString()
                    )
                    db.collection("desechos").document(id_gmail.toString()).set(desechos)
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