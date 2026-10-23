
package game.blockbreacker;

public class MovimientoRapido implements MovimientoPelota {

    @Override
    public int calcularDesplazamiento(int velocidad) {
        return velocidad * 2;
    }

}
