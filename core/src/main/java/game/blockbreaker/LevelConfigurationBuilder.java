
package game.blockbreaker;

public class LevelConfigurationBuilder {

    private int filas = 3;
    private int filasResistentes = 1;
    private int anchoBloque = 70;
    private int altoBloque = 26;

    public LevelConfigurationBuilder filas(int filas) {
        this.filas = filas;
        return this;
    }

    public LevelConfigurationBuilder filasResistentes(int filasResistentes) {
        this.filasResistentes = filasResistentes;
        return this;
    }

    public LevelConfigurationBuilder anchoBloque(int anchoBloque) {
        this.anchoBloque = anchoBloque;
        return this;
    }

    public LevelConfigurationBuilder altoBloque(int altoBloque) {
        this.altoBloque = altoBloque;
        return this;
    }

    public LevelConfiguration build() {
        if (filas <= 0 || anchoBloque <= 0 || altoBloque <= 0 ||
            filasResistentes < 0 || filasResistentes > filas) {
            throw new IllegalArgumentException(
                "Configuracion de nivel invalida"
            );
        }

        return new LevelConfiguration(
            filas, filasResistentes, anchoBloque, altoBloque
        );
    }
}
