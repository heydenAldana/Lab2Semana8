/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab8p2_heydenaldana_22111098;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author heyde
 */
public class Partida 
{
    // atributos
    private String nombre, fechacreacion;
    private ArrayList<Estrella> star; 
    private ArrayList<Jugador> ply;
    
    // comnstructor

    public Partida(String nombre) {
        this.nombre = nombre;
        this.fechacreacion = obtenerfecha();
        this.star = new ArrayList<Estrella>();
        this.ply = new ArrayList<Jugador>();
    }

    public String obtenerfecha()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return dtf.format(LocalDateTime.now());
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechacreacion() {
        return fechacreacion;
    }

    public ArrayList<Estrella> getStar() {
        return star;
    }

    public void setStar(int distancia, String descripcion, String nombre) {
        this.star.add(new Estrella(distancia, descripcion, nombre));
    }

    public ArrayList<Jugador> getPly() {
        return ply;
    }

    public void setPly(double velocidad, String nombre) {
        this.ply.add(new Jugador(velocidad, nombre));
    }
    
    
    
}