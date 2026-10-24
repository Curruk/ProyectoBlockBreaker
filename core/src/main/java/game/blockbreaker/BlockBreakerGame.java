package game.blockbreaker;

import java.util.ArrayList;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class BlockBreakerGame extends ApplicationAdapter {
    private OrthographicCamera camera;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private ShapeRenderer shape;
	private PingBall ball;
	private Paddle pad;
	private ArrayList<Block> blocks = new ArrayList<>();
	private GameManager gestor = GameManager.getInstancia();
    
		@Override
		public void create () {	
			camera = new OrthographicCamera();
		    camera.setToOrtho(false, 800, 480);
		    batch = new SpriteBatch();
		    font = new BitmapFont();
		    font.getData().setScale(3, 2);
		    gestor.reiniciarPartida();
		    crearBloques(2 + gestor.getNivel());
			
		    shape = new ShapeRenderer();
		    ball = new PingBall(Gdx.graphics.getWidth()/2-10, 41, 10, 5, 7, true);
		    pad = new Paddle(Gdx.graphics.getWidth()/2-50,40,100,10);
		       
		}


		public void crearBloques(int filas) {

			Level nivelActual;
			ConfiguracionNivel config;

			if (filas < 5) {
				nivelActual = new EasyLevel();

				config = new ConfiguracionNivelBuilder()
						.filas(3)
						.filasResistentes(1)
						.anchoBloque(70)
						.altoBloque(26)
						.build();

			} else {
				nivelActual = new HardLevel();

				config = new ConfiguracionNivelBuilder()
						.filas(5)
						.filasResistentes(2)
						.anchoBloque(70)
						.altoBloque(26)
						.build();
			}

			blocks = nivelActual.crearNivel(
					Gdx.graphics.getWidth(),
					Gdx.graphics.getHeight(),
					config
					);
		}

		public void dibujaTextos() {
			//actualizar matrices de la cámara
			camera.update();
			//actualizar 
			batch.setProjectionMatrix(camera.combined);
			batch.begin();
			//dibujar textos
			font.draw(batch, "Puntos: " + gestor.getPuntaje(), 10, 25);
			font.draw(batch, "Vidas: " + gestor.getVidas(), Gdx.graphics.getWidth()-180, 25);
			
			batch.end();
		}	
		

		@Override
		public void render() {
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			shape.begin(ShapeRenderer.ShapeType.Filled);
			pad.draw(shape);

			if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
				ball.setEstrategia(new NormalMovement());
			}

			if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
				ball.setEstrategia(new FastMovement());
			}


			// Monitorear inicio del juego
			if (ball.estaQuieto()) {
				ball.setXY(pad.getX()+pad.getWidth()/2-5,
                   pad.getY()+pad.getHeight()+11);

				if (Gdx.input.isKeyPressed(Input.Keys.SPACE))
					ball.setEstaQuieto(false);
				} else {
					ball.update();
				}

			// Verificar si la pelota cayó
			if (ball.getY() < 0) {
				gestor.perderVida();

				ball = new PingBall(
						pad.getX()+pad.getWidth()/2-5,
						pad.getY()+pad.getHeight()+11,
						10, 5, 7, true);
			}

			// Verificar Game Over
			if (gestor.getVidas() <= 0) {
				gestor.reiniciarPartida();
				crearBloques(2 + gestor.getNivel());
			}

			// Verificar si se terminó el nivel
			if (blocks.size() == 0) {
				gestor.siguienteNivel();
				crearBloques(2 + gestor.getNivel());

				ball = new PingBall(
						pad.getX()+pad.getWidth()/2-5,
						pad.getY()+pad.getHeight()+11,
						10, 5, 7, true);
			}

			// Dibujar bloques
			for (Block b : blocks) {
				b.draw(shape);
				ball.checkCollision(b);
			}

			// Actualizar estado de los bloques
			for (int i = 0; i < blocks.size(); i++) {
				Block b = blocks.get(i);

				if (b.isDestroyed()) {
					gestor.sumarPunto();
					blocks.remove(b);
					i--;
				}
			}

			ball.checkCollision(pad);
			ball.draw(shape);

			shape.end();

			dibujaTextos();
		}

		
		@Override
		public void dispose () {
			shape.dispose();
		    batch.dispose();
		    font.dispose();
		}
	}
