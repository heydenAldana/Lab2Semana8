package lab8p2_heydenaldana_22111098;

import java.util.ArrayList;


public class BDD 
{
    // atributos para base de datos
    private ArrayList<Partida> partida = new ArrayList<Partida>();
    private ArrayList<Estrella> estrella; 
    private ArrayList<Jugador> player;
    
    // crear partida
    public boolean crearPartida(String nombre)
    {
        for (Partida p : partida) 
        {
            if(p.getNombre().equals(nombre))
                return false;
        }
        partida.add(new Partida(nombre));
        return true;
    }
    
    // editar partida
    public boolean editarPartida(String nombre, String nombrem)
    {
        for (Partida p : partida) 
        {
            if(p.getNombre().equals(nombre))
            {
                p.setNombre(nombrem);
                return true;
            }  
        }
        return false;
    }
    
    // eliminar partida
    public boolean eliminarPartida(String nombre)
    {
        for (int i = 0; i < partida.size(); i++) 
        {
            if(partida.get(i).getNombre().equals(nombre))
            {
                partida.remove(i);
                return true;
            }
        }
        return false;
    }
}
