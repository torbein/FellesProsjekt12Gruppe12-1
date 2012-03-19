package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {
	
	public CustomTableCellRenderer(){
		
	}
	
	public Component getTableCellRendererComponent(JTable kalender, Object value, boolean isSelected,
			boolean hasFocus, int row, int column){
		Component cell = super.getTableCellRendererComponent(kalender, value, 
				isSelected, hasFocus, row, column);	
				
		cell.setBackground(Color.PINK);
		
		return cell;
		
	}

}
