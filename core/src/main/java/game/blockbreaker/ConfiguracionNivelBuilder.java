
package game.blockbreaker;

public class ConfiguracionNivelBuilder {

    private int filas = 3;
    private int filasResistentes = 1;
    private int anchoBloque = 70;
    private int altoBloque = 26;

    public ConfiguracionNivelBuilder filas(int filas) {
        this.filas = filas;
        return this;
    }

    public ConfiguracionNivelBuilder filasResistentes(int filasResistentes) {
        this.filasResistentes = filasResistentes;
        return this;
    }

    public ConfiguracionNivelBuilder anchoBloque(int anchoBloque) {
        this.anchoBloque = anchoBloque;
        return this;
    }

    public ConfiguracionNivelBuilder altoBloque(int altoBloque) {
        this.altoBloque = altoBloque;
        return this;
    }

    public ConfiguracionNivel build() {
        if (filas <= 0 || anchoBloque <= 0 || altoBloque <= 0 ||
            filasResistentes < 0 || filasResistentes > filas) {
            throw new IllegalArgumentException(
                "Configuracion de nivel invalida"
            );
        }

        return new ConfiguracionNivel(
            filas, filasResistentes, anchoBloque, altoBloque
        );
    }
}
