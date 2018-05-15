package ui;

import game.Mastermind;

import java.util.Arrays;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

public class MastermindGUI extends Canvas implements ActionListener{

    private final int GRIDROWS = 20;
    private final int WINDOW_X = 330;
    private final int WINDOW_Y = 600;
    
    private final String MESSAGE_WIN = "Congratulations, you win!";
    private final String MESSAGE_LOSE = "You lose, please play again!";
    
    private final Color colors[] = {Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.YELLOW, Color.BLACK, Color.GRAY, Color.PINK, Color.ORANGE};
    
    private int currentColor = 0, currentRow = 0, currentColumn = 0;
    private Color guesses[][] = new Color[GRIDROWS][6];
    
    private Mastermind mastermind;
    private int pegWidth, pegHeight;

    public void paint( Graphics g ) {
        pegHeight = (int) getSize().height / (GRIDROWS+2);
        pegWidth = (int) getSize().width / 12;
        createBoard( g );
        
    }

    public void createBoard(Graphics g){

        for (int i=0; i < GRIDROWS+1; i++)
            g.drawLine(pegWidth, pegHeight*(i+1), 7*pegWidth, pegHeight*(i+1));
        for (int i=0; i < 7; i++)
            g.drawLine(pegWidth*(i+1), pegHeight, pegWidth*(i+1), pegHeight*(GRIDROWS+1));

        for (int i=1; i < (GRIDROWS*2)+2; i++)
            g.drawLine(7*pegWidth, (pegHeight*(i+1))/2, (17*pegWidth)/2, (pegHeight*(i+1))/2);
        for (int i=14; i < 17; i++)
            g.drawLine((pegWidth*(i+1))/2, pegHeight, (pegWidth*(i+1))/2, pegHeight*(GRIDROWS+1));

        for (int i=0; i < GRIDROWS+1; i++)
            g.drawLine(pegWidth, pegHeight*(i+1), 7*pegWidth, pegHeight*(i+1));

        for (int i=1; i<21; i++)
            g.drawString(Integer.toString(i), (pegWidth / 2)-3, (pegHeight*(i+1))-8);

        for (int i=0; i < 10; i++) {
            g.setColor( colors[i] );
            g.fillOval( 9*pegWidth, (i*pegHeight*2)+pegHeight, pegWidth*2, pegHeight*2 );
        }
    }

    public MastermindGUI(){
        JFrame frame = new JFrame("Mastermind");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.WHITE);
        
        JPanel controlPane = new JPanel();
        JPanel buttonPane = new JPanel();
        
        controlPane.setLayout(new BoxLayout(controlPane, BoxLayout.PAGE_AXIS));
        controlPane.setPreferredSize(new Dimension(WINDOW_X,WINDOW_Y));
        controlPane.add(this);
        
        JButton giveUp = new JButton( "Give Up" );
        JButton submit = new JButton( "Submit" );
        JButton reset = new JButton( "Reset" );
        
        buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPane.setPreferredSize(new Dimension(40,40));
        buttonPane.add(giveUp);
        buttonPane.add(submit);
        buttonPane.add(reset);
        buttonPane.setBackground(Color.WHITE);
        
        giveUp.addActionListener(this);
        submit.addActionListener(this);
        reset.addActionListener(this);
        
        addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                int x = e.getX();
                int y = e.getY();
                if (x > pegWidth*8 && currentColumn < 6) {
                    currentColor = (y - pegHeight) / (pegHeight*2);
                    if (currentColor > 9)
                        currentColor = 9;
                    guesses[currentRow][currentColumn] = colors[currentColor];
                    Graphics g = getGraphics();
                    g.setColor( colors[currentColor] );
                    g.fillRect((pegWidth*(currentColumn+1))+1, (pegHeight*(currentRow+1))+1, pegWidth-1, pegHeight-1 );
                    currentColumn++;
                }
            }
        });

        frame.getContentPane().add(buttonPane, BorderLayout.SOUTH );
        frame.getContentPane().add(controlPane, BorderLayout.NORTH);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        
        mastermind = new Mastermind();
    }

    
    public void actionPerformed(ActionEvent e) {
        
        if (e.getActionCommand().equals("Give Up")) {
            List<Color> nullInput = Arrays.asList(null, null, null, null, null, null);
            while(currentRow < 20){
                mastermind.guess(nullInput);
                currentRow++;
            }
            List<Color> solution = mastermind.getSelection();
            repaint();
            Graphics g = getGraphics();
            
            currentColumn = 0;
            currentRow = 0;
            String solutionString = ("You have given up. The solution will now be shown in row 1.");
            JOptionPane.showMessageDialog(null, solutionString);
            while(currentColumn < 6){
                g.setColor(solution.get(currentColumn));
                g.fillRect((pegWidth*(currentColumn+1))+1, (pegHeight*(currentRow+1))+1, pegWidth-1, pegHeight-1 );
                currentColumn++;
            }
        }
        
        if (e.getActionCommand().equals("Submit")) {
            List<Color> userInput = Arrays.asList(guesses[currentRow][0], guesses[currentRow][1], guesses[currentRow][2],
                    guesses[currentRow][3], guesses[currentRow][4], guesses[currentRow][5]);
            Map<Mastermind.Response, Long> response = mastermind.guess(userInput);

            long black = (long) response.get(Mastermind.Response.POSITIONAL_MATCH);
            long silver = (long) response.get(Mastermind.Response.MATCH);
            long empty = (long) response.get(Mastermind.Response.NO_MATCH);

            Graphics g = getGraphics();
            for (int i = 0; i < 6; i++) {
                if (black > i)
                    g.setColor(Color.BLACK);
                else if ((black + silver) > i)
                    g.setColor(Color.LIGHT_GRAY);
                else
                    break;
                    
                if (i < 3)
                    g.fillRect((pegWidth * (14 + i)) / 2, pegHeight * (currentRow + 1), pegWidth / 2, pegHeight / 2);
                else
                    g.fillRect((pegWidth * (11 + i)) / 2, (pegHeight * (currentRow + 1)) + (pegHeight / 2), pegWidth / 2, pegHeight / 2);
            }

            if (mastermind.getGameStatus() == Mastermind.GameStatus.WON) {
                JOptionPane.showMessageDialog(null, MESSAGE_WIN);
            } else if (mastermind.getGameStatus() == Mastermind.GameStatus.LOST) {
                JOptionPane.showMessageDialog(null, MESSAGE_LOSE);
            }

            currentRow++;
            currentColumn = 0;
        }
        
        if (e.getActionCommand().equals("Reset")) {
            Graphics g = getGraphics();
            g.setColor(Color.WHITE);
            currentColumn = 0;
            
            while(currentColumn < 6){
                guesses[currentRow][currentColumn] = Color.WHITE;
                g.fillRect((pegWidth*(currentColumn+1))+1, (pegHeight*(currentRow+1))+1, pegWidth-1, pegHeight-1 );
                currentColumn++;
            }
            currentColumn = 0;
        }
    }
    
    public static void main( String[] args ){
        MastermindGUI mastermindGUI = new MastermindGUI();
    }
}