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
public class MUsuario {
    public static int addUsu(int id_perf, Usuario usuario) throws SQLException{
        int estatus = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

        try{
             con = ConexionSQL.getConnection();
            String q = "Insert into Musuario(id_perf, altu_usu, peso_usu, calTo_usu) "
                    + "values(?,?,?,?)";
            
             ps = con.prepareStatement(q);
            
            ps.setInt(1, id_perf);
            ps.setFloat(2, usuario.getAltu_usu());
            ps.setFloat(3, usuario.getPeso_usu());
            ps.setFloat(4, usuario.getCalTo_usu());
            
            estatus = ps.executeUpdate();
            System.out.println("Registro exitoso del Usuario");
        
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
    
    public static int updateUsu(int id_perf,Usuario e) throws SQLException{
        int estatus = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

        try{
             con = ConexionSQL.getConnection();
            String q = "Update MUsuario set altu_usu = ?, peso_usu = ?, calTo_usu = ?"
                    + "where id_perf = ?";
            
            ps = con.prepareStatement(q);
            
            ps.setFloat(1, e.getAltu_usu());
            ps.setFloat(2, e.getPeso_usu());
            ps.setFloat(3, e.getCalTo_usu());
            ps.setInt(5, id_perf);
            
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
    
    public static int deleteUsu(int id_perf) throws SQLException{
        int estatus = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

        try{
             con = ConexionSQL.getConnection();
            String q = "delete from musuario where id_perf = ?";
            
             ps = con.prepareStatement(q);
            
            ps.setInt(1, id_perf);
            
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
    
    public static Usuario getUsuById(int id_perf) throws SQLException{
        
        Perfil perf = MPerfil.getPerfilById(id_perf);
        Usuario e = new Usuario(perf);
        
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
                e.setAltu_usu(rs.getFloat("altu_usu"));
                e.setPeso_usu(rs.getFloat("peso_usu"));
                e.setCalTo_usu(rs.getFloat("calTo_usu"));
            }
            
            System.out.println("Usuario encontrado");
            con.close();
        
        }catch(Exception ed){
            System.out.println("Error al buscar el Usuario");
            System.out.println(ed.getMessage());
        
        }finally{
            rs.close();
            ps.close();
            con.close();
        }
        return e;
    }
    
    public static int getIdUsu(Usuario usu) throws SQLException{
        int id_usu = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

        try{
             con = ConexionSQL.getConnection();
            String q = "select id_usua from musuario where id_perf = ? and altu_usu = ? and peso_usu = ? and calTo_usu = ?";
            
             ps = con.prepareStatement(q);
            
            ps.setInt(1, usu.getId_perf());
            ps.setFloat(2, usu.getAltu_usu());
            ps.setFloat(3, usu.getPeso_usu());
            ps.setFloat(4, usu.getCalTo_usu());
            
             rs = ps.executeQuery();
            if(rs.next()){
                id_usu = rs.getInt("id_usua");
               
            }
            
            System.out.println("Id del Usuario encontrado");
            con.close();
        
        }catch(Exception ed){
            System.out.println("Error al buscar el Id del Usuario");
            System.out.println(ed.getMessage());
        
        }finally{
            rs.close();
            ps.close();
            con.close();
        }
        return id_usu;
        
        
        
        
    }
    
}
