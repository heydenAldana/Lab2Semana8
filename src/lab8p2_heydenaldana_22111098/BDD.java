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
import javax.swing.JOptionPane;

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
        try
        {
            // crea las carpetas
            cpartida = new File("/partidas");
            cpartida.mkdirs();
            cestrella = new File("/estrellas");
            cpartida.mkdirs();
            cjugador = new File("/jugadores");
            cpartida.mkdirs();

            // agrega un registro (ver requerimiento en hoja lab)
            rpartida = new RandomAccessFile("/partidas/partida.nin", "rw");
            rpartida.seek(rpartida.length());
            rpartida.writeUTF("Ciudad Morioh");
            
            restrella = new RandomAccessFile("/estrellas/estrella.nin", "rw");           
            restrella.seek(restrella.length());
            restrella.writeInt(1000);
            restrella.writeUTF("La bella estrella de queen");
            restrella.writeUTF("Killer Queen");
            restrella.writeInt(2000);
            restrella.writeUTF("La bella estrella de la vida: queen");
            restrella.writeUTF("Life Queen");
            
            rjugador = new RandomAccessFile("/jugadores/player.nin", "rw");           
            rjugador.seek(rjugador.length());
            rjugador.writeDouble(25);
            rjugador.writeUTF("James");
            rjugador.writeDouble(55);
            rjugador.writeUTF("Josuke");
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERROR:\n" + e);
        }
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
    
    
    // ----------------------------- VERIFICADORES DE REGISTROS ------------- //
    
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
    
    
    private boolean verificarNombreEstrella(String nombreestrella) throws IOException
    {
        try
        {
            restrella = new RandomAccessFile("/partidas/estrella.nin", "rw");
            restrella.seek(0);
            for (int i = 0; i < restrella.length(); i++) 
            {
                if(restrella.readUTF().equals(nombreestrella))
                    return true;
                restrella.readUTF();
            }
        } catch (IOException e) {
            
        }
        return false;
    }
    
    
    private boolean verificarNombreJugador(String nombre) throws IOException
    {
        try
        {
            rjugador = new RandomAccessFile("/partidas/player.nin", "rw");
            rjugador.seek(0);
            for (int i = 0; i < rjugador.length(); i++) 
            {
                rjugador.readDouble();
                rjugador.readUTF();
                if(rjugador.readUTF().equals(nombre))
                    return true;
            }
        } catch (IOException e) {
            
        }
        return false;
    }
    
    
    // -------------------------------------------------------------------------- //
    
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
    
    
    // Esta funcion obtiene un arraylist de los elementos de partida
    // para los combobox
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
