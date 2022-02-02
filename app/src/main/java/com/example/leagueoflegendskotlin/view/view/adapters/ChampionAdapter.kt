package com.example.leagueoflegendskotlin.view.view.adapters

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


class ChampionAdapter : RecyclerView.Adapter<ChampionAdapter.ChampionViewHolder>(){


    inner class ChampionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        private lateinit var mListener: ChampionClickListener

        fun setListener(listener: ChampionClickListener){
            this.mListener = listener
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            mListener.onChampionClicked(v, adapterPosition)
        }

    }



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

        val layoutInflater = LayoutInflater.from(parent.context)
        val myView = layoutInflater.inflate(R.layout.champion_row, parent, false)
        val championViewHolder = ChampionViewHolder(myView)
        championViewHolder.setListener((parent.context as ChampionClickListener))

        return championViewHolder

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ChampionViewHolder, position: Int) {

        val championIconIv: ImageView = holder.itemView.findViewById(R.id.champion_avatar)
        val championNameTv: TextView = holder.itemView.findViewById(R.id.champion_name)
        val championTitleTv: TextView = holder.itemView.findViewById(R.id.champion_title)
        val championDescriptionTv: TextView = holder.itemView.findViewById(R.id.champion_description)

        val champion = differ.currentList[position]

        if(!champion.linkFix){
            champion.icon = StringBuffer(champion.icon.trim()).insert(4, "s").toString()
            champion.linkFix = true
        }

        holder.itemView.apply {
            Glide.with(this).load(champion.icon).into(championIconIv)
            championNameTv.text = champion.name
            championTitleTv.text = champion.title
            championDescriptionTv.text = champion.description
        }
    }

}