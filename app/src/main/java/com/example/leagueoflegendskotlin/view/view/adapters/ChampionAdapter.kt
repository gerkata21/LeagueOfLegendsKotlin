package com.example.leagueoflegendskotlin.view.view.adapters

import android.graphics.Paint
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
import kotlinx.android.synthetic.main.champion_row.view.*


class ChampionAdapter() : RecyclerView.Adapter<ChampionAdapter.ChampionViewHolder>(){


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

        val layoutInflater = LayoutInflater.from(parent.context)
        val myView = layoutInflater.inflate(R.layout.champion_row, parent, false)
        val championViewHolder = ChampionViewHolder(myView)



        return championViewHolder

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ChampionViewHolder, position: Int) {


        val champion = differ.currentList[position]

        if(!champion.linkFix){
            champion.icon = StringBuffer(champion.icon.trim()).insert(4, "s").toString()
            champion.linkFix = true
        }

        holder.itemView.apply {
            Glide.with(this).load(champion.icon).into(champion_avatar)
            champion_name.text = champion.name
            champion_name.underline()
            champion_title.text = champion.title
            champion_description.text = champion.description

            setOnClickListener {
                onItemClickListener?.let { it(champion) }
            }
        }
    }

    private var onItemClickListener: ((Champion) -> Unit)? = null

    fun setOnItemClickListener(listener: (Champion) -> Unit){
        onItemClickListener = listener
    }

    private fun TextView.underline() {
        paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }

}