/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author sofo9
 */
public class AccionesUsuario {
    public static int registrarUsuario(Usuario e) throws SQLException{
        int estatus = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

        try{
             con = ConexionSQL.getConnection();
            String q = "insert into Musuario( id_perf, altu_usu, peso_usu, "
                    + "calTo_usu) "
                    + "values(?,?,?,?)";
            
             ps = con.prepareStatement(q);
            
            ps.setInt(1, e.getId_perf());
            ps.setFloat(2, e.getAltu_usu());
            ps.setFloat(3, e.getPeso_usu());
            ps.setFloat(4, e.getCalTo_usu());
            
            estatus = ps.executeUpdate();
            System.out.println("Registro exitoso del Usuario");
            con.close();
        
        }catch(Exception ed){
            System.out.println("Error al registrar el Usuario");
            System.out.println(ed.getMessage());
        
        }finally{
            rs.close();
            ps.close();
            con.close();
        }
        return estatus;
    }
    
    public static int actualizarUsuario(int id_usua,Usuario e) throws SQLException{
        int estatus = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

        try{
             con = ConexionSQL.getConnection();
            String q = "update MPerfil set id_perf = ?, altu_usu = ?, peso_usu = ?, "
                    + "calTo_usu = ?"
                    + "where id_usua = ?";
            
             ps = con.prepareStatement(q);
            
            ps.setInt(1, e.getId_perf());
            ps.setFloat(2, e.getAltu_usu());
            ps.setFloat(3, e.getPeso_usu());
            ps.setFloat(4, e.getCalTo_usu());
            ps.setInt(5, id_usua);
            
            estatus = ps.executeUpdate();
            System.out.println("Actualización exitosa del Usuario");
            con.close();
        
        }catch(Exception ed){
            System.out.println("Error al actualizar el Usuario");
            System.out.println(ed.getMessage());
        
        }finally{
            rs.close();
            ps.close();
            con.close();
        }
        return estatus;
    }
    
    public static int borrarUsuario(int id_usua) throws SQLException{
        int estatus = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

        try{
             con = ConexionSQL.getConnection();
            String q = "delete from musuario where id_usua = ?";
            
             ps = con.prepareStatement(q);
            
            ps.setInt(1, id_usua);
            
            estatus = ps.executeUpdate();
            System.out.println("usuario eliminado exitosamente");
            con.close();
        
        }catch(Exception ed){
            System.out.println("Error al borrar al usuario");
            System.out.println(ed.getMessage());
        
        }finally{
            rs.close();
            ps.close();
            con.close();
        }
        return estatus;
    }
    
    public static Usuario buscarUsuarioById(int id_usua) throws SQLException{
        Usuario e = new Usuario();
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

        try{
             con = ConexionSQL.getConnection();
            String q = "select * from musuario where id_usua = ?";
            
             ps = con.prepareStatement(q);
            
            ps.setInt(1, id_usua);
            
             rs = ps.executeQuery();
            if(rs.next()){
                Perfil perf = AccionesPerfil.buscarPerfilById(rs.getInt("id_perf"));
                e = (Usuario) perf;
                e.setId_usu(id_usua);
                e.setAltu_usu(rs.getFloat("altu_usu"));
                e.setPeso_usu(rs.getFloat("peso_usu"));
                e.setCalTo_usu(rs.getFloat("calTo_usu"));
               
            }
            
            System.out.println("Perfil encontrado");
            con.close();
        
        }catch(Exception ed){
            System.out.println("Error al buscar el perfil");
            System.out.println(ed.getMessage());
        
        }finally{
            rs.close();
            ps.close();
            con.close();
        }
        return e;
    }
    
    public static Usuario buscarUsuarioByIdPerfil(int id_perf) throws SQLException{
        Usuario e = null;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

        try{
             con = ConexionSQL.getConnection();
            String q = "select * from musuario where id_perf = ?";
            
             ps = con.prepareStatement(q);
            
            ps.setInt(1, id_perf);
            
             rs = ps.executeQuery();
            if(rs.next()){
                Perfil perf = AccionesPerfil.buscarPerfilById(rs.getInt("id_perf"));
                e = (Usuario) perf;
                e.setId_usu(rs.getInt("id_usua"));
                e.setId_perf(id_perf);
                e.setAltu_usu(rs.getFloat("altu_usu"));
                e.setPeso_usu(rs.getFloat("peso_usu"));
                e.setCalTo_usu(rs.getFloat("calTo_usu"));
               
            }
            if( e != null){
                System.out.println("Usuario encontrado con el Id del Perfil");
            }
            
            con.close();
        
        }catch(Exception ed){
            System.out.println("Error al buscar el perfil");
            System.out.println(ed.getMessage());
        
        }finally{
            rs.close();
            ps.close();
            con.close();
        }
        return e;
    }
}
