package game.blockbreaker;

public class MediumMovement implements BallMovement {

    @Override
    public float calcularDesplazamiento(float velocidad) {
        return velocidad * 1.15f;
    }
}
