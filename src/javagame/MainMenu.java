package javagame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenu extends BasicGameState {

	Image playButton;
	boolean showPlayButton;
	
	//Hero's variables
	SpriteSheet hero;
	Image[] heroWalkingLeft;
	Image[] heroWalkingRight;
	boolean heroPosition = true; //true = left, false = right
	int currentSprite = 0;
	int heroX, heroY;
	boolean leftReleased, rightReleased;
	
	int delta = 0;
	
	
	
	// ID we return to class 'Application'
	public static final int ID = 2;

	// init-method for initializing all resources
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		heroX = 100;
		heroY = 100;
		leftReleased = true;
		rightReleased = true;
		playButton = new Image("res/playbutton.png");
		hero = new SpriteSheet(new Image("res/heroalpha.png"), 15, 15, 1);
		heroWalkingLeft = new Image[6];
		heroWalkingRight = new Image[6];
		for (int i = 0; i < hero.getHorizontalCount(); i++) {
			heroWalkingLeft[i] = hero.getSprite(i, 1);
			heroWalkingLeft[i].setFilter(Image.FILTER_NEAREST);
			heroWalkingLeft[i] = heroWalkingLeft[i].getScaledCopy(4f);
			
			heroWalkingRight[i] = hero.getSprite(i, 1).getFlippedCopy(true, false);
			heroWalkingRight[i].setFilter(Image.FILTER_NEAREST);
			heroWalkingRight[i] = heroWalkingRight[i].getScaledCopy(4f);
		}
		
	}
	int costumeChanges = 0;

	// render-method for all the things happening on-screen
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawString("Number of horizontal images found:"+hero.getHorizontalCount(), 100, 100);
		g.drawString("Number of vertical images found:"+hero.getVerticalCount(), 100, 120);
		g.drawString(""+delta, 200, 200);
		g.drawString(""+costumeChanges, 200, 300);
		//Draw dude
			//Facing left
			if (heroPosition) {
				g.drawImage(heroWalkingLeft[currentSprite], heroX, heroY);
			} else {
				g.drawImage(heroWalkingRight[currentSprite], heroX, heroY);
			}
		
	}
	int oldCostumeNumber = 0;
	// update-method with all the magic happening in it
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
		
		Input input = gc.getInput();

		if (input.isKeyDown(Input.KEY_LEFT)) {
			heroPosition = false;
			
			if (heroX >= 0)
			heroX-=8;
			
			if (costumeChanges > oldCostumeNumber) {
				if (currentSprite < heroWalkingLeft.length-1)
					currentSprite++;
				else currentSprite = 0;
				oldCostumeNumber = costumeChanges;
			}
			
		} else if (input.isKeyDown(Input.KEY_RIGHT)) {
			heroPosition = true;
			if (heroX <= 970)
			heroX+=8;
			
			if (costumeChanges > oldCostumeNumber) {
				if (currentSprite < heroWalkingLeft.length-1)
					currentSprite++;
				else currentSprite = 0;
				oldCostumeNumber = costumeChanges;
			}
		}
		
		
		delta += arg2;
		costumeChanges = delta / 50;
		//For whatever reason it doesnt work for 6 seconds so lets make it work after six seconds.

		
	}
	
	public void keyReleased (int key, char c) {
		if (key == Input.KEY_LEFT || key == Input.KEY_RIGHT)
			currentSprite = 0;
	}
	
	// Returning 'ID' from class 'MainMenu'
	@Override
	public int getID() {
		return MainMenu.ID;
	}
}