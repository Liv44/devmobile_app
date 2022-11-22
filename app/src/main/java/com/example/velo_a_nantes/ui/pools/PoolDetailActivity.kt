package com.example.velo_a_nantes.ui.pools

import android.content.Intent
import android.net.Uri
import android.os.Bundle
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

        val buttonOpenWebSite = findViewById<Button>(R.id.buttonOpenWebSite)
        val buttonOpenMap = findViewById<Button>(R.id.buttonOpenMap)


        poolSelected?.let{ pool ->
            poolName.text = pool.nomComplet
            buttonOpenMap.setOnClickListener{
                // Creates an Intent that will load a map of San Francisco
                val gmmIntentUri = Uri.parse("geo:0,0?q=${pool.latitude},${pool.longitude}(${pool.commune})")
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
        }
    }
}