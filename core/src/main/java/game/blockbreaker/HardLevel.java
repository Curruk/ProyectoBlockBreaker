
package game.blockbreaker;

public class HardLevel extends Level {

    @Override
    protected Block crearBloque(int fila, int x, int y, int ancho, int alto) {
    	// Calculamos el número de columna (0, 1, 2...) según la coordenada X
        int columna = (x - 5) / (ancho + 10);
    	
        // patron de fortaleza: 
        // 2 de cada 3 bloques son HardBlock y 1 es NormalBlock en diagonal
        if ((fila + columna) % 3 != 0) {
            return new HardBlock(x, y, ancho, alto);
        } else {
            return new NormalBlock(x, y, ancho, alto);
        }
    }
}
