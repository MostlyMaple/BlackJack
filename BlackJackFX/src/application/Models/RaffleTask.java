package application.Models;

import java.util.Random;

import application.Main;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * RaffleTask is a Model class that generates a random reward from using raffle tickets.
 * 
 * UTSA CS 3443 - Final Project
 * Fall 2021
 */

public class RaffleTask implements Runnable {
	
	ImageView igv;
	TextArea txt;
    
	// information I want to change
	Text balance;
    Text ticketNum;
    
    // important variable for synchronized
    private boolean finished = false;
	
	// image prefix
	Image DM = new Image("/AchieveImages/diamond.png");
	Image BZ = new Image("/AchieveImages/bronze.jpg");
	Image GD = new Image("/AchieveImages/gold.jpg");
	Image Bheart = new Image("/AchieveImages/BlackHeart.png");
	Image heart = new Image("/AchieveImages/heart.png");
	Image shose = new Image("/AchieveImages/shose.jpg");
	Image water = new Image("/AchieveImages/water.jpg");
	Image coin = new Image("/AchieveImages/coin.jpg");
	
	// image list for looping and animation
	Image list[] = {DM, GD, BZ, Bheart, heart, shose, water, coin};

	/**
	 * @param igv
	 * @param txt
	 * @param balance
	 * @param ticketNum
	 */
	public RaffleTask(ImageView igv, TextArea txt, Text balance, Text ticketNum) {
		this.igv = igv;
		this.txt = txt;
		this.balance = balance;
		this.ticketNum = ticketNum;
	}
	
	public synchronized boolean isFinished() {
		return finished;
	}

	public synchronized void setFinished(boolean finished) {
		this.finished = finished;
	}

	public int getRandomNum(int min, int max) {
		Random rand = new Random();
		int randomInt = rand.nextInt((max - min) + 1) + min;
		return randomInt;
	}
	
	public int randomImage(ImageView img) {
		int c = getRandomNum(1,100);
		
		if(c >= 1 && c < 10) {
			img.setImage(this.DM);
			return 0;
		} 
		else if(c >= 10 && c < 20) {
			img.setImage(this.GD);
			return 1;
		}
		else if(c >= 20 && c < 40) {
			img.setImage(this.BZ);
			return 2;
		}
		else if(c >= 40 && c < 50) {
			img.setImage(this.heart);
			return 3;
		}
		else if(c >= 50 && c < 70) {
			img.setImage(this.Bheart);
			return 4;
		}
		else if(c >= 70 && c < 80) {
			img.setImage(this.shose);
		}
		else if(c >= 80 && c <= 90) {
			img.setImage(this.water);
		}
		else if(c >= 90 && c <= 100) {
			img.setImage(this.coin);
			return 5;
		}
		
		return -1;
		
	}
	
	@Override
	public void run() {
		synchronized (this) {
			// play animation
			int counter = 0;
			while(counter < 3) 
			{
				for(int i = 0; i<this.list.length; i++) 
				{
					igv.setImage(this.list[i]);
					
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				counter++;
			}
			
			// set image
			int flag = randomImage(this.igv);
			switch(flag) {
				case 0:
					this.txt.appendText("You Got Diamond Medal\n");
					break;
				case 1:
					this.txt.appendText("You Got Golden Medal\n");
					break;
				case 2:
					this.txt.appendText("You Got Bronze Medal\n");
					break;
				case 3:
					this.txt.appendText("You Got Red Heart:\n  + 100 Money\n");
					Main.balance += 100;
					this.balance.setText(String.valueOf(Main.balance));
					break;
				case 4:
					this.txt.appendText("You Got Black Heart:\n  + 1 Ticket\n");
					Main.ticket += 1;
					this.ticketNum.setText(String.valueOf(Main.ticket));
					break;
				case 5:
					this.txt.appendText("You Got BitCoin!\n  + 500 Money\n");
					Main.balance += 500;
					this.balance.setText(String.valueOf(Main.balance));
					break;
				case -1:
					this.txt.appendText("You Got a Trash...\n  -10 Money\n");
					Main.balance -= 10;
					this.balance.setText(String.valueOf(Main.balance));
					break;
			}
			
			this.finished = true;
			notifyAll();
		}
	}
	
}





