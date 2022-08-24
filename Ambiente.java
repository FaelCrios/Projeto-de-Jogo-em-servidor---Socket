import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Ambiente extends JPanel {
  static final int FUNDO = 0;
  static final int ARBUSTO = 1;
  static final int GRAMA = 2;

  int largJogo = 800;
  int altuJogo = 600;
  JogadorDesenho jogador;
  AdversarioDesenho adversario;
  Image[] imagens = new Image[3];

  Ambiente(JogoCliente jogo) {
    carregaImagens();
    jogador = new JogadorDesenho(jogo);
    adversario = new AdversarioDesenho(jogo);
    adversario.inverte(true);
  }

  void carregaImagens() {
    try {
      imagens[FUNDO] = ImageIO.read(new File("chiqueiroFinal.png"));

    } catch (IOException e) {
      Mensagem.erroFatalExcecao(this, "ERRO: as imagens não puderam ser carregadas", e);
    }
  }

  public void ajustaTamanho(int largJogo, int altuJogo) {
    this.largJogo = largJogo;
    this.altuJogo = altuJogo;
    setPreferredSize(new Dimension(largJogo, altuJogo));
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.clipRect(5, 5, getWidth() - 10, getHeight() - 10);

    g.drawImage(imagens[FUNDO], 0, 0, largJogo, altuJogo, this);

    jogador.desenha(g);
    adversario.desenha(g);


    Toolkit.getDefaultToolkit().sync();
  }

}