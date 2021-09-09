package br.com.ilanguilherme.motivation.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.ilanguilherme.motivation.R
import br.com.ilanguilherme.motivation.infra.MotivationConstants
import br.com.ilanguilherme.motivation.infra.SecurityPreference
import br.com.ilanguilherme.motivation.repository.Mock

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurityPreference: SecurityPreference

    private var mPhraseFilter : Int = MotivationConstants.PHRASEFILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mSecurityPreference = SecurityPreference(this)


        val name =  mSecurityPreference.getString(MotivationConstants.KEY.PERSON_NAME)

        findViewById<TextView>(R.id.textName).text = "Olá, ${toTitledCase(name)}!"
        handleNewPhrase()

        if(supportActionBar != null){
            supportActionBar!!.hide()
        }

        findViewById<ImageView>(R.id.imageAll).setColorFilter(resources.getColor(R.color.select))
        mPhraseFilter = MotivationConstants.PHRASEFILTER.ALL

        findViewById<Button>(R.id.buttonNewPhrase).setOnClickListener(this)
        findViewById<ImageView>(R.id.imageAll).setOnClickListener(this)
        findViewById<ImageView>(R.id.imageHappy).setOnClickListener(this)
        findViewById<ImageView>(R.id.imageMorning).setOnClickListener(this)

    }

    override fun onClick(view: View) {
        val listFilter = listOf(R.id.imageAll, R.id.imageHappy, R.id.imageMorning)
        val id = view.id
        if (id == R.id.buttonNewPhrase) {
            handleNewPhrase()
        } else if (id in listFilter) {
            handleFilter(id)
        }
    }

    fun toTitledCase(str: String): String? {
        val words = str.split("\\s").toTypedArray()
        val sb = StringBuilder()
        for (i in words.indices) {
            sb.append(words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase())
            sb.append(" ")
        }
        return sb.toString()
    }

    private fun handleFilter(id: Int) {
        findViewById<ImageView>(R.id.imageAll).setColorFilter(resources.getColor(R.color.white))
        findViewById<ImageView>(R.id.imageHappy).setColorFilter(resources.getColor(R.color.white))
        findViewById<ImageView>(R.id.imageMorning).setColorFilter(resources.getColor(R.color.white))
        when (id) {
            R.id.imageAll -> {
                findViewById<ImageView>(R.id.imageAll).setColorFilter(resources.getColor(R.color.select))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.ALL
            }
            R.id.imageHappy -> {
                findViewById<ImageView>(R.id.imageHappy).setColorFilter(resources.getColor(R.color.select))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.HAPPY
            }
            R.id.imageMorning -> {
                findViewById<ImageView>(R.id.imageMorning).setColorFilter(resources.getColor(R.color.select))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.MORNING
            }
        }
    }

    private fun handleNewPhrase() {
        findViewById<TextView>(R.id.textPhrase).text = Mock().getPhrase(mPhraseFilter)
    }


}