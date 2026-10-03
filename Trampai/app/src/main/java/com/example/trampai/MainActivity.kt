package com.example.trampai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.trampai.navigation.AppNavigation
import com.example.trampai.ui.theme.TrampaiTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TrampaiTheme {
                AppNavigation()
            }
        }
    }
}