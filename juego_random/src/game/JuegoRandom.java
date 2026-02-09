package game;

import dao.JuegoDAO;
import java.util.Random;
import java.util.Scanner;

public class JuegoRandom {

    private final int numeroSecreto;
    private int intentos = 0;

    public JuegoRandom() {
        this.numeroSecreto = generarNumero8Digitos();
    }

    private int generarNumero8Digitos() {
        Random r = new Random();
        return 10000000 + r.nextInt(90000000);
    }

    public void jugar() {
        Scanner sc = new Scanner(System.in);
        JuegoDAO dao = new JuegoDAO();
        int numeroJugador;
        String resultado = "PERDIO";

        System.out.println("🎮 Adivina el número de 8 dígitos");

        do {
            System.out.print("Ingresa tu número: ");
            numeroJugador = sc.nextInt();
            intentos++;

            if (numeroJugador == numeroSecreto) {
                resultado = "GANO";
                System.out.println("✅ ¡Correcto!");
                break;
            } else {
                System.out.println("❌ Incorrecto, intenta de nuevo.");
            }
        } while (true);

        try {
            dao.guardarJuego(
                    numeroSecreto,
                    numeroJugador,
                    intentos,
                    resultado
            );
            System.out.println("💾 Resultado guardado en la BD.");
        } catch (Exception e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }
}
