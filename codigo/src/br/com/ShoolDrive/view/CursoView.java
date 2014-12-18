package br.com.ShoolDrive.view;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ShoolDrive.controler.ICursoController;
import br.com.ShoolDrive.convert.ConvertCurso;
import br.com.ShoolDrive.entidade.Curso;
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
public class CursoView {
	

	@Autowired
	private ConvertCurso convertCurso;

	@Autowired
	private ICursoController cursoController;
	
	/**
	 * Monta o form para view
	 * 
	 * @return
	 */
	@RequestMapping(value = "/formCurso", method = RequestMethod.GET)
	public ModelAndView formCurso() {
		ModelAndView model = new ModelAndView(AliasPaginas.CADASTRO_LISTA_CURSO);
		List<Curso> cursos = (List<Curso>) cursoController.findAll();
		model.addObject("cursos", cursos);
		model.addObject("curso", new Curso());
		return model;
	}

	/**
	 * Executa a operação de incluir
	 * 
	 * @return
	 */
	@RequestMapping(value = "/incluirCurso", method = RequestMethod.POST)
	public ModelAndView incluir(@ModelAttribute("curso") Curso curso, BindingResult result,
			HttpServletRequest request, RedirectAttributes redirect) {
		
		ModelAndView model = new ModelAndView(AliasPaginas.CADASTRO_LISTA_CURSO);
		
		try {
			cursoController.save(curso);
			model =this.formCurso();
			model.addObject(TipoMensagem.VARIAVEL_VIEW_TIPO.getValor(), TipoMensagem.SUCESSO.getValor());
			model.addObject(Mensagems.VARIAVEL_VIEW_MENSAGEM, "Curso Cadastrado");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping("/excluirCurso")
	public ModelAndView exluir(@RequestParam("id")Long id){
		ModelAndView model = new ModelAndView();
		try {
			cursoController.delete(id);
			model=this.formCurso();
			model.addObject(TipoMensagem.VARIAVEL_VIEW_TIPO.getValor(), TipoMensagem.SUCESSO.getValor());
			model.addObject(Mensagems.VARIAVEL_VIEW_MENSAGEM, "Disciplina Excluida");
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return model;
	}
	

}
