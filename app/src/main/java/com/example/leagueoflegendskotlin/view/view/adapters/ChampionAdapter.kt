package com.example.leagueoflegendskotlin.view.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.leagueoflegendskotlin.R
import com.example.leagueoflegendskotlin.view.db.Champion
import com.example.leagueoflegendskotlin.view.util.ChampionClickListener
import java.util.*



class ChampionAdapter : RecyclerView.Adapter<ChampionAdapter.ChampionViewHolder>(), View.OnClickListener{


    inner class ChampionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    val diffCallback = object : DiffUtil.ItemCallback<Champion>(){
        override fun areItemsTheSame(oldItem: Champion, newItem: Champion): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Champion, newItem: Champion): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    val differ = AsyncListDiffer(this,diffCallback)

    fun submitList(list: List<Champion>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampionViewHolder {
        return ChampionViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.champion_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ChampionViewHolder, position: Int) {

        var championIconIv: ImageView = holder.itemView.findViewById(R.id.champion_avatar)
        var championNameTv: TextView = holder.itemView.findViewById(R.id.champion_name)
        var championTitleTv: TextView = holder.itemView.findViewById(R.id.champion_title)
        var championDescriptionTv: TextView = holder.itemView.findViewById(R.id.champion_description)

        val champion = differ.currentList[position]

        if(!champion.linkFix){
            champion.icon = StringBuffer(champion.icon.trim()).insert(4, "s").toString()
            champion.linkFix = true
        }

        holder.itemView.apply {
            Glide.with(this).load(champion.icon).into(championIconIv)
        }
        championNameTv.text = champion.name
        championTitleTv.text = champion.title
        championDescriptionTv.text = champion.description
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

}