package game.blockbreaker;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PingBall implements Renderizable {
	    private int x;
	    private int y;
	    private int size;
	    private float xSpeed;
	    private float ySpeed;
	    private Color color = Color.WHITE;
	    private boolean estaQuieto;
	    private BallMovement estrategia;
	    
	    public PingBall(int x, int y, int size, int xSpeed, int ySpeed, boolean iniciaQuieto) {
	        this.x = x;
	        this.y = y;
	        this.size = size;
	        this.xSpeed = xSpeed;
	        this.ySpeed = ySpeed;
	        estaQuieto = iniciaQuieto;
	        estrategia = new NormalMovement();
	    }
	    
	    public boolean estaQuieto() {
	    	return estaQuieto;
	    }
	    public void setEstaQuieto(boolean bb) {
	    	estaQuieto=bb;
	    }
	    public void setXY(int x, int y) {
	    	this.x = x;
	        this.y = y;
	    }
	    public int getY() {return y;}

	    public void setEstrategia(BallMovement estrategia) {
	    	if (estrategia != null) {
	    		this.estrategia = estrategia;
	    	}
	    }

	    
	    @Override
	    public void draw(ShapeRenderer shape){
	        shape.setColor(color);
	        shape.circle(x, y, size);
	    }
	    
	    public void update() {
	        if (estaQuieto) return;

	        x += estrategia.calcularDesplazamiento(xSpeed);
	        y += estrategia.calcularDesplazamiento(ySpeed);

	        if (x-size < 0 || x+size > Gdx.graphics.getWidth()) {
	            xSpeed = -xSpeed;
	        }

	        if (y+size > Gdx.graphics.getHeight()) {
	            ySpeed = -ySpeed;
	        }
	    }
	    
	    public void checkCollision(Paddle paddle) {
	        if(collidesWith(paddle)){
	            color = Color.GREEN;
	            
	            // Modificacion para evitar bug de rebotes multiples en el paddle, sobretodo a gran velocidad
	            if (ySpeed < 0) {
	                this.y = paddle.getY() + paddle.getHeight() + 9;
	                ySpeed = -ySpeed;
	            }
	        }
	        else{
	            color = Color.WHITE;
	        }
	    }
	    
	    private boolean collidesWith(Paddle pp) {

	    	boolean intersectaX = (pp.getX() + pp.getWidth() >= x-size) && (pp.getX() <= x+size);
	        boolean intersectaY = (pp.getY() + pp.getHeight() >= y-size) && (pp.getY() <= y+size);		
	    	return intersectaX && intersectaY;
	    }
	    
	    public void checkCollision(Block block) {
	        if(collidesWith(block)){
	            ySpeed = - ySpeed;
	            
	            //Se cambió el comportamiento del gope por el método abtracto recibirGolpe, para generar distintos comportamientos
	            block.recibirGolpe();
	        }
	    }
	    private boolean collidesWith(Block bb) {

	    	//Se modificó el acceso de datos a través de los gets correspondientes
	    	boolean intersectaX = (bb.getX() + bb.getWidth() >= x-size) && (bb.getX() <= x+size);
	        boolean intersectaY = (bb.getY() + bb.getHeight() >= y-size) && (bb.getY() <= y+size);		
	    	return intersectaX && intersectaY;
	    }
	    
	}
