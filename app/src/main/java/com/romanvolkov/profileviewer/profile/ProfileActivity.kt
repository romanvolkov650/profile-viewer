package com.romanvolkov.profileviewer.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.romanvolkov.profileviewer.GlideApp
import com.romanvolkov.profileviewer.R
import com.romanvolkov.profileviewer.model.entity.Profile


class ProfileActivity : AppCompatActivity() {

    lateinit var profile: Profile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val bundle = intent.getBundleExtra("profileBundle")
        profile = bundle!!.getParcelable("profile")!!
        val photo = findViewById<ImageView>(R.id.iv_photo)
        val name = findViewById<TextView>(R.id.tv_name)
        val age = findViewById<TextView>(R.id.tv_age)
        val phone = findViewById<TextView>(R.id.tv_cell)
        val email = findViewById<EditText>(R.id.tv_email)
        val skype = findViewById<EditText>(R.id.tv_skype)
        email.keyListener = null
        email.setTextIsSelectable(true)
        skype.keyListener = null
        skype.setTextIsSelectable(true)
        GlideApp.with(photo).load(profile.picture.large).into(photo)
        name.text = "${profile.name.first} ${profile.name.last}"
        age.text = "${profile.dob.age} years old"
        phone.text = profile.phone
        email.setText(profile.email)
        skype.setText("${profile.name.first}_${profile.name.last}")
        phone.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${profile.phone}"))
            startActivity(intent)
        }
    }
}