package br.edu.ifsp.hto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.ifsp.hto.ui.theme.Programa01_layoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Programa01_layoutTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GreetingCard(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


// 1. O código abaixo é uma annotation que indica que esta função é um componente.
// composable
@Composable
fun GreetingCard(modifier: Modifier = Modifier) {
    // 2. Adicionar o primeiro elemento, que é uma coluna.
    Column(
        modifier = Modifier
            .fillMaxSize() // Faz com que a coluna ocupe o tamanho máximo disponível referente ao
            // objeto que está inserido. Como este é o primeiro elemento, refere-se
            // à tela.
            .padding(20.dp), // Adiciona espaçamento no topo, embaixo, esquerda e direita
        horizontalAlignment = Alignment.CenterHorizontally, //define o alinhamento horizontal dos
        // elementos internos (filho) à coluna.
        verticalArrangement = Arrangement.Center // define o alinhamento vertical dos elementos
        // internos (filho) à coluna.
    ) {
        // 3. Carrega uma imagem da pasta res/drawable
        val picture = painterResource(R.drawable.picture)
        Image(
            modifier = Modifier
                .size(100.dp), // Define o tamanho do elemento (no caso uma figura).
            painter = picture, // Define a figura, que é a variável picture.
            contentDescription = "Foto", // Define uma descrição textual do elemento.
            // Utilizado para acessibilidade.
        )
        // 4. Adicionar dois textos.
        Text("Jen Doe")
        Text("System Developer")

        // 5. Criar uma coluna, para manter as descrições do cartão de apresentação.
        Column(
            modifier = Modifier
                .padding(top = 30.dp),
        ) {
            // 6. Dentro da coluna é definida uma linha.
            // Esta linha mantém o dado de telefone.
            Row(
                modifier = Modifier
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.Start, // Define que os elementos vão aparecer
                // no início da linha
            ) {
                Icon(
                    imageVector = Icons.Filled.Call, // Utilização de um ícone de telefone.
                    contentDescription = "Telefone",
                    tint = Color.Black // Definição da cor do ícone.
                )
                Text("+55 19 9999-99999") // Texto do telefone.
            }
            // 7. Dentro da coluna é definida outra linha.
            // Esta linha mantém o ícone de compartilhar e o perfil do usuário.
            Row(
                modifier = Modifier
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = Icons.Filled.Share, // Use the desired icon here
                    contentDescription = "Share",
                    tint = Color.Black,
                )
                Text("@Developer")
            }
            // 8. Dentro da coluna é definida uma última linha.
            // Esta linha mantém o ícone e as informações de e-mail.
            Row(
                modifier = Modifier
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = Icons.Filled.Mail,
                    contentDescription = "E-mail",
                    tint = Color.Black,
                )
                Text("dev@email.com")
            }
        }

    }

}

// 9. Define uma prévia do elemento GreetingCard
@Preview(showBackground = true)
@Composable
fun GreetingCardPreview() {
    Programa01_layoutTheme {
        GreetingCard()
    }
}