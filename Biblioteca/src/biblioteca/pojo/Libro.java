/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.pojo;

/**
 *
 * @author JosepHy
 */
public class Libro {
    private int idLibro;
    private String autor;
    private String titulo;
    private String editorial;
    private String edicion;
    
    public Libro()
    {
        this.idLibro = 0;
        this.autor = "SIN DATOS";
        this.titulo = "SIN DATOS";
        this.editorial = "SIN DATOS";
        this.edicion = "SIN DATOS";
    }
    
    //getters
    public int getIdLibro()
    {
        return this.idLibro;
    }
    
    public String getAutor()
    {
        return this.autor;
    }
    
    public String getTitulo()
    {
        return this.titulo;
    }
    
    public String getEditorial()
    {
        return this.editorial;
    }
    
    public String getEdicion()
    {
        return this.edicion;
    }
    
    //setters 
    
    public void setIdLibro(int  value)
    {
        this.idLibro = value;
    }
    
    public void setAutor (String value)
    {
        this.autor = value;
    }
    
    public void setEditorial (String value)
    {
        this.editorial = value;
    }
    
    public void setTitulo(String value)
    {
        this.titulo = value;
    }
    
    public void setEdicion (String value)
    {
        this.edicion = value;
    }
    
}
