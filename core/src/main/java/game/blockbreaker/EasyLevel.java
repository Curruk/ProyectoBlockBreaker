
package game.blockbreaker;

public class EasyLevel extends Level {

	@Override
	protected Block crearBloque(int fila, int x, int y, int ancho, int alto) {

		// En este nivel hacemos todos los blockes normales
		return new NormalBlock(x, y, ancho, alto);
	}
}

