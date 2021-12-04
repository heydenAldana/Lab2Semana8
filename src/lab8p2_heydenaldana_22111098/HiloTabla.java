/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab8p2_heydenaldana_22111098;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTable;

/**
 *
 * @author heyde
 */
public class HiloTabla extends Thread 
{
    private JTable tabla;
    private JProgressBar barra;
    private JLabel labels[];
    private BDD bdd;
    private boolean isAvanzar;
    
    public HiloTabla(JTable tabla, JProgressBar barra, JLabel labels[]) {
        this.tabla = tabla;
        this.barra = barra;
        this.labels = new JLabel[5];
    }

    public JTable getTabla() {
        return tabla;
    }

    public void setBarra(JTable tabla) {
        this.tabla = tabla;
    }

    public JProgressBar getBarra() {
        return barra;
    }

    public void setBarra(JProgressBar barra) {
        this.barra = barra;
    }

    public boolean isAvanzar() {
        return isAvanzar;
    }

    public void setAvanzar(boolean isAvanzar) {
        this.isAvanzar = isAvanzar;
    }
    
    
    
    @Override
    public void run() 
    {
        JProgressBar progreso = barra;
        String actualizar[] = new String[4];
        try
        {
            while(isAvanzar)
            {
                for (int i = 0; i < tabla.getRowCount(); i++) 
                {
                    try
                    {
                        progreso.setMaximum(bdd.getDistanciaEstrella(tabla.getValueAt(i, 1).toString()));
                    } catch (IOException e) {
                            
                    }
                    while(progreso.getValue() != progreso.getMaximum())
                    {
                        tabla.setValueAt("EN ESPERA", i, 4);
                        progreso.setValue(progreso.getValue() + Integer.valueOf((String)tabla.getValueAt(i, 2)));
                        labels[0].setText(tabla.getValueAt(i, 0).toString());
                        labels[1].setText(tabla.getValueAt(i, 1).toString());
                        int distancer = progreso.getMaximum() - progreso.getValue();
                        labels[2].setText(String.valueOf(distancer));
                        labels[3].setText(tabla.getValueAt(i, 3).toString());
                     
                    }
                    tabla.setValueAt("FINALIZADO", i, 4);
                }
                yaAcabo(false);
                try
                {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("TABLA MURIO :(");
                }
            }
        } catch (Exception e) {
            
        }
    
    }
    
    
    public boolean yaAcabo(boolean condicion){
        return condicion;
    }
    
    
    public void ActualizarJLabel(String[] criterio)
    {
        for (int i = 0; i < labels.length; i++) 
        {
            labels[i].setText(criterio[i]);
        }
    }
}
