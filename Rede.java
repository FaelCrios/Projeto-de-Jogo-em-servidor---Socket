import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Rede {
  DataOutputStream os = null;
  Socket socket = null;
  DataInputStream is = null;

  Rede() {
    try {
      socket = new Socket("127.0.0.1", 50000);
      os = new DataOutputStream(socket.getOutputStream());
      is = new DataInputStream(socket.getInputStream());
    } catch (UnknownHostException e) {
      Mensagem.erroFatalExcecao("Erro com o servidor", e);
    } catch (IOException e) {
      Mensagem.erroFatalExcecao("Erro ao se conectar com o servidor", e);
    }
  }

  public void enviaComandosEDadosDoJogador(int estado) {
    try {
      os.writeInt(estado);
      os.flush();
    } catch (IOException e) {
      Mensagem.erroFatalExcecao("Erro de envio de dados", e);
    }
  }

  public void recebeSituacao(Jogador jogador) {
    try {
      jogador.posicao(is.readInt());
      jogador.posicaoCima((is.readInt()));
      jogador.estado(is.readInt());
      jogador.inverte(is.readBoolean());
    } catch (IOException e) {
      Mensagem.erroFatalExcecao("Programa finalizado por ERRO", e);
    }
  }

  public void recebeDadosIniciais(Ambiente ambiente) {
    try {
      int largJogo = is.readInt();
      int altuJogo = is.readInt();
      ambiente.jogador.posicaoXY(50, altuJogo - 15);
      ambiente.adversario.posicaoXY(200, altuJogo - 15);
      ambiente.ajustaTamanho(largJogo, altuJogo);
    } catch (IOException e) {
      Mensagem.erroFatalExcecao(e);
    }
  }

}
