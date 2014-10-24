package br.com.pacotePrincipal.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.pacotePrincipal.entidade.Curso;
import br.com.pacotePrincipal.entidade.Disciplina;

/**
 * Classe Responsavel pelo Controller da Disciplina
 * @author Adriano
 *
 */
@Controller
@Secured("ROLE_ADMIN")
public class DisciplinaView {
	private final String CADASTRO_DISCIPLINA = "admin/disciplina/cadastroDisciplina"; 
	
	
	/**
	 * Monta o form para view
	 * @return 
	 */
	@RequestMapping(value = "/formAddDisciplina" ,method = RequestMethod.GET)
	public ModelAndView formDisciplina(){
		ModelAndView model = new ModelAndView(CADASTRO_DISCIPLINA);
		Curso curso;
		List<Curso> l = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			curso = new Curso();
			curso.setId(i);
			curso.setNome("Nome"+i);
			l.add(curso);
		}
		model.addObject("cursos", l);
		model.addObject("disciplina", new Disciplina());
		return model;
	}
	
	
	/**
	 *Executa a operação de incluir
	 * @return
	 */
	@RequestMapping(value = "/incluirDisciplina" ,method = RequestMethod.GET)
	public ModelAndView incluir(@ModelAttribute("disciplina")Disciplina disciplina, BindingResult result){
		System.out.println(disciplina.getCurso().getNome());
		ModelAndView model = new ModelAndView("");	
		return model;
	}
	
	/**
	 * Lista a Disciplina para cada curso
	 * @return
	 */
	@RequestMapping(value = "/listaDisciplina" ,method = RequestMethod.GET)
	public ModelAndView formLista(){
		ModelAndView model = new ModelAndView("cadastroDisciplina");
		return model;
	}
	

}
