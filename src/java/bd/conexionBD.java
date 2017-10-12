
package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Yo
 */
public class conexionBD {
    Connection con = null; //Variables para la base de datos
    Statement stm = null;
    ResultSet r = null;
    
    public String Conectar() throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        String mensaje; 
        try
            {  //Conexion a la base de datos
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection("jdbc:mysql://localhost/LoginPractica", "root", "n0m3l0");
                stm = con.createStatement(); //Declaracion y asignacion de valores para las variables
                mensaje = "Conexión exitosa";
            }catch (SQLException error){
                mensaje = error.toString();
            }
        return mensaje;
    }
    
    public Boolean BuscarUsuario(String Nombre, String Pass ) throws SQLException  //Método que se utiliza para validar
    {
        Boolean VerExist = false;
        r = stm.executeQuery("SELECT * FROM Usuarios;"); //Instruccion SQL
        
        while(r.next())
        {
            if(Nombre.equals(r.getString("NombreUsuario")) && Pass.equals(r.getString("PassUsuario")))  //Busca meiante el Nombre y Contraseña si existe el campo
            {
                VerExist = true;
            }
        }
        
        return VerExist;  //Regresa un valor a la clase en la que sea llamado
    }
}
