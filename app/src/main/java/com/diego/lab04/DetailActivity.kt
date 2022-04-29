package com.diego.lab04

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.diego.lab04.databinding.ActivityDetailBinding
import com.diego.lab04.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_detail.*

const val PARAMETER_EXTRA_NOMBRE = "nombre"
const val PARAMETER_EXTRA_CORREO = "correo"
const val PARAMETER_EXTRA_OFICINA = "oficina"
const val PARAMETER_EXTRA_TELEFONO= "telefono"
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_detail)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val extras = this.intent.extras
        if(extras != null){
            if(extras.get(PARAMETER_EXTRA_NOMBRE)!=null){
                binding.edtNombre.setText(extras.getString(PARAMETER_EXTRA_NOMBRE))
            }
            if(extras.get(PARAMETER_EXTRA_CORREO)!=null){
                binding.edtCorreo.setText(extras.getString(PARAMETER_EXTRA_CORREO))

            }
            if(extras.get(PARAMETER_EXTRA_OFICINA)!=null){
                binding.edtOficina.setText(extras.getString(PARAMETER_EXTRA_OFICINA))
            }
            if(extras.get(PARAMETER_EXTRA_TELEFONO)!=null){
                binding.edtTelefono.setText(extras.getString(PARAMETER_EXTRA_TELEFONO))
            }
        }
    }
    fun CloseActivity(view: android.view.View){
        val nombre = binding.edtNombre.text.toString()
        val correo = binding.edtCorreo.text.toString()
        val oficina = binding.edtOficina.text.toString()
        val telefono = binding.edtTelefono.text.toString()

        val intent = Intent(this,MainActivity::class.java)
        intent.putExtra(PARAMETER_EXTRA_NOMBRE,nombre)
        intent.putExtra(PARAMETER_EXTRA_CORREO,correo)
        intent.putExtra(PARAMETER_EXTRA_OFICINA,oficina)
        intent.putExtra(PARAMETER_EXTRA_TELEFONO,telefono)
        setResult(RESULT_OK,intent)
        finish()
    }
}