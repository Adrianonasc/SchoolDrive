package br.com.ShoolDrive.view;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ShoolDrive.controler.ICursoController;
import br.com.ShoolDrive.controler.IDisciplinaController;
import br.com.ShoolDrive.convert.ConvertCurso;
import br.com.ShoolDrive.entidade.Curso;
import br.com.ShoolDrive.entidade.Disciplina;
import br.com.ShoolDrive.util.AliasPaginas;
import br.com.ShoolDrive.util.Mensagems;
import br.com.ShoolDrive.util.TipoMensagem;

/**
 * Classe Responsavel pelo Controller da Disciplina
 * 
 * @author Adriano
 * 
 */
@Controller
@Secured("ROLE_ADMIN")
public class DisciplinaView {

	@Autowired
	private ConvertCurso convertCurso;

	@Autowired
	private ICursoController cursoController;
	
	@Autowired
	private IDisciplinaController disciplinaController;
	

	/**
	 * Responsavel por inicializar os converts e registralos no jsp
	 * 
	 * @param webDataBinder
	 */
	@InitBinder
	public void intblind(WebDataBinder binder) {
		binder.registerCustomEditor(Curso.class, this.convertCurso);
	}

	/**
	 * Monta o form para view
	 * 
	 * @return
	 */
	@RequestMapping(value = "/formDisciplina", method = RequestMethod.GET)
	public ModelAndView formDisciplina() {
		ModelAndView model = new ModelAndView(AliasPaginas.CADASTRO_DISCIPLINA);
		List<Curso> cursos = (List<Curso>) cursoController.findAll();

		model.addObject("cursos", cursos);
		model.addObject("disciplina", new Disciplina());
		return model;
	}

	/**
	 * Executa a operação de incluir
	 * 
	 * @return
	 */
	@RequestMapping(value = "/incluirDisciplina", method = RequestMethod.POST)
	public ModelAndView incluir(@ModelAttribute("disciplina") Disciplina disciplina, BindingResult result,HttpServletRequest request, RedirectAttributes redirect) {
		ModelAndView model = new ModelAndView(AliasPaginas.LISTA_DISCIPLINA);
		try {
			disciplina.setCurso(cursoController.findById(Long.valueOf(request.getParameter("curso"))));
			disciplinaController.save(disciplina);
			
			model.addObject(TipoMensagem.VARIAVEL_VIEW_TIPO.getValor(), TipoMensagem.SUCESSO.getValor());
			model.addObject(Mensagems.VARIAVEL_VIEW_MENSAGEM, Mensagems.DisciplinaCadastrada);
			model = this.reload(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	/**
	 * Lista a Disciplina para cada curso
	 * 
	 * @return
	 */
	@RequestMapping(value = "/listaDisciplina", method = RequestMethod.GET)
	public ModelAndView formLista() {
		ModelAndView model = new ModelAndView(AliasPaginas.LISTA_DISCIPLINA);
		List<Disciplina> disciplinas = (List<Disciplina>) disciplinaController.findAll();
		model.addObject("disciplinas", disciplinas);
		return model;
	}
	
	@RequestMapping("/excluirDisciplina")
	public ModelAndView exluir(@RequestParam("id")Long id){
		ModelAndView model = new ModelAndView(AliasPaginas.LISTA_DISCIPLINA);
		try {
			disciplinaController.delete(id);	
			model.addObject(TipoMensagem.VARIAVEL_VIEW_TIPO.getValor(), TipoMensagem.SUCESSO.getValor());
			model.addObject(Mensagems.VARIAVEL_VIEW_MENSAGEM, "Disciplina Excluida");
			model  = this.reload(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	
	private ModelAndView reload(ModelAndView model){
		List<Disciplina> disciplinas = (List<Disciplina>) disciplinaController.findAll();
		model.addObject("disciplinas", disciplinas);
		return model;
	}

}
