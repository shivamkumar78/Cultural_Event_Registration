package com.jetbrains;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class home extends JFrame implements ActionListener {
    JLabel head;
    JFrame f;
    JPanel panel;
    JButton judgePortal,showMarks;
    Image backgroundImage;
    public home(){

        head=new JLabel("Foundation-X");
        head.setBounds(150,20,200,50);
        f=new JFrame("Foundation X");
        panel=new JPanel();

        panel.setLayout(null);
        head.setFont(new Font("Serif",Font.BOLD,30));
        judgePortal=new JButton("Judge Portal");
        judgePortal.setFont(new Font("Serif",Font.BOLD,20));
        judgePortal.setBounds(125,200,250,30);
        showMarks=new JButton("Show Marks");
        showMarks.setFont(new Font("Serif",Font.BOLD,20));
        showMarks.setBounds(125,250,250,30);
        panel.add(showMarks);
        panel.add(judgePortal);

        judgePortal.addActionListener(this);
        showMarks.addActionListener(this);
        setLayout(new BorderLayout());
        panel.setSize(500,500);
        panel.add(head);
        add(panel);
        //add(head);
        setVisible(true);
        setLocation(0,0);
        setSize(500,500);
        setDefaultCloseOperation(3);
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\lenovo\\Desktop\\iiitLogo.jpeg"));
        }catch(Exception e){ }
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==judgePortal){
            setVisible(false);
            judge j=new judge(this);
        }
        if(e.getSource()==showMarks){
            setVisible(false);
            showMarks s=new showMarks(this);
        }
    }
    public void paint( Graphics g ) {
        super.paint(g);
        g.drawImage(backgroundImage, 0, 0, null);
    }
}
