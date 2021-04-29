/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clothing_store;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class Clothing_store {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Splash_Screen splash=new Splash_Screen();
        splash.show();
        
        Login_Window lpage=new Login_Window();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Login_Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        splash.setVisible(false);
        lpage.setLocationRelativeTo(null);
        lpage.show();
    }
    
}
