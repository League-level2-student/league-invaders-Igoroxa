import java.util.ArrayList;
import java.util.Random;

public class ObjectManagement {
Rocketship rocket;
ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
ArrayList<Alien> aliens = new ArrayList<Alien>();
Random random = new Random();


ObjectManagement(Rocketship rocket) {
	this.rocket = rocket;
}
public void addProjectile(Projectile p) {
	projectiles.add(p);
}
public void addAlien(Alien a) {
	aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH),0,50,50));

}
public void update () {
	for (int i = 0; i < aliens.size(); i++) {
	aliens.get(i);
	aliens.update();
	}
}
}