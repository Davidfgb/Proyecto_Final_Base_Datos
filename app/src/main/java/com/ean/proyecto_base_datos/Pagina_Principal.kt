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


        val boton_ingresar_info = findViewById<Button>(R.id.bn_pg_ingreso_info)
        boton_ingresar_info.setOnClickListener {
            val intent = Intent(this,Ingreso_Info::class.java)
            intent.putExtra("correo", id_correo)
            startActivity(intent)

        }





    }
}