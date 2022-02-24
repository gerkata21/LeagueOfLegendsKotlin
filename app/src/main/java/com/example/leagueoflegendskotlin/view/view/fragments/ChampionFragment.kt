package com.example.leagueoflegendskotlin.view.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.leagueoflegendskotlin.R
import com.example.leagueoflegendskotlin.view.db.Champion
import com.example.leagueoflegendskotlin.view.view.MainActivity
import com.example.leagueoflegendskotlin.view.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.character_fragment.*

class ChampionFragment : Fragment(R.layout.character_fragment) {

    private val args: ChampionFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        backBtn()

    }

    @SuppressLint("SetTextI18n")
    private fun setupViews(){

        val champion = args.champion

        Glide.with(this).load(champion.icon).centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.cross)
            .into(avatar_iv)
        Name_Tv.text = champion.name
        style_Tv.text =  champion.tags.toString()
        hp_tv.text = "Health: " + champion.stats!!.hp.toString()
        armor_tv.text = "Armor: " + champion.stats.armor.toString()
        magic_resist_tv.text = "Magic Resist: " + champion.stats.spellBlock.toString()
        attack_damage_tv.text = "Attack Damage: " + champion.stats.attackDamage.toString()
        attack_range_tv.text = "Attack Range: " + champion.stats.attackRange.toString()
        attack_speed_tv.text = "Attack Speed: " + champion.stats.attackSpeed.toString()
        move_speed_tv.text = "Move Speed: " + champion.stats.moveSpeed.toString()
        mana_tv.text = "Mana: " + champion.stats.mp.toString()
        hp_inc_tv.text = champion.stats.hpperLevel.toString()
        mana_inc_tv.text = champion.stats.mpPerLevel.toString()
        magic_resist_inc_tv.text = champion.stats.spellBlockPerLevel.toString()
        attack_damage_inc_tv.text = champion.stats.attackDamagePerLevel.toString()
        attack_speed_inc_tv.text = champion.stats.attackSpeedPerLevel.toString()
        armor_inc_tv.text = champion.stats.armorPerLevel.toString()

    }

    private fun backBtn(){

        return_btn.setOnClickListener { activity?.onBackPressed() }

    }
}