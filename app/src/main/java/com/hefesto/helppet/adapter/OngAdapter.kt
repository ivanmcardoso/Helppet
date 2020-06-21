package com.hefesto.helppet.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.model.LatLng
import com.hefesto.helppet.R
import com.hefesto.helppet.model.Ong
import com.hefesto.helppet.utils.MathUtils
import kotlinx.android.synthetic.main.list_item_ong.view.*


class OngAdapter(private val ongs: List<Ong>, private val userLatLng: LatLng ) : RecyclerView.Adapter<OngAdapter.OngViewHolder>() {
    class OngViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(ong: Ong, userLatLng: LatLng){
            itemView.tvOngName.text = ong.name
            val userDistance = MathUtils.distance(userLatLng.latitude, userLatLng.longitude, ong.lat, ong.lng)
            itemView.tvOngLocation.text = "%.2fkm".format(userDistance)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OngViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.list_item_ong, parent, false)
            .also { return OngViewHolder(it) }
    }

    override fun getItemCount() = ongs.size

    override fun onBindViewHolder(holder: OngViewHolder, position: Int) {
        holder.bindView(ongs[position], userLatLng)
    }
}