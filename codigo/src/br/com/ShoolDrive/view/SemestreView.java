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

import br.com.ShoolDrive.controler.ISemestreController;
import br.com.ShoolDrive.entidade.Semestre;
import br.com.ShoolDrive.exception.RNException;
import br.com.ShoolDrive.util.AliasPaginas;
import br.com.ShoolDrive.util.Mensagems;
import br.com.ShoolDrive.util.TipoMensagem;

@Controller
@Secured("ROLE_ADMIN")
public class SemestreView {

	@Autowired
	private ISemestreController semestreController;

	/**
	 * Monta o form para view
	 * 
	 * @return
	 */
	@RequestMapping(value = "/formSemestre", method = RequestMethod.GET)
	public ModelAndView formSemestre(ModelAndView model) {
		model.setViewName(AliasPaginas.CADASTRO_LISTA_SEMESTRE);
		List<Semestre> semestres = (List<Semestre>) semestreController.findAll();
		model.addObject("semestres", semestres);
		model.addObject("semestre", new Semestre());
		return model;
	}

	/**
	 * Executa a operação de incluir
	 * 
	 * @return
	 */
	@RequestMapping(value = "/incluirSemestre", method = RequestMethod.POST)
	public ModelAndView incluir(@ModelAttribute("semestre") Semestre semestre, BindingResult result, HttpServletRequest request,
			RedirectAttributes redirect) {

		ModelAndView model = new ModelAndView(AliasPaginas.LISTA_SEMESTRE);
		try {
			semestreController.save(semestre);	
			model.addObject(TipoMensagem.VARIAVEL_VIEW_TIPO.getValor(), TipoMensagem.SUCESSO.getValor());
			model.addObject(Mensagems.VARIAVEL_VIEW_MENSAGEM, "Semestre Aberto");
			model.addObject(this.formSemestre(model));
		} catch (RNException e) {
			model.addObject(this.formSemestre(model));
			model.addObject(TipoMensagem.VARIAVEL_VIEW_TIPO.getValor(), TipoMensagem.ERRO.getValor());
			model.addObject(Mensagems.VARIAVEL_VIEW_MENSAGEM, e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping("/fecharSemestre")
	public ModelAndView fecharSemestre(@RequestParam("id") Long id) {
		ModelAndView model = new ModelAndView(AliasPaginas.LISTA_SEMESTRE);
		try {
			semestreController.fecharSemestre(id);
			model.addObject(this.formSemestre(model));
			model.addObject(TipoMensagem.VARIAVEL_VIEW_TIPO.getValor(), TipoMensagem.SUCESSO.getValor());
			model.addObject(Mensagems.VARIAVEL_VIEW_MENSAGEM, "Semestre Fechado");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

}
