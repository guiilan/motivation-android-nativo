package br.com.ilanguilherme.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import br.com.ilanguilherme.motivation.R
import br.com.ilanguilherme.motivation.infra.MotivationConstants
import br.com.ilanguilherme.motivation.infra.SecurityPreference


class SplashActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurityPreference: SecurityPreference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val buttonSave = findViewById<Button>(R.id.buttonSave)
        buttonSave.setOnClickListener(this)

        mSecurityPreference  = SecurityPreference(this)
        verifyName()

    }

    override fun onClick(v: View) {
        val id = v.id
        if(id == R.id.buttonSave){
            handleSave()
        }
    }

    private fun verifyName() {
        val name = mSecurityPreference.getString(MotivationConstants.KEY.PERSON_NAME)
        if(name != ""){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }

    private fun handleSave() {
        val name = findViewById<EditText>(R.id.editName).text.toString()
        if(name != ""){
            mSecurityPreference.storeString(MotivationConstants.KEY.PERSON_NAME, name)

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }else{
            Toast.makeText(this, "Informe seu nome!", Toast.LENGTH_SHORT).show()
        }
    }

}