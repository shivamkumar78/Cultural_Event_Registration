package com.jetbrains;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class display extends JFrame implements ActionListener {
    JLabel head;
    JFrame f,backFrame;
    JPanel panel;
    JButton addTeam,giveMarks,back;
    Image backgroundImage;
    String judge;
    public display(JFrame backFrame,String judge){
        this.judge=judge;
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\lenovo\\Desktop\\judge.jpeg"));
        }catch(Exception e){ }
        this.backFrame=backFrame;
        head=new JLabel("Foundation-X");
        head.setBounds(150,20,200,50);
        f=new JFrame();
        panel=new JPanel();
        add(panel);
        back=new JButton("Sign Out");
        back.setBounds(0,0,100,30);
        back.addActionListener(this);
        panel.setLayout(null);
        head.setFont(new Font("Serif",Font.BOLD,30));
        addTeam=new JButton("Register a Team");
        addTeam.setFont(new Font("Serif",Font.BOLD,20));
        addTeam.setBounds(125,330,250,30);
        giveMarks=new JButton("Award Marks");
        giveMarks.setFont(new Font("Serif",Font.BOLD,20));
        giveMarks.setBounds(125,280,250,30);
        panel.add(addTeam);
        panel.add(giveMarks);
        addTeam.addActionListener(this);
        giveMarks.addActionListener(this);
        setLayout(new BorderLayout());
        panel.setSize(500,500);
        panel.add(head);
        panel.add(back);
        setVisible(true);
        setLocation(0,0);
        setSize(500,500);
        setDefaultCloseOperation(3);
    }
    @Override
    public void actionPerformed(ActionEvent e){
            if(e.getSource()==addTeam){
                setVisible(false);
                team newTeam=new team(this);
            }
            if(e.getSource()==back){
                setVisible(false);
                backFrame.setVisible(true);
            }
            if(e.getSource()==giveMarks){
                setVisible(false);
                awardMarks a=new awardMarks(this,judge);
            }
    }
    public void paint( Graphics g ) {
        super.paint(g);
        g.drawImage(backgroundImage, 0, 0, null);
    }
}
