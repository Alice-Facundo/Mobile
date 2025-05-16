package com.example.cadapp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FormularioCadastro()
        }
    }
}

@Composable
fun FormularioCadastro() {
    var nome by remember { mutableStateOf("") }
    var sobrenome by remember { mutableStateOf("") }
    var dataNascimento by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var celular by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var endereco by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf("") }
    var bairro by remember { mutableStateOf("") }
    var cidade by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf("") }
    var cep by remember { mutableStateOf("") }
    var nacionalidade by remember { mutableStateOf("") }
    var profissao by remember { mutableStateOf("") }

    fun limparCampos() {
        nome = ""
        sobrenome = ""
        dataNascimento = ""
        genero = ""
        telefone = ""
        celular = ""
        email = ""
        endereco = ""
        numero = ""
        bairro = ""
        cidade = ""
        estado = ""
        cep = ""
        nacionalidade = ""
        profissao = ""
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(value = nome, onValueChange = { nome = it }, label = { Text("Nome") })
        TextField(value = sobrenome, onValueChange = { sobrenome = it }, label = { Text("Sobrenome") })
        TextField(value = dataNascimento, onValueChange = { dataNascimento = it }, label = { Text("Data de nascimento") })
        TextField(value = genero, onValueChange = { genero = it }, label = { Text("Gênero") })
        TextField(value = telefone, onValueChange = { telefone = it }, label = { Text("Telefone") })
        TextField(value = celular, onValueChange = { celular = it }, label = { Text("Celular") })
        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        TextField(value = endereco, onValueChange = { endereco = it }, label = { Text("Endereço") })
        TextField(value = numero, onValueChange = { numero = it }, label = { Text("Número") })
        TextField(value = bairro, onValueChange = { bairro = it }, label = { Text("Bairro") })
        TextField(value = cidade, onValueChange = { cidade = it }, label = { Text("Cidade") })
        TextField(value = estado, onValueChange = { estado = it }, label = { Text("Estado") })
        TextField(value = cep, onValueChange = { cep = it }, label = { Text("CEP") })
        TextField(value = nacionalidade, onValueChange = { nacionalidade = it }, label = { Text("Nacionalidade") })
        TextField(value = profissao, onValueChange = { profissao = it }, label = { Text("Profissão") })

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { }) {
                Text("Enviar")
            }
            Button(onClick = { limparCampos() }) {
                Text("Limpar")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFormularioCadastro() {
    FormularioCadastro()
}
