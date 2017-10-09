package com.LuDik.EvoAI;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * This class functions as the frame in which all the graphical components are contained and as the startup for the EvoAI program.
 * It contains three panels: the cameraPanel, the infoPanel, and the actionPanel.
 * It also has a reference to the Board this program simulates.
 */

public class EvoAI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	// The panels in which the frame is divided:
	private CameraPanel cameraPanel;
	private InfoPanel infoPanel;
	private ActionPanel actionPanel;
	
	
	private Board board;
	
	
	public EvoAI() {
		initUI();
		
	}
	
	private void initUI() {
		setLayout(new FlowLayout());
			
		cameraPanel = new CameraPanel(this);		
		infoPanel = new InfoPanel(this);
		actionPanel = new ActionPanel(this);
		
		add(actionPanel);
		add(cameraPanel);
		add(infoPanel);
		
		setResizable(false);
		pack();
					
		setTitle("EvoAI");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	

	public CameraPanel getCameraPanel() {
		return cameraPanel;
	}

	public InfoPanel getInfoPanel() {
		return infoPanel;
	}

	public ActionPanel getActionPanel() {
		return actionPanel;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	
}
