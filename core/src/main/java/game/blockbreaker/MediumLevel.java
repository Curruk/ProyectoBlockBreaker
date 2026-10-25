package game.blockbreaker;

public class MediumLevel extends Level {
    
	@Override
    protected Block crearBloque(int fila, int x, int y, int ancho, int alto) {
		
		//nivel con filas intercaldas
		if (fila % 2 == 0) {
            return new HardBlock(x, y, ancho, alto);
        } else {
            return new NormalBlock(x, y, ancho, alto);
        }
    }
}