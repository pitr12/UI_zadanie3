package work;

import java.util.Random;

public class Gene {
	private int x;
	private int y;
	private String orientation = "default";
	
	public String toString(){
		return "(" +y + "," +x + "," +orientation + ")";
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public void setOrientation(String o){
		this.orientation = o;
	}
	
	public String getOrientation(){
		return this.orientation;
	}
	
	/**
	 * generate random Gene*/
	public static Gene generateGene(){
		Gene gene = new Gene();
		Random r = new Random();
		/**
		 * generate random x*/
		int x = r.nextInt(2*Search.dimension_x);
		int y;
				
		/**
		 * generate random y*/
		if(x == 0 || x == 9 || x>= Search.dimension_x){
			y = r.nextInt(Search.dimension_y);
		}
		else{
			y = r.nextInt(2);
			if(y == 1){
				y = Search.dimension_y - 1;
			}
		}
		
		/**
		 * set x in range 0-dimension_x*/
		if(x >= Search.dimension_x){
			x = r.nextInt(2);
			if(x == 1){
				x = Search.dimension_x - 1;
			}
		}
		
		/**
		 * set way orienatations*/
		if(x == 0 && y == 0){
			int pom = r.nextInt(2);
			if(pom == 0)
				gene.setOrientation("right");
			else gene.setOrientation("down");
		}
		if(y == 0 && x == Search.dimension_x-1){
			int pom = r.nextInt(2);
			if(pom == 0)
				gene.setOrientation("left");
			else gene.setOrientation("down");
		}
		if(y == Search.dimension_y-1 && x == Search.dimension_x-1){
			int pom = r.nextInt(2);
			if(pom == 0)
				gene.setOrientation("left");
			else gene.setOrientation("up");
		}
		if(y == Search.dimension_y-1 && x == 0){
			int pom = r.nextInt(2);
			if(pom == 0)
				gene.setOrientation("right");
			else gene.setOrientation("up");
		}
		if(x>0 && x<Search.dimension_x-1){
			if(y == 0)
				gene.setOrientation("down");
			else gene.setOrientation("up");
		}
		if(x == 0 && gene.getOrientation().equals("default")){
			gene.setOrientation("right");
		}
		if(x == Search.dimension_x-1 && gene.getOrientation().equals("default")){
			gene.setOrientation("left");
		}
		
		gene.setX(x);
		gene.setY(y);
		
		return gene;
	}
}
