package project2;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ModGrid extends JFrame {
	
	private int mod;
	private int[][] grid;
	private JPanel display;

	
	//Constructs a new ModGrid of a specified size consisting of random numbers.
	public ModGrid(String title, int rows, int cols, int mod) {
		super(title);
		this.mod = mod;
		grid = new int[rows][cols];
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < cols; c++) {
				grid[r][c] = (int)(Math.random() * mod);
			}
		}
		setDisplay();
	}
	
	//Constructs a new ModGrid based on an existing 2D array.
	//Later changes to either will not affect the other.
	public ModGrid(String title, int[][] numGrid, int mod) {
		super(title);
		this.mod = mod;
		grid = new int[numGrid.length][numGrid[0].length];
		for(int r = 0; r < grid.length; r++) {
			for(int c = 0; c < grid[r].length; c++) {
				grid[r][c] = numGrid[r][c];
			}
		}
		setDisplay();
	}
	
	//Constructs a new ModGrid based on a String of integers.
	//The numbers in the String represent the number of rows, the number of columns, the modular number the numbers in the 2D array in that order, and the title 
	public ModGrid(String input, String title) {
		super(title);
		String[] nums = input.split(" ");
		grid = new int[Integer.parseInt(nums[0])][Integer.parseInt(nums[1])];
		mod = Integer.parseInt(nums[2]);
		int index = 3;
		for(int r = 0; r < grid.length; r++) {
			for(int c = 0; c < grid[r].length; c++) {
				grid[r][c] = Integer.parseInt(nums[index]);
				index++;
			}
		}
		setDisplay();
	}
	
	//Returns a ModGrid that is a copy of this ModGrid.
	public ModGrid copy() {
		return new ModGrid ("copy of " + this.getTitle(), grid, mod);
	}
	
	//If the other ModGrid has a different number of rows, columns, or modular number, returns false.
	//Otherwise, adds the numbers from the other ModGrid to the numbers in this ModGrid, modding by the modular number, and returns true.
	public boolean add(ModGrid otherGrid) {
		if(mod != otherGrid.mod || grid.length != otherGrid.grid.length || grid[0].length != otherGrid.grid[0].length) {
			return false;
		}
		for(int r = 0; r < grid.length; r++) {
			for(int c = 0; c < grid[r].length; c++) {
				grid[r][c] = (grid[r][c] + otherGrid.grid[r][c]) % mod;
			}
		}
		setDisplay();
		return true;
	}
	
	//Returns true if this ModGrid is equal in all respects to other, false otherwise.
	public boolean equals(Object other) {
		if(!(other instanceof ModGrid)) {
			return false;
		}
		ModGrid otherGrid = (ModGrid) other;
		if(mod != otherGrid.mod || grid.length != otherGrid.grid.length || grid[0].length != otherGrid.grid[0].length) {
			return false;
		}
		for(int r = 0; r < grid.length; r++) {
			for(int c = 0; c < grid[r].length; c++) {
				if(grid[r][c] != otherGrid.grid[r][c]) {
					return false;
				}
			}
		}
		return true;
	}
	//method to get the GUIs display 
	public void setDisplay() {
		//JPanel display = new JPanel(new GridLayout(grid.length, grid[0].length));
		
		/*if(display == null) {
			display = new JPanel(new GridLayout(grid.length, grid[0].length));
			this.add(display);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(300, 300);
		}else {
			display.removeAll();
		}*/
		display = new JPanel(new GridLayout(grid.length, grid[0].length));
		this.add(display);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300, 300);
		for(int r = 0; r < grid.length; r++) {
			for(int c = 0; c < grid[r].length; c++) {
				JLabel spot = new JLabel(String.valueOf(grid[r][c]));
				spot.setOpaque(true);
                
				Color green;
				if (grid[r][c] == 0) {
					green = Color.WHITE;
				}else {
					int opacity = (int)(255 * (double) grid[r][c]/mod);
					green = new Color(0, opacity,0);
				}
				spot.setBackground(green);
                spot.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                display.add(spot);    
			}
		}
		this.setVisible(true);
	}
}
