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

        val sdf = SimpleDateFormat("yyyy")
        val currentDate = sdf.format(Date())
        val fecha_actual = currentDate.toInt()



        val gmail = findViewById<TextView>(R.id.editText_ingr_info_gmail)
        val fecha = findViewById<TextView>(R.id.editText_ingr_fecha)
        val mes = findViewById<TextView>(R.id.editText_ingr_mes)
        val inertes = findViewById<TextView>(R.id.editTex_ingre_info_inertes)
        val urbanos = findViewById<TextView>(R.id.editText_ingr_info_urbanos)
        val peligrosos = findViewById<TextView>(R.id.editText_ingr_info_peligrosos)
        val otros = findViewById<TextView>(R.id.editText_ingr_info_otros)
        val boton_guardar = findViewById<Button>(R.id.bn_ingr_guardar)
        val boton_volver = findViewById<Button>(R.id.bn_ingr_volver)


        val desechos_info = arrayListOf<Pair<String,Int>>()

        boton_guardar.setOnClickListener {
            try {
                var i = 0
                val id_gmail = gmail.text.toString()
                val id_fecha = fecha.text.toString()
                val id_mes = mes.text.toString()
                val desecho_inertes = inertes.text.toString()
                val desecho_urbanos = urbanos.text.toString()
                val desecho_peligrosos = peligrosos.text.toString()
                val desecho_otros = otros.text.toString()

                if (desecho_inertes.isEmpty() || desecho_peligrosos.isEmpty() || desecho_urbanos.isEmpty() || desecho_otros.isEmpty()|| id_gmail.isEmpty() || id_fecha.isEmpty() || id_mes.isEmpty()) {
                    Toast.makeText(baseContext, "Campos Vacios ", Toast.LENGTH_SHORT).show()
                } else {

                    val desechos = hashMapOf(
                        //mes_desecho to desechos_list,
                        "desechos_inertes" to desecho_inertes.toInt(),
                        "desechos_urbanos" to desecho_urbanos.toInt(),
                        "desechos_peligrosos" to desecho_peligrosos.toInt(),
                        "desechos_otros" to desecho_otros.toInt(),
                    )
                   /* desechos_info.add(Pair("inertes",desecho_inertes.toInt()))
                    desechos_info.add(Pair("urbanos",desecho_urbanos.toInt()))
                    desechos_info.add(Pair("peligrosos",desecho_peligrosos.toInt()))
                    desechos_info.add(Pair("otros",desecho_otros.toInt()))
                    i++*/
                    db.collection("desechos").document(id_gmail).collection(id_fecha).document(id_mes).set(desechos)
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
        boton_volver.setOnClickListener {
            val intent = Intent(this,Pagina_Principal::class.java)
            startActivity(intent)
        }
    }
}