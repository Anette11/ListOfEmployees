package com.example.listofemployees

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.listofemployees.navigation.MainNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        setContent {
            MainNavHost(
                onPhoneClick = { phone ->
                    makePhoneCall(phone = phone)
                }
            )
        }
    }

    private fun makePhoneCall(
        phone: String
    ) {
        if (phone.isBlank()) return
        val intent = Intent(
            Intent.ACTION_DIAL,
            Uri.fromParts("tel", phone, null)
        )
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            if (BuildConfig.DEBUG) e.printStackTrace()
            Toast.makeText(
                this,
                getString(R.string.activity_not_found_text),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}