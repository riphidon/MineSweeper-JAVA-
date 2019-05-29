
import java.awt.GridBagConstraints;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.w3c.dom.css.Counter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class Demineur {
	
	private int size;
	
	private int content;
	private int landMines;
	private int nbLandmines;
	private BoxDem demGrid [][];
	private JFrame frame;
	private int nbFlag;
	private int counter = 0;
	private int activatedBox = 0;
	
	
	//create a grid and fill it with boxes
	public Demineur(int size, int landMines){
		// !!STORE the data or else it will be == 0 when used in the other method of the class!!!!!
		this.size = size;
		this.landMines = landMines;
		demGrid = new BoxDem[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				demGrid[i][j] = new BoxDem(i, j); 
			}
		}
	}
	
	
	
	public int getLandMines() {
		return landMines;
	}

	public void setLandMines(int landMines) {
		this.landMines = landMines;
	}
	
	public void placeLandMines(int currentRow, int currentCol) {
		int row;
		int col;
		int index = 0;
		while ( index < landMines) {
			row = (int)(Math.random()*demGrid.length);
			col = (int)(Math.random()*demGrid.length);
			if (!demGrid[row][col].isLandmine() && demGrid[row][col] != demGrid[currentRow][currentCol] ) {
				demGrid[row][col].setLandmine(true);
				System.out.println(demGrid[row][col]);
				index++;
			}
		}
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				demGrid[i][j].setSurroundingLandMines(checkSurroundings(i , j));
			}
		}
		
	}

	
	
	public int checkSurroundings(int row, int col) {
		int dangers =0;
		for ( int di = -1; di < 2; di++) {
			for ( int dj = -1 ; dj < 2; dj++) {
				try {
					if (demGrid[row + di][col + dj].isLandmine()) {
						dangers +=1;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		}
		return dangers;
	}
	
	
	public void checkAndReveal(int row, int col) {
		if (demGrid[row][col].getValue() == "") {
			for ( int ri = -1; ri < 2; ri++) {
				for ( int rj = -1 ; rj < 2; rj++) {
					try {
						if (!demGrid[row + ri][col + rj].isActivated() && !demGrid[row + ri][col + rj].isLandmine()) {
							demGrid[row + ri][col + rj].setActivated(true);
							demGrid[row + ri][col + rj].styliseBox();
							activatedBox += 1;
							if (demGrid[row + ri][col + rj].getValue() == "" && !demGrid[row + ri][col + rj].isLandmine()) {
								checkAndReveal(row + ri, col + rj);
							}
						}
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					
				}
			}
		}
	}
	
	
	public BoxDem [][] getBoxDem() {
		return demGrid;
	}
	
	
	
	public BoxDem getOneBox(int i, int j) {
		return demGrid[i][j];
	}
	
	
	
	public String playBox(int row, int col) {
		String winner;
		if (counter == 0) {
			demGrid[row][col].setValue(" ");
			demGrid[row][col].setActivated(true);
			demGrid[row][col].styliseBox();
			placeLandMines(row, col);
			activatedBox +=1;
			counter ++;
		}
		if (!demGrid[row][col].isActivated() && !demGrid[row][col].isFlag()) {
			demGrid[row][col].setActivated(true);
			demGrid[row][col].styliseBox();
			if(demGrid[row][col].isLandmine()) {
				return gameOver(row, col);
			}
			activatedBox += 1;
			checkAndReveal(row, col);
			counter ++;
		}
		System.out.println(activatedBox);
		System.out.println((demGrid.length)*(demGrid.length));
		if ((demGrid.length)*(demGrid.length) == activatedBox + landMines) {
			JOptionPane.showMessageDialog(frame, "YOU WIN");
		}
		return "winner";
		
	}
	
	
	
	public int getSize() {
		return size;
	}



	public void setSize(int size) {
		this.size = size;
	}



	public void flag(int row, int col){
		nbFlag = 5;
		if (!demGrid[row][col].isActivated()) {
			if (!demGrid[row][col].isFlag()) {
				demGrid[row][col].setFlag(true);
				demGrid[row][col].styliseBox();
				nbFlag--;
				
			}else {
				demGrid[row][col].setFlag(false);
				demGrid[row][col].styliseBox();
				nbFlag++;
			}
		}
	}

	private String win() {
		if (demGrid.length == activatedBox + landMines) {
			JOptionPane.showMessageDialog(frame, "YOU WIN");
		}
		return "winner";
	}
	
	private String gameOver(int row, int col) {
			demGrid[row][col].styliseBox();
			activatedBox-=1;
			JOptionPane.showMessageDialog(frame, "YOU LOOSE");
			//System.out.println("YOU LOOSE");
		return "Looser";
	}
}
