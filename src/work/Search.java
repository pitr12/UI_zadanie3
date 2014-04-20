package work;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Search {
	public static int dimension_y = 0;
	public static int dimension_x = 0;
	public static int stones_count;
	public static ArrayList<Pair> stones = new ArrayList<Pair>();
	public static ArrayList<FitnessPair> sorted_generation = new ArrayList<FitnessPair>();
	public static ArrayList<FitnessPair> new_generation = new ArrayList<FitnessPair>();
	public static ArrayList<Chromozome> parrents = new ArrayList<Chromozome>();
	public static HashSet<Integer> random_num = new HashSet<Integer>();
	
	public static void main(String[] args) {	
		Scanner input = new Scanner(System.in);
		int generation_number = 0;

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
			stones.add(new Pair(y,x));
		}
		input.close();	
		
		int generation_size = 100;
		/**
		 * generate random Chromozomes for initial generation....size = 100*/
		for(int i=0; i<generation_size; i++){
			Chromozome ch = new Chromozome(true);
			sorted_generation.add(new FitnessPair(ch.getFitness(), ch));
		}

		
		/**
		 * generation period which will repeat max 10 000 times*/
		while(generation_number < 50000){
			generation_number++;
			
			/**
			 * rake map and compute choromozome fitness*/
			for(int i=0; i<generation_size; i++){
				Chromozome ch = sorted_generation.get(i).chromozome;
					/**
					 * rake map based on its chromozome*/
					
				if(ch.getMap() != null)
					ch.setMap(ch.getMap().rakeMap(ch));
						/**
						 * compute fitness function for chromozome*/
						ch.setFitness(Map.computeFitness(ch));
						
					new_generation.add(new FitnessPair(ch.getFitness(), ch));						
			}
			Collections.sort(new_generation);
			sorted_generation.clear();
			sorted_generation.addAll(new_generation);
			new_generation.clear();
			Random r = new Random();
			////SELECT NUMBER OF SELECTION METHOD
			int method = 0;
			
			switch(method){
			case 0:
					////////////////////SELECTION METHOD 1///////////////////////////////////
					/**
					 * select 10 best spieces which will automatically survive into next generation*/
					for(int i=0; i<10; i++){
						new_generation.add(sorted_generation.get(i));
					}
					
					/**
					 * select 10 random spieces (from first 30 spieces based on fitness value) which will represent parrents*/
					for(int i=0; i<10; i++){
						int number = r.nextInt(15);
						while(true){
							number = r.nextInt(15);
							if(random_num.contains(number) == false)
								break;
						}
						random_num.add(number);
					}
		
					for (Integer number : random_num) {
						parrents.add(sorted_generation.get(number).chromozome);
					}
					random_num.clear();
					break;
					
			case 1:
					////////////////////SELECTION METHOD 2///////////////////////////////////
					/**
					 * select 10 best spieces which will automatically survive into next generation and make them parrents too*/
					for(int i=0; i<10; i++){
						new_generation.add(sorted_generation.get(i));
						parrents.add(sorted_generation.get(i).chromozome);
					}
					break;
			}
			
			
			/**
			 * combine selected parrents to create new spieces*/
			Chromozome.combineChromozomes();
			
			Chromozome.mutateChromozomes();
			sorted_generation.clear();
			sorted_generation.addAll(new_generation);
			Collections.sort(sorted_generation);
			new_generation.clear();
			parrents.clear();
			
			if(sorted_generation.get(0).fitness == (dimension_x*dimension_y - stones_count)){
				System.out.println("Solution found!  Generation: " +generation_number);
				Map.printMap(sorted_generation.get(0).chromozome.getMap());
				break;
			}
		}
	}
}
