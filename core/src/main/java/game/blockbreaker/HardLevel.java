
package game.blockbreaker;

public class HardLevel extends Level {

    @Override
    protected int obtenerFilas() {
        return 5;
    }


    @Override
    protected Block crearBloque(int fila, int x, int y, int ancho, int alto) {

    	NormalBlock bloque = new NormalBlock(x, y, ancho, alto);
    	bloque.setColor(com.badlogic.gdx.graphics.Color.PURPLE);

    	return bloque;
    }

}
