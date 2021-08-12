package com.jetbrains;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class judge extends JFrame implements ActionListener {
    JLabel head;
    JFrame f;
    JPanel panel;
    JLabel id,pass;
    JButton login,back;
    JTextField iTxt;
    JPasswordField pTxt;
    JFrame backFrame;
    Image backgroundImage;
    public judge(JFrame fr){
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\lenovo\\Desktop\\login.png"));
        }catch(Exception e){ }
        backFrame=fr;
        head=new JLabel("Login Portal");
        head.setBounds(150,20,200,50);
        f=new JFrame();
        panel=new JPanel();
        add(panel);

        panel.setLayout(null);
        iTxt=new JTextField();
        pTxt=new JPasswordField();
        back=new JButton("Back");
        back.setBounds(0,0,80,30);
        back.addActionListener(this);
        login=new JButton("Sign In");
        login.setBackground(Color.red);
        login.setFont(new Font("Serif",Font.BOLD,16));
        login.setForeground(Color.white);
        login.setBounds(180,258,100,30);
        iTxt.setBounds(78,112,350,30);
        pTxt.setBounds(78,182,350,30);
        head.setFont(new Font("Serif",Font.BOLD,30));
        id=new JLabel("User ID :");
        id.setFont(new Font("Serif",Font.BOLD,20));
        id.setBounds(100,200,100,30);
        pass=new JLabel("Password :");
        pass.setFont(new Font("Serif",Font.BOLD,20));
        pass.setBounds(100,250,100,30);
        id.setForeground(Color.RED);
        pass.setForeground(Color.RED);
        panel.add(id);
        panel.add(pass);
        panel.add(iTxt);
        panel.add(pTxt);
        panel.add(login);
        panel.add(back);
        login.addActionListener(this);
        setLayout(new BorderLayout());
        panel.setSize(500,500);
        panel.add(head);
        //add(head);
        setVisible(true);
        setLocation(0,0);
        setSize(500,500);
        setDefaultCloseOperation(3);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==login){

            try{
                Connection con=DB.getConnection();

                PreparedStatement ps=con.prepareStatement("select  password from jury where id=?");
                ps.setString(1,iTxt.getText());
                ResultSet rs=ps.executeQuery();
                //System.out.println("ls "+rs.getString(1));

                rs.next();
                System.out.println(rs.getRow());
                System.out.println(iTxt.getText());
                System.out.println(pTxt.getText());
                if(rs.getRow()==0){
                    JOptionPane.showMessageDialog(this,"Wrong User ID");

                }
                else if(rs.getString(1).equals(pTxt.getText())){
                    setVisible(false);
                    display d=new display(this,iTxt.getText());
                }
                else{
                    JOptionPane.showMessageDialog(this,"Wrong Password");
                }
                con.close();
            }catch(Exception ex){System.out.println(ex);}

        }
        if(e.getSource()==back){
            setVisible(false);
            backFrame.setVisible(true);
        }
    }
    public void paint( Graphics g ) {
        super.paint(g);
        g.drawImage(backgroundImage, 0, 0, null);
    }
}
