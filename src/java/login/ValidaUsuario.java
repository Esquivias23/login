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

    conexionBD BD = new conexionBD();
    String nombre;
    String password;

    public void llama() {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        nombre = request.getParameter("NOMBRE");
        password = request.getParameter("Pass");
        /* TODO output your page here. You may use following sample code. */
        String nombre = request.getParameter("NombreUsuario"); //Nombre
        //Variables que son recibidas en el formulario
        String pass = request.getParameter("PassUsuario");//Contraseña
        Boolean verificacion; //Booleano para indicar estado

        BD.Conectar(); //LLamar a la conexion 
        verificacion = BD.BuscarUsuario(nombre, pass);

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

    }
}
