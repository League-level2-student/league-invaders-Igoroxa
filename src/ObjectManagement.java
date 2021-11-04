import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class ObjectManagement implements ActionListener {
	Rocketship rocket;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	Random random = new Random();
	Alien italien;
	Projectile itproject;
	int score = 0;

	ObjectManagement(Rocketship rocket) {
		this.rocket = rocket;
	}

	public void addProjectile(Projectile p) {
		projectiles.add(p);
	}

	public void addAlien(Alien a) {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH), 0, 50, 50));

	}

	public void update() {
		for (int i = 0; i < aliens.size(); i++) {
			Alien italien = aliens.get(i);
			italien.update();
			if (LeagueInvaders.HEIGHT < italien.y) {
				italien.isActive = false;
			}
		}

		for (int i = 0; i < projectiles.size(); i++) {
			Projectile itproject = projectiles.get(i);
			itproject.update();
			if (0 > itproject.y) {
				itproject.isActive = false;
			}
				purgeObjects();

		}
		checkCollision();
rocket.update();
	}

	public void draw(Graphics g) {
		rocket.draw(g);
		for (int i = 0; i < aliens.size(); i++) {
			Alien yitalien = aliens.get(i);
			yitalien.draw(g);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile yitproject = projectiles.get(i);
			yitproject.draw(g);
		}

	}

	public void purgeObjects() {
		for (int i = 0; i < aliens.size(); i++) {
			Alien newalien = aliens.get(i);
			if (newalien.isActive == false) {
				aliens.remove(i);
			}
		}

		for (int i = 0; i < projectiles.size(); i++) {
			Projectile newproject = projectiles.get(i);
			if (newproject.isActive == false) {
				projectiles.remove(i);
			}
		}
	}

	public void checkCollision() {
		for (int i = 0; i < aliens.size(); i++) {
			System.out.println("loop");
			Alien finalalien = aliens.get(i);
			if (finalalien.collisionBox.intersects(rocket.collisionBox)) {
				
				rocket.isActive = false;
				finalalien.isActive = false;
				System.out.println("work");
		
			}
			for (int n = 0; n < projectiles.size(); n++) {
				Projectile finalproject = projectiles.get(n);
				if (finalproject.collisionBox.intersects(finalalien.collisionBox)) {
					finalalien.isActive = false;
					finalproject.isActive = false;
					score++;
				}
				
			}
		}

	}
	public int getScore() {
		return score;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Alien nextalien = new Alien(LeagueInvaders.WIDTH, 0, 50, 50);
		addAlien(nextalien);

	}
}