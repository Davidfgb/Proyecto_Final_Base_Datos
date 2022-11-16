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
        val boton_volver = findViewById<Button>(R.id.bn_ingr_volver)
        val inertes = findViewById<TextView>(R.id.editTex_ingre_info_inertes)
        val urbanos = findViewById<TextView>(R.id.editText_ingr_info_urbanos)
        val peligrosos = findViewById<TextView>(R.id.editText_ingr_info_peligrosos)
        val otros = findViewById<TextView>(R.id.editText_ingr_info_otros)
        val mes = findViewById<TextView>(R.id.editText_ingr_info_mes)
        val año = findViewById<TextView>(R.id.editText_ingr_info_año)



        val bundle = intent.extras
        val id_gmail = bundle?.getString("correo")

        val desechos_list =  arrayListOf<Pair<String,Int>>()
        val id = id_gmail.toString()

       /* boton_volver.setOnClickListener {
            val año_desecho = año.text.toString()
            val intent = Intent(this,Pagina_Principal::class.java)
            intent.putExtra("document",año_desecho)
            startActivity(intent)
        }*/

        boton_guardar.setOnClickListener {
            try {

                val desecho_inertes = inertes.text.toString()
                val desecho_urbanos = urbanos.text.toString()
                val desecho_peligrosos = peligrosos.text.toString()
                val desecho_otros = otros.text.toString()
                val mes_desecho = mes.text.toString()
                val año_desecho = año.text.toString()


                if (desecho_inertes.isEmpty() || desecho_peligrosos.isEmpty() || desecho_urbanos.isEmpty() || desecho_otros.isEmpty()) {
                    Toast.makeText(baseContext, "Campos vacios", Toast.LENGTH_SHORT).show()
                } else {
                    desechos_list.add(Pair("desechos inertes",desecho_inertes.toInt()))
                    desechos_list.add(Pair("desechos urbanos",desecho_urbanos.toInt()))
                    desechos_list.add(Pair("desechos peligrosos",desecho_peligrosos.toInt()))
                    desechos_list.add(Pair("otros",desecho_otros.toInt()))
                    val desechos = hashMapOf(
                        mes_desecho to desechos_list,
                        "Desechos Inertes" to inertes.text.toString()
                        , "Desechos Urbanos" to urbanos.text.toString()
                        , "Desechos Peligrosos" to peligrosos.text.toString()
                        , "Otros" to otros.text.toString()
                    )
                    db.collection(id).document(año_desecho).set(desechos)
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