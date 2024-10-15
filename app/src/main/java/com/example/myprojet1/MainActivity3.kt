package com.example.myprojet1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myprojet1.ui.theme.MyProjet1Theme

class MainActivity3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyProjet1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ForgotPasswordScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ForgotPasswordScreen(modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") } // État pour le message d'erreur
    val context = LocalContext.current // Obtenir le contexte ici

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Forgot Password",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFFE91E63) // Couleur violette
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Champ de texte pour l'e-mail
        TextField(
            value = email,
            onValueChange = {
                email = it
                errorMessage = "" // Réinitialiser le message d'erreur lors de la saisie
            },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = errorMessage.isNotEmpty() // Indique que le champ est en erreur
        )

        // Affichage du message d'erreur si présent
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall // Style pour le message d'erreur
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Bouton pour envoyer la demande de récupération de mot de passe
        Button(
            onClick = {
                // Vérifier si le champ e-mail est vide
                if (email.isBlank()) {
                    errorMessage = "Email is required." // Message d'erreur
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE91E63)) // Couleur violette
        ) {
            Text(text = "Send Recovery Email", color = Color.White) // Texte blanc sur bouton violet
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Texte pour retourner à la page de connexion
        TextButton(onClick = {
            val intent = Intent(context, MainActivity::class.java) // Utiliser le contexte local
            context.startActivity(intent) // Démarrer l'activité de connexion
        }) {
            Text(
                text = "Back to Login",
                color = Color(0xFFE91E63),
                textDecoration = TextDecoration.Underline
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForgotPasswordScreenPreview() {
    MyProjet1Theme {
        ForgotPasswordScreen()
    }
}