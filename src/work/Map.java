package work;

public class Map {
	private String[][] array = new String[Search.dimension_y][Search.dimension_x];
	public int pos = 1;
	
	public Map(){
		for(int i=0; i<Search.dimension_y; i++){
			for(int j=0; j<Search.dimension_x; j++){
				array[i][j] = "0";
			}
		}
	}
	
	public void setStone(int x, int y){
		array[x][y] = "K";
	}
	
	public String[][] getMap(){
		return this.array;
	}
	
	/**
	 * function to print map*/
	public static void printMap(Map map){
		for(int i=0; i<Search.dimension_y; i++){
			for(int j=0; j<Search.dimension_x; j++){
				if(!map.array[i][j].equals("K")){
					if(Integer.parseInt(map.array[i][j]) > 9){
						System.out.print(map.array[i][j] + "  ");
					}
					else{
						System.out.print(" " +map.array[i][j] + "  ");
					}
				}
				else{
					System.out.print(" " +map.array[i][j] + "  ");
				}
			}
			System.out.println();
		}
	}
	
	/**
	 * function to rake map based on chromozome*/
	public Map rakeMap(Chromozome ch){
		Map map = ch.getMap();
		Gene[] chromozome = ch.getChromozome(); 
		for(int i=0; i<chromozome.length; i++){
			map = Map.makeMove(map, chromozome[i].getX(), chromozome[i].getY(), chromozome[i].getOrientation());
			if(map == null)
				break;
		}
		
		return map;
	}
	
	/**
	 * make on move on map*/
	public static Map makeMove(Map map, int x, int y, String orientation){
		String c = Integer.toString(map.pos);
		
		if(orientation.equals("down")){
			if(!map.getMap()[y][x].equals("0")){
				return map;
			}
			while(y < Search.dimension_y){
				if(map.getMap()[y][x].equals("0")){
					map.getMap()[y][x] = c;
					y++;
				}
				else{
					if(x+1 < Search.dimension_x && map.getMap()[y-1][x+1].equals("0")){
							return Map.makeMove(map, x+1, y-1, "right");
					}
					if(x+1 == Search.dimension_x){
						map.pos++;
						return map;
					}
					else{
						if(x-1 >= 0 && map.getMap()[y-1][x-1].equals("0")){
							return Map.makeMove(map, x-1, y-1, "left");
						}
						if(x-1 == -1){
							map.pos++;
							return map;
						}
					}
					return null;
				}
			}
			map.pos++;
			return map;
		}
		if(orientation.equals("right")){
			if(!map.getMap()[y][x].equals("0")){
				return map;
			}
			while(x < Search.dimension_x){
				if(map.getMap()[y][x].equals("0")){
					map.getMap()[y][x] = c;
					x++;
				}
				else{
					if(y+1 < Search.dimension_y && map.getMap()[y+1][x-1].equals("0")){
							return Map.makeMove(map, x-1, y+1, "down");
					}
					if(y+1 == Search.dimension_y){
						map.pos++;
						return map;
					}
					else{
						if(y-1 >= 0 && map.getMap()[y-1][x-1].equals("0")){
							return Map.makeMove(map, x-1, y-1, "up");
						}
						if(y-1 == -1){
							map.pos++;
							return map;
						}
					}
					return null;
				}
			}
			map.pos++;
			return map;
		}
		if(orientation.equals("left")){
			if(!map.getMap()[y][x].equals("0")){
				return map;
			}
			while(x >= 0){
				if(map.getMap()[y][x].equals("0")){
					map.getMap()[y][x] = c;
					x--;
				}
				else{
					if(y+1 < Search.dimension_y && map.getMap()[y+1][x+1].equals("0")){
							return Map.makeMove(map, x+1, y+1, "down");
					}
					if(y+1 == Search.dimension_y){
						map.pos++;
						return map;
					}
					else{
						if(y-1 >= 0 && map.getMap()[y-1][x+1].equals("0") ){
							return Map.makeMove(map, x+1, y-1, "up");
						}
						if(y-1 == -1){
							map.pos++;
							return map;
						}
					}
					return null;
				}
			}
			map.pos++;
			return map;
		}
		if(orientation.equals("up")){
			if(!map.getMap()[y][x].equals("0")){
				return map;
			}
			while(y >= 0){
				if(map.getMap()[y][x].equals("0")){
					map.getMap()[y][x] = c;
					y--;
				}
				else{
					if(x+1 < Search.dimension_x && map.getMap()[y+1][x+1].equals("0")){
							return Map.makeMove(map, x+1, y+1, "right");
					}
					if(x+1 == Search.dimension_x){
						map.pos++;
						return map;
					}
					else{
						if(x-1 >= 0 && map.getMap()[y+1][x-1].equals("0")){
							return Map.makeMove(map, x-1, y+1, "left");
						}
						if(x-1 == -1){
							map.pos++;
							return map;
						}
					}
					return null;
				}
			}
			map.pos++;
			return map;
		}
		return null;
	}
	
	/**
	 * function to compute fitness*/
	public static int computeFitness(Chromozome ch){
		Map map = ch.getMap();
		int fitness = 0;
		if(map != null){
			/**
			 * count number of raked boxes*/
			for(int i=0; i<Search.dimension_y; i++){
				for(int j=0; j<Search.dimension_x; j++){
					if(!map.array[i][j].equals("K")){
						if(Integer.parseInt(map.array[i][j]) > 0){
							fitness++;
						}
					}
				}
			}
		}
		return fitness;
	}
	
}
