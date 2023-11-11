package main;

import javax.swing.JFrame;

public class main {

	public static void main(String[] args) {
		
		JFrame j = new JFrame();
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setResizable(false);
		j.setTitle("Snake in a Maze");
		
		GamePanel gamePanel = new GamePanel();
		j.add(gamePanel);
		
		j.pack();
		
		j.setLocationRelativeTo(null);
		j.setVisible(true);
		
		gamePanel.setupGame();
		gamePanel.startGameThread();

	}

}
