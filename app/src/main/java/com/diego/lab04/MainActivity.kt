package com.diego.lab04

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.diego.lab04.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


const val ACTIVITY_A_REQUEST = 991
const val ACTIVITY_B_REQUEST = 992
const val PARAMETER_EXTRA_NOMBRE_NEW = "nombre"
const val PARAMETER_EXTRA_CORREO_NEW = "correo"
const val PARAMETER_EXTRA_OFICINA_NEW = "oficina"
const val PARAMETER_EXTRA_TELEFONO_NEW= "telefono"
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }

    fun sendExplicit(view: android.view.View){

        //ACCEDEMOS A LOS COMPONENTES CON VIEWBINDING
        val nombre = binding.tvNombre.text.toString()
        val correo = binding.tvCorreo.text.toString()
        val oficina = binding.tvOficina.text.toString()
        val telefono = binding.tvTelefono.text.toString()
        validateInputFields(nombre, correo, oficina,telefono)
        DetailActivity(nombre,correo,oficina,telefono)
    }
    private fun DetailActivity(nombre: String, correo: String, oficina: String, telefono: String){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(PARAMETER_EXTRA_NOMBRE,nombre)
        intent.putExtra(PARAMETER_EXTRA_CORREO,correo)
        intent.putExtra(PARAMETER_EXTRA_OFICINA,oficina)
        intent.putExtra(PARAMETER_EXTRA_TELEFONO,telefono)
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
                        binding.tvNombre.setText(extras.getString(PARAMETER_EXTRA_NOMBRE_NEW))
                    }
                    if(extras.get(PARAMETER_EXTRA_CORREO_NEW)!=null){
                        binding.tvCorreo.setText(extras.getString(PARAMETER_EXTRA_CORREO_NEW))
                    }
                    if(extras.get(PARAMETER_EXTRA_OFICINA_NEW)!=null){
                        binding.tvOficina.setText(extras.getString(PARAMETER_EXTRA_OFICINA_NEW))
                    }
                    if(extras.get(PARAMETER_EXTRA_TELEFONO_NEW)!=null){
                        binding.tvTelefono.setText(extras.getString(PARAMETER_EXTRA_TELEFONO_NEW))
                    }
                }
            }
        }
    }

    fun makeCalls(view: android.view.View){
        //val telefono = tvTelefono.text.toString()
        val telefono = binding.tvTelefono.text.toString()
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", telefono, null))
        startActivity(intent);
    }

    fun whatsapp(view: android.view.View){
        try {
            val correo = binding.tvCorreo.text.toString()
            val oficina = binding.tvOficina.text.toString()
            val nombre = binding.tvNombre.text.toString()
            val telefono = binding.tvTelefono.text.toString()
            val text = "Mensaje WSP para: Nombre: $nombre Correo:$correo Oficina:$oficina"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("http://api.whatsapp.com/send?phone=$telefono&text=$text")
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun sendMessage(view: android.view.View){
        val nombre = binding.tvNombre.text.toString()
        val telefono = binding.tvTelefono.text.toString()
        val uri = Uri.parse("smsto: $telefono")
        val it = Intent(Intent.ACTION_SENDTO, uri)
        it.putExtra("sms_body", "Hola $nombre te mando este mensaje desde Android")
        startActivity(it)
    }
}