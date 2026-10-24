
package game.blockbreaker;

public class EasyLevel extends Level {


	@Override
	protected Block crearBloque(int fila, int x, int y, int ancho, int alto) {

		NormalBlock bloque = new NormalBlock(x, y, ancho, alto);
		bloque.setColor(com.badlogic.gdx.graphics.Color.BLUE);

		return bloque;
	}
	@Override
	protected int obtenerFilas() {
	    return 3;
	}

}

