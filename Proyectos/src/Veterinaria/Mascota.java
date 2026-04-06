/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Veterinaria;

/**
 *
 * @author Administrador
 */
public class Mascota {
    private String nombre;
    private String especie;
    private String edad;
    private String sexo;
    private double peso;
    private boolean vacunas;
    private String historial;
    private String idDueno;

    public Mascota() {}

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }

    public String getEdad() { return edad; }
    public void setEdad(String edad) { this.edad = edad; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }

    public boolean isVacunas() { return vacunas; }
    public void setVacunas(boolean vacunas) { this.vacunas = vacunas; }

    public String getHistorial() { return historial; }
    public void setHistorial(String historial) { this.historial = historial; }

    public String getIdDueno() { return idDueno; }
    public void setIdDueno(String idDueno) { this.idDueno = idDueno; }
}
