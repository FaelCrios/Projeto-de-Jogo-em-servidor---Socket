import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

// INTEGRANTES DO PROJETO:
// RAFAEL COLIN RIOS RA: 211021946
// MURILO ZANARDO RA: 211021253

public class JogoCliente extends JFrame {
  Rectangle[] rect;
  Rede rede = new Rede();
  Ambiente ambiente;

  JogoCliente() {
    super("Pig Simulator");
    ambiente = new Ambiente(this);
    rede.recebeDadosIniciais(ambiente);
    
    iniciaJanela();
    tratamentoTeclas();
    controleDoJogo();
  }

  void iniciaJanela() {    
    add(ambiente);
    pack();
    setResizable(false);
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }
  
  void tratamentoTeclas() {
    addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
          rede.enviaComandosEDadosDoJogador(Jogador.ANDA0);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
          rede.enviaComandosEDadosDoJogador(Jogador.ANDA1);
        } else if (e.getKeyCode() == KeyEvent.VK_UP){
          rede.enviaComandosEDadosDoJogador(Jogador.ANDA2);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN){
          rede.enviaComandosEDadosDoJogador(Jogador.ANDA3);
        }
      }
      public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
          rede.enviaComandosEDadosDoJogador(Jogador.PARADO);
        } else if( e.getKeyCode() == KeyEvent.VK_LEFT){
          rede.enviaComandosEDadosDoJogador(Jogador.PARADO);
        } else if( e.getKeyCode() == KeyEvent.VK_UP){
          rede.enviaComandosEDadosDoJogador(Jogador.PARADO);
        } else if( e.getKeyCode() == KeyEvent.VK_DOWN){
          rede.enviaComandosEDadosDoJogador(Jogador.PARADO);
        }

      }
    });
  }
  
  void controleDoJogo() {
    new Thread(new Runnable() {
      public void run() {
        while (true) {
          rede.recebeSituacao(ambiente.jogador);
          rede.recebeSituacao(ambiente.adversario);
          ambiente.repaint();
        }
      }
    }).start();
  }
  
  static public void main(String[] args) {
    new JogoCliente();
  }
}
