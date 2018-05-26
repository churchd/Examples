import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.URL;
import java.util.List;
import java.util.ArrayList;

public class CalcExample extends JPanel {
   public TextField displayField;
   public JPanel   buttonPanel;
   public JButton  oneButton, twoButton, threeButton,
                  fourButton, fiveButton, sixButton,
                  sevenButton, eightButton, nineButton,
                  zeroButton, addButton, subtractButton,
                  multiplyButton, divideButton,
                  equalsButton, clearButton, dotButton;
   // operands and operators for calulation
   private double op1 = 0;
   private double op2 = 0;
   private String operator = null;
   
   // other flags
   private boolean nextOp;
   
   // *********** constructor **************
   public CalcExample() {
      setLayout(new BorderLayout());
      displayField = new TextField();
      add("North", displayField);
      
      buttonPanel = new JPanel();
      buttonPanel.setLayout(new GridLayout(5,4));
      
      oneButton = new JButton("1");
      oneButton.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e) {
            //displayField.setText("One");
            numPress("1");
         }
      });
      twoButton = new JButton("2");
      twoButton.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e) {
            numPress("2");
         }
      });
      threeButton = new JButton("3");
      threeButton.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e) {
            numPress("3");
         }
      });
      fourButton = new JButton("4");
      fourButton.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e) {
            numPress("4");
         }
      });
      fiveButton = new JButton("5");
      fiveButton.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e) {
            numPress("5");
         }
      });
      sixButton = new JButton("6");
      sixButton.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e) {
            numPress("6");
         }
      });
      sevenButton = new JButton("7");
      sevenButton.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e) {
            numPress("7");
         }
      });
      eightButton = new JButton("8");
      eightButton.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e) {
            numPress("8");
         }
      });
      nineButton = new JButton("9");
      nineButton.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e) {
            numPress("9");
         }
      });
      zeroButton = new JButton("0");
      zeroButton.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e) {
            numPress("0");
         }
      });
      addButton = new JButton("+");
      addButton.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e) {
            opKeyPress("+");
         }
      });

      subtractButton = new JButton("-");
      subtractButton.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e) {
            opKeyPress("-");
         }
      });
      multiplyButton = new JButton("*");
      multiplyButton.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e) {
            opKeyPress("*");
         }
      });
      divideButton = new JButton("/");
      divideButton.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e) {
            opKeyPress("/");
         }
      });
      equalsButton = new JButton("=");
      equalsButton.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e) {
            equalsOp();
         }
      });
      clearButton = new JButton("C");
      clearButton.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e) {
            cPress("C");
         }
      });
      dotButton = new JButton(".");
      dotButton.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e) {
            numPress(".");
         }
      });
      
      buttonPanel.add(oneButton);
      buttonPanel.add(twoButton);
      buttonPanel.add(threeButton);
      buttonPanel.add(addButton);
      
      buttonPanel.add(fourButton);
      buttonPanel.add(fiveButton);
      buttonPanel.add(sixButton);
      buttonPanel.add(multiplyButton);
      
      buttonPanel.add(sevenButton);
      buttonPanel.add(eightButton);
      buttonPanel.add(nineButton);
      buttonPanel.add(divideButton);
      
      buttonPanel.add(dotButton);
      buttonPanel.add(zeroButton);
      buttonPanel.add(equalsButton);
      
      buttonPanel.add(clearButton);
      
      buttonPanel.add(subtractButton);
      
      add("Center", buttonPanel);
      
      displayField.setText("Start");
      
      nextOp = true;
      operator = "";
   }
   
   // ************ Methods ****************
   
   /*
    * Handle Number buttons
    * @ param is the string version of the
    * button that was pressed
   */
   private void numPress(String num) {
      //String temp = displayField.getText() + num;
      //displayField.setText(temp);
      if (nextOp){
        // setup for a new operation
        op1 = 0;
        displayField.setText("");
        nextOp = false;
        if (num.equals(".")) {
          // if first char is a dot then insert a leading 0
          displayField.setText("0");
        }
      }
      String temp = displayField.getText() + num;
      displayField.setText(temp);
   }
   
   
   private void cPress(String b) {
      displayField.setText("");
      op1 = 0;
      op2 = 0;
      nextOp = true;
      operator = "";
   }
   
   /*
    * Handle Operator buttons: * / + -
    * @ param is the string version of the
    * button that was pressed
   */
   private void opKeyPress(String k) {
     if ( !operator.equals("") ) {
       //perform pending operation before proceding
       equalsOp();
     }
     
     op2 = getDoubleValue(displayField.getText());
     nextOp = true;
     operator = k;
   }
   /*
    * Handle Equals button
   */
  
   private void equalsOp() {
     // perform equals operation
     if ( operator.equals(null) ) {
       // no operation pending
       return;
     }
     op1 = getDoubleValue(displayField.getText());
     executeOp();
     nextOp = true;
     operator = "";
   }
   
  private double getDoubleValue(String txt)
  {
    double realVal = 0;
    // check for a negative number
    int sign = 1;
    if (txt.startsWith("-"))
    {
      sign = -1;
      txt = txt.replace("-", "");
    }
    // check for a percent
    int percent = 1;
    if (txt.endsWith("%"))
    {
      percent = 100;
      txt = txt.replace("%", "");
    }
    // start parsing through the string for a decimal point
    int point = txt.indexOf(".");
    
    if (point == -1) point = txt.length();
    // start scanning from left of the point adding up the digits
    for ( int i = point - 1; i >= 0; i--)
    {
      realVal += realConvert(txt.charAt(i)) * Math.pow(10, point - i - 1) ;
    }
    
    // now scan to the right adding the the decimal digits
    for (int i = point + 1; i < txt.length(); i++)
    {
      realVal += realConvert(txt.charAt(i)) * Math.pow(10, point - i );
    }
    
    // add in the sign
    realVal *= sign;
    // factor in the percent
    realVal = realVal / percent;
    //return (double) point;
    return realVal;
  }
  // convert char to a double 
  private double realConvert(char ch)
  {
    char compare = '0';  // char to use as the base
    int res = 0; // stores the result
    for (int x = 0; x < 10; x++)
    {
      if ( x == (int)(ch - compare) ) res = x;
    }
    return (double) res;
  }
   
   private void executeOp() {
     // do the pending operation
     // double res = 0;
     if (operator.equals("*")) {
       op1 = op2 * op1;
     }
     else if (operator.equals("/") ) {
       if ( op1 == 0 ) {
         op1 = 1.0E-7;
       }
       op1 = op2 / op1;
     }
     else if (operator.equals("+") ) {
       op1 = op2 + op1;
     }
     else if (operator.equals("-") ) {
       op1 = op2 - op1;
     }
     operator = "";  // maybe let calling method handle this?
     
     displayField.setText(convertStr(op1));
   }
   
   /*
    * Converts a double into a string
   */
   private String convertStr(double n) {
     String res = String.valueOf(n);
     return res;
   }
   // ************ MAIN ******************
   public static void main(String[] args) {
      JFrame baseFrame = new JFrame();
      CalcExample c = new CalcExample();
      baseFrame.add("Center", c);
      baseFrame.reshape(10,10,200,200);
      baseFrame.setTitle("Calculator Example");
      baseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      baseFrame.show();
   }
}