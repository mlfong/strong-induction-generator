package org.stronginduction;
/***
 * Class name:			StrongInduction
 * Class description:	Generates a strong induction problem
 * 
 * @author 				mlfong
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class StrongInduction extends JFrame
{
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 900;
	private static final int HEIGHT = 600;

	public int small, big, minBasis;

	private JButton calculateB, exitB;
	private JButton btnAnswer, btnNewButton;
	private JLabel question, basisStep;

	private ArrayList<JTextField> basisStepJTextFields;
	private ArrayList<JLabel> basisStepJLabels;
	private ArrayList<Integer> basisStepAnswers;
	
	//Button handlers:
	private CalculateButtonHandler cbHandler;
	private AnswerButtonHandler aHandler;
	private HintButtonHandler hHandler;
	private ExitButtonHandler ebHandler;
	private JTextField textField_1, textField_2, textField_3, textField_4,
	textField_5, textField_6, textField_7, textField_8, textField_9,
	textField_10, textField_11, textField_12;
	private JLabel label_3, label_4;

	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblInductiveStep;
	private JLabel lblWeCanForm;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblConclusion;
	private JLabel lblWeHaveCompleted;
	private JLabel lblAndThe;
	private JLabel lblSoBy;
	private JLabel lblTheStatement;
	private JLabel lblNewLabel_5;

	public StrongInduction()
	{
		setLocation(300, 100);
		Random generator = new Random();
		boolean coprime = false;
		while(!coprime)
		{
			big = generator.nextInt(10) + 6;
			small = (generator.nextInt(big) + 2) ;
			while( Math.abs(big - small) < 2 )
				small = (generator.nextInt(big) + 2) ;
			if(big < small)
			{
				int temp = big;
				big = small;
				small = temp;
			}//end if
			small = small > 6 ? 6 : small;
			if(small % 2 != big % 2)
				if(small % 3 != big % 3)
					if(small % 5 != big % 5)
						coprime = true;
		}//end while
		minBasis = (big-1) * (small-1);

		boolean bool = generator.nextBoolean();
		String qtype = (bool ? "amount of postage" : "dollar value");
		String mtype = (bool ? "cent stamps" : "dollars");

		basisStepJLabels = new ArrayList<JLabel>();
		for(int i = 0; i < small; i++)
		{
			basisStepJLabels.add(new JLabel("P("));
			basisStepJLabels.add(new JLabel("):"));
			basisStepJLabels.add(new JLabel("" + small + (bool ? " cent(s)" : " dollars(s)")));			
			basisStepJLabels.add(new JLabel("" + big + (bool ? " cent(s)" : " dollar(s).")));
		}//end for
		for(int i = 0; i < basisStepJLabels.size(); i = i + 4)
		{
			basisStepJLabels.get(i).setBounds(92, 61 + (i/4)*25, 16, 14);
			basisStepJLabels.get(i+1).setBounds(133, 61 +(i/4)*25 , 16, 14);
			basisStepJLabels.get(i+2).setBounds(180, 61 + (i/4)*25, 110, 14);
			basisStepJLabels.get(i+3).setBounds(310, 61 + (i/4)*25, 110, 14);
			getContentPane().add(basisStepJLabels.get(i));
			getContentPane().add(basisStepJLabels.get(i+1));
			getContentPane().add(basisStepJLabels.get(i+2));
			getContentPane().add(basisStepJLabels.get(i+3));
		}//end for

		basisStepJTextFields = new ArrayList<JTextField>();
		basisStepAnswers = new ArrayList<Integer>();
		for(int i = 0; i < small; i++)
		{
			basisStepJTextFields.add(new JTextField());
			basisStepJTextFields.add(new JTextField());
			basisStepJTextFields.add(new JTextField());
		}//end for
		for(int i = 0; i < basisStepJTextFields.size(); i = i + 3)
		{
			//set bounds
			basisStepJTextFields.get(i).setBounds(109, 58 + (i/3)*25, 23, 20);
			basisStepJTextFields.get(i+1).setBounds(142, 58 + (i/3)*25, 23, 20);
			basisStepJTextFields.get(i+2).setBounds(274, 58 + (i/3)*25, 23, 20);
			//make answers
			basisStepAnswers.add(minBasis+(i/3));
			MyInt x = new MyInt(minBasis + (i/3));
			MyInt a = new MyInt(small);
			MyInt b = new MyInt(big);
			MyInt s = new MyInt(0);
			MyInt t = new MyInt(0);
			findSolution(x,a,b,s,t);
			basisStepAnswers.add(s.getValue());
			basisStepAnswers.add(t.getValue());
			//add to pane
			getContentPane().add(basisStepJTextFields.get(i));
			getContentPane().add(basisStepJTextFields.get(i+1));
			getContentPane().add(basisStepJTextFields.get(i+2));
		}//end for
		calculateB = new JButton("Check");
		calculateB.setBounds(8, 401, 387, 80);
		cbHandler = new CalculateButtonHandler();
		calculateB.addActionListener(cbHandler);

		
		
		
		exitB = new JButton("Exit");
		exitB.setBounds(8, 480, 780, 80);
		ebHandler = new ExitButtonHandler();
		exitB.addActionListener(ebHandler);

		setTitle("Strong Induction by mlfong");
		question = new JLabel("Prove that every " + qtype + " greater than " + (minBasis - 1) + 
				" can be made up of only " + small + " " + mtype + " and " + big + " " + mtype + ".");
		question.setBounds(10, 11, 864, 27);

		getContentPane().setLayout(null);

//		setResizable(false);

		getContentPane().add(question);

		basisStep = new JLabel("Basis Step:");
		basisStep.setBounds(10, 36, 122, 27);
		getContentPane().add(basisStep);

		getContentPane().add(calculateB);
		getContentPane().add(exitB);

		JLabel lblPjWeCan = new JLabel("We can form");
		lblPjWeCan.setBounds(93, 252, 137, 14);
		getContentPane().add(lblPjWeCan);

		JLabel lblInductiveHypothesis = new JLabel("Inductive Hypothesis: P(j)");
		lblInductiveHypothesis.setBounds(10, 227, 220, 14);
		getContentPane().add(lblInductiveHypothesis);

		textField_1 = new JTextField();
		textField_1.setBounds(240, 249, 23, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JLabel label = new JLabel("<=");
		label.setBounds(273, 252, 20, 14);
		getContentPane().add(label);

		textField_2 = new JTextField();
		textField_2.setBounds(303, 249, 23, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);

		lblNewLabel = new JLabel((bool ? "postage" : "dollars") + " with");
		lblNewLabel.setBounds(399, 252, 106, 14);
		getContentPane().add(lblNewLabel);

		textField_3 = new JTextField();
		textField_3.setBounds(515, 249, 23, 20);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);

		label_3 = new JLabel(">=");
		label_3.setBounds(548, 252, 20, 14);
		getContentPane().add(label_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(578, 249, 23, 20);
		getContentPane().add(textField_4);

		lblNewLabel_1 = new JLabel(mtype + ".");
		lblNewLabel_1.setBounds(611, 252, 192, 14);
		getContentPane().add(lblNewLabel_1);

		label_4 = new JLabel("<=");
		label_4.setBounds(336, 252, 20, 14);
		getContentPane().add(label_4);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(366, 249, 23, 20);
		getContentPane().add(textField_5);

		lblInductiveStep = new JLabel("Inductive Step: P(j)->P(k+1)");
		lblInductiveStep.setBounds(10, 276, 220, 14);
		getContentPane().add(lblInductiveStep);

		lblWeCanForm = new JLabel("We can form ");
		lblWeCanForm.setBounds(52, 301, 100, 14);
		getContentPane().add(lblWeCanForm);

		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(162, 298, 39, 20);
		getContentPane().add(textField_6);

		lblNewLabel_2 = new JLabel((bool ? "cents" : "dollars")+ " so we can add a");
		lblNewLabel_2.setBounds(211, 301, 178, 14);
		getContentPane().add(lblNewLabel_2);

		textField_7 = new JTextField();
		textField_7.setBounds(411, 298, 23, 20);
		getContentPane().add(textField_7);
		textField_7.setColumns(10);

		lblNewLabel_3 = new JLabel((bool ? "cent stamp" : "dollar bill") + " to make");
		lblNewLabel_3.setBounds(444, 301, 155, 14);
		getContentPane().add(lblNewLabel_3);

		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(609, 298, 39, 20);
		getContentPane().add(textField_8);

		lblNewLabel_4 = new JLabel((bool ? "cent postage." : "dollars."));
		lblNewLabel_4.setBounds(658, 301, 174, 14);
		getContentPane().add(lblNewLabel_4);

		lblConclusion = new JLabel("Conclusion:");
		lblConclusion.setBounds(10, 326, 106, 14);
		getContentPane().add(lblConclusion);

		lblWeHaveCompleted = new JLabel("We have completed 1.");
		lblWeHaveCompleted.setBounds(92, 351, 170, 14);
		getContentPane().add(lblWeHaveCompleted);

		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(259, 348, 82, 20);
		getContentPane().add(textField_9);

		lblAndThe = new JLabel("and 2.");
		lblAndThe.setBounds(351, 351, 46, 14);
		getContentPane().add(lblAndThe);

		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(408, 348, 82, 20);
		getContentPane().add(textField_10);

		lblSoBy = new JLabel(", so by the principle of");
		lblSoBy.setBounds(488, 351, 160, 14);
		getContentPane().add(lblSoBy);

		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(10, 376, 106, 20);
		getContentPane().add(textField_11);

		lblTheStatement = new JLabel(", the statement is true for all "+(bool ? "postage" : "change") + " greater than or equal to");
		lblTheStatement.setBounds(115, 379, 453, 14);
		getContentPane().add(lblTheStatement);

		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(578, 376, 39, 20);
		getContentPane().add(textField_12);

		lblNewLabel_5 = new JLabel((bool ? "cents." : "dollars."));
		lblNewLabel_5.setBounds(627, 379, 174, 14);
		getContentPane().add(lblNewLabel_5);
		
		btnAnswer = new JButton("Answer");
		btnAnswer.setBounds(392, 401, 396, 80);
		getContentPane().add(btnAnswer);
		
		
		btnNewButton = new JButton("Hint");
		btnNewButton.setBounds(785, 401, 89, 159);
		getContentPane().add(btnNewButton);

		aHandler = new AnswerButtonHandler();
		btnAnswer.addActionListener(aHandler);
		
		hHandler = new HintButtonHandler();
		btnNewButton.addActionListener(hHandler);
		
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public class AnswerButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			textField_1.setText("" + StrongInduction.this.minBasis);
			textField_2.setText("j");
			textField_3.setText("k");
			textField_4.setText("" + (StrongInduction.this.minBasis + StrongInduction.this.small - 1));
			textField_5.setText("k");
			textField_6.setText("k-" + (StrongInduction.this.small - 1));
			textField_7.setText("" + StrongInduction.this.small);
			textField_8.setText("k+1");
			textField_9.setText("basis step");
			textField_10.setText("inductive step");
			textField_11.setText("strong induction");
			textField_12.setText("" + StrongInduction.this.minBasis);
			
			for(int i = 0; i < basisStepJTextFields.size(); i++)
			{
				String ans = basisStepAnswers.get(i).toString();
				basisStepJTextFields.get(i).setText(ans);
			}//end for
			
		}
	}
	public class HintButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Random generator = new Random();
			int someNum = generator.nextInt(5) + 1;
			if(someNum == 1)
				JOptionPane.showMessageDialog(null, "Minimum postage/dollar amount is (n-1)(m-1) for the two given values.");
			else if(someNum == 2)
				JOptionPane.showMessageDialog(null, "Number of base cases is the min(m,n).");
			else if(someNum == 3)
				JOptionPane.showMessageDialog(null, "For the Ind. Step, we want a number in range of j.");
			else if(someNum == 4)
				JOptionPane.showMessageDialog(null, "Don't forget to write out the whole conclusion.");
			else if(someNum == 5)
				JOptionPane.showMessageDialog(null, "The given two values, m and n, must be coprime to work!");
			else if(someNum == 6)
				JOptionPane.showMessageDialog(null, "You should never see this :)");
			
		}
	}
	
	public class CalculateButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String minBasis = textField_1.getText();
			String j = textField_2.getText();
			String k2 = textField_3.getText();
			String maxBasis = textField_4.getText();
			String k = textField_5.getText();
			String kMinus = textField_6.getText();
			String small = textField_7.getText();
			String kPlusOne = textField_8.getText();
			String basisStep = textField_9.getText();
			String indStep = textField_10.getText();
			String mathInd = textField_11.getText();
			String minBasis2 = textField_12.getText();

			int numWrong = 0;
			
			for(int i = 0; i < basisStepJTextFields.size(); i++)
			{
				String jtext = basisStepJTextFields.get(i).getText();
				String ans = basisStepAnswers.get(i).toString();
				if(! jtext.equalsIgnoreCase(ans))
				{
					numWrong++;
					basisStepJTextFields.get(i).setBackground(Color.RED);
				}//end if
				else
					basisStepJTextFields.get(i).setBackground(Color.WHITE);
			}//end for

			if( !StrongInduction.isNum(minBasis2)|| Integer.parseInt(minBasis2) != StrongInduction.this.minBasis)
			{
				numWrong++;
				textField_12.setBackground(Color.RED);
			}//end if
			else
				textField_12.setBackground(Color.WHITE);

			if(!mathInd.equalsIgnoreCase("strong induction") )
			{
				numWrong++;
				textField_11.setBackground(Color.red);
			}
			else
				textField_11.setBackground(Color.white);

			if(!indStep.equalsIgnoreCase("inductive step"))
			{
				numWrong++;
				textField_10.setBackground(Color.red);
			}
			else
				textField_10.setBackground(Color.white);

			if(!basisStep.equalsIgnoreCase("basis step"))
			{
				numWrong++;
				textField_9.setBackground(Color.red);
			}
			else
				textField_9.setBackground(Color.white);

			if(!kPlusOne.equalsIgnoreCase("k+1"))
			{
				numWrong++;
				textField_8.setBackground(Color.red);
			}
			else
				textField_8.setBackground(Color.white);

			if( !StrongInduction.isNum(small)|| Integer.parseInt(small) != StrongInduction.this.small)
			{
				numWrong++;
				textField_7.setBackground(Color.RED);
			}//end if
			else
				textField_7.setBackground(Color.WHITE);

			String oneLess = "k-" + (StrongInduction.this.small - 1);
			if(!kMinus.equalsIgnoreCase(oneLess))
			{
				numWrong++;
				textField_6.setBackground(Color.red);
			}
			else
				textField_6.setBackground(Color.white);

			if( !StrongInduction.isNum(minBasis)|| Integer.parseInt(minBasis) != StrongInduction.this.minBasis)
			{
				numWrong++;
				textField_1.setBackground(Color.RED);
			}//end if
			else
				textField_1.setBackground(Color.WHITE);

			if( !StrongInduction.isNum(maxBasis)|| Integer.parseInt(maxBasis) != StrongInduction.this.minBasis + StrongInduction.this.small - 1)
			{
				numWrong++;
				textField_4.setBackground(Color.RED);
			}//end if
			else
				textField_4.setBackground(Color.WHITE);

			if(!j.equalsIgnoreCase("j"))
			{
				numWrong++;
				textField_2.setBackground(Color.RED);
			}
			else
				textField_2.setBackground(Color.WHITE);

			if(!k.equalsIgnoreCase("k"))
			{
				numWrong++;
				textField_5.setBackground(Color.RED);
			}//end if
			else
				textField_5.setBackground(Color.white);

			if(!k2.equalsIgnoreCase("k"))
			{
				numWrong++;
				textField_3.setBackground(Color.RED);
			}//end if
			else
				textField_3.setBackground(Color.white);
			if(numWrong == 0)
				JOptionPane.showMessageDialog(null, "Everything is right!");
		}//end actionPerformed

	}

	private static boolean isNum(String s)
	{
		try { Integer.parseInt(s); }
		catch (NumberFormatException nfe)
		{return false;}
		return true;
	}//end isNum

	public class ExitButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}

	//x = as + bt
	public static void findSolution(MyInt x, MyInt a, MyInt b, MyInt s, MyInt t)
	{
		if(x.getValue() % a.getValue() == 0)
		{
			s.setValue(x.getValue() / a.getValue());
			return;
		}//end if
		else if ( x.getValue() % b.getValue() == 0)
		{
			t.setValue(x.getValue() / b.getValue());
			return;
		}//end else if
		else
		{
			x.setValue(x.getValue() - a.getValue());
			s.setValue(s.getValue() + 1);
			findSolution(x,a,b,s,t);
		}
	}

	public static void main(String[] args)
	{
		StrongInduction si = new StrongInduction();
		si.setVisible(true);
	}
}
