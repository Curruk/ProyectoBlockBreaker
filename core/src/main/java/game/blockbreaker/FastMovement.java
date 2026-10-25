
package game.blockbreaker;

public class FastMovement implements BallMovement {

    @Override
    public float calcularDesplazamiento(float velocidad) {
        return velocidad * 1.25f;
    }

}
