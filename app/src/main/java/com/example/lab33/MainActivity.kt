package com.example.lab33

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab33.ui.theme.Lab33Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab33Theme {
                CalculatorScreen()
            }
        }
    }
}

@Composable
fun CalculatorScreen() {
    var firstNumber by remember { mutableStateOf("") }
    var secondNumber by remember { mutableStateOf("") }
    var thirdNumber by remember { mutableStateOf("") }
    var operation by remember { mutableStateOf("") }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        CalculatorForm(
            firstNumber = firstNumber,
            onFirstNumberChange = { firstNumber = it },
            secondNumber = secondNumber,
            onSecondNumberChange = { secondNumber = it },
            thirdNumber = thirdNumber,
            onThirdNumberChange = { thirdNumber = it },
            operation = operation,
            onOperationChange = { operation = it },
            contentPadding = innerPadding
        )
    }
}

@Composable
private fun CalculatorForm(
    firstNumber: String,
    onFirstNumberChange: (String) -> Unit,
    secondNumber: String,
    onSecondNumberChange: (String) -> Unit,
    thirdNumber: String,
    onThirdNumberChange: (String) -> Unit,
    operation: String,
    onOperationChange: (String) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(contentPadding)
            .padding(horizontal = 24.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "Введите три числа и выберите действие для вычисления среднего значения.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                NumberField("Первое число", firstNumber, onFirstNumberChange)
                NumberField("Второе число", secondNumber, onSecondNumberChange)
                NumberField("Третье число", thirdNumber, onThirdNumberChange)

                OutlinedTextField(
                    value = operation,
                    onValueChange = onOperationChange,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Символ") },
                    supportingText = { Text("a — среднее арифметическое, g — геометрическое") },
                    singleLine = true
                )
            }
        }
    }
}

@Composable
private fun NumberField(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        singleLine = true
    )
}

@Preview(showBackground = true)
@Composable
private fun CalculatorScreenPreview() {
    Lab33Theme {
        CalculatorScreen()
    }
}