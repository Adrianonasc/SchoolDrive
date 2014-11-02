package br.com.ShoolDrive.view;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.ShoolDrive.controler.IAdministradorController;
import br.com.ShoolDrive.controler.IAlunoController;
import br.com.ShoolDrive.controler.IProfessorController;
import br.com.ShoolDrive.entidade.Administrador;
import br.com.ShoolDrive.entidade.Aluno;
import br.com.ShoolDrive.entidade.Professor;
import br.com.ShoolDrive.entidade.Usuario;
import br.com.ShoolDrive.util.AliasPaginas;
import br.com.ShoolDrive.util.Mensagems;
import br.com.ShoolDrive.util.TipoMensagem;

@Controller
@Secured("ROLE_ADMIN")
public class UsuarioView {

	@Autowired
	private IAdministradorController administradorController;
	
	@Autowired
	private IProfessorController professorController;
	
	@Autowired
	private IAlunoController alunoController;
	
	
	@RequestMapping(value = "/formUsuario", method = RequestMethod.GET)
	public ModelAndView formUsuario() {
		ModelAndView model = new ModelAndView(AliasPaginas.CADASTRO_USUARIO);
		model.addObject("usuario", new Usuario());
		return model;
	}

	@RequestMapping("/addUsuario")
	public ModelAndView addUsuario(@ModelAttribute("usuario") Usuario usuario, BindingResult result) {
		ModelAndView model = new ModelAndView(AliasPaginas.LISTA_USUARIO);
		try {
		/**
		 *	Dependendo da Role Atribuida insere a entidade
		 */
		if (usuario.getRole().name().equals("ROLE_ADMIN")) {
			Administrador admin = new Administrador(usuario.getId(), usuario.getEmail(), usuario.getSenha(), usuario.getNome(), usuario.getRole());
			administradorController.save(admin);
		} else if (usuario.getRole().name().equals("ROLE_ALUNO")) {
			Aluno aluno = new Aluno(usuario.getId(), usuario.getEmail(), usuario.getSenha(), usuario.getNome(), usuario.getRole());
			alunoController.save(aluno);
		} else if (usuario.getRole().name().equals("ROLE_PROFESSOR")) {
			Professor professor = new Professor(usuario.getId(), usuario.getEmail(), usuario.getSenha(), usuario.getNome(), usuario.getRole());
			professorController.save(professor);
		}
			
		
		model.addObject(TipoMensagem.VARIAVEL_VIEW.getValor(), TipoMensagem.SUCESSO.getValor());
		model.addObject(Mensagems.VARIAVEL_VIEW.getMensagem(), Mensagems.UsuarioCadastrado.getMensagem());
		model.addObject("usuarios", listaUsuario());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping("/listaUsuario")
	public ModelAndView listaUsuario(HttpServletRequest request) {
		ModelAndView model = new ModelAndView(AliasPaginas.LISTA_USUARIO);
		try {
			model.addObject("usuarios", listaUsuario());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping("/excluirUsuario")
	public ModelAndView excluirUsuario(@RequestParam("id") Long id, @RequestParam("role") String role) {
		ModelAndView model = new ModelAndView(AliasPaginas.LISTA_USUARIO);
		try {
			if (role.equals("ROLE_ADMIN")) {
				administradorController.delete(id);
			} else if (role.equals("ROLE_ALUNO")) {
				alunoController.delete(id);
			} else if (role.equals("ROLE_PROFESSOR")) {
				professorController.delete(id);
			}
			model.addObject(this.listaUsuario(null));
			model.addObject(TipoMensagem.VARIAVEL_VIEW.getValor(), TipoMensagem.SUCESSO.getValor());
			model.addObject(Mensagems.VARIAVEL_VIEW.getMensagem(), Mensagems.UsuarioExcluido.getMensagem());
		} catch (Exception e) {
			model.addObject(TipoMensagem.VARIAVEL_VIEW.getValor(), TipoMensagem.ERRO.getValor());
			model.addObject(Mensagems.VARIAVEL_VIEW.getMensagem(), Mensagems.ErroOperacaoUsuario.getMensagem());
			e.printStackTrace();
		}
		return model;
	}
	
	
	private List<Usuario> listaUsuario(){
		Usuario usuario;
		List<Usuario> listUsuario = new ArrayList<>();
		
		for (Aluno aluno : alunoController.findAll()) {
			usuario = new Usuario(aluno.getId(), aluno.getEmail(), aluno.getSenha(), aluno.getNome(), aluno.getRole());
			listUsuario.add(usuario);
		}
		for (Professor professor : professorController.findAll()) {
			usuario = new Usuario(professor.getId(), professor.getEmail(), professor.getSenha(), professor.getNome(), professor.getRole());
			listUsuario.add(usuario);
		}
		for (Administrador administrador : administradorController.findAll()) {
			usuario = new Usuario(administrador.getId(), administrador.getEmail(), administrador.getSenha(), administrador.getNome(), administrador.getRole());
			listUsuario.add(usuario);
		}
		return listUsuario;
	}
	
}
