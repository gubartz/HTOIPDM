package br.edu.ifsp.hto.navegacao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.ui.Modifier
import br.edu.ifsp.hto.navegacao.navigation.NavigationNavHost
import br.edu.ifsp.hto.navegacao.ui.theme.NavegacaoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavegacaoTheme {
                Box(
                    modifier = Modifier
                        .safeDrawingPadding()
                ) {
                    NavigationNavHost()
                }
            }
        }
    }
}

