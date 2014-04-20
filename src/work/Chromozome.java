package work;

import java.util.Random;


public class Chromozome {
	private Gene[] chromozome = new Gene[Search.dimension_x + Search.dimension_y + Search.stones_count];
	private int fitness = 0;
	public Map map = new Map();
	
	/**
	 * constructor generating random array of genes*/
	public Chromozome(boolean init){
		if(init){
			for(int i=0; i<chromozome.length; i++){
				Gene g = Gene.generateGene();
				while(true){
					if(!this.containsGene(g))
						break;
					g = Gene.generateGene();
				}
				chromozome[i] = g;
			}
		}
		
		for(int i=0; i<Search.stones_count; i++){
			map.setStone(Search.stones.get(i).key(), Search.stones.get(i).value());
		}
	}
	
	public boolean containsGene(Gene g){
		for(int i=0; i<chromozome.length; i++){
			if(chromozome[i] != null){
				if(chromozome[i].getX() == g.getX() && chromozome[i].getY() == g.getY() && chromozome[i].getOrientation().equals(g.getOrientation())){
					return true;
				}
			}
		}
		return false;
	}
	
	public Gene[] getChromozome(){
		return this.chromozome;
	}
	
	public int getFitness(){
		return this.fitness;
	}
	
	public void setFitness(int fitness){
		this.fitness = fitness;
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
	
	/**
	 * function to combine two Chromozomes*/
	public static void combineChromozomes(){
		for(int i=0; i<10; i++){
			Chromozome parrent_a = Search.parrents.get(i);
			for(int j=0; j<10; j++){
				if(j != i){
					Chromozome parrent_b = Search.parrents.get(j);
					/**
					 * combine parrent_a with parrent_b and create one new chromozome*/
					Random r = new Random();
					int length = parrent_a.chromozome.length;
					int number = r.nextInt(length);
					Chromozome child = new Chromozome(false);
					for(int k=0; k<length; k++){
						if(k<number){
							Gene g = parrent_a.chromozome[k];
							if(g == null)
								g = Gene.generateGene();
							child.chromozome[k] = g;
						}
						else{
							Gene g = parrent_b.chromozome[k];
							if(g == null)
								g = Gene.generateGene();
							child.chromozome[k] = g;
						}
					}
					if(child.getMap() != null)
						child.setMap(child.getMap().rakeMap(child));
					child.setFitness(Map.computeFitness(child));
					Search.new_generation.add(new FitnessPair(child.getFitness(), child));
				}
			}
		}
	}
	
	public static void mutateChromozomes(){
		int size = Search.new_generation.size();
		for(int i=10; i<size; i++){
			Random r = new Random();
			int number2 = r.nextInt(10);
			if(number2 < 3){
				for(int j=0; j<Search.new_generation.get(i).chromozome.chromozome.length; j++){
					int number = r.nextInt(10);
					/**
					 * mutate gene with probability of 40%*/
					if(number < 5){
							Gene g = Gene.generateGene();
							while(true){
								if(!Search.new_generation.get(i).chromozome.containsGene(g))
									break;
								g = Gene.generateGene();
							}		
						Search.new_generation.get(i).chromozome.chromozome[j] = g;
					}
				}
			}
			Chromozome ch = Search.new_generation.get(i).chromozome;
			if(Search.new_generation.get(i).chromozome.getMap() != null)
				Search.new_generation.get(i).chromozome.setMap(Search.new_generation.get(i).chromozome.getMap().rakeMap(Search.new_generation.get(i).chromozome));
			Search.new_generation.get(i).chromozome.setFitness(Map.computeFitness(ch));
		}
	}
}
