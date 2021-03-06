import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 
 */

/**
 * @author woytek
 * @author Isabelle Schroeder
 * 
 * This program plays a variation on the game blackjack using two six-sided dice 
 * instead of cards. No gambling though.
 *
 */
public class GameOfTwentyOne {
	
	static JLabel label;
	static JButton roll;
	static JTextField textField;
	static JButton hold;
	static GridBagLayout layout;
	static JLabel otherLabel;
	
	static int total = 0; // user's total
	static Dice DieOne = new Dice(6); // first roll
	static Dice DieTwo = new Dice(6); // second roll
	static int computer = 0; // computer's total
	
	/**
	 * This is the main class for GameOfTwentyOne program.
	 * @param args main arguments for the GameOfTwentyOne class
	 */
	public static void main(String[] args) {

		JFrame frame = new JFrame( "Game of 21" ); // setting frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close frame for default
		
		layout = new GridBagLayout(); // used GridBag layout
	    frame.setLayout( layout );
	        
	    GridBagConstraints c = new GridBagConstraints(); // setting constraints for a while
	    c.fill = GridBagConstraints.BOTH;
	        
	    c.weightx = 0.0;
	    c.gridwidth = GridBagConstraints.RELATIVE;
        
	    // adding roll again button and its constraints
	    c.gridwidth = GridBagConstraints.RELATIVE;
        roll = new JButton( "Roll again" );
        roll.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        ActionListener listener = new SuperListener();
        roll.addActionListener(listener);
        layout.setConstraints( roll, c );
        frame.add( roll );
        
        // adding hold button and its constraints
        c.gridwidth = GridBagConstraints.REMAINDER;
        hold = new JButton( "Hold" );
        hold.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        hold.addActionListener(listener);
        layout.setConstraints( hold, c );
        frame.add( hold );
        
        // adding other label so I can have two wordie things
        otherLabel = new JLabel( " " );
        otherLabel.setFont( new Font("TimesRoman", Font.PLAIN, 18) );
        layout.setConstraints( otherLabel, c );
        frame.add( otherLabel );
        
        // everything gets packed happily into frame and frame size can be changed
        frame.pack(); 
        frame.setSize( frame.getPreferredSize() );
        
        // original rolling
	    DieOne.roll(); // user can choose to roll the dice until their score is over 21
		
		total += DieOne.getValue();
	
		DieTwo.roll();
		total += DieTwo.getValue(); // dice rolls for user
	
		DieOne.roll();
		computer += DieOne.getValue();
	
		DieTwo.roll();
		computer += DieTwo.getValue(); // secret dice rolls for computer
	    

	    label = new JLabel( "You're total is " + total +"." ); // telling people their score
	    label.setFont( new Font("TimesRoman", Font.PLAIN, 18) );
        layout.setConstraints( label, c );
        frame.add( label );
        
        // make the whole thing visible because we don't want to play an invisible game
        frame.setVisible( true ); 
	}
	
	static class SuperListener implements ActionListener {

	        public void actionPerformed(ActionEvent e) {
	         
	            switch( e.getActionCommand() ){ // using a cool little switch
	            	case "Roll again": // roll again
	            		if( total <= 21 ){
	            		DieOne.roll(); // user can choose to roll the dice until their score is over 21
	    			
	            		total += DieOne.getValue();
	    		
	    				DieTwo.roll();
	    				total += DieTwo.getValue(); // dice rolls for user
	    		
	    				DieOne.roll();
	    				computer += DieOne.getValue();
	    		
	    				DieTwo.roll();
	    				computer += DieTwo.getValue(); // secret dice rolls for computer
	           
	    				// option to roll again
	    				label.setText( "You're new total is " + total + ". Roll again?" );
	            		}
	            		break; // Switches need breaks. This is very serious.
	            	case "Hold": // no more rolling
	            		// showing the totals
	            		label.setText( "My total is " + computer + ". Yours is " + total + ".");
            	    
	            		if ( total > computer && total <= 21 ){
	            			otherLabel.setText( "You win!!" ); 
	            			// user winning when neither player goes over 21
	    			
	            		} else if( computer > total && computer <= 21 ){
	            			otherLabel.setText( "I WIN!! Not like I cheated or anything.." ); 
	            			// computer winning when neither player goes over 21
	            			
	            		} else if( total == computer && total <= 21 ){
	            			otherLabel.setText( "We tied!" ); // Players tie, no one wins
	    		
	            		} else if( total > 21 && computer <= 21 ){
	            			otherLabel.setText( "You went over 21! I win:P" );
	            			// User goes over 21, computer automatically wins
	    		
	            		} else if( computer > 21 && total <= 21 ){
	            			otherLabel.setText( "I went over 21! I guess you win." ); 
	            			// user wins when computer goes over 21
	    			
	            		} else if( computer > 21 && total > 21 ){
	            			otherLabel.setText( "We both lose!" ); // everyone is sad
	            		} break;
	            }
	      } 
	 }

}
