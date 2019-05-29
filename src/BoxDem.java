import java.awt.Color;

import javax.swing.JButton;



public class BoxDem extends JButton{
	
	private int row;
	private int col;
	private String value;
	private boolean landmine;
	private boolean activated;
	private boolean flag;
	
	private boolean hintBox;
	private int surroundingLandMines;
	
	
	public BoxDem(int row, int col) {
		this.row = row;
		this.col = col;
		setValue("");
		
		surroundingLandMines=0;
		
	}

	public int getSurroundingLandMines() {
		return surroundingLandMines;
	}

	public void setSurroundingLandMines(int surroundingLandMines) {
		this.surroundingLandMines = surroundingLandMines;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
			
		this.value = value;
	}
	
	public void styliseBox() {
		if (!activated) {
			if (flag){
				setBackground(Color.lightGray);
				setText("F");
				
			}else {
				setBackground(null);
				setText(" ");
			}	
		}else {
			if (landmine) {
				setBackground(Color.RED);
				setText("x");
			}else {
				setBackground(Color.lightGray);
				if (surroundingLandMines == 0) {
					setText(" ");
				}else {
					setValue(Integer.toString((surroundingLandMines)));
					setText(Integer.toString((surroundingLandMines)));
					
				}
			}
		}
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	
	public boolean isLandmine() {
		return landmine;
	}

	public void setLandmine(boolean landmine) {
		this.landmine = landmine;
	}
	
	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public boolean isHintBox() {
		return hintBox;
	}

	public void setHintBox(boolean hintBox) {
		this.hintBox = hintBox;
	}

}
