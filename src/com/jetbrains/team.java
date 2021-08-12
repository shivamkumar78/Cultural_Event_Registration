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

public class team extends JFrame implements ActionListener{
    JButton save,back;
    JButton[] update;
    JPanel panel;
    JFrame f;
    JLabel[] label;
    JTextField[] text;
    String id,size,year;
    String type;
    JFrame Display;
    Image backgroundImage;
    public team(JFrame f){
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\lenovo\\Desktop\\team.jpeg"));
        }catch(Exception e){ }
        this.Display=f;
        panel=new JPanel();
        f=new JFrame();
        save=new JButton("Register");
        update=new JButton[3];
        back=new JButton("BACK");
        label=new JLabel[5];
//        id=new Integer();
//        size=new Integer();
//        year=new Integer();
//        type=new String();
        text=new JTextField[4];
        add(panel);
        panel.setLayout(null);

        setLayout(new BorderLayout());
        back.addActionListener(this);
        save.addActionListener(this);

        label[0]=new JLabel("Please Enter Team Credentials");
        label[1]=new JLabel("Team ID  :");
        label[2]=new JLabel("Performance Type :");
        label[3]=new JLabel("Team Size :");
        label[4]=new JLabel("Batch Year :");
        label[0].setBounds(100,50,300,40);
        int offset=0;
        for(int i=1;i<5;i++){
            label[i].setBounds(50,120+offset,200,40);
            panel.add(label[i]);
            offset+=50;
        }
        offset=0;
        for(int i=0;i<4;i++){
            text[i]=new JTextField();
            text[i].setBounds(170,120+offset,200,40);
            panel.add(text[i]);
            if(i>0) {
                update[i-1] = new JButton("UPDATE");
                update[i-1].setFont(new Font("Serif", Font.BOLD, 7));
                update[i-1].addActionListener(this);
                update[i-1].setBounds(375, 140 + offset, 70, 20);
                panel.add(update[i-1]);
            }
            offset+=50;
        }
        back.setBounds(0,0,100,30);
        save.setBounds(200,400,100,40);
        setVisible(true);
        //f.add(back);
        panel.add(save);
        panel.add(back);
        panel.setBackground(Color.lightGray);
        panel.setSize(500,500);
        //panel.setLocation(520,180);
        setLocation(0,0);
        setSize(500,500);
        setDefaultCloseOperation(3);
    }
    @Override
    public void actionPerformed(ActionEvent e){
            if(e.getSource()==save) {
                id = text[0].getText();
                type = text[1].getText();
                size = text[2].getText();
                year = text[3].getText();
                if (id.equals("") || type.equals("") || size.equals("") || year.equals("")) {
                    JOptionPane.showMessageDialog(this, "Incorrect Credential");
                } else {
                    try {
                        Connection con = DB.getConnection();

                        PreparedStatement ps = con.prepareStatement("insert into team values(?,?,?,?,null,null,null)");
                        ps.setString(1, id);
                        ps.setString(2, type);
                        ps.setString(3, size);
                        ps.setString(4, year);
                        ps.execute();
                        JOptionPane.showMessageDialog(this,"SUCCESS : Team Registered");
                        con.close();
                    } catch (Exception ex) {
                        String[] s = ex.getMessage().split(":");
                        System.out.println(ex.getMessage());
                        JOptionPane.showMessageDialog(this, s[s.length - 1]);
                    }
                }
            }
            if(e.getSource()==update[0]||e.getSource()==update[1]||e.getSource()==update[2]){
                id = text[0].getText();
                type = text[1].getText();
                size = text[2].getText();
                year = text[3].getText();
                String query="update team set ";
                String in="";
                if(id.equals("")){
                    JOptionPane.showMessageDialog(this, "ID cannot be left blank.");

                }
                else{
                    if(e.getSource()==update[0]){
                        query+="type=?";
                        in=type;

                    }
                    else if(e.getSource()==update[1]){
                        query+="size=?";
                        in=size;
                    }
                    else if(e.getSource()==update[2]){
                        query+="year=?";
                        in=year;
                    }
                    query+=" where id=?;";


                }

                if(in.equals("")){
                    JOptionPane.showMessageDialog(this, "This Field cannot be left blank.");
                }
                else {
                    try {
                        Connection con = DB.getConnection();
                        PreparedStatement ps = con.prepareStatement("select count(id) from team where id=?;");
                        ps.setString(1, id);
                        ResultSet rs=ps.executeQuery();
                        rs.next();
                        if(rs.getString(1).equals("0")){
                            JOptionPane.showMessageDialog(this,"Team ID is not Registered.");

                        }
                        else {
                            ps = con.prepareStatement(query);
                            ps.setString(1, in);
                            ps.setString(2, id);
                            ps.execute();
                            JOptionPane.showMessageDialog(this, "SUCCESS : Update Successful");
                        }
                        con.close();
                    } catch (Exception ex) {
                        String[] s = ex.getMessage().split(":");
                        System.out.println(ex.getMessage());
                        JOptionPane.showMessageDialog(this, s[s.length - 1]);
                    }
                }
            }
            if(e.getSource()==back){
                setVisible(false);
                Display.setVisible(true);
            }
    }
    public void paint( Graphics g ) {
        super.paint(g);
        g.drawImage(backgroundImage, 0, 0, null);
    }

}
