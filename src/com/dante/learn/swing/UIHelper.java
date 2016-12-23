package com.dante.learn.swing;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class UIHelper {
	public static void createGridBagConstraints(GridBagConstraints gridBagConstraints, int insetTop, int insetLeft, int insetBottom, int insetRight, int gridX, int gridY) {
		gridBagConstraints.insets = new Insets(insetTop, insetLeft, insetBottom, insetRight);
		gridBagConstraints.gridx = gridX;
		gridBagConstraints.gridy = gridY;
	}
	
	public static void createGridBagConstraints(GridBagConstraints gridBagConstraints, 
			Insets insets, int gridX, int gridY, 
			Integer fill, Integer anchor) {
		createGridBagConstraints(gridBagConstraints, insets, gridX, gridY, fill, anchor, null, null);
	}
	
	public static void createGridBagConstraints(GridBagConstraints gridBagConstraints, 
			Insets insets, int gridX, int gridY, 
			Integer fill, Integer anchor, Integer gridwidth, Integer gridheight) {
		gridBagConstraints.insets = insets;
		gridBagConstraints.gridx = gridX;
		gridBagConstraints.gridy = gridY;

		if (fill != null) {
			gridBagConstraints.fill = fill;
		}
		
		if (anchor != null) {
			gridBagConstraints.anchor = anchor;
		}
		
		if (gridwidth != null) {
			gridBagConstraints.gridwidth = gridwidth;
		}
		
		if (gridheight != null) {
			gridBagConstraints.gridheight = gridheight;
		}
	}
}
