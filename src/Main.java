import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;



import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Main {
	private JFrame frame;
	private BoxDem [][] demArray;
	private Demineur demineur;
	private int size;
	private int landMines;
	private JTextField inputSize;
	private int customSize = 0;
	private int defaultSize = 5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		frame = new JFrame();
		frame.setBounds(50, 50, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		size = defaultSize;
		landMines = size;
		demineur = new Demineur(size, landMines);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//window that contains the game
		
		
		JButton sizeBtn = new JButton("Size");
		sizeBtn.setBounds(492, 10, 86, 25);
		frame.getContentPane().add(sizeBtn);
		
		inputSize = new JTextField();
		inputSize.setBounds(592, 12, 45, 22);
		frame.getContentPane().add(inputSize);
		
		
		sizeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				customSize = Integer.parseInt(inputSize.getText());
				if (customSize > 0){
					size = customSize;
					frame.getContentPane().removeAll();	
					System.out.println(size);
					demineur = new Demineur(size, landMines);
					initialize();
					frame.revalidate();
					frame.repaint();
				}
			}
		});
		

			
	
			
		
		
		
		demArray = demineur.getBoxDem();
		for (int i = 0; i < size; i++) {
			final int row = i;
			for (int j = 0; j < size; j++) {
				final int col = j;
				demArray[i][j].setBounds(i*50+150, j*50+150, 50, 50);
				frame.getContentPane().add(demArray[i][j]);
				
				demArray[i][j].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (SwingUtilities.isRightMouseButton(e)) {
							demineur.flag(row, col);
						}else {
							demineur.playBox(row, col);
						}
					}
				});
				
				
					
			}

		}
	}
}