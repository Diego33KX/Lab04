package com.diego.lab04

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


const val ACTIVITY_A_REQUEST = 991
const val ACTIVITY_B_REQUEST = 992
const val PARAMETER_EXTRA_NOMBRE_NEW = "nombre"
const val PARAMETER_EXTRA_CORREO_NEW = "correo"
const val PARAMETER_EXTRA_OFICINA_NEW = "oficina"
const val PARAMETER_EXTRA_TELEFONO_NEW= "telefono"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun sendExplicit(view: android.view.View){
        val nombre = tvNombre.text.toString()
        val correo = tvCorreo.text.toString()
        val oficina = tvOficina.text.toString()
        val telefono = tvTelefono.text.toString()
        validateInputFields(nombre, correo, oficina,telefono)
        DetailActivity(nombre,correo,oficina,telefono)
    }
    private fun DetailActivity(nombre: String, correo: String, oficina: String, telefono: String){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("nombre",nombre)
        intent.putExtra("correo",correo)
        intent.putExtra("oficina",oficina)
        intent.putExtra("telefono",telefono)
        startActivityForResult(intent,ACTIVITY_B_REQUEST)

    }
    private fun validateInputFields(nombre: String, correo: String, oficina: String, telefono: String){
        if(nombre.isBlank() && correo.isBlank() && oficina.isBlank() && telefono.isBlank()) return

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "requestCode:$requestCode")
        Log.d(TAG, "resultCode:$resultCode")
        Log.d(TAG, "data:" + android.R.attr.data)
        when(requestCode){
            ACTIVITY_A_REQUEST -> Log.d(TAG,"Regresamos a la pagina principal")
            ACTIVITY_B_REQUEST -> {
                if(resultCode=== RESULT_OK){
                    val valor: String = data?.extras?.getString("valor").toString()
                    Log.d(TAG,"valor: $valor")
                }
                val extras = data?.extras
                if(extras != null){
                    if(extras.get(PARAMETER_EXTRA_NOMBRE_NEW)!=null){
                        tvNombre.setText(extras.getString(PARAMETER_EXTRA_NOMBRE_NEW))
                    }
                    if(extras.get(PARAMETER_EXTRA_CORREO_NEW)!=null){
                        tvCorreo.setText(extras.getString(PARAMETER_EXTRA_CORREO_NEW))
                    }
                    if(extras.get(PARAMETER_EXTRA_OFICINA_NEW)!=null){
                        tvOficina.setText(extras.getString(PARAMETER_EXTRA_OFICINA_NEW))
                    }
                    if(extras.get(PARAMETER_EXTRA_TELEFONO_NEW)!=null){
                        tvTelefono.setText(extras.getString(PARAMETER_EXTRA_TELEFONO_NEW))
                    }
                }
            }
        }
    }

    fun make_calls(view: android.view.View){
        val telefono = tvTelefono.text.toString()
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", telefono, null))
        startActivity(intent);
    }

    fun whatsapp(view: android.view.View){
        try {
            val correo = tvCorreo.text.toString()
            val oficina = tvOficina.text.toString()
            val nombre = tvNombre.text.toString()
            val text = "Mensaje WSP para: Nombre: $nombre Correo:$correo Oficina:$oficina"
            val telefono = tvTelefono.text.toString()
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("http://api.whatsapp.com/send?phone=$telefono&text=$text")
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun SendMessage(view: android.view.View){
        val nombre = tvNombre.text.toString()
        val telefono = tvTelefono.text.toString()
        //startActivity(Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", telefono, null)))
        val uri = Uri.parse("smsto: $telefono")
        val it = Intent(Intent.ACTION_SENDTO, uri)
        it.putExtra("sms_body", "Hola $nombre te mando este mensaje desde Android")
        startActivity(it)
    }
}