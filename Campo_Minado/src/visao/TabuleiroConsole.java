package visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.jogo.excecao.ExplocaoException;
import br.com.jogo.excecao.SairException;
import br.com.jogo.modelo.Tabuleiro;

public class TabuleiroConsole {

	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);

	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;

		executarJogo();
	}

	private void executarJogo() {
		try {
			boolean continuar = true;

			while (continuar) {
				cicloDoJogo();

				System.out.println(" \\_(õ.Õ)_/ Outra Partida ? (S/n) ");
				String resposta = entrada.nextLine();

				if ("n".equalsIgnoreCase(resposta)) {
					continuar = false;
					tabuleiro.reniciar();
				}
			}

		} catch (SairException e) {
			System.out.println("Tchau !! :D");
		} finally {
			entrada.close();
		}
	}

	private void cicloDoJogo() {
		try {

			while (!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro);

				String digitado = capturaValorDigitado("Digite (x , y): ");
				Iterator<Integer> xy = Arrays.stream(digitado.split(",")).map(e -> Integer.parseInt(e.trim()))
						.iterator();

				digitado = capturaValorDigitado("1 - Abrir ou 2 - (Des)Marca: ");
				if ("1".equalsIgnoreCase(digitado)) {
					tabuleiro.abrir(xy.next(), xy.next());
				} else if ("2".equalsIgnoreCase(digitado)) {
					tabuleiro.abrir(xy.next(), xy.next());
				}
			}
			System.out.println(tabuleiro);
			System.out.println("Você Ganhou!!");
		} catch (ExplocaoException e) {
			System.out.println(tabuleiro);
			System.out.println("Você Perdeu ! ");
		}
	}

	private String capturaValorDigitado(String texto) {
		System.out.print(texto);
		String digitado = entrada.nextLine();

		if ("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}

		return digitado;

	}

}
