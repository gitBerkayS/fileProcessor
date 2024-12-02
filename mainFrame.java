import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JSlider;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;

public class mainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Sort sort = new Sort();
	Merge merge = new Merge();
	Filter filter = new Filter();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame frame = new mainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainFrame() {
		//removes top right and left (x) (-) buttons
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1024, 768);
		setLocationRelativeTo(null); 
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(121, 186, 255), 1, true));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//object that allows further functions for the list.
		DefaultListModel listModelListDir = new DefaultListModel<>();
		//DefaultListModel<String> listModelListData = new DefaultListModel<>();
		
		JTextArea textBox = new JTextArea();
		textBox.setBorder(new LineBorder(new Color(121, 186, 255), 1, true));
		textBox.setBounds(519, 61, 452, 517);
		textBox.setEnabled(false);
		contentPane.add(textBox);
		
		JList<String> listDir = new JList<>(listModelListDir);
		listDir.setBorder(new LineBorder(new Color(121, 186, 255), 1, true));
		listDir.setBounds(49, 61, 452, 517);
		contentPane.add(listDir);
		
		//using the selection model to get the selection listener command
		listDir.getSelectionModel().addListSelectionListener(e -> {
			//listModelListData.clear();
			
			//clearing the textbox and data so we can click select as many times we want without any duplicates printed.
			textBox.setText("");
			Sort.parsedData.clear();
			
			//selected value = current file. which is selected and parsed by .selectfile
			String currentfile = listDir.getSelectedValue();
			Sort.selectFile(currentfile);
			
			//appending each entryset in the list of map entries
			for (Map.Entry<String, Integer> entry : Sort.parsedData.entrySet()) {
		        //listModelListData.addElement(entry.getKey() + ": " + entry.getValue());
				textBox.append(entry.getKey() + ": " + entry.getValue() + "\n" );
				
			}
		});
		
		//imp
		//JList<String> listData = new JList<>(listModelListData);
		//listData.setBorder(new LineBorder(new Color(121, 186, 255), 1, true));
		//listData.setBounds(519, 61, 452, 517);
		//contentPane.add(listData);
		
		
		JLabel lblNewLabel = new JLabel("Java Program for File Processing:");
		lblNewLabel.setFont(new Font("PT Serif Caption", Font.BOLD, 16));
		lblNewLabel.setBounds(49, 33, 262, 22);
		contentPane.add(lblNewLabel);
		
		JButton btnSort = new JButton("SORT");
		btnSort.setBorder(new LineBorder(new Color(121, 186, 255), 1, true));
		btnSort.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 15));
		btnSort.setBounds(318, 594, 155, 52);
		contentPane.add(btnSort);
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//clearing data and textbox to remove duplicate entries in data.
				Sort.sortedParsedData.clear();
				textBox.setText("");
				//listModelListData.clear();
				
				//appending sorted values to textbox
				for (Map.Entry<String, Integer> entry :  Sort.sortData()) {
					textBox.append(entry.getKey() + ": " + entry.getValue() + "\n");
					//listModelListData.addElement(entry.getKey() + ": " + entry.getValue());
				}
			}
		});
		
		JButton btnMerge = new JButton("MERGE");
		btnMerge.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 15));
		btnMerge.setBounds(564, 594, 155, 52);
		btnMerge.setBorder(new LineBorder(new Color(121, 186, 255), 1, true));
		contentPane.add(btnMerge);
		btnMerge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//clearing data and textbox to remove duplicate entries in data.
				Merge.combinedDataList.clear();
				merge.parsedData1.clear();
				merge.parsedData2.clear();
				textBox.setText(" ");
				//listModelListData.clear();
				
					//selected values are referred to per index, stored in an array.
				    int[] selectedIndices = listDir.getSelectedIndices();
				    
				    //using a model retrieving selected values(file names).
				    String file1 = listDir.getModel().getElementAt(selectedIndices[0]);
				    String file2 = listDir.getModel().getElementAt(selectedIndices[1]);
				    
				    //parsing the two file data
				    merge.selectFile(file1, file2);
				    
				    //performing merge, and sort, then appending
				    for (Map.Entry<String, Integer> entry :  merge.compareTo()) {
				        //listModelListData.addElement(entry.getKey() + ": " + entry.getValue());
				    	textBox.append(entry.getKey() + ": " + entry.getValue() + "\n");
					}
				    

				    
			}
		});
		
		JButton btnFilter = new JButton("FILTER");
		btnFilter.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 15));
		btnFilter.setBounds(805, 594, 155, 52);
		btnFilter.setBorder(new LineBorder(new Color(121, 186, 255), 1, true));
		contentPane.add(btnFilter);
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//clearing data and textbox to remove duplicate data.
				filter.filteredData.clear();
				//saving the userinput returned by the showinputdialog method.
				String userInput =  JOptionPane.showInputDialog(contentPane,"Insert a string to filter by:");
				textBox.setText(" ");
				
				//filtering for data, and appending onto the textbox
				for (Map.Entry<String, Integer> entry : filter.dataFilter(userInput)) {
			        //listModelListData.addElement(entry.getKey() + ": " + entry.getValue());
			    	textBox.append(entry.getKey() + ": " + entry.getValue() + "\n");
				}
			}
		});
		
		JButton btnLoad = new JButton("LOAD");
		btnLoad.setToolTipText("This button reads the current directory and let's you know of the files available to use.");
		btnLoad.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 15));
		btnLoad.setBorder(new LineBorder(new Color(121, 186, 255), 1, true));
		btnLoad.setBounds(59, 594, 155, 52);
		contentPane.add(btnLoad);
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//clearing list information to prevent duplicate information
				listModelListDir.clear();
				//performing search to find wanted files
				sort.search();
				//adding files onto list
				for (String file : Sort.files) {
		            listModelListDir.addElement(file);
		        }
			}
		});
		
		JButton btnExit = new JButton("X");
		btnExit.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 15));
		btnExit.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnExit.setBounds(989, 6, 29, 28);
		contentPane.add(btnExit);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//adding an shutdown function to the 'x' button
				System.exit(0);
			}
		});

		
		

		
	}
}
