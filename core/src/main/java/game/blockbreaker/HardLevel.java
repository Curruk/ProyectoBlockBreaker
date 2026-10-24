
package game.blockbreaker;

public class HardLevel extends Level {

    @Override
    protected int obtenerFilas() {
        return 5;
    }

    @Override
    protected Block crearBloque(int fila, int x, int y,
                                int ancho, int alto) {

        if (fila < 2) {
            return new HardBlock(x, y, ancho, alto);
        }

        return new NormalBlock(x, y, ancho, alto);
    }
}
