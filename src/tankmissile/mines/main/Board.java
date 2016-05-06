package tankmissile.mines.main;

import java.util.Random;

public class Board {
	private int width, height;
	
	private Tile[][] board;
	
	Board(int w, int h, int m){
		width = w;
		height = h;
		
		board = new Tile[width][height];
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				board[i][j] = new Tile();
			}
		}
		
		//initialize the mines
		initMines(m);
		
		//for debug
		//debugPrintBoard();
	}
	
	/* Print the entire board to the console, with all tiles revealed */
	@SuppressWarnings("unused")
	private void debugPrintBoard(){
		System.out.println("Generated Board:");
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				Tile temp = board[i][j];
				System.out.print("[ ");
				if(!temp.mine)
					if(temp.count !=0)System.out.print(temp.count);
					else System.out.print(" ");
				else
					System.out.print("M");
				System.out.print(" ] ");
			}
			System.out.print("\n");
		}
		//an extra line for good measure
		System.out.print("\n");
	}
	
	private void initMines(int m){
		Random r = new Random();
		int rx, ry; //random coordinates to place a mine
		
		//place M mines in random coordinates, avoiding overlaps
		//also update the count
		for(int i = 0; i < m; i++){
			//find a random coordinate
			rx = r.nextInt(width);
			ry = r.nextInt(height);
			
			//if the tile is not a mine, make it one
			//and update adjacent tiles' counts
			if(!board[rx][ry].mine){
				board[rx][ry].mine = true;
				
				for(int a = -1; a <= 1; a++){
					if(rx+a < 0 || rx+a > width-1) continue; //don't go out of bounds
					for(int b = -1; b <= 1; b++){
						if(ry+b < 0 || ry+b > height-1) continue; //don't go out of bounds
						
						if(a == 0 && b == 0) continue; //ignore the current tile
						
						board[rx+a][ry+b].count++; //update the count of the tile
					}
				}
				
				//System.out.println("Mine " + i + " placed at (" + rx + "," + ry + ").");
			}
			//otherwise repeat this mine placement
			else{
				i--;
			}
		}
	}
}

/* Stats for each tile */
class Tile {
	boolean mine = false;
	int count = 0;
	
	enum State { HIDDEN, REVEALED, FLAGGED };
	
	State s = State.HIDDEN;
	
	/* Reveal the current tile, returning true if it is blank */
	boolean reveal(){
		s = State.REVEALED;
		
		return count == 0;
	}
	
	/* toggle the tile's FLAGGED status, if it is not revealed */
	void flag(){
		if(s == State.HIDDEN) s = State.FLAGGED;
		else if (s == State.FLAGGED) s = State.HIDDEN;
	}
}