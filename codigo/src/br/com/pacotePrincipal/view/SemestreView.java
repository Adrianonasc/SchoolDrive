package br.com.pacotePrincipal.view;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.pacotePrincipal.controler.ISemestreController;
import br.com.pacotePrincipal.entidade.Semestre;
import br.com.pacotePrincipal.exception.RNException;
import br.com.pacotePrincipal.util.Mensagems;
import br.com.pacotePrincipal.util.TipoMensagem;

@Controller
@Secured("ROLE_ADMIN")
public class SemestreView {

	private final String CADASTRO_LISTA_SEMESTRE = "admin/semestre/incluir";

	@Autowired
	private ISemestreController semestreController;

	/**
	 * Monta o form para view
	 * 
	 * @return
	 */
	@RequestMapping(value = "/formSemestre", method = RequestMethod.GET)
	public ModelAndView formSemestre() {
		ModelAndView model = new ModelAndView(CADASTRO_LISTA_SEMESTRE);
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

		ModelAndView model = new ModelAndView();
		try {
			
			model = this.formSemestre();
			semestreController.save(semestre);
			
			model.addObject(TipoMensagem.VARIAVEL_VIEW.getValor(), TipoMensagem.SUCESSO.getValor());
			model.addObject(Mensagems.VARIAVEL_VIEW.getMensagem(), "Semestre Aberto");

		} catch (RNException e) {
			
			model = this.formSemestre();
			model.addObject(TipoMensagem.VARIAVEL_VIEW.getValor(), TipoMensagem.ERRO.getValor());
			model.addObject(Mensagems.VARIAVEL_VIEW.getMensagem(), e.getMensagem());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping("/fecharSemestre")
	public ModelAndView fecharSemestre(@RequestParam("id") Long id) {
		ModelAndView model = new ModelAndView();
		try {
			semestreController.fecharSemestre(id);
			model = this.formSemestre();
			model.addObject(TipoMensagem.VARIAVEL_VIEW.getValor(), TipoMensagem.SUCESSO.getValor());
			model.addObject(Mensagems.VARIAVEL_VIEW.getMensagem(), "Semestre Fechado");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

}
