import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

public class CardLayoutExample extends JFrame implements ActionListener{
   
	/** Height of the  frame. */
	private static final int DEFAULT_HEIGHT = 302;
	/** Width of the game frame. */
	private static final int DEFAULT_WIDTH = 400;
   
   public CardLayout layout;
   private JPanel  cardPanel;
   
   private JButton backB;
   private JButton nextB;
   
   // ****  constructor ******
   public CardLayoutExample() {
      setTitle("Card Layout Example");
      JPanel navigationPanel = new JPanel();
      
      backB = new JButton("<");
      navigationPanel.add(backB);
      backB.addActionListener(this);
      
      nextB = new JButton(">");
      navigationPanel.add(nextB);
      nextB.addActionListener(this);
      
      navigationPanel.setBackground(Color.black);
      navigationPanel.setForeground(Color.white);
      add(navigationPanel, "North");
      
      cardPanel = new JPanel();
      layout = new CardLayout();
      cardPanel.setLayout(layout);
      
      add(cardPanel, "South");
      
      Panel p1 = new Panel();
      JPanel p2 = new CalcExample();
      Panel p3 = new Panel();
      
      Button b1 = new Button("1");
      //Button b2 = new Button("2");
      Button b3 = new Button("3");
      
      p1.add(b1);
      //p2.add(b2);
      p3.add(b3);
      
      cardPanel.add("first", p1);
      cardPanel.add("second", p2);
      cardPanel.add("third", p3);
      
      this.reshape(20, 20, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		
   }
   
   public static void main(String[] args) {
      JFrame f = new CardLayoutExample();
      // f.reshape(10,10,200,200);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.show();
   }
   /**
    * Respond to  clicks on either the Next button or Back button
    */
   public void actionPerformed(ActionEvent e) {
      if (e.getSource().equals(backB) ) {
         layout.previous(cardPanel);
      }
      else if (e.getSource().equals(nextB) ) {
         layout.next(cardPanel);
      }
   }
   
   public boolean action(Event evt, Object obj) {
      if (obj.equals(">")) {
         layout.next(cardPanel);
      }
      else return super.action(evt,obj);
      return true;
   }
}