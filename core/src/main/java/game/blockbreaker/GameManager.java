
package game.blockbreaker;

public class GameManager {

    // Única instancia del administrador
    private static final GameManager instancia = new GameManager();

    // Estado de la partida
    private int vidas;
    private int puntaje;
    private int nivel;

    // Constructor privado
    private GameManager() {
        vidas = 3;
        puntaje = 0;
        nivel = 1;
    }

    // Acceso a la única instancia
    public static GameManager getInstancia() {
        return instancia;
    }

    public int getVidas() {
        return vidas;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public int getNivel() {
        return nivel;
    }

    public void perderVida() {
        vidas--;
    }

    public void sumarPunto() {
        puntaje++;
    }

    public void siguienteNivel() {
        nivel++;
    }

    public void reiniciarPartida() {
        vidas = 3;
        puntaje = 0;
        nivel = 1;
    }
}
