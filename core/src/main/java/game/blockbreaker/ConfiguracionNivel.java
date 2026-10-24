
package game.blockbreaker;

public class ConfiguracionNivel {

    private final int filas;
    private final int filasResistentes;
    private final int anchoBloque;
    private final int altoBloque;

    public ConfiguracionNivel(int filas, int filasResistentes, int anchoBloque, int altoBloque) {
        this.filas = filas;
        this.filasResistentes = filasResistentes;
        this.anchoBloque = anchoBloque;
        this.altoBloque = altoBloque;
    }

    public int getFilas() {
        return filas;
    }

    public int getFilasResistentes() {
        return filasResistentes;
    }

    public int getAnchoBloque() {
        return anchoBloque;
    }

    public int getAltoBloque() {
        return altoBloque;
    }
}
