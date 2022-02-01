package com.example.leagueoflegendskotlin.view.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.leagueoflegendskotlin.R
import com.example.leagueoflegendskotlin.view.model.championsData.ChampionsItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetails : AppCompatActivity() {

    private lateinit var mAvatar : ImageView
    private lateinit var mNameTitleTv : TextView
    private lateinit var mTypeTv : TextView
    private lateinit var mHealthTv : TextView
    private lateinit var mArmorTv : TextView
    private lateinit var mMagicResist : TextView
    private lateinit var mAttackDamage : TextView
    private lateinit var mAttackRange : TextView
    private lateinit var mAttackSpeed : TextView
    private lateinit var mMoveSpeed : TextView
    private lateinit var mMana : TextView
    private lateinit var mHealthPerLevel : TextView
    private lateinit var mManaPerLevel : TextView
    private lateinit var mMagicResistPerLevel : TextView
    private lateinit var mAttackDamagePerLevel : TextView
    private lateinit var mAttackSpeedPerLevel : TextView
    private lateinit var mArmorPerLevel : TextView
    private lateinit var mBackBtn : Button


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)

        initialize()

        mBackBtn.setOnClickListener {
            finish()
        }

        val mIntent = intent
        val mChampion = mIntent.getSerializableExtra("champion") as ChampionsItem

        Glide.with(this).load(mChampion.icon).centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.cross)
            .into(mAvatar)

        mNameTitleTv.text = mChampion.name
        mTypeTv.text =  mChampion.tags.toString()
        mHealthTv.text = "Health: " + mChampion.stats.hp.toString()
        mArmorTv.text = "Armor: " + mChampion.stats.armor.toString()
        mMagicResist.text = "Magic Resist: " + mChampion.stats.spellBlock.toString()
        mAttackDamage.text = "Attack Damage: " + mChampion.stats.attackDamage.toString()
        mAttackRange.text = "Attack Range: " + mChampion.stats.attackRange.toString()
        mAttackSpeed.text = "Attack Speed: " + mChampion.stats.attackSpeed.toString()
        mMoveSpeed.text = "Move Speed: " + mChampion.stats.moveSpeed.toString()
        mMana.text = "Mana: " + mChampion.stats.mp.toString()
        mHealthPerLevel.text = mChampion.stats.hpperLevel.toString()
        mManaPerLevel.text = mChampion.stats.mpPerLevel.toString()
        mMagicResistPerLevel.text = mChampion.stats.spellBlockPerLevel.toString()
        mAttackDamagePerLevel.text = mChampion.stats.attackDamagePerLevel.toString()
        mAttackSpeedPerLevel.text = mChampion.stats.attackSpeedPerLevel.toString()
        mArmorPerLevel.text = mChampion.stats.armorPerLevel.toString()

    }

    private fun initialize() {
        mAvatar = findViewById(R.id.avatar_iv)
        mNameTitleTv = findViewById(R.id.Name_Tv)
        mTypeTv = findViewById(R.id.style_Tv)
        mHealthTv = findViewById(R.id.hp_tv)
        mArmorTv = findViewById(R.id.armor_tv)
        mMagicResist = findViewById(R.id.magic_resist_tv)
        mAttackDamage = findViewById(R.id.attack_damage_tv)
        mAttackRange = findViewById(R.id.attack_range_tv)
        mAttackSpeed = findViewById(R.id.attack_speed_tv)
        mMoveSpeed = findViewById(R.id.move_speed_tv)
        mMana = findViewById(R.id.mana_tv)
        mHealthPerLevel = findViewById(R.id.hp_inc_tv)
        mManaPerLevel = findViewById(R.id.mana_inc_tv)
        mMagicResistPerLevel = findViewById(R.id.magic_resist_inc_tv)
        mAttackDamagePerLevel = findViewById(R.id.attack_damage_inc_tv)
        mAttackSpeedPerLevel = findViewById(R.id.attack_speed_inc_tv)
        mArmorPerLevel = findViewById(R.id.armor_inc_tv)
        mBackBtn = findViewById(R.id.return_btn)
    }

}