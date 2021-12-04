/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab8p2_heydenaldana_22111098;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTable;

/**
 *
 * @author heyde
 */
public class HiloLabel extends Thread
{
    private JLabel label;
    private JProgressBar barra;
    
    public HiloLabel(JLabel label, JProgressBar barra){
        this.label = label;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public JProgressBar getBarra() {
        return barra;
    }

    public void setBarra(JProgressBar barra) {
        this.barra = barra;
    }
    
    
    
    @Override
    public void run()
    {
        while(barra.getValue() != barra.getMaximum())
        {
            label.setText(this.getLabel().getText());
            try
            {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("LABEL MURIO :(");
            }
        }
        
    }
}
