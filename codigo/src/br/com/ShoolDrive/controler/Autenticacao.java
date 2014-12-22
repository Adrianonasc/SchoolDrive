package br.com.ShoolDrive.controler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ShoolDrive.dao.AutenticacaoDao;
import br.com.ShoolDrive.entidade.Administrador;
import br.com.ShoolDrive.entidade.Aluno;
import br.com.ShoolDrive.entidade.Professor;

/**
 * 
 * 17/12/2014
 * @author Adriano 
 * Classe Responsavel pela autenticação Usuario com suas permissao 
 */
@Service
public class Autenticacao implements UserDetailsService {

	@Autowired
	private AutenticacaoDao autenticacaoDao;
	
	

	/**
	 * Metodo Responsavel por dar as autoridades nesessarias e retornar ao
	 * container do Spring
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Object objUsuario = this.autenticacaoDao.getbyEmail(email);
		UserDetails user;
		List<GrantedAuthority> listGranted;
		SimpleGrantedAuthority simpleGrantedAuthority;

		if (objUsuario instanceof Aluno) {
			Aluno aluno = (Aluno) objUsuario;
			listGranted = new ArrayList<GrantedAuthority>();
			simpleGrantedAuthority = new SimpleGrantedAuthority(aluno.getRole().toString());
			listGranted.add(simpleGrantedAuthority);
			user = new org.springframework.security.core.userdetails.User(aluno.getEmail(), aluno.getSenha(), true, true, true, true,
					listGranted);
			return user;
		} else if (objUsuario instanceof Professor) {
			Professor professor = (Professor) objUsuario;
			listGranted = new ArrayList<GrantedAuthority>();
			simpleGrantedAuthority = new SimpleGrantedAuthority(professor.getRole().toString());
			listGranted.add(simpleGrantedAuthority);
			user = new org.springframework.security.core.userdetails.User(professor.getEmail(), professor.getSenha(), true, true, true, true,
					listGranted);
			return user;
		} else  {
			Administrador admin = (Administrador) objUsuario;
			listGranted = new ArrayList<GrantedAuthority>();
			simpleGrantedAuthority = new SimpleGrantedAuthority(admin.getRole().toString());
			listGranted.add(simpleGrantedAuthority);
			user = new org.springframework.security.core.userdetails.User(admin.getEmail(), admin.getSenha(), true, true, true, true,
					listGranted);

			return user;
		}

	}

}
