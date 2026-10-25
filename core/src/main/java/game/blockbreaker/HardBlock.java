package game.blockbreaker;

import com.badlogic.gdx.graphics.Color;

public class HardBlock extends Block {
	
	//Creamos un contador de vidas para que el bloque cumpla la funcion de tener mas de una vida
	private int vidas;
	
	public HardBlock(int x, int y, int width, int height) {
		super(x, y, height, width);
		
		//El bloque nace con 3 vidas
		this.vidas = 3;
		
		//El bloque comienza con color rojo
		this.setColor(Color.RED);
	}
	
	@Override
	public void recibirGolpe() {
		//Al recibir un golpe restamos una vida
		vidas--;
		
		//Cambiamos de color el bloque a medida que va perdiendo vidas
		if (vidas == 2) { this.setColor(Color.ORANGE); }
		else if (vidas == 1) { this.setColor(Color.YELLOW); }
		else if (vidas <= 0) {this.setDestroyed(true); }
	}
}
