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
import java.text.SimpleDateFormat
import java.util.*


class Ingreso_Info : AppCompatActivity() {



    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingreso_info)

        val boton_guardar = findViewById<Button>(R.id.bn_ingr_guardar)
        val boton_volver = findViewById<Button>(R.id.bn_ingr_volver)
        val inertes = findViewById<TextView>(R.id.editTex_ingre_info_inertes)
        val urbanos = findViewById<TextView>(R.id.editText_ingr_info_urbanos)
        val peligrosos = findViewById<TextView>(R.id.editText_ingr_info_peligrosos)
        val otros = findViewById<TextView>(R.id.editText_ingr_info_otros)

        val sdf = SimpleDateFormat("M")
        val mes_fecha = sdf.format(Date())
        val mes_id = mes_fecha.toString()

        val aaa = SimpleDateFormat("yyyy")
        val fecha_a = aaa.format(Date())
        val fecha_id = fecha_a.toString()

        val bundle = intent.extras
        val id_gmail = bundle?.getString("correo")

        val desechos_list =  arrayListOf<Pair<String,Int>>()
        val id = id_gmail.toString()

        boton_volver.setOnClickListener {
            val intent = Intent(this,Pagina_Principal::class.java)
            startActivity(intent)
        }

        boton_guardar.setOnClickListener {
            try {

                val desecho_inertes = inertes.text.toString()
                val desecho_urbanos = urbanos.text.toString()
                val desecho_peligrosos = peligrosos.text.toString()
                val desecho_otros = otros.text.toString()


                if (desecho_inertes.isEmpty() || desecho_peligrosos.isEmpty() || desecho_urbanos.isEmpty() || desecho_otros.isEmpty()) {
                    Toast.makeText(baseContext, "Campos vacios", Toast.LENGTH_SHORT).show()
                } else {
                    val desechos = hashMapOf(
                        //mes_desecho to desechos_list,
                        "Desechos Inertes" to inertes.text.toString()
                        , "Desechos Urbanos" to urbanos.text.toString()
                        , "Desechos Peligrosos" to peligrosos.text.toString()
                        , "Otros" to otros.text.toString()
                    )
                    db.collection("desechos").document(id).collection(fecha_id).document(mes_id).set(desechos)
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