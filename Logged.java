/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmc;

/**
 *
 * @author RGUKT
 */
public class Logged {
    private static boolean status = false;
    private static String id = "NA";
    private static int roomNo = 0;

    
    public static String getId(){
        return id;
    }
    
    public static int getRoomNo(){
        return roomNo;
    }
    
    public static void setRoomNo(int roomno)
    {
        roomNo = roomno;
    }
    
    public static boolean getStatus(){
        return status;
    }
    
    public static void setLogged(String str){
        status = true;
        id=str;
    }
    
    public static void remLogged(){
        status = false;
        id="NA";
        
    }
}
