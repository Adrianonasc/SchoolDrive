package br.com.ShoolDrive.controler.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ShoolDrive.controler.IAdministradorController;
import br.com.ShoolDrive.dao.IAdministradorDao;
import br.com.ShoolDrive.entidade.Administrador;
import br.com.ShoolDrive.entidade.Role;

@Service
public class AdministradorController implements IAdministradorController {

	@Autowired
	private IAdministradorDao administradorDao;

	@Override
	public Administrador findByEmail(String email) {
		return administradorDao.findByEmail(email);
	}

	@Override
	public Administrador findById(int id) {
		return administradorDao.findById(id);
	}

	@Override
	public Administrador findByRole(Role role) {
		return administradorDao.findByRole(role);
	}

	@Override
	public void delete(Long id) {
		 administradorDao.delete(id);
	}

	@Override
	public void save(Administrador admin) {
		administradorDao.save(admin);
	}

	@Override
	public List<Administrador> findAll() {
		return (List<Administrador>) administradorDao.findAll();
	}

}
