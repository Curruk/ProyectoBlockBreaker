package game.blockbreaker;

public class NormalBlock extends Block {

	public NormalBlock(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	@Override
	public void recibirGolpe() {
		this.setDestroyed(true);	
	}
}
