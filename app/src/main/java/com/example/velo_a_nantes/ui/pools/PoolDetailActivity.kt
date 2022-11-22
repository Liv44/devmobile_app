package com.example.velo_a_nantes.ui.pools

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.velo_a_nantes.R
import com.example.velo_a_nantes.models.poolSelected


class PoolDetailActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pool_detail)
        val poolName = findViewById<TextView>(R.id.poolName)
        val phoneNumber = findViewById<TextView>(R.id.phoneNumber)
        val addressDetailled = findViewById<TextView>(R.id.addressDetailled)
        val transports = findViewById<TextView>(R.id.transports)
        val infrastructures = findViewById<TextView>(R.id.infrastructures)
        val bassins = findViewById<TextView>(R.id.bassins)

        val buttonOpenWebSite = findViewById<Button>(R.id.buttonOpenWebSite)
        val buttonOpenMap = findViewById<Button>(R.id.buttonOpenMap)


        poolSelected?.let{ pool ->
            poolName.text = pool.nomComplet
            phoneNumber.text = pool.tel
            val spanString = SpannableString(pool.tel)
            spanString.setSpan(UnderlineSpan(), 0, spanString.length, 0)
            phoneNumber.setText(spanString)
            addressDetailled.text = "${pool.adresse} - ${pool.cp} ${pool.commune}"
            var transportsInfos1 = pool.accesTransportsCommun.replace("<p>", "")
            var transportsInfos2 =  transportsInfos1.replace("</p>", "")
            var transportsInfos3 = transportsInfos2.replace("<br />", "\n")

            //transports.text = "\n- ${finalTransportsInfos[0]}\n- ${finalTransportsInfos[1]}}"
            transports.text= transportsInfos3

            var infrastructureTemp = ""
            if(pool.plongeoir =="OUI"){
                infrastructureTemp +="\n- Plongeoir"
            }
            if(pool.toboggan == "OUI"){
                infrastructureTemp +="\n- Toboggan"
            }
            if(pool.pataugeoire == "OUI"){
                infrastructureTemp +="\n- Pataugeoire"
            }
            if(infrastructureTemp==""){
                infrastructures.text = "Pas d'infrastuctures"
            }else{
                infrastructures.text = infrastructureTemp
            }

            var bassinsTemp = ""
            if(pool.bassinLoisir =="OUI"){
                bassinsTemp +="\n- Bassin Loisir"
            }
            if(pool.bassinApprentissage == "OUI"){
                bassinsTemp +="\n- Bassin Apprentissage"
            }
            if(pool.bassinSportif == "OUI"){
                bassinsTemp +="\n- Bassin sportif"
            }
            if(bassinsTemp==""){
                bassins.text = "Pas de bassins spécifiés"
            }else{
                bassins.text = bassinsTemp
            }

            buttonOpenMap.setOnClickListener{
                // Creates an Intent that will load a map of San Francisco
                val gmmIntentUri = Uri.parse("geo:0,0?q=${pool.latitude},${pool.longitude}(${pool.nomComplet})")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            }

            buttonOpenWebSite.setOnClickListener {
                val urlString = pool.web
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlString))
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.setPackage("com.android.chrome")
                startActivity(intent);
            }

            phoneNumber.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:${pool.tel}")
                startActivity(intent)
            }

        }
    }
}