
package game.blockbreaker;

public class FastMovement implements BallMovement {

    @Override
    public int calcularDesplazamiento(int velocidad) {
        return velocidad * 2;
    }

}
