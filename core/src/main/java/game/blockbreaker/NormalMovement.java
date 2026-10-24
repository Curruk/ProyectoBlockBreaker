
package game.blockbreaker;

public class NormalMovement implements BallMovement {

    @Override
    public int calcularDesplazamiento(int velocidad) {
        return velocidad;
    }

}
