package br.com.ShoolDrive.view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ShoolDrive.controler.IDisciplinaController;
import br.com.ShoolDrive.controler.IProfessorController;
import br.com.ShoolDrive.convert.ConvertDisciplina;
import br.com.ShoolDrive.convert.ConvertProfessor;
import br.com.ShoolDrive.entidade.Disciplina;
import br.com.ShoolDrive.entidade.Professor;
import br.com.ShoolDrive.exception.RNException;
import br.com.ShoolDrive.util.AliasPaginas;
import br.com.ShoolDrive.util.Mensagems;
import br.com.ShoolDrive.util.TipoMensagem;

@Controller
@Secured("ROLE_ADMIN")
public class ConfiguracoesView {
	
	@Autowired
	private IProfessorController professorController;
	
	@Autowired
	private IDisciplinaController disciplinaController;
	
	@Autowired
	private ConvertDisciplina convertDisciplina;
	
	@Autowired
	private ConvertProfessor convertProfessor;

	
	/**
	 * Responsavel por inicializar os converts e registralos no jsp
	 * 
	 * @param webDataBinder
	 */
	@InitBinder
	public void intblind(WebDataBinder binder) {
		binder.registerCustomEditor(Disciplina.class, this.convertDisciplina);
		binder.registerCustomEditor(Professor.class, this.convertProfessor);
	}
	
	@RequestMapping(value = "/formAlocarProfessor", method = RequestMethod.GET)
	public ModelAndView formAlocarProfessor(){
		ModelAndView model =new ModelAndView(AliasPaginas.VIEW_ALOCAR_PROFESSOR);
		
		try {
		List<Professor> professores = professorController.findAll();
		model.addObject("professores", professores);
		model.addObject("disciplinas", disciplinaController.listaDisciplina());
		} catch (RNException e) {
			model.addObject(TipoMensagem.VARIAVEL_VIEW.getValor(), TipoMensagem.ERRO.getValor());
			model.addObject(Mensagems.VARIAVEL_VIEW.getMensagem(), e.getMensagem());
		}
		return model;
	}
	

	/**
	 * Executa a operação de incluir
	 * 
	 * @return
	 */
	@RequestMapping(value = "/alocarProfessor", method = RequestMethod.POST)
	public ModelAndView alocar(HttpServletRequest request, RedirectAttributes redirect) {
		ModelAndView model = new ModelAndView(AliasPaginas.VIEW_ALOCAR_PROFESSOR);
		
		Disciplina disciplina;
		Professor professor;
		try {
			disciplina = disciplinaController.findOne(Long.valueOf(request.getParameter("disciplina")));
			professor = professorController.findById(Long.valueOf(request.getParameter("professor")));
			
			//Adiciona o professor a disciplina
			disciplina.setProfessor(professor);
			disciplinaController.save(disciplina);
			
			model.addObject(TipoMensagem.VARIAVEL_VIEW.getValor(), TipoMensagem.SUCESSO.getValor());
			model.addObject(Mensagems.VARIAVEL_VIEW.getMensagem(), "Professor Alocado A disciplina !!");
			
		}
		catch (Exception e) {
			e.printStackTrace();
			model.setViewName("redirect:home");
			model.addObject(TipoMensagem.VARIAVEL_VIEW.getValor(), TipoMensagem.ERRO.getValor());
			model.addObject(Mensagems.VARIAVEL_VIEW.getMensagem(), "Ocorreu um erro !");
		}
		return model;
	}
}
