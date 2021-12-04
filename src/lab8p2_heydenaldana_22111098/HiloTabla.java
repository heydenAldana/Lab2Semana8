/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab8p2_heydenaldana_22111098;

import javax.swing.JProgressBar;
import javax.swing.JTable;

/**
 *
 * @author heyde
 */
public class HiloTabla extends Thread 
{
    private JTable tabla;
    private BDD bdd;
    
    public HiloTabla(JTable tabla) {
        this.tabla = tabla;
    }

    public JTable getTabla() {
        return tabla;
    }

    public void setBarra(JTable tabla) {
        this.tabla = tabla;
    }
    
    
    public void run(int fila, JProgressBar progreso)
    {
        while(true)
        {
            for (int i = 0; i < tabla.getRowCount(); i++) 
            {
                if(progreso.getValue() == progreso.getMaximum())
                {
                    tabla.setValueAt("FINALIZADO", i, 4);
                }
                else
                    tabla.setValueAt("EN ESPERA", i, 4);
            }
            try
            {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("TABLA MURIO :(");
            }
        }
    }
}
