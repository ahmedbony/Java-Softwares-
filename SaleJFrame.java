import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;

public class SaleJFrame implements PropertyListener {

	private JFrame frmPosSoftware;
	private JTextField ItemIDField;
	private JTextField QtyField;
	private JTextField TotalCost;
	
	ProcessSaleController psc;
	SaleLineItem sLineItem;										//ProductSpec of the last added item is stored in this
	LinkedList<SaleLineItem> sli;
	int counter;
	String total;
	String vat;
	String GTotal;
	int selectIndex;
	boolean error = false; 			// This boolean controls Table update
	boolean saleInit = false;									// This boolean checks whether the Sale is initialed or not 
	boolean invalidInput = false;								// This boolean checks whether the input is valid or not (i.e. Valid input Number) 
	boolean noDiscount = false;
	boolean emptyCart = true;
	private JTextField VAT;
	private JTextField GrandTotal;
	private JTextField discountField;
	Beeper beep;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaleJFrame window = new SaleJFrame();
					window.frmPosSoftware.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SaleJFrame() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		try {
			psc = new ProcessSaleController();
			psc.makeNewSale();
			counter = 0;
			//DateText.setText(psc.sale.getDate());

		} catch (Exception e2) {
			System.out.println("New Sale Button Exception");
		}
		
		frmPosSoftware = new JFrame();
		frmPosSoftware.setTitle("POS Software");
		frmPosSoftware.setBounds(100, 100, 675, 493);
		frmPosSoftware.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPosSoftware.getContentPane().setLayout(null);

		

		
		// -----------------------TextIDFields----------------------------
		
		ItemIDField = new JTextField();
		ItemIDField.setBounds(86, 44, 226, 20);
		frmPosSoftware.getContentPane().add(ItemIDField);
		ItemIDField.setColumns(10);

		JLabel lblItemId = new JLabel("Item ID");
		lblItemId.setBounds(10, 47, 46, 14);
		frmPosSoftware.getContentPane().add(lblItemId);

		QtyField = new JTextField();
		QtyField.setBounds(86, 75, 226, 20);
		frmPosSoftware.getContentPane().add(QtyField);
		QtyField.setColumns(10);

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(10, 78, 66, 14);
		frmPosSoftware.getContentPane().add(lblQuantity);

		
		//--------------------AddButton----------------------------
		
		
		JButton btnAddItem = new JButton("Add Item");

		btnAddItem.setBounds(86, 106, 89, 23);
		frmPosSoftware.getContentPane().add(btnAddItem);

		//--------------------------Table Set--------------------------
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(86, 140, 517, 180);
		frmPosSoftware.getContentPane().add(scrollPane);

		DefaultTableModel model = new DefaultTableModel();
		final JTable table = new JTable(model);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "SL#", "Item Name", "Unit Price", "Quantity", "Sub Total" }));

		
		//------------------------Total Cost Field-----------------------
		
		TotalCost = new JTextField();
		TotalCost.setFont(new Font("Tahoma", Font.BOLD, 11));
		TotalCost.setEditable(false);
		TotalCost.setBounds(520, 331, 86, 20);
		frmPosSoftware.getContentPane().add(TotalCost);
		TotalCost.setColumns(10);

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTotal.setBounds(464, 331, 46, 14);
		frmPosSoftware.getContentPane().add(lblTotal);

		// -----------------Add button Action-----------------------------

		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//---------------------Calling the worker Method
				
				addNewItemButtonAction(e);

				// ------------------Update table-------------------------
				
				if (!error) {											//checking whether Update is valid or not
					sli = psc.getSaleLineItemList();

					sLineItem = sli.getLast();							//Just adding the latest item in current Sale List

					int colNum = table.getModel().getColumnCount();

					Object[] item = new Object[colNum];

					item[0] = ++counter;
					item[1] = sLineItem.getPs().getName();
					item[2] = sLineItem.getPs().getPrice();
					item[3] = sLineItem.getQuantity();
					item[4] = sLineItem.getSubtotal();
					((DefaultTableModel) table.getModel()).addRow(item);

					total = Double.toString(psc.sale.getPreDiscountTotal());
					TotalCost.setText(total);
					
					
						try {
							vat = Integer.toString(psc.sale.getVATAmount());
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (InstantiationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IllegalAccessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						VAT.setText(vat);
					
					
					
						try {
							GTotal = Double.toString(psc.sale.getPreDiscountTotal() + psc.sale.getVATAmount());
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (InstantiationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IllegalAccessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						GrandTotal.setText(GTotal);

					
				}
			}
		});
		
		//--------------------New Sale button---------------------------
		
		JButton btnNewSale = new JButton("New Sale");
		btnNewSale.setBounds(86, 11, 89, 23);
		frmPosSoftware.getContentPane().add(btnNewSale);
		
		JLabel lblVat = new JLabel("VAT:");
		lblVat.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblVat.setBounds(464, 359, 46, 14);
		frmPosSoftware.getContentPane().add(lblVat);
		
		VAT = new JTextField();
		VAT.setFont(new Font("Tahoma", Font.BOLD, 11));
		VAT.setEditable(false);
		VAT.setColumns(10);
		VAT.setBounds(520, 357, 86, 20);
		frmPosSoftware.getContentPane().add(VAT);
		
		GrandTotal = new JTextField();
		GrandTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		GrandTotal.setEditable(false);
		GrandTotal.setColumns(10);
		GrandTotal.setBounds(519, 411, 86, 20);
		frmPosSoftware.getContentPane().add(GrandTotal);
		
		JLabel lblGrandTotal = new JLabel("Grand Total:");
		lblGrandTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGrandTotal.setBounds(427, 413, 86, 14);
		frmPosSoftware.getContentPane().add(lblGrandTotal);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setBounds(84, 331, 161, 20);
		frmPosSoftware.getContentPane().add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select a Discount",
				"Senior Discount (10%)", "Eid Discount", "Best for Store", "Best for Customer"}));
		
		discountField = new JTextField();
		discountField.setForeground(new Color(34, 139, 34));
		discountField.setFont(new Font("Tahoma", Font.BOLD, 11));
		discountField.setEditable(false);
		discountField.setBounds(520, 384, 86, 20);
		frmPosSoftware.getContentPane().add(discountField);
		discountField.setColumns(10);
		
		JLabel lblDiscount = new JLabel("Discount:");
		lblDiscount.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDiscount.setBounds(444, 384, 66, 14);
		frmPosSoftware.getContentPane().add(lblDiscount);
		
		JButton calcDiscount  = new JButton("Calculate Discount");
		calcDiscount.setBounds(86, 408, 159, 23);
		frmPosSoftware.getContentPane().add(calcDiscount);
calcDiscount.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				
				selectIndex = comboBox.getSelectedIndex();
				
				/*if(!saleInit){
					JOptionPane.showMessageDialog(frmPosSoftware, "Please Start a Sale First");
					return;
				}
				
				if(emptyCart){
					JOptionPane.showMessageDialog(frmPosSoftware, "Please Add an Item First");
					return;
				}
					
				if(selectIndex==0 || noDiscount){
					JOptionPane.showMessageDialog(frmPosSoftware, "Please Select a Discount Option");
					return;
				}*/
					
				calculateDiscountButtonAction(arg0);	
				total = Double.toString(psc.sale.getPreDiscountTotal());
				TotalCost.setText(total);
				
				try {
					GTotal = Integer.toString((int) psc.sale.getGrandTotal());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
				

				
				GrandTotal.setText(GTotal);
				
					discountField.setText(Integer.toString( (int) ((int) psc.sale.getPreDiscountTotal()- psc.sale.getTotal())));
				
				ItemIDField.setText("");
				QtyField.setText("");
				beep.onPropertyEvent(psc.sale, "sale.total", (int) psc.sale.getTotal());
				
				
			}
		});

		
		// -----------------------New Sale Button Action----------------------------
	

		btnNewSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//----------------Calling Worker Method----------------
				
				newSaleButtonAction(e);
				
				
				//-----------------Clearing Table-----------------------
				
				table.setModel(new DefaultTableModel(new Object[][] {},
						new String[] { "SL#", "Item Name", "Unit Price", "Quantity", "Sub Total" }));
						TotalCost.setText("");
						ItemIDField.setText("");
						QtyField.setText("");
						VAT.setText("");
						GrandTotal.setText("");
						

			}
		});
		
		
	}

	

	// -------------------Button Action Functions ----------------

	public void addNewItemButtonAction(ActionEvent e) {

		int id = Integer.parseInt(ItemIDField.getText());
		int quantity = Integer.parseInt(QtyField.getText());
		
		if (psc.getProductSpec(id) == null) {
			error = true;
			JOptionPane.showMessageDialog(frmPosSoftware,  "No Such Item Found!!!");

		} else {
			try {
				psc.addItem(id, quantity);
				ItemIDField.setText("");
				QtyField.setText("");
				error = false;
				noDiscount =false;
				emptyCart = false;

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
	}

	public void newSaleButtonAction(ActionEvent e) {
		try {
			psc = new ProcessSaleController();
			psc.makeNewSale();
			counter = 0;
			beep = new Beeper();
			beep.register(psc);
			counter = 0;
			saleInit = true;																	//Marking the button action of "New Sale" button
			noDiscount = true;
			emptyCart = true;
			//DateText.setText(psc.sale.getDate());

		} catch (Exception e2) {
			System.out.println("New Sale Button Exception");
		}

	}
	
public void calculateDiscountButtonAction(ActionEvent e){
		
		
		
		if(selectIndex==1){
			psc.selectedPricingStrategy("SeniorCitizenDiscount");
		}
		
		
		if(selectIndex==2){
			 psc.selectedPricingStrategy("EidDiscount");
		}
		
		
		if(selectIndex==3){
			psc.selectedPricingStrategy("CompositeBestForStorePS");
		}
		
		if(selectIndex==4){
			psc.selectedPricingStrategy("CompositeBestForCustomerPS");
		}
		
		
		
	}

public void register(ProcessSaleController psc){
	psc.sale.addPropertyListener(this);
}

public void onPropertyEvent(Sale source, String name, int value) {
	 
	if(name.equals("sale.total")){		
		
		 
		 TotalCost.setText(Integer.toString(value));
		 
	 }
	
	if(name.equals("vat.Amount")){		 			
		
		
		 VAT.setText(Integer.toString(value));
		 
	 }
	
	
}


}
