import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


class AdversarioDesenho extends Jogador {
  JFrame janelaDona;
  Image[] imagens = new Image[9];

  int pequenoMovimento = 0;

  AdversarioDesenho(JFrame janelaDona) {
    this.janelaDona = janelaDona;
    carregaImagens();
  }

  AdversarioDesenho(int x, int y, JFrame janelaDona) {
    super(x, y);
    this.janelaDona = janelaDona;
    carregaImagens();

  }

  void carregaImagens() {
    try {
      imagens[PARADO] = ImageIO.read(new File("JavaliParado.png"));
      imagens[ANDA0] = ImageIO.read(new File("Javali.png"));
      imagens[ANDA1] = ImageIO.read(new File("JavaliInvertido.png"));
      imagens[ANDA2] = ImageIO.read(new File("JavaliCima.png"));
      imagens[ANDA3] = ImageIO.read(new File("JavaliBaixo.png"));
      
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
    }
    g.drawImage(imagens[estado], x, y, larg, rectColisao.height,janelaDona);

  }
}
