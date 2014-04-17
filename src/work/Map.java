package work;

public class Map {
	private char[][] array = new char[Search.dimension_y][Search.dimension_x];
	
	public Map(){
		for(int i=0; i<Search.dimension_y; i++){
			for(int j=0; j<Search.dimension_x; j++){
				array[i][j] = '0';
			}
		}
	}
	
	public void setStone(int x, int y){
		array[x][y] = 'K';
	}
	
	public char[][] getMap(){
		return this.array;
	}
	
	public void printMap(){
		for(int i=0; i<Search.dimension_y; i++){
			for(int j=0; j<Search.dimension_x; j++){
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
	}
}
