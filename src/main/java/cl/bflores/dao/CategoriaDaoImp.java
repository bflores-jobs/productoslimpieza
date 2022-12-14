package cl.bflores.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cl.bflores.connection.AdministradorConexion;
import cl.bflores.interfaces.CategoriaDao;
import cl.bflores.model.Categoria;

public class CategoriaDaoImp extends AdministradorConexion implements CategoriaDao {

	public CategoriaDaoImp() {
		conn = generaPoolConexion();
	}
	
	//busca categoria por id
	@Override
	public Categoria findById(int id) {
		
		Categoria categoria = new Categoria();
		String query = "SELECT * FROM categoria WHERE id_categoria = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				categoria.setId(rs.getInt("id_categoria"));
				categoria.setNombre(rs.getString("nombre_categoria"));
			}
			return categoria;
						
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}			
	}

	//obtiene todas las categoria
	@Override
	public List<Categoria> findAll() {
		
		List<Categoria> listaCategorias = new ArrayList<Categoria>();
		String query = "SELECT * FROM categoria";
		
		try {
			pstm = conn.prepareStatement(query);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setId(rs.getInt("id_categoria"));
				categoria.setNombre(rs.getString("nombre_categoria"));
				
				listaCategorias.add(categoria);
			}
			return listaCategorias;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Categoria>();
		}
	}

}
