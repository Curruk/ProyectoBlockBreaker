package game.blockbreaker;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;

public abstract class Block implements Renderizable {
    private int x,y,width,height;
    private Color cc;
    private boolean destroyed;
    
    public  Block(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        destroyed = false;
        Random r = new Random(x + y);
        
       cc = new Color(0.1f+r.nextFloat(), r.nextFloat(), r.nextFloat(), 10);
  
    }
    
    @Override
    public void draw(ShapeRenderer shape){
    	shape.setColor(cc);
        shape.rect(x, y, width, height);
    }
    
    //método abstracto
    public abstract void recibirGolpe();
    
    //Setters (corrcción feedback, ahora se hace copia)
    public void setColor(Color color) {
    	if (color != null) {
    		this.cc = new Color(color);
    	}
    }
    
    public void setDestroyed(boolean destroyed) {
    	this.destroyed = destroyed;
    }
    
    //Getters
    public int getX() { return x;}
    
    public int getY() { return y;}
    
    public int getWidth() { return width;}
    
    public int getHeight() { return height;}
    
    public boolean isDestroyed() { return destroyed;}
    
    //Correcion de la función get, también hacemos una copia ahora de forma defensiva
    public Color getColor() { return new Color(cc);}
    
    
    
}
