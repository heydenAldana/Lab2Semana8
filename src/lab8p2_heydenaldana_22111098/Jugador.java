/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab8p2_heydenaldana_22111098;

/**
 *
 * @author heyde
 */
public class Jugador 
{
    // atributos
    private double velocidad;
    private String nombre;

    // constructor
    public Jugador(double velocidad, String nombre) {
        this.velocidad = velocidad;
        this.nombre = nombre;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
