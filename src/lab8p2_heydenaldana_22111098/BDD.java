package lab8p2_heydenaldana_22111098;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class BDD 
{
    // atributos para base de datos
    private File cestrella, cjugador, cpartida;
    private RandomAccessFile rpartida, restrella, rjugador;
    
    /*
     * RPARTIDA (String nombre, String fechacreacion
     * RESTRELLA (int distancia, String descripcion, Strign nombre)
     * RJUGADOR (double velocidad, String nombre)
    */
    
    // Crear una partida con dos jugadores y dos estrellas
    public void crearPrimerRegistro() throws IOException
    {
        // crea las carpetas
        cpartida = new File("/partidas");
        cpartida.mkdirs();
        cestrella = new File("/estrellas");
        cpartida.mkdirs();
        cjugador = new File("/jugadores");
        cpartida.mkdirs();
        
        String p = "Ciudad Morioh";
        partida.add(new Partida(p));
        partida.get(0).setStar(1000, "En un valle enrome con una cuidad\n"
                    + "diminuta pero autosustentable, se\n"
                    + "esconde un misterio, o mejor dicho,\n"
                    + " un caso sin resolver por \nmucho tiempo.", "Star Queen");
        partida.get(0).setStar(800, "Un lugar repleto de hongos, de todo\n"
                    + "tipo, todas las posibilidades en este\n"
                    + "lugar pueden existir, incluso lo ilogico.", "Roca Champiñon");
        partida.get(0).setPly(20, "James");
        partida.get(0).setPly(30, "Toad");
    }
    
    
    // crear partida
    public boolean crearPartida(String nombre) throws IOException
    {
        try
        {
            if(verificarNombrePartida(nombre))
                return false;
            rpartida = new RandomAccessFile("/partidas/partida.nin", "rw");
            rpartida.seek(rpartida.length());
            rpartida.writeUTF(nombre);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            rpartida.writeUTF(dtf.format(LocalDateTime.now()));
            return true;
        } catch (IOException e) {
            
        }
        return false;
    }
    
    
    private boolean verificarNombrePartida(String nombre) throws IOException
    {
        try
        {
            rpartida = new RandomAccessFile("/partidas/partida.nin", "rw");
            rpartida.seek(0);
            for (int i = 0; i < rpartida.length(); i++) 
            {
                if(rpartida.readUTF().equals(nombre))
                    return true;
                rpartida.readUTF();
            }
        } catch (IOException e) {
            
        }
        return false;
    }
    
    
    // editar partida
    public boolean editarPartida(String nombre, String nombrem) throws IOException
    {
        try
        {
            rpartida = new RandomAccessFile("/partidas/partida.nin", "rw");
            rpartida.seek(0);
            for (int i = 0; i < rpartida.length(); i++) 
            {
                if(rpartida.readUTF().equals(nombre))
                {
                    rpartida.writeUTF(nombrem);
                    return true;
                }
                rpartida.readUTF();
            }
        } catch (IOException e) {
            
        }
        return false;
    }
    
    
    // eliminar partida
    public boolean eliminarPartida(String nombre) throws IOException
    {
        try
        {
            rpartida = new RandomAccessFile("/partidas/partida.nin", "rw");
            rpartida.seek(0);
            for (int i = 0; i < rpartida.length(); i++) 
            {
                if(rpartida.readUTF().equals(nombre))
                {
                    rpartida.writeUTF(null);
                    rpartida.writeUTF(null);
                    return true;
                }
                rpartida.readUTF();
            }
        } catch (IOException e) {
            
        }
        return false;
    }
    
    
    // agregar estrella a x partida
    public boolean agregarEstrellaaPartida(String nombrepartida, int distancia, String descripcion, String nombreestrella) throws IOException
    {
        try
        {
            if(verificarNombrePartida(nombrepartida))
            {
                restrella = new RandomAccessFile("/estrellas/estrella.nin", "rw");           
                restrella.seek(0);
                for (int i = 0; i < restrella.length(); i++) 
                {
                    restrella.readInt();
                    restrella.readUTF();
                    if(restrella.readUTF().equals(nombreestrella))
                        return false;
                }
                
                // lo agrega
                restrella.seek(restrella.length());
                restrella.writeInt(distancia);
                restrella.writeUTF(descripcion);
                restrella.writeUTF(nombreestrella);
            }
            else 
            {
                return false;
            }
        } catch (IOException e) {
            
        }
        
        return true;
    }
    
    
    // agregar jugador a x partida
    public boolean agregarJugadoraPartida(String nombrepartida, double velocidad, String nombrejugador) throws IOException
    {
        try
        {
            if(verificarNombrePartida(nombrepartida))
            {
                rjugador = new RandomAccessFile("/jugadores/player.nin", "rw");           
                rjugador.seek(0);
                for (int i = 0; i < rjugador.length(); i++) 
                {
                    rjugador.readDouble();
                    if(rjugador.readUTF().equals(nombrejugador))
                        return false;
                }
                
                // lo agrega
                rjugador.seek(rjugador.length());
                rjugador.writeDouble(velocidad);
                rjugador.writeUTF(nombrejugador);
            }
            else 
            {
                return false;
            }
        } catch (IOException e) {
            
        }
        
        return true;
    }
    
    private ArrayList<String> obtenerTotalPartidas()
    {
        ArrayList<String> totalpartidas =  new ArrayList<String>();
        try
        {
            
            rpartida = new RandomAccessFile("/partidas/partida.nin", "rw");
            rpartida.seek(0);
            for (int i = 0; i < rpartida.length(); i++) 
            {
                totalpartidas.add(rpartida.readUTF());
                rpartida.readUTF();
            }
        } catch (IOException e) {
            
        }
        return totalpartidas;
    }
    
    
    // cargar combobox de partidas
    private JComboBox cb_partida;
    public void rellenacbpartida(JComboBox cb_partida)
    {
        ArrayList<String> cosas = new ArrayList<String>();
        for (int i = 0; i < obtenerTotalPartidas().size(); i++) 
        {
            cosas.add(obtenerTotalPartidas().get(i));
        }
        cb_partida.setModel(new DefaultComboBoxModel(cosas.toArray()));
        cosas.clear();
    }
    
    
}
