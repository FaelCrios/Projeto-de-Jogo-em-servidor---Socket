import java.awt.Rectangle;

class Tamanho {
  int larg, altu;

  Tamanho(int largura, int altura) {
    larg = largura;
    altu = altura;
  }
}

public class Jogador {
  static final int PARADO = 0;
  static final int ANDA0 = 1;
  static final int ANDA1 = 2;
  static final int ANDA2 = 3;
  static final int ANDA3 = 4;


  int x, y;
  
  Rectangle rectColisao;
  
  boolean invertido = false;
  int estado = PARADO;
  
  Tamanho[] tamanho = new Tamanho[9];
  
  int dxCentroJogador = 32;
  int dyCentroJogador = 32;
  
  int pontos = 0;

  Jogador() {
    inicia();
  }

  Jogador(int x, int y) {
    inicia();
    posicaoXY(x, y);
  }

  void inicia() {
    defineTamanhoJogador();
    rectColisao = new Rectangle(0, 0, tamanho[estado].larg, tamanho[estado].altu);
    estado(PARADO);
  }

  void defineTamanhoJogador() {
    tamanho[PARADO] = new Tamanho(80, 80);
    tamanho[ANDA0] = new Tamanho(80, 80);
    tamanho[ANDA1] = new Tamanho(80, 80);
    tamanho[ANDA2] = new Tamanho(80, 80);
    tamanho[ANDA3] = new Tamanho(80, 80);


  }

  void inverte(boolean invertido) {
    this.invertido = invertido;
    posicao(x);
  }

  void estado(int estado) {
    this.estado = estado;
    rectColisao.setSize(tamanho[estado].larg, tamanho[estado].altu);
    posicaoXY(x, y);
  }

  void posicaoXY(int x, int y) {
    this.y = y;
      if(invertido){
        rectColisao.setLocation(y - tamanho[estado].larg + dxCentroJogador, rectColisao.y);
      }else {
        rectColisao.setLocation(x - dxCentroJogador, rectColisao.y);
      }
      rectColisao.y = y - rectColisao.height;
    posicao(x);
    
  }

 void posicao(int x) {
   this.x = x;
   if (invertido) {
     rectColisao.setLocation(x - tamanho[estado].larg + dxCentroJogador, rectColisao.y);
   } else {
     rectColisao.setLocation(x - dxCentroJogador, rectColisao.y);
   }
 }
 
  void posicaoCima(int y){
    this.y = y;
    if (invertido) {
      rectColisao.setLocation(y - tamanho[estado].larg, rectColisao.y);
    } else {
      rectColisao.setLocation(y - dxCentroJogador, rectColisao.y);
    }
  }
  


  


  // boolean verificaColisao(Jogador j) {
  //   return rectColisao.intersects(j.rectColisao);
  // }
}
