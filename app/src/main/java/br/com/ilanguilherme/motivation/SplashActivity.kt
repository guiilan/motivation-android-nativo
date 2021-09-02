package br.com.ilanguilherme.motivation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class SplashActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val buttonSave = findViewById<Button>(R.id.buttonSave)

        buttonSave.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        val id = v.id
        if(id == R.id.buttonSave){
            handleSave()
        }
    }

    private fun handleSave() {
        val name = findViewById<EditText>(R.id.editName).text.toString()
        if(name != ""){
            startActivity(Intent(this, MainActivity::class.java))
        }else{
            Toast.makeText(this, "Informe seu nome!", Toast.LENGTH_SHORT).show()
        }
    }

}