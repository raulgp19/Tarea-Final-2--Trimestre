package Modelo;

public class ModeloUsuario {
    private int id;
    private String nombre;
    private String hash;
    private String salt;
    private int algoritmo;

    public ModeloUsuario(int id, String nombre, String hash, String salt, int algoritmo) {
        this.id = id;
        this.nombre = nombre;
        this.hash = hash;
        this.salt = salt;
        this.algoritmo = algoritmo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHash() {
        return hash;
    }

    public String getSalt() {
        return salt;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getAlgoritmo() {
        return algoritmo;
    }

    public void setAlgoritmo(int algoritmo) {
        this.algoritmo = algoritmo;
    }
}
