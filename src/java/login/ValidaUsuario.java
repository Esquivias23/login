/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import bd.conexionBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adria
 */
@WebServlet(name = "ValidaUsuario", urlPatterns = {"/index"})
public class ValidaUsuario extends HttpServlet {

    private String comprobar = "";
    String nombre;
    String password;
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        nombre = request.getParameter("NOMBRE");
        password = request.getParameter("Pass");
        
        Boolean verificacion; 

        verificacion = conexionBD.buscaUsuario(nombre);

        if (verificacion) //Verificar la informacion
        {
            PrintWriter out = new PrintWriter(response.getOutputStream());
            out.println("<html>");
            out.println("<head><title>Respuesta al Formulario del Servlet</title></head>");
            out.println("<body>");
            out.println("<p><h1><center>Su nombre es:<B>" + nombre + "</B> </center></h1></p>");
            out.println("hoy es " + new Date());
            out.println("</body></html>");
            out.close();
        } else {
            PrintWriter out = new PrintWriter(response.getOutputStream());
        out.println("<html>");
        out.println("<head><title>Respuesta al Formulario del Servlet</title></head>");
        out.println("<body>");
        out.println("<p><h1><center>no puedes entrar<B></B> </center></h1></p>");
        out.println("hoy es " + new Date());
        out.println("</body></html>");
        out.close();

        }

    }
    
    public void buscaUsuario(String nombre){
        
        conexionBD bd = new conexionBD();
        try {
            bd.conectar();
            ResultSet rsVal = conexionBD.consulta("call buscaUsuario('" + nombre + "');");            

        } catch (Exception xxD) {
            comprobar = xxD.getMessage();
        }
    }
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        nombre = request.getParameter("NOMBRE");
        password = request.getParameter("Password");

        guardaUsuario(nombre,password);
        
        res.setContentType("text/html");
        PrintWriter out = new PrintWriter(res.getOutputStream());
        out.println("<html>");
        out.println("<head><title>Login</title></head>");
        out.println("<body>");
        out.println("<h1><center>Se ha registrado exitosamente al usuario: "+usuario+"</center></h1>");
        out.println("</body></html>");
        out.close();
    }
    public void guardaUsuario(String nombre, String password){
        
        conexionBD bd = new conexionBD();
        try {
            bd.conectar();
            ResultSet rsVal = bd.consulta("call guardaUsuario('" + nombre + "', '"+password+"');");            

        } catch (Exception xxD) {
            comprobar = xxD.getMessage();
        }
    }
}
