package Diseño;
import java.awt.Image;
import javax.swing.*;
public class design {
    public design(JFrame jFrame,String Logo,String Title){
        
        jFrame.setResizable(false);
        jFrame.setTitle(Title);
        jFrame.setIconImage(new ImageIcon(getClass().getResource(Logo)).getImage());
        ((JPanel)jFrame.getContentPane()).setOpaque(false);  
        jFrame.repaint();

        
   }
}
