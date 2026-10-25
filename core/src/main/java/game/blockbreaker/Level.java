
package game.blockbreaker;

import java.util.ArrayList;

public abstract class Level {

	//TM
    public final ArrayList<Block> crearNivel( int ancho, int alto, LevelConfiguration config) {

        ArrayList<Block> bloques = new ArrayList<>();

        int anchoBloque = config.getAnchoBloque();
        int altoBloque = config.getAltoBloque();
        int filas = config.getFilas();
        
        int y = alto - 40;

        for (int fila = 0; fila < filas; fila++) {
            y -= altoBloque + 10;

            for (int x = 5; x < ancho; x += anchoBloque + 10) {

            	Block bloque;

            	// Rango para HardBlocks
            	if (fila < config.getFilasResistentes()) { 
            		bloque = new HardBlock(x, y, anchoBloque, altoBloque);
            	} else {
            	    bloque = crearBloque(fila, x, y, anchoBloque, altoBloque);
            	}

                bloques.add(bloque);
            }
        }

        return bloques;
    }

    protected abstract Block crearBloque(int fila, int x, int y, int ancho, int alto);
}
