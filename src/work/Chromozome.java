package work;

import java.util.Iterator;
import java.util.Map.Entry;

public class Chromozome {
	private Gene[] chromozome = new Gene[Search.dimension_x + Search.dimension_y];
	private int fitness = 0;
	private Map map = new Map();
	
	/**
	 * constructor generating random array of genes*/
	public Chromozome(){
		for(int i=0; i<chromozome.length; i++){
			chromozome[i] = Gene.generateGene();
		}
		
		Iterator<Entry<Integer, Integer>> iterator = Search.stones.entrySet().iterator();
		while( iterator.hasNext() ){
		     Entry<Integer, Integer> entry = iterator.next();
		    	map.setStone(entry.getKey(),entry.getValue());
		}
	}
	
	public Gene[] getChromozome(){
		return this.chromozome;
	}
	
	public int getFitness(){
		return this.fitness;
	}
	
	public Map getMap(){
		return this.map;
	}
	
	public void printChromozome(){
		for(int i=0; i<chromozome.length; i++){
			System.out.println(chromozome[i].toString());
		}
		System.out.println();
		this.map.printMap();
	}
}
