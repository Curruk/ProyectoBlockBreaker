
package game.blockbreaker;

import java.util.ArrayList;

public abstract class Level {

    // Template Method original
    public final ArrayList<Block> crearNivel(int ancho, int alto) {
        return crearNivel(
            ancho, alto,
            new ConfiguracionNivelBuilder()
                .filas(obtenerFilas())
                .build()
        );
    }

    // Template Method que utiliza Builder
    public final ArrayList<Block> crearNivel( int ancho, int alto, ConfiguracionNivel config) {

        ArrayList<Block> bloques = new ArrayList<>();

        int anchoBloque = config.getAnchoBloque();
        int altoBloque = config.getAltoBloque();
        int filas = config.getFilas();

        int y = alto;

        for (int fila = 0; fila < filas; fila++) {
            y -= altoBloque + 10;

            for (int x = 5; x < ancho; x += anchoBloque + 10) {

            	Block bloque;

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

    protected abstract int obtenerFilas();

    protected abstract Block crearBloque(
        int fila, int x, int y, int ancho, int alto
    );
}
