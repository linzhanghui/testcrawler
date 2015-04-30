package cn.zohar;

import java.util.Random;

public class Coin {
	public static void main(String[] args) {
		for(int i=1;i<900;i++){
			Random rand = new Random();
			int x = rand.nextInt(10);
			System.out.println("x="+x+" ");
		}
	}
}
