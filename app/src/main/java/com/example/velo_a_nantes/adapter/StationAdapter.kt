package com.example.velo_a_nantes.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.velo_a_nantes.R
import com.example.velo_a_nantes.models.Station
import com.example.velo_a_nantes.models.allStations
import com.example.velo_a_nantes.models.currentLocation
import com.example.velo_a_nantes.models.stationSelected
import com.example.velo_a_nantes.ui.home.StationMapsActivity
import com.example.velo_a_nantes.ui.station.StationDetailActivity
import org.w3c.dom.Text
import java.lang.Math.round
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class StationAdapter(private val stations:List<Station>, private val context:Context) :
    RecyclerView.Adapter<StationAdapter.ViewHolder>() {
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val cardView : CardView = itemView.findViewById(R.id.cardView)
        val name : TextView = itemView.findViewById(R.id.name)
        val address : TextView = itemView.findViewById(R.id.address)
        val status : ImageView = itemView.findViewById(R.id.status)
        val availability : TextView = itemView.findViewById(R.id.availability)
        val distance : TextView = itemView.findViewById(R.id.distance)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_item,parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val station = stations[position]
        holder.name.text = station.name

        if(currentLocation != null){
            holder.distance.text = "${String.format("%.2f", currentLocation!!.distanceTo(station.toLocation())/1000)}KM"
        }else{
            holder.distance.text = "Géolocalisation désactivée."
        }

        holder.address.text = station.address
        holder.availability.text =  "🚲 ${station.availableBikes} - 📣 ${station.availableBikeStands} - ✅${station.bikeStands}"
        if(station.availableBikes.toInt() == 0){
            holder.name.setTextColor(context.getColor(R.color.empty_bike))
        }else{
            holder.name.setTextColor(context.getColor(R.color.black))
        }
        if("OPEN" == station.status){
            holder.status.setImageResource(R.drawable.ic_baseline_green_button)
        }else{
            holder.status.setImageResource(R.drawable.ic_baseline_red_button)
        }


        holder.cardView.setOnClickListener {
            val intent = Intent(context, StationMapsActivity::class.java)
            intent.putExtra("station", station.name)
            stationSelected = station
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return stations.size
    }
}