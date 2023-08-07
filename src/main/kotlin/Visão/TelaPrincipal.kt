package Visão

import Modelo.Tabuleiro
import Modelo.TabuleiroEvento
import javax.swing.JFrame
import javax.swing.JOptionPane
import javax.swing.SwingUtilities

fun main() {
    TelaPrincipal()
}

class TelaPrincipal : JFrame(){
    private val tabuleiro = Tabuleiro(qtdeLinhas = 16, qtdeColunas = 30, qtdeMinas = 30)
    private val painelTabuleiro = PainelTabuleiro(tabuleiro)

    init {
        tabuleiro.onEvento(this::mostrarResultado)
        add(painelTabuleiro)

        setSize(690,430)
        setLocationRelativeTo(null)
        defaultCloseOperation = EXIT_ON_CLOSE
        title = "Campo Minado"
        isVisible = true
    }
    private fun mostrarResultado(evento: TabuleiroEvento){
        SwingUtilities.invokeLater {
            val msg = when(evento){
                TabuleiroEvento.VITORIA -> "Você Ganhou"
                TabuleiroEvento.DERROTA -> "Você Perdeu... :p"
            }
            JOptionPane.showMessageDialog(this, msg)
            tabuleiro.reiniciar()

            painelTabuleiro.repaint()
            painelTabuleiro.validate()
        }
    }
}