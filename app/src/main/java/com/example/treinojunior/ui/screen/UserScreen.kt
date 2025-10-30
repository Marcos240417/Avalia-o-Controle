package com.example.treinojunior.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.treinojunior.repository.entity.UserEntity
import com.example.treinojunior.ui.viewmodel.UserViewModel

@Composable
fun UserScreen(viewModel: UserViewModel) {
    val users by viewModel.user.collectAsState() // Observa a lista de usuários em tempo real

    var id by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // 🔍 Campo de pesquisa
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Pesquisar por nome") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { viewModel.getUsername(searchQuery) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Text("Buscar Usuário")
        }

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            thickness = DividerDefaults.Thickness,
            color = DividerDefaults.color
        )

        // 🧾 Campos de entrada para inserir/atualizar
        OutlinedTextField(
            value = id,
            onValueChange = { id = it },
            label = { Text("ID (para atualizar/deletar)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 🔘 Botões de ação
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = {
                if (name.isNotBlank() && email.isNotBlank()) {
                    viewModel.addUser(id.toIntOrNull() ?: 0, name, email)
                    id = ""
                    name = ""
                    email = ""
                }
            }) {
                Text("Inserir")
            }

            Button(onClick = {
                val user = UserEntity(
                    id = id.toIntOrNull() ?: 0,
                    name = name,
                    email = email
                )
                viewModel.updateUser(user)
            }) {
                Text("Atualizar")
            }

            Button(onClick = {
                val user = UserEntity(
                    id = id.toIntOrNull() ?: 0,
                    name = name,
                    email = email
                )
                viewModel.deleteUser(user)
            }) {
                Text("Deletar")
            }
        }

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            thickness = DividerDefaults.Thickness,
            color = DividerDefaults.color
        )

        // 📋 Exibe a lista de usuários
        LazyColumn {
            items(users) { user ->
                Text(
                    text = "ID: ${user.id} | ${user.name} (${user.email})",
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}
