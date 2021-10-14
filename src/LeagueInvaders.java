import javax.swing.JFrame;

public class LeagueInvaders {
public static void main(String[] args) {
	LeagueInvaders invaders = new LeagueInvaders();
	invaders.setup();
	
	
}
JFrame frame;
public static final int WIDTH = 500;
public static final int HEIGHT= 800;
GamePanel gp;
LeagueInvaders(){
	frame = new JFrame();
	gp = new GamePanel();
}
void setup() {

	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.add(gp);
	frame.setSize(WIDTH, HEIGHT);
	frame.addKeyListener(gp);

}
}
