/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab8p2_heydenaldana_22111098;

import javax.swing.JProgressBar;

/**
 *
 * @author heyde
 */
public class HiloBarra extends Thread 
{
    private JProgressBar barra;
    private boolean avanzar, vive;
    private BDD bdd;
    
    public HiloBarra(JProgressBar barra) {
        this.barra = barra;
        avanzar = true;
        vive = true;
    }

    public JProgressBar getBarra() {
        return barra;
    }

    public void setBarra(JProgressBar barra) {
        this.barra = barra;
    }

    public boolean isAvanzar() {
        return avanzar;
    }

    public void setAvanzar(boolean avanzar) {
        this.avanzar = avanzar;
    }

    public boolean isVive() {
        return vive;
    }

    public void setVive(boolean vive) {
        this.vive = vive;
    }
    
    
    public void run(int velocidad, int maximadistancia)
    {
        while(vive)
        {
            if(avanzar)
            {
                barra.setValue(barra.getValue() + velocidad);
                if(barra.getValue()==maximadistancia)
                    vive = false;
            }
            try
            {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("BARRA MURIO :(");
            }
        }
    }
}