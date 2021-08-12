package com.jetbrains;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {

    public static void main(String[] args) {
	// write your code here
//        display d=new display();

//    judge t=new judge();
        home h=new home();
//       showMarks a=new showMarks();
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from team;");
            ResultSet rs=ps.executeQuery();

            while(rs.next()) {
                for (int i = 1; i <= 7; i++) {
                    System.out.print(rs.getString(i)+" ");
                }
                System.out.println();

            }

            con.close();
        }catch(Exception e){System.out.println(e);}
    }
}
