/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelvf;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Armax
 */
public class DBbridge {
    Connection con;
    Statement stat;
    Statement stat2;
    ResultSet res;
    String sql;
    DBbridge(){
        try{
            con = DriverManager.getConnection("jdbc:MYSQL://LOCALHOST:3306/HotelDB","root","laconchadetumadre1");
            stat=con.createStatement();
            stat2=con.createStatement();
            ResultSet habitacion = stat.executeQuery("Select * from habitacion");
        }
        catch(SQLException | NumberFormatException | HeadlessException exc){
        }
    }
    public void reservacion(Date fechaa,Date fechas,String tipo,int numh,String nombre){
        try{
            Calendar start = Calendar.getInstance();
            Calendar end = Calendar.getInstance();
            SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");
            Random rand= new Random();
            String codigo = Integer.toString(rand.nextInt(900) + 100);
            int posible=10;
            int Habitot=0;
            int Habidisp=0;
            int[] idH;
            idH = new int[numh];
            int reit=0;
                start.setTime(fechaa);
                end.setTime(fechas);
                if("Doble".equals(tipo)){
                    String fecha=ft.format(fechaa);
                    ResultSet habitacion = stat.executeQuery("Select count(idH) from habitacion where tipo='Doble'");
                    while(habitacion.next()){
                        Habitot=Integer.parseInt(habitacion.getString("count(idH)"));
                    }
                    habitacion = stat.executeQuery("Select count(habitacion.idH) from habitacion,reserva where tipo='Doble' and fecha='"+fecha+"'and habitacion.idh=reserva.idh");
                    while(habitacion.next()){
                        Habidisp=Integer.parseInt(habitacion.getString("count(habitacion.idH)"));
                    }
                    Habitot=Habitot-Habidisp;
                    if(numh>Habitot){
                        JOptionPane.showMessageDialog(null, "No se encuentran suficientes habitaciones dobles");
                        return;
                    }
                    else{
                        for(int x=0;x<numh;x++){
                            habitacion = stat.executeQuery("Select idH from habitacion where Tipo='Sencilla' and idH NOT IN(Select idH from reserva where fecha='"+fecha+"')");
                            for(int y=0;y<=x;y++){
                                while(habitacion.next()){
                                    boolean checkem=true;
                                    idH[x]=habitacion.getInt("idH");
                                    for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
                                        ResultSet  check = stat2.executeQuery("Select idH from reserva where idh="+idH[x]);
                                        if(check.next()){
                                            checkem=false;
                                            break;
                                        }
                                    }
                                    if(checkem)
                                        break;
                                }
                            }
                        }
                    }     
                }
                if("Sencilla".equals(tipo)){
                    String fecha=ft.format(fechaa);
                    ResultSet habitacion = stat.executeQuery("Select count(idH) from habitacion where tipo='Sencilla'");
                    while(habitacion.next()){
                        Habitot=Integer.parseInt(habitacion.getString("count(idH)"));
                    }
                    habitacion = stat.executeQuery("Select count(habitacion.idH) from habitacion,reserva where tipo='Sencilla' and fecha='"+fecha+"'and habitacion.idh=reserva.idh");
                    while(habitacion.next()){
                        Habidisp=Integer.parseInt(habitacion.getString("count(habitacion.idH)"));
                    }
                    Habitot=Habitot-Habidisp;
                    if(numh>Habitot){
                        JOptionPane.showMessageDialog(null, "No se encuentran suficientes habitaciones Secillas");
                        return;
                    }
                    else{
                        for(int x=0;x<numh;x++){
                            habitacion = stat.executeQuery("Select idH from habitacion where Tipo='Sencilla' and idH NOT IN(Select idH from reserva where fecha='"+fecha+"')");
                            for(int y=0;y<=x;y++){
                                while(habitacion.next()){
                                    boolean checkem=true;
                                    idH[x]=habitacion.getInt("idH");
                                    for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
                                        ResultSet  check = stat2.executeQuery("Select idH from reserva where idh="+idH[x]);
                                        if(check.next()){
                                            checkem=false;
                                            break;
                                        }
                                    }
                                    if(checkem)
                                        break;
                                }
                            }
                        }
                    }     
                }

            int idR=1;
            ResultSet reserva;
            for(int x=0;x<numh;x++){
                start.setTime(fechaa);
                end.setTime(fechas);
                reserva = stat.executeQuery("Select max(idR) from reserva");
                reserva.next();
                idR=reserva.getInt("max(idR)");
                idR++;
                for (Date date = start.getTime(); start.before(end)||start.equals(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
                    String fecha=ft.format(date);
                    sql="insert into reserva values ("+idR+","+idH[x]+",'"+fecha+"','"+nombre+"','"+codigo+"')";
                    System.out.println(sql);
                    stat.executeUpdate(sql);
                    idR++;
                }
            }
            JOptionPane.showMessageDialog(null, "Reservacion con exito, tu codigo es "+codigo+" para cancelar o usarla");
        } catch (SQLException ex) {
            Logger.getLogger(DBbridge.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void cancelar(String nombre,String codigo){
        try {
            stat.executeUpdate("delete from reserva where nombre='"+nombre+"' and codigo='"+codigo+"'");
            JOptionPane.showMessageDialog(null, "Reservacion de "+nombre+" eliminada, gracias.");
        } catch (SQLException ex) {
            Logger.getLogger(DBbridge.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String comprobar(String nombre,String codigo){
        try {
            int idH;
            int aux=0;
            String[] nhabit;
            String[] fecha=new String[2];
            
            ResultSet habitacion;
            habitacion = stat.executeQuery("Select * from reserva,habitacion where nombre='"+nombre+"' and codigo='"+codigo+"' and reserva.idH=habitacion.idh");
            while(habitacion.next()){
                if(habitacion.isFirst())
                    fecha[0]=habitacion.getString("fecha");
                if(habitacion.isLast())
                    fecha[1]=habitacion.getString("fecha");
            }
            habitacion = stat.executeQuery("Select count(idH) from reserva where nombre='"+nombre+"' and codigo='"+codigo+"' group by idH");
            int j=0;
            while(habitacion.next()){
                j++;
            }
            nhabit=new String[j+1];
            habitacion = stat.executeQuery("Select * from reserva,habitacion where nombre='"+nombre+"' and codigo='"+codigo+"' and reserva.idH=habitacion.idh group by numero");
            int x=0;
            while(habitacion.next()){
                nhabit[x]=habitacion.getString("numero");
                x++;
            }
            String regreso;
            regreso = "Sus habitaciones reservadas de "+fecha[0]+" a "+fecha[1]+" son: \n";
            for(x=0;x<nhabit.length-1;x++){
                regreso=regreso+"Habitacion "+(x+1)+".- "+nhabit[x]+"\n";
            }
            return regreso;
            
        } catch (SQLException ex) {
        Logger.getLogger(DBbridge.class.getName()).log(Level.SEVERE, null, ex);
        return "error al leer";
        }
    }
}
