package entornosPractica10;

import java.util.Random;
import java.util.Scanner;

public class Juego {

	private static final int FILAS = 5;
	private static final int COLUMNAS = 5;
	private static final int TESOROS_MAX = 5;
	private static final int MAX_INTENTOS = 7;

	private Casilla[][] tablero;
	private int intentos;
	private int tesorosEncontrados;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Iniciando juego...");

		Casilla[][] tablero = new Casilla[FILAS][COLUMNAS];

		// Inicializaci�n del tablero
		iniciarTablero(tablero);

		// Colocar tesoros mal (puede repetir posiciones)
		colocarTesoros(tablero);

		int intentos = 0;
		int encontrados = 0;
		while (intentos <= MAX_INTENTOS) {
			int f = pedirFila(sc);
			int c = pedirColumna(sc);

			tablero[f][c].visitada = true;

			if (verificarCasilla(tablero, f, c)) {
				encontrados++;
			}

			intentos++;
			// Imprime el tablero
			imprimirTablero(tablero);

			if (encontrados == TESOROS_MAX) {
				System.out.println("Has ganado");
				break;
			}
		}
		if (intentos > MAX_INTENTOS) {
			System.out.println("Has perdido");
		}
	}

	/**
	 * @param tablero
	 */
	public static void imprimirTablero(Casilla[][] tablero) {
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				if (tablero[i][j].visitada) {
					if (tablero[i][j].tesoro) {
						System.out.print(" O ");
					} else {
						System.out.print(" X ");
					}
				} else {
					System.out.print(" - ");
				}
			}
			System.out.println();
		}
	}

	/**
	 * @param tablero
	 * @param f
	 * @param c
	 * @return
	 */
	public static boolean verificarCasilla(Casilla[][] tablero, int f, int c) {
		if (tablero[f][c].tesoro) {
			System.out.println("¡Tesoro encontrado!");
			return true;
		} else {
			System.out.println("Nada aquí...");
			return false;
		}
	}

	/**
	 * @param tablero
	 */
	public static void colocarTesoros(Casilla[][] tablero) {
		Random r = new Random();
		int tesorosColocados = 0;
		while (tesorosColocados < TESOROS_MAX) {
			int f = r.nextInt(FILAS);
			int c = r.nextInt(COLUMNAS);
			if (!tablero[f][c].tesoro) {
				tablero[f][c].tesoro = true;
				tesorosColocados++;
			}
		}
	}

	/**
	 * @param tablero
	 */
	public static void iniciarTablero(Casilla[][] tablero) {
		for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				tablero[i][j] = new Casilla();
			}
		}
	}

	public Casilla[][] getTablero() {
		return tablero;
	}

	public void setTablero(Casilla[][] tablero) {
		this.tablero = tablero;
	}

	public int getIntentos() {
		return intentos;
	}

	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}

	public int getTesorosEncontrados() {
		return tesorosEncontrados;
	}

	public void setTesorosEncontrados(int tesorosEncontrados) {
		this.tesorosEncontrados = tesorosEncontrados;
	}

	public static int getFilas() {
		return FILAS;
	}

	public static int getColumnas() {
		return COLUMNAS;
	}

	public static int getTesorosMax() {
		return TESOROS_MAX;
	}

	public static int getMaxIntentos() {
		return MAX_INTENTOS;
	}

	/**
	 * @param tablero
	 */
	public static void LimpiarTablero(Casilla[][] tablero) {
		for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				tablero[i][j] = new Casilla();
			}
		}
	}

	/**
	 * @param sc
	 * @return
	 */
	public static int pedirColumna(Scanner sc) {
		int c;
		do {
			System.out.print("Introduce columna: ");
			c = sc.nextInt();
		} while (c < 0 || c >= COLUMNAS);
		return c;
	}

	/**
	 * @param sc
	 * @return
	 */
	public static int pedirFila(Scanner sc) {
		int f;
		do {
			System.out.print("Introduce columna: ");
			f = sc.nextInt();
		} while (f < 0 || f >= FILAS);
		return f;
	}
}
