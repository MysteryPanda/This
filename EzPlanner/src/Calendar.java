import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.util.Properties;

import java.sql.*;

public class Calendar extends JFrame implements Runnable
{
    private static final String dbClassName = "com.mysql.jdbc.Driver";
    private static final String CONNECTION = "jdbc:mysql://127.0.0.1/cal";

    public Calendar()
    { super("EZY-L Calendar"); }

    public JPanel makeHeader()
    {
        JPanel header = new JPanel();
        header.setLayout(new GridLayout(1,5));
        JButton month = new JButton("Month");
        JButton week = new JButton("Week");
        JButton day = new JButton("Day");
        JButton newTask = new JButton("New Task");
        JButton logout = new JButton("Logout");
        //month.addActionListener(e -> { state = "month"; });
        //week.addActionListener(e -> { state = "week"; });
        //day.addActionListener(e -> { state = "day"; });
        logout.addActionListener(e -> { dispose(); login.main(new String[0]); });
        header.add(month);
        header.add(week);
        header.add(day);
        header.add(newTask);
        header.add(logout);
        return header;
    }

    public JPanel makeCal()
    {
        JPanel Cal = new JPanel();
        return Cal;
    }

    public void run()
    {
        setSize(950,775);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(makeHeader(), BorderLayout.NORTH);
        setVisible(true);
    }

    public static void main(String[] args)
    { javax.swing.SwingUtilities.invokeLater(new Calendar()); }
}
