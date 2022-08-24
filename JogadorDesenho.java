import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


class JogadorDesenho extends Jogador {
  JFrame janelaDona;
  Image[] imagens = new Image[9];

  int pequenoMovimento = 0;

  JogadorDesenho(JFrame janelaDona) {
    this.janelaDona = janelaDona;
    carregaImagens();
  }

  JogadorDesenho(int x, int y, JFrame janelaDona) {
    super(x, y);
    this.janelaDona = janelaDona;
    carregaImagens();

  }

  void carregaImagens() {
    try {
      imagens[PARADO] = ImageIO.read(new File("PigFinalParado.png"));
      imagens[ANDA0] = ImageIO.read(new File("PigFinal.png"));
      imagens[ANDA1] = ImageIO.read(new File("PigFinalInvertido.png"));
      imagens[ANDA2] = ImageIO.read(new File("PigFinalCima.png"));
      imagens[ANDA3] = ImageIO.read(new File("PigFinalBaixo.png"));


    } catch (IOException e) {
      Mensagem.erroFatalExcecao(janelaDona, "ERRO: as imagens não puderam ser carregadas", e);
    }
  }

  void desenha(Graphics g) {
    int larg = rectColisao.width;
    int x = rectColisao.x;
    int y = rectColisao.y;
    if (invertido) {
      larg = -larg;
      x -= larg;
      y-= larg;
    }
    g.drawImage(imagens[estado], x, y, larg, rectColisao.height,janelaDona);

  }
}
