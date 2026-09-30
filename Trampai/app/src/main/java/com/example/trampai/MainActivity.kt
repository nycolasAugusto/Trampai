package com.example.trampai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.trampai.ui.theme.TrampaiTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            TrampaiTheme {

                Scaffold(
                        modifier = androidx.compose.ui.Modifier.fillMaxSize()
                ) { innerPadding ->

                    Box(modifier = androidx.compose.ui.Modifier.padding(innerPadding)) {

                        var telaAtual by remember {
                            mutableIntStateOf(1)
                        }

                        when (telaAtual) {

                            1 -> TelaUm(
                                    irParaProxima = {
                                        telaAtual = 2
                                    },
                                    irParaBusca = {
                                        telaAtual = 4
                                    },
                                    irParaRelampago = {
                                        telaAtual = 5
                                    },
                                    irParaPerfil =
                                        { telaAtual = 6
                                    }
                            )

                            2 -> TelaDois(
                                    voltarParaInicio = {
                                        telaAtual = 1
                                    },
                                    irParaRelampago = {
                                        telaAtual = 5
                                    }
                            )

                            3 -> TelaTres(
                                    voltarParaInicio = {
                                        telaAtual = 1
                                    }
                            )

                            4 -> TelaBuscaServicos(
                                    voltarParaInicio = {
                                        telaAtual = 1
                                    }
                            )

                            5 -> TelaServicoRelampago(
                                    voltarParaInicio = {
                                        telaAtual = 1
                                    }
                            )

                            6 -> TelaPerfil(
                                voltarParaInicio = { telaAtual = 1 }
                            )
                        }
                    }
                }
            }
        }
    }
}
