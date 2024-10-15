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

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyProjet1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SignInScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun SignInScreen(modifier: Modifier = Modifier) {
    // Obtenir le contexte à utiliser pour les intents dans un composable
    val context = LocalContext.current

    // Variables d'état pour chaque champ
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    // Variables d'état pour les messages d'erreur
    var errorMessage by remember { mutableStateOf("") }

    // Structure de la page avec des champs de texte et un bouton
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Ligne pour les boutons "Login" et "Sign In" alignés à droite
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, end = 16.dp), // Placement en haut et espacement à droite
            horizontalArrangement = Arrangement.End
        ) {
            // Bouton "Login" avec un intent vers MainActivity
            TextButton(
                onClick = {
                    // Utiliser l'intent pour lancer MainActivity
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                },
                colors = ButtonDefaults.textButtonColors(contentColor = Color(0xFFE91E63)) // Couleur rose
            ) {
                Text(text = "Login", textDecoration = TextDecoration.None) // Sans soulignement
            }

            Spacer(modifier = Modifier.width(8.dp)) // Espacement entre les boutons

            TextButton(
                onClick = { /* Action pour Sign In */ },
                colors = ButtonDefaults.textButtonColors(contentColor = Color(0xFFE91E63)) // Violette
            ) {
                Text(text = "Sign In", textDecoration = TextDecoration.None) // Sans soulignement
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Texte "Sign In" centré et en violet
        Text(
            text = "Sign In",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFFE91E63), // Violette
            modifier = Modifier.align(Alignment.CenterHorizontally) // Centré
        )

        Spacer(modifier = Modifier.height(32.dp)) // Espace entre le texte et le formulaire

        // Champ pour le prénom
        TextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Champ pour le nom de famille
        TextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Champ pour l'email
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Champ pour le mot de passe
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Champ pour la confirmation du mot de passe
        TextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Afficher le message d'erreur s'il y en a un
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Bouton "Sign In"
        Button(
            onClick = {
                // Vérifier si tous les champs sont remplis
                when {
                    firstName.isEmpty() -> errorMessage = "Please enter your first name."
                    lastName.isEmpty() -> errorMessage = "Please enter your last name."
                    email.isEmpty() -> errorMessage = "Please enter your email."
                    password.isEmpty() -> errorMessage = "Please enter your password."
                    confirmPassword.isEmpty() -> errorMessage = "Please confirm your password."
                    password != confirmPassword -> errorMessage = "Passwords do not match."
                    else -> {
                        errorMessage = ""
                        // Action pour valider l'inscription
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE91E63)) // Couleur violette
        ) {
            Text(text = "Sign In", color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    MyProjet1Theme {
        SignInScreen()
    }
}