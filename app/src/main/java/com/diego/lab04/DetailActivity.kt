package com.diego.lab04

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*

const val PARAMETER_EXTRA_NOMBRE = "nombre"
const val PARAMETER_EXTRA_CORREO = "correo"
const val PARAMETER_EXTRA_OFICINA = "oficina"
const val PARAMETER_EXTRA_TELEFONO= "telefono"
class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val extras = this.intent.extras
        if(extras != null){
            if(extras.get(PARAMETER_EXTRA_NOMBRE)!=null){
                edtNombre.setText(extras.getString(PARAMETER_EXTRA_NOMBRE))
            }
            if(extras.get(PARAMETER_EXTRA_CORREO)!=null){
                edtCorreo.setText(extras.getString(PARAMETER_EXTRA_CORREO))

            }
            if(extras.get(PARAMETER_EXTRA_OFICINA)!=null){
                edtOficina.setText(extras.getString(PARAMETER_EXTRA_OFICINA))
            }
            if(extras.get(PARAMETER_EXTRA_TELEFONO)!=null){
                edtTelefono.setText(extras.getString(PARAMETER_EXTRA_TELEFONO))
            }
        }
    }
    fun CloseActivity(view: android.view.View){
        val nombre = edtNombre.text.toString()
        val correo = edtCorreo.text.toString()
        val oficina = edtOficina.text.toString()
        val telefono = edtTelefono.text.toString()

        val intent = Intent(this,MainActivity::class.java)
        intent.putExtra("nombre",nombre)
        intent.putExtra("correo",correo)
        intent.putExtra("oficina",oficina)
        intent.putExtra("telefono",telefono)
        setResult(RESULT_OK,intent)
        finish()
    }
}