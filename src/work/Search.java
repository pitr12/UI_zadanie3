package work;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class Search {
	public static int dimension_y = 0;
	public static int dimension_x = 0;
	public static int stones_count;
	public static LinkedHashMap<Integer,Integer> stones = new LinkedHashMap<Integer,Integer>();
	
	public static void main(String[] args) {	
		Scanner input = new Scanner(System.in);

		/**
		 * loading dimension x*/
	    do {
			System.out.println("Zadaj x-ový rozmer mapy: ");
	        while (!input.hasNextInt()) {
	            System.out.println("Zadali ste nesprávny rozmer!");
				System.out.println("Zadaj rozmer x hracieho pola: ");
	            input.next(); 
	        }
	        dimension_x = input.nextInt();
	    } while (dimension_x <= 0);
		
		input.nextLine();
		
		/**
		 * loading dimension y*/
		do {
			System.out.println("Zadaj y-ový rozmer mapy: ");
	        while (!input.hasNextInt()) {
	            System.out.println("Zadali ste nesprávny rozmer!");
				System.out.println("Zadaj rozmer y hracieho pola: ");
	            input.next(); 
	        }
	        dimension_y = input.nextInt();
	    } while (dimension_y <= 0);
		input.nextLine();
		
		/**
		 * loading number of stones*/
		do {
			System.out.println("Zadaj poèet kameòov na mape: ");
	        while (!input.hasNextInt()) {
	            System.out.println("Zadali ste nesprávny poèet!");
				System.out.println("Zadaj poèet kameòov na mape: ");
	            input.next(); 
	        }
	        stones_count = input.nextInt();
	    } while (stones_count < 0);
		input.nextLine();
		
		
		for(int i=0; i<stones_count; i++){
			int x,y;
			System.out.println("Zadaj súradnice kameòa " + (i+1));
			y = input.nextInt();
			x = input.nextInt();
			stones.put(y, x);
		}
		input.close();	
		
		Chromozome ch = new Chromozome();
		ch.printChromozome();
		
	}
}
