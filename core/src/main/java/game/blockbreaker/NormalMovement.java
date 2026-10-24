
package game.blockbreaker;

public class NormalMovement implements BallMovement {

    @Override
    public float calcularDesplazamiento(float velocidad) {
        return velocidad;
    }

}
