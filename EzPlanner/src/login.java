import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.Container;

import java.util.Properties;

import java.sql.*;

public class login extends JFrame implements Runnable
{
    private static final String jdbcDriver = "com.mysql.jdbc.Driver";
    private static final String dburl = "jdbc:mysql://127.0.0.1/cal";
    static final String user = "root";
    static final String pass = "avihome";

    public login()
    { super("EZY-L Calendar"); }

    public int validate(JTextField u, JTextField p)
    {
        Connection conn = null;
        Statement stmt = null;
        int id = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dburl,user,pass);
            String[] cred = {u.getText(), p.getText()};
            
            stmt = conn.createStatement();
            String valid = String.format("SELECT id FROM user WHERE username='%s' AND password=MD5('%s');", u.getText(), p.getText());
            ResultSet rs = stmt.executeQuery(valid);

            while(rs.next())
            { id = rs.getInt("id"); }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(SQLException se) { se.printStackTrace(); }
        catch(Exception e) { e.printStackTrace(); }
        finally{
            try{ if(stmt!=null) { stmt.close(); } }
            catch(SQLException se2) {}
            try{ if(conn!=null) { conn.close(); } }
            catch(SQLException se) { se.printStackTrace(); } }
        return id;
    }

    public void run()
    {
        setSize(250,100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container cp = getContentPane();
        cp.setLayout(new GridLayout(3,2));
        JLabel un = new JLabel("username: ");
        JLabel pw = new JLabel("password: ");
        JTextField username = new JTextField();
        JTextField password = new JPasswordField();
        JButton signin = new JButton("Sign In");
        JButton exit = new JButton("Exit");
        signin.addActionListener(e -> {
            int check = validate(username, password);
            if (check == 0)
            {
                username.setText("wrong username or password");
                password.setText("");
            }
            else
            {
                dispose();
                String[] cred = {Integer.toString(check)};
                Calendar.main(cred);
            }
        });
        exit.addActionListener(e -> { System.exit(0); });
        cp.add(un);
        cp.add(username);
        cp.add(pw);
        cp.add(password);
        cp.add(signin);
        cp.add(exit);
        setVisible(true);
    }

    public static void main(String [] args)
    { javax.swing.SwingUtilities.invokeLater(new login()); }
}
