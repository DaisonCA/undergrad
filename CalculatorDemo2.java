/* PROGRAM NO : Section E, Q1
 * AUTHOR     : Daison C A
 * DATE		  : 29th November 2020
 * OBJECTIVE  : To demonstrate writing to and reading from
 * 				from a file
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class CalculatorDemo2 {
		public static void main(String args[]) {
			new Calculator();
		}
}

class CalculatorComponents {
	JButton[] btnList; 			// stores all buttons other than result
	JButton resultBtn;
	JLabel textView;			// displays input and result
	JPanel resultBtnHolder;		// holds result button
	JPanel tvHolder;			// holds textView
	JPanel numpad;				// holds the number pad 
	JFrame frame;				// holds the panels together
	
	boolean isOperatorPressed;
	boolean isDotPressed;
	boolean isResultBtnPressed;
}

//The code for calculator UI and Logic
class Calculator {
	Calculator() {
		createCalculator();
	}
	
	//UI component
	private void createCalculator() {
		CalculatorComponents calcComp = new CalculatorComponents();
		calcComp.btnList = new JButton[20];
		calcComp.resultBtn = new JButton();
		calcComp.textView = new JLabel();
		calcComp.resultBtnHolder = new JPanel();
		calcComp.tvHolder = new JPanel();
		calcComp.numpad = new JPanel();
		calcComp.frame = new JFrame();
		
		calcComp.isDotPressed= false;
		calcComp.isOperatorPressed = false;
		calcComp.isResultBtnPressed = false;
		
		setTextView(calcComp);
		setButtons(calcComp);
		btnPressed(calcComp);
		addResultBtn(calcComp);
		setFrame(calcComp);
	}
	
	//UI component
		private void setTextView(CalculatorComponents calcComp) {
			
			calcComp.textView.setFont(new Font("Arial", Font.PLAIN, 50));
			calcComp.textView.setForeground(Color.WHITE);
			
			calcComp.tvHolder.setOpaque(true);
			calcComp.tvHolder.setSize(300, 200);
			calcComp.tvHolder.setForeground(Color.WHITE);
			calcComp.tvHolder.setBackground(Color.DARK_GRAY);
			calcComp.tvHolder.setLayout(new FlowLayout(FlowLayout.LEFT));
			calcComp.tvHolder.add(calcComp.textView);
		}
	
	//UI component
	private void setButtons(CalculatorComponents calcComp) {
		
		String[] btnName = new String[] {"1", "2", "3", "plus","4",
							"5", "6", "minus", "7", "8", "9", 
							"multiply", "dot", "0", "CLR", "mod"};
		
		//Generate buttons
		for (int i = 0; i < btnName.length; i++) {
			
			JButton btn = new JButton();
			String name = btnName[i];
			
			btn.setText(name);
			btn.setBorderPainted(false);
			btn.setBackground(Color.BLACK);
			btn.setForeground(Color.WHITE);
			btn.setFont(new Font("Arial", Font.BOLD, 20));
			
			if (btnName[i] == "plus") {
				btn.setText("+");
				btn.setBackground(Color.WHITE);
				btn.setForeground(Color.BLACK);
			}
			
			if (btnName[i] == "minus") {
				btn.setText("-");
				btn.setFont(new Font("Arial", Font.BOLD, 32));
				btn.setBackground(Color.WHITE);
				btn.setForeground(Color.BLACK);
			}
			
			if (btnName[i] == "multiply") {
				btn.setText("x");
				btn.setBackground(Color.WHITE);
				btn.setForeground(Color.BLACK);
			}
			
			if (btnName[i] == "mod") {
				btn.setText("%");
				btn.setFont(new Font("Arial", Font.BOLD, 27));
				btn.setBackground(Color.WHITE);
				btn.setForeground(Color.BLACK);
			}
			
			if (btnName[i] == "dot") {
				btn.setText(".");
			}
			
			if (btnName[i] == "CLR") {
				btn.setText("CLR");
				btn.setBackground(Color.RED);
			}
			
			calcComp.btnList[i] = btn;
			calcComp.numpad.add(btn);
		}
		
		//Setting buttons to numpad
		GridLayout layout = new GridLayout(4,4);
		layout.setHgap(0);
		layout.setVgap(0);
		calcComp.numpad.setLayout(layout);
		calcComp.numpad.setSize(300,350);
		calcComp.numpad.setVisible(true);
	}
	
	//Logic Component: for button presses other than the result button
		public void btnPressed (CalculatorComponents calcComp) {
			for (int i = 0; i < 16; i++) {
				
				JButton btn = calcComp.btnList[i];
				
				btn.addActionListener(new ActionListener() {
					
					public void actionPerformed (ActionEvent e) {
						if (btn.getText() == "CLR") {
							calcComp.tvHolder.removeAll();
							calcComp.textView.setFont(new Font("Arial",Font.PLAIN, 40));
							calcComp.textView.setForeground(Color.WHITE);
							calcComp.textView.setText("");
							calcComp.tvHolder.add(calcComp.textView);
							calcComp.isDotPressed = false;
							calcComp.isOperatorPressed = false;
							calcComp.isResultBtnPressed = false;
						}
						
						if ((btn.getText() == "+" ||btn.getText() == "-"||btn.getText() == "%"
							|| btn.getText() == "x") && (calcComp.isOperatorPressed == false)
							) {
							calcComp.tvHolder.removeAll();
							calcComp.textView.setFont(new Font("Arial",Font.PLAIN, 40));
							calcComp.textView.setForeground(Color.WHITE);
							calcComp.textView.setText(calcComp.textView.getText() + btn.getText());
							calcComp.tvHolder.add(calcComp.textView);
							calcComp.isOperatorPressed = true;
						}
						
						if (btn.getText()== "1"|| btn.getText()== "2"|| btn.getText()== "3"||
							btn.getText()== "4"|| btn.getText()== "5"|| btn.getText()== "5"||
							btn.getText()== "6"|| btn.getText()== "7"|| btn.getText()== "8"||
							btn.getText()== "9"|| btn.getText()== "0") {
							calcComp.tvHolder.removeAll();
							calcComp.textView.setFont(new Font("Arial",Font.PLAIN, 40));
							calcComp.textView.setForeground(Color.WHITE);
							calcComp.textView.setText(calcComp.textView.getText() + btn.getText());
							calcComp.tvHolder.add(calcComp.textView);
							if(calcComp.isDotPressed && (calcComp.isOperatorPressed==true)) {
								calcComp.isDotPressed = false;
							}
							if(calcComp.isResultBtnPressed) {
								calcComp.textView.setText("");
								calcComp.isResultBtnPressed = false;
							}
						}
						
						if (btn.getText() == "." && calcComp.isDotPressed == false) {
							calcComp.tvHolder.removeAll();
							calcComp.textView.setFont(new Font("Arial",Font.PLAIN, 40));
							calcComp.textView.setForeground(Color.WHITE);
							calcComp.textView.setText(calcComp.textView.getText() + btn.getText());
							calcComp.tvHolder.add(calcComp.textView);
							calcComp.isDotPressed = true;
						}
						
						
						
						setFrame(calcComp);	
					}
				});
			}
		}
		
		//UI component: Set Frame
		private void setFrame(CalculatorComponents calcComp) {
			calcComp.frame.setTitle("Dasion C A | roll no 23");
			calcComp.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			calcComp.frame.add(calcComp.tvHolder);
			calcComp.frame.add(calcComp.numpad);
			calcComp.frame.add(calcComp.resultBtnHolder);
			calcComp.frame.setLayout(new BoxLayout(calcComp.frame.getContentPane(), BoxLayout.Y_AXIS));
			calcComp.frame.setSize(350,550);
			calcComp.frame.setVisible(true);
		}
	
	//UI + Logical component: for result button and result computation
	public void addResultBtn (CalculatorComponents calcComp){
		Color color = new Color(70,137,2);
		
		//Result button settings
		calcComp.resultBtn.setBorder(null);
		calcComp.resultBtn.setBackground(color);
		calcComp.resultBtn.setForeground(Color.WHITE);
		//calcComp.resultBtn.setMinimumSize(new Dimension (350,100));
		calcComp.resultBtn.setFont(new Font("Arial",Font.BOLD, 30));
		calcComp.resultBtn.setText("            =            ");
		calcComp.resultBtn.addActionListener(new ActionListener() {
			
			String delimit;
			float result;
			
			public void actionPerformed(ActionEvent e) {
				
				if(!calcComp.isResultBtnPressed) {
					String textViewString = calcComp.textView.getText();
					
					try {
						if(textViewString.contains("+") || textViewString.contains("-") ||
						   textViewString.contains("x") || textViewString.contains("%")) {
							
							if(textViewString.contains("+")) this.delimit = "\\+";
							if(textViewString.contains("-")) this.delimit = "\\-";
							if(textViewString.contains("x")) this.delimit = "x";
							if(textViewString.contains("%")) this.delimit = "%";
							
							String[] tokens = calcComp.textView.getText().split(delimit);
							float a = Float.parseFloat(tokens[0]);
							float b = Float.parseFloat(tokens[1]);
							
							if(textViewString.contains("+")) this.result = a+b;
							if(textViewString.contains("-")) this.result = a-b;
							if(textViewString.contains("x")) this.result = a*b;
							if(textViewString.contains("%")) this.result = a%b;
							calcComp.textView.setText(Float.toString(result));
							calcComp.isResultBtnPressed = true;
						}
					} catch (Exception a) {
						calcComp.textView.setText("Arithmetic Exception");
					}
				}
			}
		});
		
		calcComp.resultBtnHolder.add(calcComp.resultBtn);
		calcComp.resultBtnHolder.setOpaque(true);
		calcComp.resultBtnHolder.setBackground(color);
		calcComp.resultBtnHolder.setForeground(Color.WHITE);
		calcComp.resultBtnHolder.setMaximumSize(new Dimension(350,100));
	}
}