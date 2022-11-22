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
import com.example.velo_a_nantes.R
import com.example.velo_a_nantes.models.Pool
import com.example.velo_a_nantes.models.currentLocation
import com.example.velo_a_nantes.models.poolSelected
import com.example.velo_a_nantes.models.stationSelected
import com.example.velo_a_nantes.ui.home.StationMapsActivity
import com.example.velo_a_nantes.ui.pools.PoolDetailActivity

class PoolAdapter(private val pools:List<Pool>, private val context:Context) :
    RecyclerView.Adapter<PoolAdapter.ViewHolder>() {
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val cardView : CardView = itemView.findViewById(R.id.cardView)
        val name : TextView = itemView.findViewById(R.id.name)
        val city : TextView = itemView.findViewById(R.id.city)
        val distance : TextView = itemView.findViewById(R.id.distance)
        val childPool : ImageView = itemView.findViewById(R.id.childPool)
        val waterSlide: ImageView = itemView.findViewById(R.id.waterSlide)
        val jump: ImageView = itemView.findViewById(R.id.jump)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_item_pool,parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val pool = pools[position]
        val nomComplet = pool.nomComplet.split(" - ")
        holder.name.text = nomComplet[0]

        if(currentLocation != null){
            holder.distance.text = "${String.format("%.2f", currentLocation!!.distanceTo(pool.toLocation())/1000)}KM"
        }else{
            holder.distance.text = "Géolocalisation désactivée."
        }
        if(pool.pataugeoire != "OUI"){
            holder.childPool.visibility = View.INVISIBLE
        }
        if(pool.toboggan != "OUI"){
            holder.waterSlide.visibility = View.INVISIBLE
        }
        if(pool.plongeoir != "OUI"){
            holder.jump.visibility = View.INVISIBLE
        }
        holder.city.text = "${pool.commune} - ${pool.cp}"
        holder.cardView.setOnClickListener {
            val intent = Intent(context, PoolDetailActivity::class.java)
            intent.putExtra("pool", pool.nomComplet)
            poolSelected = pool
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return pools.size
    }
}