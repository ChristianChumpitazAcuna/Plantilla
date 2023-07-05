package pe.edu.vallegrande.app.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pe.edu.vallegrande.app.db.AccesoDB;
import pe.edu.vallegrande.app.model.Client;
import pe.edu.vallegrande.app.service.spec.CrudServiceSpec;
import pe.edu.vallegrande.app.service.spec.RowMapper;

public class CrudClientService implements CrudServiceSpec<Client>, RowMapper<Client> {

	private final String SQL_SELECT_BASE = "SELECT id, name, last_name, document_type, document_number, cellphone, status FROM client";
	private final String SQL_INSERT = "INSERT INTO client(name, last_name, document_type, document_number, cellphone, status) VALUES (?,?,?,?,?,?)";
	private final String SQL_UPDATE = "UPDATE client SET name=?, last_name=?, document_type=?, document_number=?, cellphone=?, status=? WHERE id=?";
	private final String SQL_DELETE = "UPDATE client SET status= 'I' WHERE id=? ";
	private final String SQL_REACTIVE = "UPDATE client SET status= 'A' WHERE id=? ";

	@Override
	public Client mapRow(ResultSet rs) throws SQLException {
		Client bean = new Client();

		bean.setId(rs.getInt("id"));
		bean.setName(rs.getString("name"));
		bean.setLast_name(rs.getString("last_name"));
		bean.setDocument_type(rs.getString("document_type"));
		bean.setDocument_number(rs.getString("document_number"));
		bean.setCellphone(rs.getString("cellphone"));
		bean.setStatus(rs.getString("status"));
		return bean;
	}

	@Override
	public List<Client> getAll() {
		Connection cn = null;
		List<Client> lista = new ArrayList<>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Client bean;

		try {
			cn = AccesoDB.getConnection();
			pstm = cn.prepareStatement(SQL_SELECT_BASE + " WHERE status ='A' ");
			rs = pstm.executeQuery();
			while (rs.next()) {
				bean = mapRow(rs);
				lista.add(bean);
			}
			rs.close();
			pstm.close();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				cn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

		return lista;
	}

	@Override
	public List<Client> getAllInactive() {
		Connection cn = null;
		List<Client> lista = new ArrayList<>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Client bean;

		try {
			cn = AccesoDB.getConnection();
			pstm = cn.prepareStatement(SQL_SELECT_BASE + " WHERE status ='I' ");
			rs = pstm.executeQuery();
			while (rs.next()) {
				bean = mapRow(rs);
				lista.add(bean);
			}
			rs.close();
			pstm.close();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				cn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

		return lista;
	}

	@Override
	public List<Client> get(Client bean) {
		Connection cn = null;
		List<Client> lista = new ArrayList<>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Client item;
		String sql;

		String name = "%" + bean.getName() + "%";
		String last_name = "%" + bean.getLast_name() + "%";
		String document_number = bean.getDocument_number() + "%";
		String cellphone = bean.getCellphone() + "%";

		try {
			cn = AccesoDB.getConnection();
			sql = SQL_SELECT_BASE
					+ " WHERE name LIKE ? AND last_name LIKE ? AND document_number LIKE ? AND cellphone LIKE ?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, name);
			pstm.setString(2, last_name);
			pstm.setString(3, document_number);
			pstm.setString(4, cellphone);
			rs = pstm.executeQuery();
			while (rs.next()) {
				item = mapRow(rs);
				lista.add(item);
			}
			rs.close();
			pstm.close();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				cn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return lista;
	}

	@Override
	public void insert(Client bean) {
		Connection cn = null;
		// String sql = null;
		PreparedStatement pstm = null;
		// ResultSet rs;
		// Integer id = 0;

		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);

			/*
			 * sql = "SELECT value FROM controll WHERE parameter='client' "; pstm =
			 * cn.prepareStatement(sql); rs = pstm.executeQuery(); if (!rs.next()) {
			 * rs.close(); pstm.close(); throw new
			 * SQLException("contador de client no existe"); } id =
			 * Integer.parseInt(rs.getString("value")); rs.close(); pstm.close();
			 * 
			 * id++; sql = "UPDATE controll SET value=? WHERE parameter='client' "; pstm =
			 * cn.prepareStatement(sql); pstm.setString(1, id + ""); pstm.executeUpdate();
			 * pstm.close();
			 */

			pstm = cn.prepareStatement(SQL_INSERT);
			pstm.setString(1, bean.getName());
			pstm.setString(2, bean.getLast_name());
			pstm.setString(3, bean.getDocument_type());
			pstm.setString(4, bean.getDocument_number());
			pstm.setString(5, bean.getCellphone());
			pstm.setString(6, bean.getStatus());
			pstm.executeUpdate();
			pstm.close();

			// bean.setId(id);
			cn.commit();
		} catch (SQLException e) {
			try {
				cn.rollback();
				cn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				cn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

	}

	@Override
	public void update(Client bean) {
		Connection cn = null;
		PreparedStatement pstm = null;
		int filas;

		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);

			pstm = cn.prepareStatement(SQL_UPDATE);
			pstm.setString(1, bean.getName());
			pstm.setString(2, bean.getLast_name());
			pstm.setString(3, bean.getDocument_type());
			pstm.setString(4, bean.getDocument_number());
			pstm.setString(5, bean.getCellphone());
			pstm.setString(6, bean.getStatus());
			pstm.setInt(7, bean.getId());
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas != 1) {
				throw new SQLException("Error verifique sus datos");
			}
			cn.commit();
		} catch (SQLException e) {
			try {
				cn.rollback();
				cn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				cn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}

	@Override
	public void delete(String id) {
		Connection cn = null;
		PreparedStatement pstm = null;

		try {
			// Establecer conexión
			cn = AccesoDB.getConnection();
			// Preparar consulta
			pstm = cn.prepareStatement(SQL_DELETE);
			pstm.setString(1, id);
			// Ejecutar consulta
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Error al eliminar client: " + e.getMessage());
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (cn != null) {
					cn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public void reactive(String id) {
		Connection cn = null;
		PreparedStatement pstm = null;

		try {
			// Establecer conexión
			cn = AccesoDB.getConnection();
			// Preparar consulta
			pstm = cn.prepareStatement(SQL_REACTIVE);
			pstm.setString(1, id);
			// Ejecutar consulta
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Error al reactivar client: " + e.getMessage());
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (cn != null) {
					cn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

}
