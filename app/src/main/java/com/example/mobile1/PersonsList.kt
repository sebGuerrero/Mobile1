package com.example.mobile1

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun PersonsList() {
    var name by remember {
        mutableStateOf("")
    }

    var age by remember {
        mutableStateOf("")
    }

    var persons by remember {
        mutableStateOf(listOf<Person>())
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        TextField(value = name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text("Enter name") },
            onValueChange = { text ->
                name = text
            })

        TextField(
            value = age.toString(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text("Enter the age") },
            onValueChange = { text ->
                age = text
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                val intAge = age.toIntOrNull()
                if (name.isNotBlank() && intAge != null) {
                    persons = persons + Person(name = name, age = intAge)

                    name = ""
                    age = ""
                } else {
                    Toast.makeText(
                        context,
                        "Enter a valid information",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }) {
            Icon(
                imageVector = Icons.Default.Add, contentDescription = "icono de añadir"
            )
        }


        LazyColumn {
            items(persons) { currentPerson ->
                Row {
                    Text(
                        text = currentPerson.name,
                        modifier = Modifier
                            .padding(16.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = currentPerson.age.toString(),
                        modifier = Modifier.padding(16.dp)
                    )
                }

                Divider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PersonsListPreview() {
    PersonsList()
}