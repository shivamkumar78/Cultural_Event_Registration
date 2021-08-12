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
import java.sql.ResultSetMetaData;

public class showMarks extends JFrame implements ActionListener {
    String[] column;
    String[][] data;
    JTable table;
    JFrame backFrame;
    JButton back;
    public showMarks(JFrame backFrame){
        setBackground(Color.lightGray);
        this.backFrame=backFrame;
        back=new JButton("Back");
        back.setBounds(210,400,80,30);
        add(back);
        back.addActionListener(this);
        try{
            Connection con=DB.getConnection();
            PreparedStatement ps=con.prepareStatement("select * from team");
            ResultSet rs=ps.executeQuery();
            ResultSetMetaData rsmd=rs.getMetaData();
            int cols=rsmd.getColumnCount();
            column=new String[cols];
            for(int i=0;i<cols;i++){
                column[i]=rsmd.getColumnName(i+1);
            }
            rs.last();
            int rows=rs.getRow();
            rs.beforeFirst();;
            data= new String[rows][cols];
            int count=0;
            while(rs.next()){
                for(int i=1;i<=cols;i++){
                    data[count][i-1]=rs.getString(i);
                }
                count++;
            }
            con.close();
        }catch(Exception e){System.out.println(e);}
        table=new JTable(data,column);

        JScrollPane sp=new JScrollPane(table);

        setLayout(new BorderLayout());
        add(sp);
        setVisible(true);
        setSize(500,500);
        setDefaultCloseOperation(3);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==back){
            setVisible(false);
            backFrame.setVisible(true);
        }
    }

}
