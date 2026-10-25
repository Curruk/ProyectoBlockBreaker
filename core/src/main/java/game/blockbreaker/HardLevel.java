
package game.blockbreaker;

public class HardLevel extends Level {

    @Override
    protected Block crearBloque(int fila, int x, int y, int ancho, int alto) {
    	//Full hardblocks
    	return new HardBlock(x, y, ancho, alto);
    }
}
