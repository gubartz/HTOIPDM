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
                GreetingCard()
            }
        }
    }
}


/*
TODO(1)O código abaixo é uma anotação (annotation) que indica que esta função é um composable.
 A interface gráfica no Android é definida de maneira declarativa (UI declarativa). Anteriormente
 a interface era definida em arquivos xml separados. No Android o framework Jetpack Compose permite
 a definição de UI de maneira declarativa.
 */
@Composable
fun GreetingCard() {
    /*
     TODO(2) Adicionar o primeiro elemento, que é uma coluna. Uma coluna faz com que todos elementos
      contidos em seu iteriro (dentro dela) apareçam um abaixo do outro na ordem em que estão
      definidos.
     */
    Column(
        modifier = Modifier
            /*
            TODO(3) O fillMaxSize() faz com que a coluna ocupe o tamanho máximo disponível referente
             ao objeto em que está inserida. Como este é o primeiro elemento, neste caso refere-se
             à tela total.
             */
            .fillMaxSize()
            /*
            TODO(4) O padding adiciona um espaçamento no topo, abaixo, a esquerda e a direita. O dp
             significa density-independent pixel e é uma unidade de medida usada para garantir que
             elementos da interface tenham tamanhos consistentes em diferentes tamanhos e densidades
              de tela.
             */
            .padding(20.dp),
        /*
        TODO(5) define o alinhamento horizontal dos elementos internos (filhoS) da coluna.
         */
        horizontalAlignment = Alignment.CenterHorizontally,
        /*
        TODO(6) define o alinhamento vertical dos elementos internos (filhos) da coluna.
         */
        verticalArrangement = Arrangement.Center
    ) {
        /*
        TODO(7) Carrega uma imagem do diretório res/drawable
         */
        val picture = painterResource(R.drawable.picture)
        Image(
            modifier = Modifier
                // TODO(8) Define o tamanho do elemento (no caso uma figura).
                .size(100.dp),
            // TODO(9) Define a figura, que está na variável picture.
            painter = picture,
            /*
            TODO(10) Define uma descrição para a figura. Utilizado para acessibilidade.
             */
            contentDescription = "Foto",
        )
        // TODO(11) Adicionar dois textos. Um para o nome da pessoa e outro para o cargo/função.
        Text("Jen Doe")
        Text("System Developer")

        // TODO(12) Criar uma coluna, para manter as descrições do cartão de apresentação.
        Column(
            modifier = Modifier
                .padding(top = 30.dp),
        ) {
            /*
             TODO(13) Dentro da coluna é definida uma linha. Esta linha mantém o dado de telefone.
             */
            Row(
                modifier = Modifier
                    .padding(bottom = 10.dp),
                /*
                TODO(14) Define que os elementos desta linha vão aparecer no início.
                 */
                horizontalArrangement = Arrangement.Start,
            ) {
                Icon(
                    // TODO(15) Utilização de um ícone de telefone.
                    imageVector = Icons.Filled.Call,
                    contentDescription = "Telefone",
                    // TODO(16) Definição da cor do ícone.
                    tint = Color.Black
                )
                // TODO(17) Texto do telefone.
                Text("+55 19 9999-99999")
            }
            /*
            TODO(18) Dentro da coluna é definida outra linha. Esta linha mantém o ícone de
             compartilhar e o perfil do usuário no Instagram.
             */
            Row(
                modifier = Modifier
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    // TODO(19) Definição do ícone de compartilhar.
                    imageVector = Icons.Filled.Share,
                    contentDescription = "Share",
                    tint = Color.Black,
                )
                Text("@Developer")
            }
            /*
            TODO(20) Dentro da coluna é definida uma última linha. Esta linha mantém o ícone e as
             informações de e-mail.
             */
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

/*
 TODO(20) Define uma prévia do elemento GreetingCard. Utilizado para verificar como está o layout
  do componente sem a necessidade de compilar e emular o aplicativo.
 */
@Preview(showBackground = true)
@Composable
fun GreetingCardPreview() {
    Programa01_layoutTheme {
        GreetingCard()
    }
}