package com.choice.monkeyfoodfork

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.choice.monkeyfoodfork.components.AppUI
import com.choice.theme.MonkeyDeliveryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MonkeyDeliveryTheme {
                AppUI()
            }
        }
    }
}