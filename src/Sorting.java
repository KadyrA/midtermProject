import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.SystemColor;

public class Sorting {

	private JFrame frame;
	private JTextField sizeOfArray;
	private JTextField new_int;
	private JTextField searchValue;
	private ButtonGroup btnGroup;
	int[] myarray;
	int arraySize;
	int index = 0;
	JList<Integer> list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sorting window = new Sorting();
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
	public Sorting() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.menu);
		frame.setBounds(100, 100, 477, 433);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Size of an array");
		lblNewLabel.setBounds(35, 21, 99, 14);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblEnterNewInteger = new JLabel("Enter new Integer");
		lblEnterNewInteger.setBounds(155, 21, 106, 14);
		frame.getContentPane().add(lblEnterNewInteger);

		sizeOfArray = new JTextField();
		sizeOfArray.setBounds(35, 46, 99, 20);
		frame.getContentPane().add(sizeOfArray);
		sizeOfArray.setColumns(10);

		new_int = new JTextField();
		new_int.setBounds(155, 46, 106, 20);
		frame.getContentPane().add(new_int);
		new_int.setColumns(10);

		JRadioButton rdbtnEnterManually = new JRadioButton("Enter manually");
		rdbtnEnterManually.setBounds(290, 46, 109, 23);
		frame.getContentPane().add(rdbtnEnterManually);

		JRadioButton rdbtnGenerateRandomly = new JRadioButton("Generate randomly");
		rdbtnGenerateRandomly.setBounds(290, 88, 149, 23);
		frame.getContentPane().add(rdbtnGenerateRandomly);

		btnGroup = new ButtonGroup();
		btnGroup.add(rdbtnEnterManually);
		btnGroup.add(rdbtnGenerateRandomly);

		JLabel lblValueToBe = new JLabel("Value to be search");
		lblValueToBe.setBounds(35, 184, 110, 14);
		frame.getContentPane().add(lblValueToBe);

		searchValue = new JTextField();
		searchValue.setBounds(155, 181, 106, 20);
		frame.getContentPane().add(searchValue);
		searchValue.setColumns(10);

		JButton btnAddInteger = new JButton("Add Integer");
		btnAddInteger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (rdbtnEnterManually.isSelected()) {

					int newInt = Integer.parseInt(new_int.getText().toString());
					myarray = addElement(myarray, newInt);
					System.out.println(Arrays.toString(myarray));
				}
				if (rdbtnGenerateRandomly.isSelected()) {

					Random r = new Random();
					for (int i = 0; i < arraySize; i++) {
						myarray[i] = r.nextInt(100);
					}
					System.out.println(Arrays.toString(myarray));
				}
			}

			int[] addElement(int[] a, int e) {

				a = Arrays.copyOf(a, a.length);
				a[index] = e;
				index++;
				return a;

			}
		});
		btnAddInteger.setBounds(35, 87, 99, 23);
		frame.getContentPane().add(btnAddInteger);

		JButton btnDeleteInteger = new JButton("Delete Integer");
		btnDeleteInteger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDeleteInteger.setBounds(155, 87, 106, 23);
		frame.getContentPane().add(btnDeleteInteger);

		JButton btnSortArray = new JButton("Sort Array");
		btnSortArray.setBounds(294, 128, 128, 23);
		frame.getContentPane().add(btnSortArray);

		btnSortArray.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Arrays.sort(myarray);
				List<Integer> intlist = Arrays.stream(myarray).boxed().collect(Collectors.toList());
				System.out.println(Arrays.toString(myarray));
				DefaultListModel<Integer> listModel = new DefaultListModel<Integer>();
				for (int i = 0; i < intlist.size(); i++) {
					listModel.addElement(intlist.get(i));
				}
				list.setModel(listModel);

			}
		});

		JButton btnSearchInArray = new JButton("Search in Array");
		btnSearchInArray.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// boolean result = IntStream.of(myarray).anyMatch(x -> x ==
				// Integer.parseInt(searchValue.getText().toString()));
				int foundIndex = 0;
				int currentIndex = -1;
				for (int i : myarray) {
					currentIndex++;
					if (Integer.parseInt(searchValue.getText().toString()) == i) {
						foundIndex = currentIndex;
						JOptionPane.showMessageDialog(frame, "Element found at index of " + foundIndex, "Search Result",
								JOptionPane.INFORMATION_MESSAGE);
					}
					
				}
				

				if (foundIndex == '\0') {
					JOptionPane.showMessageDialog(frame, "Could not find an element", "Search Result",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnSearchInArray.setBounds(294, 175, 128, 23);
		frame.getContentPane().add(btnSearchInArray);

		list = new JList<Integer>();
		list.setBounds(155, 225, 57, 158);
		list.setVisible(true);
		frame.getContentPane().add(list);
		frame.getContentPane()
				.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { lblNewLabel, list,
						lblEnterNewInteger, sizeOfArray, new_int, btnAddInteger, btnDeleteInteger, rdbtnEnterManually,
						rdbtnGenerateRandomly, btnSortArray, lblValueToBe, searchValue, btnSearchInArray }));

		// declaring array size
		sizeOfArray.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				arraySize = Integer.parseInt(sizeOfArray.getText().toString());
				System.out.println(arraySize);
				myarray = new int[arraySize];
			}
		});

	}
}
