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
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import java.text.Normalizer.Form

@Composable
fun NameList() {
    var name by remember {
        mutableStateOf("")
    }

    var names by remember {
        mutableStateOf(listOf<String>())
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        FormTimer(duration = 15)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            TextField(
                value = name,
                placeholder = { Text("Enter name") },
                onValueChange = { text ->
                    name = text
            })

            Spacer(modifier = Modifier
                .weight(1f)
            )

            Button(onClick = {

                if (name.isNotBlank()) {
                    names = names + name
                } else {
                    Toast
                        .makeText(
                            context,
                            "Enter a valid name",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                }
                name = ""
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "icono de añadir"
                )
            }
        }

        LazyColumn {
            items(names) {currentName ->
                Text(
                    text = currentName,
                    modifier = Modifier
                        .padding(16.dp)
                )
                Divider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListPreview() {
    NameList()
}