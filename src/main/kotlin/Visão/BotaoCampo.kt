package Visão

import Modelo.Campo
import Modelo.CampoEvento
import java.awt.Color
import java.awt.Font
import javax.swing.BorderFactory
import javax.swing.JButton
import javax.swing.SwingUtilities

private val COR_BG_NORMAL = Color(95,201,174)
private val COR_BG_MARCADO = Color(0,71,255)
private val COR_BG_EXPLOSAO = Color(253,0,2)
private val COR_TXT_VERDE = Color(0,100,0)

@Suppress("UNUSED_PARAMETER")
class BotaoCampo(private val campo: Campo) : JButton(){
    init {
        font = font.deriveFont(Font.BOLD)
        background = COR_BG_NORMAL
        isOpaque = true
        border = BorderFactory.createBevelBorder(0)
        addMouseListener(MouseCliqueListener(campo, { it.abrir() }, {it.alterarMarcacao()}))

        campo.onEvento(this::aplicarEstilo)
    }
    private fun aplicarEstilo(campo:Campo, evento: CampoEvento){
        when(evento){
            CampoEvento.EXPLOSAO -> aplicarEstiloExplodido()
            CampoEvento.ABERTURA -> aplicarEstiloAberto()
            CampoEvento.MARCACAO -> aplicarEstiloMarcado()
            else -> aplicarEstiloPadrao()
        }
        SwingUtilities.invokeLater {
            repaint()
            validate()
        }
    }
    private fun aplicarEstiloExplodido(){
        background = COR_BG_EXPLOSAO
        text = "x"
    }
    private fun aplicarEstiloAberto(){
        background = Color.DARK_GRAY
        border = BorderFactory.createLineBorder(Color.GRAY)

        foreground = when (campo.qtDeVizinhosMinados){
            1 -> COR_TXT_VERDE
            2 -> Color.BLUE
            3 -> Color.YELLOW
            4,5,6 -> Color.RED
            else -> Color.PINK
        }

        text = if(campo.qtDeVizinhosMinados > 0) campo.qtDeVizinhosMinados.toString() else ""
    }
    private fun aplicarEstiloMarcado(){
        background = COR_BG_MARCADO
        foreground = Color.BLACK
        text = "M"
    }
    private fun aplicarEstiloPadrao(){
        background = COR_BG_NORMAL
        border = BorderFactory.createBevelBorder(0)
        text = ""
    }
}