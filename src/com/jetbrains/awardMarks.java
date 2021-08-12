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

public class awardMarks extends JFrame implements ActionListener {
    JLabel id,marks,head;
    JTextField iTxt,mTxt;
    JButton back,save;
    JFrame backFrame;
    String judge;
    Image backgroundImage;
    public awardMarks(JFrame backFrame,String j){
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\lenovo\\Desktop\\team.jpeg"));
        }catch(Exception e){ }
        judge=j;
        this.backFrame=backFrame;
        id=new JLabel("Team ID :");
        marks=new JLabel("Marks :");
        head=new JLabel("");
        head.setBounds(150,30,200,40);
        id.setBounds(125,200,100,30);
        marks.setBounds(125,250,100,30);
        head.setFont(new Font("serif",Font.BOLD,24));
        iTxt=new JTextField();
        mTxt=new JTextField();
        iTxt.setBounds(250,200,100,30);
        mTxt.setBounds(250,250,100,30);
        back=new JButton("Back");
        back.setBounds(0,0,80,30);
        back.addActionListener(this);
        save=new JButton("SAVE");
        save.setBounds(170,300,100,30);
        save.addActionListener(this);
        add(id);
        add(back);
        add(save);
        add(iTxt);
        add(mTxt);
        add(marks);
        add(head);

        setVisible(true);
        setSize(500,500);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==back){
            setVisible(false);
            backFrame.setVisible(true);
        }
        if(e.getSource()==save){
            if(iTxt.getText().equals("")){
                JOptionPane.showMessageDialog(this,"User ID can not be left Blank.");
            }
            else {
                if(mTxt.getText().equals("")||Integer.parseInt(mTxt.getText())<0||Integer.parseInt(mTxt.getText())>10){
                    JOptionPane.showMessageDialog(this,"Marks can only Range from 0-10.");

                }
                else {
                    String query="";
                    if(judge.equals("judge1")){
                        query="update team set marks1=? where id=?;";
                    }
                    else if(judge.equals("judge2")){
                        query="update team set marks2=? where id=?;";

                    }
                    else if(judge.equals("judge3")){
                        query="update team set marks3=? where id=?;";

                    }
                    try {
                        Connection con = DB.getConnection();
                        PreparedStatement ps = con.prepareStatement("select count(id) from team where id=?;");
                        ps.setString(1, iTxt.getText());
                        ResultSet rs=ps.executeQuery();
                        rs.next();
                        if(rs.getString(1).equals("0")){
                            JOptionPane.showMessageDialog(this,"Team ID is not Registered.");

                        }
                        else {
                            ps = con.prepareStatement(query);
                            ps.setString(1, mTxt.getText());
                           // ps.setString(2, mTxt.getText());
                            ps.setString(2, iTxt.getText());
                            ps.execute();
                            JOptionPane.showMessageDialog(this,"SUCCESS : Marks Saved");
                        }
                        con.close();
                    } catch (Exception ex) {
                        String[] s = ex.getMessage().split(":");
                        System.out.println(ex.getMessage());
//                        JOptionPane.showMessageDialog(this, s[s.length - 1]);
                    }
                }

            }
        }
    }
    public void paint( Graphics g ) {
        super.paint(g);
        g.drawImage(backgroundImage, 0, 0, null);
    }
}
