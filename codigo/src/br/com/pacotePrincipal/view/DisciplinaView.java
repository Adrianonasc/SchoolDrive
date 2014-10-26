package br.com.pacotePrincipal.view;

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

import br.com.pacotePrincipal.controler.ICursoController;
import br.com.pacotePrincipal.controler.IDisciplinaController;
import br.com.pacotePrincipal.convert.ConvertCurso;
import br.com.pacotePrincipal.entidade.Curso;
import br.com.pacotePrincipal.entidade.Disciplina;
import br.com.pacotePrincipal.util.Mensagems;
import br.com.pacotePrincipal.util.TipoMensagem;

/**
 * Classe Responsavel pelo Controller da Disciplina
 * 
 * @author Adriano
 * 
 */
@Controller
@Secured("ROLE_ADMIN")
public class DisciplinaView {
	private final String CADASTRO_DISCIPLINA = "admin/disciplina/cadastro";
	private final String LISTA_DISCIPLINA = "admin/disciplina/lista";

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
		ModelAndView model = new ModelAndView(CADASTRO_DISCIPLINA);
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
	public ModelAndView incluir(@ModelAttribute("disciplina") Disciplina disciplina, BindingResult result,
			HttpServletRequest request, RedirectAttributes redirect) {
		ModelAndView model = new ModelAndView();
		
		try {
			disciplina.setCurso(cursoController.findById(Long.valueOf(request.getParameter("curso"))));
			disciplinaController.save(disciplina);
			
			model.addObject(TipoMensagem.VARIAVEL_VIEW.getValor(), TipoMensagem.SUCESSO.getValor());
			model.addObject(Mensagems.VARIAVEL_VIEW.getMensagem(), Mensagems.DisciplinaCadastrada.getMensagem());
			model.addObject(this.formDisciplina());
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
		ModelAndView model = new ModelAndView(LISTA_DISCIPLINA);
		List<Disciplina> disciplinas = (List<Disciplina>) disciplinaController.findAll();
		model.addObject("disciplinas", disciplinas);
		return model;
	}
	
	@RequestMapping("/excluirDisciplina")
	public ModelAndView exluir(@RequestParam("id")Long id){
		ModelAndView model = new ModelAndView(LISTA_DISCIPLINA);
		try {
			disciplinaController.delete(id);
			model.addObject(this.formLista());
			model.addObject(TipoMensagem.VARIAVEL_VIEW.getValor(), TipoMensagem.SUCESSO.getValor());
			model.addObject(Mensagems.VARIAVEL_VIEW.getMensagem(), "Disciplina Excluida");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return model;
	}
	

}
