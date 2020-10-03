import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TicTacToeGUI extends JFrame implements ActionListener
{
    private JLabel winner;
    private JButton position[][];

    private JFrame label;
    private JPanel panel1;

    int hold[][]={{-1,-1,-1},{-1,-1,-1},{-1,-1,-1}};
    TicTacToeGUI()
    {
        label=new JFrame();
        label.setTitle("TIC TAC TOE");

        label.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        winner=new JLabel("Play Player 1");

        position=new JButton[3][3];
        int i,j;
        for(i=0;i<3;i++)
        {
            for(j=0;j<3;j++)
            {
                position[i][j]=new JButton("");
                position[i][j].setPreferredSize(new Dimension(70,70));
            }
        }
        panel1=new JPanel();
        panel1.setLayout(new GridLayout(3,3));
        for(i=0;i<3;i++)
        {
            for(j=0;j<3;j++)
            {
                panel1.add(position[i][j]);
            }
        }

        label.setLayout(new BorderLayout());
        label.add(winner,BorderLayout.NORTH);
        label.add(panel1,BorderLayout.CENTER);

        label.pack();
        for(i=0;i<3;i++)
        {
            for(j=0;j<3;j++)
            {
                position[i][j].addActionListener(this);
            }
        }
        label.setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==position[0][0])setButton(0,0);
        if(e.getSource()==position[0][1])setButton(0,1);
        if(e.getSource()==position[0][2])setButton(0,2);
        if(e.getSource()==position[1][0])setButton(1,0);
        if(e.getSource()==position[1][1])setButton(1,1);
        if(e.getSource()==position[1][2])setButton(1,2);
        if(e.getSource()==position[2][0])setButton(2,0);
        if(e.getSource()==position[2][1])setButton(2,1);
        if(e.getSource()==position[2][2])setButton(2,2);
    }
    public void setButton(int a,int b)
    {
        if(hold[a][b]==-1 && winner.getText().equals("Play Player 1"))
        {
            winner.setText("Play Player 2");
            position[a][b].setText("X");
            position[a][b].setFont(new Font("Verdana",Font.BOLD,32));
            hold[a][b]=1;
        }
        else if(hold[a][b]==-1 && winner.getText().equals("Play Player 2"))
        {
            winner.setText("Play Player 1");
            position[a][b].setText("O");
            position[a][b].setFont(new Font("Verdana",Font.BOLD,32));
            hold[a][b]=0;
        }
        if(check()==1)
        {
            winner.setText("Player 1 wins");
            JOptionPane.showMessageDialog(label,"Player 1 wins");
            label.setVisible(false);
            new TicTacToeGUI();
        }
        else if(check()==0)
        {
            winner.setText("Player 2 wins");
            JOptionPane.showMessageDialog(label,"Player 2 wins");
            label.setVisible(false);
            new TicTacToeGUI();
        }
        else if(check()==-1)
        {
            winner.setText("Its a tie");
            JOptionPane.showMessageDialog(label,"Its a tie");
            label.setVisible(false);
            new TicTacToeGUI();
        }
    }
    public int check()
    {
        if(hold[0][0]==hold[0][1] && hold[0][0]==hold[0][2] && hold[0][0]!=-1)return hold[0][0];
        else if(hold[1][0]==hold[1][1] && hold[1][0]==hold[1][2] && hold[1][0]!=-1)return hold[1][0];
        else if(hold[2][0]==hold[2][1] && hold[2][0]==hold[2][2] && hold[2][0]!=-1)return hold[2][0];

        else if(hold[0][0]==hold[1][0] && hold[0][0]==hold[2][0] && hold[0][0]!=-1)return hold[0][0];
        else if(hold[0][1]==hold[1][1] && hold[0][1]==hold[2][1] && hold[0][1]!=-1)return hold[0][1];
        else if(hold[0][2]==hold[1][2] && hold[0][2]==hold[2][2] && hold[0][2]!=-1)return hold[0][2];


        else if(hold[0][0]==hold[1][1] && hold[0][0]==hold[2][2] && hold[0][0]!=-1)return hold[0][0];
        else if(hold[0][2]==hold[1][1] && hold[0][2]==hold[2][0] && hold[0][2]!=-1)return hold[0][2];
        else if(hold[0][0]!=-1 && hold[0][1]!=-1 && hold[0][2]!=-1
                && hold[1][0]!=-1 && hold[1][1]!=-1 && hold[1][2]!=-1
                && hold[2][0]!=-1 && hold[2][1]!=-1 && hold[2][2]!=-1)return -1;
        else return 2;
    }
    public static void main(String args[])
    {
        new TicTacToeGUI();
    }
}