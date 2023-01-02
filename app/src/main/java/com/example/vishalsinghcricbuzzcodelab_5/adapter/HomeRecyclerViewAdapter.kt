package com.example.codelab5_crickbuzz.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.vishalsinghcricbuzzcodelab_5.R
import com.example.vishalsinghcricbuzzcodelab_5.db.CricketPlayer
import com.example.vishalsinghcricbuzzcodelab_5.db.CricketPlayerData
import com.example.vishalsinghcricbuzzcodelab_5.ui.ItemClicked.Clicked
import kotlinx.android.synthetic.main.item_rv.view.*

class HomeRecyclerViewAdapter(private val list:List<CricketPlayer>, private val md: Clicked)
    : RecyclerView.Adapter<HomeRecyclerViewAdapter.MyViewHolder>()
{

    //var navController: NavController? = null
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: CricketPlayer) {
            itemView.iv_plimage.setImageBitmap(data.photo)
            itemView.tv_plyname.text = data.name.trim()
            itemView.tv_cricketerCountry.text = data.country.trim()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = list[position]
        holder.bind(data)
        holder.itemView.setOnClickListener {


            Log.e("cleck","press")
            val pD= CricketPlayerData(
                data.id,
                data.photo,
                data.name,
                data.team,
                data.country,
                data.dob,
                data.batter,
                data.bowler,
                data.match,
                data.run,
                data.wicket,
                data.favourite
            )

            md.withData(pD)

        }





    }

    override fun getItemCount(): Int {
        return list.size
    }
}