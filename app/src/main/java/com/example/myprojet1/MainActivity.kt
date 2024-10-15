package com.example.myprojet1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myprojet1.ui.theme.MyProjet1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyProjet1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(
                onClick = {
                    val intent = Intent(context, MainActivity2::class.java)
                    context.startActivity(intent)
                },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color(0xFFE91E63)
                ),
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Text(text = "Sign In", textDecoration = TextDecoration.None)
            }

            TextButton(
                onClick = { /* Action pour se connecter */ },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color(0xFFE91E63)
                )
            ) {
                Text(text = "Login", textDecoration = TextDecoration.None)
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Login",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFFE91E63)
        )

        Spacer(modifier = Modifier.height(30.dp))

        TextField(
            value = email,
            onValueChange = {
                email = it
                errorMessage = ""
            },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(35.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = {
            val intent = Intent(context, MainActivity3::class.java)
            context.startActivity(intent)
        }) {
            Text(
                text = "Forgot Password?",
                color = Color(0xFFE91E63),
                textDecoration = TextDecoration.Underline
            )
        }

        Spacer(modifier = Modifier.height(35.dp))

        Button(
            onClick = {
                // E-mail et mot de passe pour la connexion
                val validEmail = "cyrine@gmail.com"
                val validPassword = "123"

                if (email.isEmpty()) {
                    errorMessage = "Please enter your email."
                } else if (password.isEmpty()) {
                    errorMessage = "Please enter your password."
                } else if (email == validEmail && password == validPassword) {
                    // Redirection vers MainActivity5

                } else {
                    errorMessage = "Invalid email or password."
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE91E63))
        ) {
            Text(text = "Login", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MyProjet1Theme {
        LoginScreen()
    }
}