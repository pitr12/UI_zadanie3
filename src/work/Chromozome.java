package work;


public class Chromozome {
	private Gene[] chromozome = new Gene[Search.dimension_x + Search.dimension_y];
	private int fitness = 0;
	public Map map = new Map();
	
	/**
	 * constructor generating random array of genes*/
	public Chromozome(){
		for(int i=0; i<chromozome.length; i++){
			chromozome[i] = Gene.generateGene();
		}
		
		for(int i=0; i<Search.stones_count; i++){
			map.setStone(Search.stones.get(i).key(), Search.stones.get(i).value());
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
	
	public void setMap(Map map){
		this.map = map;
	}
	
	/**
	 * function to print chromozome*/
	public void printChromozome(){
		for(int i=0; i<chromozome.length; i++){
			System.out.println(chromozome[i].toString());
		}
		System.out.println();
	}
}
