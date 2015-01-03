package br.com.ShoolDrive.view;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.ShoolDrive.controler.IAdministradorController;
import br.com.ShoolDrive.controler.IAlunoController;
import br.com.ShoolDrive.controler.ICursoController;
import br.com.ShoolDrive.controler.IProfessorController;
import br.com.ShoolDrive.convert.ConvertCurso;
import br.com.ShoolDrive.entidade.Administrador;
import br.com.ShoolDrive.entidade.Aluno;
import br.com.ShoolDrive.entidade.Curso;
import br.com.ShoolDrive.entidade.Professor;
import br.com.ShoolDrive.entidade.Usuario;
import br.com.ShoolDrive.exception.RNException;
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

	@Autowired
	private ICursoController cursoControler;

	@Autowired
	private ConvertCurso convertCurso;


	/**
	 * Responsavel por inicializar os converts e registralos no jsp
	 * 
	 * @param webDataBinder
	 */
	@InitBinder
	public void intblind(WebDataBinder binder) {
		binder.registerCustomEditor(Curso.class, this.convertCurso);
	}

	@RequestMapping(value = "/formUsuario", method = RequestMethod.GET)
	public ModelAndView formUsuario() {
		ModelAndView model = new ModelAndView(AliasPaginas.CADASTRO_USUARIO);
		List<Curso> cursos = (List<Curso>) this.cursoControler.findAll();
		model.addObject("usuario", new Usuario());
		model.addObject("cursos", cursos);
		return model;
	}

	@RequestMapping("/addUsuario")
	public ModelAndView addUsuario(@ModelAttribute("usuario") Usuario usuario, BindingResult result,
	                               @RequestParam("curso") long CursoId) {
		ModelAndView model = new ModelAndView(AliasPaginas.LISTA_USUARIO);
		try {
			/**
			 *	Dependendo da Role Atribuida insere a entidade
			 */
			if (usuario.getRole().name().equals("ROLE_ADMIN")) {
				Administrador admin = new Administrador(usuario.getId(), usuario.getEmail(), usuario.getSenha(),
				                                        usuario.getNome(), usuario.getRole());
				this.administradorController.save(admin);
			}
			else if (usuario.getRole().name().equals("ROLE_ALUNO")) {
				Aluno aluno = new Aluno(usuario.getId(), usuario.getEmail(), usuario.getSenha(), usuario.getNome(),
				                        usuario.getRole());
				if (CursoId > 0) {
					aluno.setCurso(this.cursoControler.findById(CursoId));
				}
				this.alunoController.save(aluno);
			}
			else if (usuario.getRole().name().equals("ROLE_PROFESSOR")) {
				Professor professor = new Professor(usuario.getId(), usuario.getEmail(), usuario.getSenha(),
				                                    usuario.getNome(), usuario.getRole());
				this.professorController.save(professor);
			}
			model.addObject(TipoMensagem.VARIAVEL_VIEW_TIPO.getValor(), TipoMensagem.SUCESSO.getValor());
			model.addObject(Mensagems.VARIAVEL_VIEW_MENSAGEM, Mensagems.UsuarioCadastrado);
			model.addObject("usuarios", this.listaUsuario());


		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping("/listaUsuario")
	public ModelAndView listaUsuario(HttpServletRequest request) {
		ModelAndView model = new ModelAndView(AliasPaginas.LISTA_USUARIO);
		try {
			model.addObject("usuarios", this.listaUsuario());
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
				this.administradorController.delete(id);
			}
			else if (role.equals("ROLE_ALUNO")) {
				this.alunoController.delete(id);
			}
			else if (role.equals("ROLE_PROFESSOR")) {
				this.professorController.delete(id);
			}
			model.addObject("usuarios", this.listaUsuario());
			model.addObject(TipoMensagem.VARIAVEL_VIEW_TIPO.getValor(), TipoMensagem.SUCESSO.getValor());
			model.addObject(Mensagems.VARIAVEL_VIEW_MENSAGEM, Mensagems.UsuarioExcluido);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}


	private List<Usuario> listaUsuario() {
		Usuario usuario;
		List<Usuario> listUsuario = new ArrayList<>();


		try {
			// Lista Aluno
			for (Aluno aluno : this.alunoController.findAll()) {
				usuario = new Usuario(aluno.getId(), aluno.getEmail(), aluno.getSenha(), aluno.getNome(), aluno.getRole());
				listUsuario.add(usuario);
			}
			// Lista Professor
			for (Professor professor : this.professorController.findAll()) {
				usuario = new Usuario(professor.getId(), professor.getEmail(), professor.getSenha(), professor.getNome(),
				                      professor.getRole());
				listUsuario.add(usuario);
			}
			// Lista Adm
			for (Administrador administrador : this.administradorController.findAll()) {
				usuario = new Usuario(administrador.getId(), administrador.getEmail(), administrador.getSenha(),
				                      administrador.getNome(), administrador.getRole());
				listUsuario.add(usuario);
			}
		} catch (RNException e) {
			e.printStackTrace();
		}

		return listUsuario;
	}
}
