package game.blockbreacker;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

//Creamos está interfaz para poder implementarla en las clases que se renderizan
public interface Renderizable {
	
	void draw(ShapeRenderer shape);
}
