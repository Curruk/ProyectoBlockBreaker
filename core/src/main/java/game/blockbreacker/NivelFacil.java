
package game.blockbreacker;

public class NivelFacil extends Nivel {

    @Override
    protected int obtenerFilas() {
        return 3;
    }

    @Override
    protected Block crearBloque(int fila, int x, int y,
                                int ancho, int alto) {

        if (fila == 0) {
            return new HardBlock(x, y, ancho, alto);
        }

        return new NormalBlock(x, y, ancho, alto);
    }
}

