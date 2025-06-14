package project2;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.JOptionPane;

public class GridRunner {

	public static void main(String[] args) {

		//prompts the user for input.
		int row = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of rows:  "));
		int col = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of cols:  "));
		int mod = Integer.parseInt(JOptionPane.showInputDialog("Enter the modular number:  "));
		
		ModGrid grid1 = new ModGrid("Grid 1", row, col, mod);
		ModGrid grid2 = new ModGrid("Grid 2", row, col, mod);
		ModGrid copyGrid1 = grid1.copy();
		
		//setting the ADD button 
		JFrame adding = new JFrame("Add grid1 and grid2");
		JButton addingButton = new JButton (" ADD ");
		//prevents the grid1 and copyGrid1 from setting off the actionListener condition
		final int[] counter = {0};
		addingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(counter[0] == 0) {
					counter[0]++;
					grid1.add(grid2);
					
				}
				if(grid1.equals(grid2)) {
					JOptionPane.showMessageDialog(null, "grid1 is the same as grid2");
				}else if(grid1.equals(copyGrid1)) {
					JOptionPane.showMessageDialog(null, "grid1 has return to its origen");
				}else {
					grid1.add(grid2);
				}
			}
		});
		adding.add(addingButton);
		adding.setSize(100, 100);
		adding.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		adding.setVisible(true);
		
		
	}	
	
}
