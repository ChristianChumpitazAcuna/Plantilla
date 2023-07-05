package pe.edu.vallegrande.app.tests;

import java.sql.Connection;

import pe.edu.vallegrande.app.db.AccesoDB;

public class Conexion {
	public static void main(String[] args) {
		try {
			Connection cn = AccesoDB.getConnection();
			System.out.println("conexion exitosa");
			cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
