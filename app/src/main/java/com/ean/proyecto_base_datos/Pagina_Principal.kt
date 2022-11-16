package com.ean.proyecto_base_datos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Pagina_Principal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagina_principal)

        val bundle = intent.extras
        val id_correo = bundle?.getString("correo")

        val bundle2 = intent.extras
        val año_desecho = bundle2?.getString("document")

        val bundle3 = intent.extras
        val total_desechos = bundle?.getString("total_desechos")






        val boton_ingresar_info = findViewById<Button>(R.id.bn_pg_ingreso_info)
        boton_ingresar_info.setOnClickListener {
            val intent = Intent(this,Ingreso_Info::class.java)
            intent.putExtra("correo", id_correo)
            startActivity(intent)
        }

        val boton_visualizar_desechos = findViewById<Button>(R.id.bn_pg_vizual_desechos)
        boton_visualizar_desechos.setOnClickListener {
            val intent = Intent(this,Visualizar_Desechos::class.java)
            intent.putExtra("correo", id_correo)
            intent.putExtra("document", año_desecho)
            intent.putExtra("total_desechos", total_desechos)
            startActivity(intent)
        }





    }
}