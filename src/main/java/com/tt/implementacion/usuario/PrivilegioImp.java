package com.tt.implementacion.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tt.fachada.usuario.IPrivilegio;
import com.tt.modelo.usuario.Privilegio;
import com.tt.modelo.usuario.Rol;
import com.tt.modelo.usuario.Usuario;
import com.tt.utilidades.JPAUtil;

public class PrivilegioImp implements IPrivilegio {

	EntityManager entity;

	public PrivilegioImp() {
		entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	}

	private List<Privilegio> listaPrivilegio = new ArrayList<Privilegio>();
	Query q;

	@Override
	public List<Privilegio> encontrarTodo() {
		try {
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT p FROM Privilegio p");
			listaPrivilegio = q.getResultList();
			this.entity.getTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			JPAUtil.shutdown();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaPrivilegio;
	}

	@Override
	public Privilegio econtrarId(int id) {
		Privilegio privilegio = new Privilegio();
		try {
			this.entity.getTransaction().begin();
			privilegio = this.entity.find(Privilegio.class, id);
			this.entity.close();
		} catch (Exception e) {
			e.printStackTrace();
			JPAUtil.shutdown();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return privilegio;
	}

	@Override
	public void agregar(Privilegio privilegio) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(privilegio);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			JPAUtil.shutdown();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
	}

	@Override
	public void actualizar(Privilegio privilegio) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(privilegio);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			JPAUtil.shutdown();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
	}

	@Override
	public void eliminar(int id) {
		try {
			Privilegio privilegio = new Privilegio();
			privilegio = this.entity.find(Privilegio.class, id);
			this.entity.getTransaction().begin();
			this.entity.remove(privilegio);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
	}
	
	public List<Privilegio> exportPrivilegio(int id) {
		RolImp rolImp = new RolImp();
		Rol rol = new Rol();
		try {
			rol = rolImp.econtrarId(id);
			this.entity.getTransaction().begin();
			Query q = this.entity.createQuery("SELECT p FROM Privilegio p WHERE p.idRol.id="+id+"");
			this.listaPrivilegio = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.entity != null) {
				this.entity.close();
				this.q = null;
				System.out.println("Cerrando la entity");
			}
		}
		return listaPrivilegio;
	}


}
