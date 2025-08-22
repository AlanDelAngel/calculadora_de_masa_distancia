package com.example.calculadora_de_masa_distancia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calculadora_de_masa_distancia.ui.theme.Calculadora_de_masa_distanciaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Calculadora_de_masa_distanciaTheme {
                AppNavigation()
            }
        }
    }
}

// -------------------- NAVIGATION --------------------
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "menu") {
        composable("menu") { MenuScreen(navController) }
        composable("page1") { conversion_lbs_kg() }
        composable("page2") { }
        composable("page3") { }
        composable("page4") { }
    }
}

// -------------------- MAIN MENU --------------------
@Composable
fun MenuScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Menú Principal", style = MaterialTheme.typography.headlineMedium)

        Button(onClick = { navController.navigate("page1") }, modifier = Modifier.fillMaxWidth()) {
            Text("Conversión lbs → kg")
        }
        Button(onClick = { navController.navigate("page2") }, modifier = Modifier.fillMaxWidth()) {
            Text("Función 2")
        }
        Button(onClick = { navController.navigate("page3") }, modifier = Modifier.fillMaxWidth()) {
            Text("Función 3")
        }
        Button(onClick = { navController.navigate("page4") }, modifier = Modifier.fillMaxWidth()) {
            Text("Función 4")
        }
    }
}


fun convertir_lbs_a_kg(libras: Float): Double {
    return 0.453592 * libras
}

@Composable
fun conversion_lbs_kg(modifier: Modifier = Modifier) {
    var inputText by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf<Double?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Ingrese libras") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val libras = inputText.toFloatOrNull()
                if (libras != null) {
                    resultado = convertir_lbs_a_kg(libras)
                } else {
                    resultado = null
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Convertir a Kg")
        }

        Text(
            text = resultado?.let { "Resultado: %.2f kg".format(it) } ?: "Ingrese un número válido",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Calculadora_de_masa_distanciaTheme {
        MenuScreen(navController = rememberNavController())
    }
}