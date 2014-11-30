/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aerolinea;

/**
 *
 * @author Inspiron
 */
class MyEdge {

    private double precio;
    private String aerolinea;
    private String desde;
    private String hacia;

    public MyEdge() {
    }

    public MyEdge(double precio, String aerolinea, String desde, String hacia) {
        this.precio = precio;
        this.aerolinea = aerolinea;
        this.desde = desde;
        this.hacia = hacia;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(String aerolinea) {
        this.aerolinea = aerolinea;
    }

    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getHacia() {
        return hacia;
    }

    public void setHacia(String hacia) {
        this.hacia = hacia;
    }

    @Override
    public String toString() {
        return aerolinea + "-" + precio + "-" + desde + "-" + hacia + "-";
    }

}
