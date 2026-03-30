package com.example.waste2zero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.waste2zero.ui.theme.Waste2ZeroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Waste2ZeroTheme {
                var currentScreen by remember { mutableStateOf("login") }

                if (currentScreen == "login") {
                    LoginScreen(
                        onLoginSuccess = { currentScreen = "scanner" }
                    )
                } else {
                    ScannerScreen(
                        onLogout = { currentScreen = "login" }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    Waste2ZeroTheme {
        LoginScreen(onLoginSuccess = {})
    }
}
