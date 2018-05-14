package javagame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Splash extends BasicGameState {
	Image img;
	Sound tweet;
	boolean tweeted;
	
	//Counter for delta time
	int counter;
	// ID we return to class 'Application'
	public static final int ID = 1;

	// init-method for initializing all resources
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		img = new Image("res/logo.png");
		tweet = new Sound("res/tweet.ogg");
		tweeted = false;
	}

	// render-method for all the things happening on-screen
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(img, 103, 0);
	}

	// update-method with all the magic happening in it
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {	
		if (!tweeted) {
			tweet.play();
			tweeted = true;
		}
		counter+= arg2;
		if (counter > 1000)
			sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
	}

	// Returning 'ID' from class 'MainMenu'
	@Override
	public int getID() {
		return Splash.ID;
	}
}