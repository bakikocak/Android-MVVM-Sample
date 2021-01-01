package com.bakikocak.android_mvvm_sample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import com.bakikocak.android_mvvm_sample.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp() =
        Navigation.findNavController(this, R.id.navHostFragment).navigateUp()
}