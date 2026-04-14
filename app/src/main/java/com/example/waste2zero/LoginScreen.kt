package com.example.waste2zero

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.waste2zero.network.AuthRepository
import com.example.waste2zero.ui.theme.GreenWaste
import com.example.waste2zero.ui.theme.LightGreenWaste
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    val authRepository = remember { AuthRepository() }

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var statusMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GreenWaste)
            .padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Waste2Zero Logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre", color = Color.White) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo", color = Color.White) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contrasena", color = Color.White) },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    scope.launch {
                        isLoading = true
                        statusMessage = authRepository.checkHealth()
                            .fold(onSuccess = { it }, onFailure = { "Error de red: ${it.message}" })
                        isLoading = false
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(32.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2B5D40),
                    contentColor = Color.White
                ),
                enabled = !isLoading
            ) {
                Text(
                    text = "Comprobar API",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    scope.launch {
                        if (name.isBlank() || email.isBlank() || password.isBlank()) {
                            statusMessage = "Completa nombre, correo y contrasena para registro"
                            return@launch
                        }

                        isLoading = true
                        statusMessage = authRepository.register(name, email, password)
                            .fold(onSuccess = { it }, onFailure = { "Error de red: ${it.message}" })
                        isLoading = false
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                shape = RoundedCornerShape(32.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LightGreenWaste,
                    contentColor = Color.White
                ),
                enabled = !isLoading
            ) {
                Text(
                    text = "Registrarse",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(
                onClick = {
                    scope.launch {
                        if (email.isBlank() || password.isBlank()) {
                            statusMessage = "Completa correo y contrasena para login"
                            return@launch
                        }

                        isLoading = true
                        val result = authRepository.login(email, password)
                        statusMessage = result.fold(
                            onSuccess = { it },
                            onFailure = { "Error de red: ${it.message}" }
                        )
                        isLoading = false

                        if (result.isSuccess) {
                            onLoginSuccess()
                        }
                    }
                },
                enabled = !isLoading
            ) {
                Text(
                    text = "Iniciar Sesion",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            if (isLoading) {
                Spacer(modifier = Modifier.height(12.dp))
                CircularProgressIndicator(color = Color.White)
            }

            if (statusMessage.isNotBlank()) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = statusMessage,
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}
