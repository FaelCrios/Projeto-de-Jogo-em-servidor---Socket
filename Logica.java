public class Logica {
  Jogo jogo;
  Jogador jogador;
  Jogador adversario;

  Logica(Jogo jogo) { 
    this.jogo = jogo;
    jogador = this.jogo.jogadores[0];
    adversario = this.jogo.jogadores[1];
  }

  public void executa() {
    verificaAnda(jogador, 10);
    verificaAnda(adversario, -10);
  }

  void verificaAnda(Jogador jogador, int tamPasso) {
    if (jogador.estado == Jogador.ANDA0) {
      jogador.posicao(jogador.x + tamPasso);
    } else if (jogador.estado == Jogador.ANDA1) {
      jogador.posicao(jogador.x - tamPasso);
    }else if (jogador.estado == Jogador.ANDA2) {
      jogador.posicaoCima(jogador.x + tamPasso);
    }else if (jogador.estado == Jogador.ANDA3) {
      jogador.posicaoCima(jogador.x - tamPasso);
    }
  }



}  
