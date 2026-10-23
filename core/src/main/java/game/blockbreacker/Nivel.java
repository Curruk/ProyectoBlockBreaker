
package game.blockbreacker;

import java.util.ArrayList;

public abstract class Nivel {

    // Método plantilla: define los pasos para crear un nivel
    public final ArrayList<Block> crearNivel(int ancho, int alto) {

        ArrayList<Block> bloques = new ArrayList<>();

        int filas = obtenerFilas();

        int anchoBloque = 70;
        int altoBloque = 26;
        int y = alto;

        for (int fila = 0; fila < filas; fila++) {

            y -= altoBloque + 10;

            for (int x = 5; x < ancho; x += anchoBloque + 10) {

                Block bloque = crearBloque(
                    fila, x, y, anchoBloque, altoBloque
                );

                bloques.add(bloque);
            }
        }

        return bloques;
    }

    // Las clases hijas definen estos pasos
    protected abstract int obtenerFilas();

    protected abstract Block crearBloque(
        int fila, int x, int y, int ancho, int alto
    );
}