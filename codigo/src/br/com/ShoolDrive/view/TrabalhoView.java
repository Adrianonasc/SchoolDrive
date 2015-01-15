package br.com.ShoolDrive.view;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.ShoolDrive.controler.IAlunoController;
import br.com.ShoolDrive.controler.IDisciplinaController;
import br.com.ShoolDrive.controler.IEntregaController;
import br.com.ShoolDrive.controler.IProfessorController;
import br.com.ShoolDrive.controler.ITrabalhoController;
import br.com.ShoolDrive.convert.ConvertDisciplina;
import br.com.ShoolDrive.entidade.Disciplina;
import br.com.ShoolDrive.entidade.Entrega;
import br.com.ShoolDrive.entidade.Professor;
import br.com.ShoolDrive.entidade.Trabalho;
import br.com.ShoolDrive.exception.RNException;
import br.com.ShoolDrive.util.AliasPaginas;
import br.com.ShoolDrive.util.Mensagems;
import br.com.ShoolDrive.util.TipoMensagem;

@Controller
@Secured("ROLE_PROFESSOR")
public class TrabalhoView {

	@Autowired
	private IDisciplinaController disciplinaController;

	@Autowired
	private IProfessorController professorControler;

	@Autowired
	private ITrabalhoController trabalhoController;

	@Autowired
	private IEntregaController entregaController;

	@Autowired
	private ConvertDisciplina convertDisciplina;

	@Autowired
	private IAlunoController alunoController;

	/**
	 * Responsavel por inicializar os converts e registralos no jsp
	 * 
	 * @param webDataBinder
	 */
	@InitBinder
	public void intblind(WebDataBinder binder) {
		binder.registerCustomEditor(Disciplina.class, this.convertDisciplina);
	}

	@RequestMapping("/formPublicarTrabalho")
	public ModelAndView formTrabalho() {
		ModelAndView model = new ModelAndView(AliasPaginas.VIEW_INCLUIR_TRABALHO);
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String email = auth.getName();
			Professor professor = this.professorControler.findByEmail(email);
			model.addObject("disciplinas", this.disciplinaController.DisciplinasProfessor(professor.getId()));
			model.addObject("trabalho", new Trabalho());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/incluirTrabalho", method = RequestMethod.POST)
	public ModelAndView incluirTrabalho(@ModelAttribute("trabalho") Trabalho trabalho,
	                                    @RequestParam("disciplina") Long disciplinaID) {
		ModelAndView model = new ModelAndView("redirect:formPublicarTrabalho");
		try {
			trabalho.setDisciplina(this.disciplinaController.findOne(disciplinaID));
			this.trabalhoController.save(trabalho);
			model = this.formTrabalho();
			model.addObject(TipoMensagem.VARIAVEL_VIEW_TIPO.getValor(), TipoMensagem.SUCESSO.getValor());
			model.addObject(Mensagems.VARIAVEL_VIEW_MENSAGEM, "Trabalho Cadastrado");
		} catch (Exception e) {
			e.printStackTrace();
			return model;
		}
		return model;
	}

	@RequestMapping("/listaTrabalhos")
	public ModelAndView listaTrabalho() {
		ModelAndView model = new ModelAndView(AliasPaginas.VIEW_LISTA_TRABALHO);
		try {
			model.addObject("trabalhos", this.trabalhoController.findAll());
		} catch (RNException e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping("/formConsultarTrabalho")
	public ModelAndView listaTrabalhoDisciplina() {
		ModelAndView model = new ModelAndView(AliasPaginas.VIEW_LISTA_DISCIPLINA_TRABALHO);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		Professor professor = this.professorControler.findByEmail(email);
		model.addObject("disciplinas", this.disciplinaController.DisciplinasProfessor(professor.getId()));
		return model;
	}

	@RequestMapping("/listaEntregasTrabalho")
	public ModelAndView listaEntregasAlunos(@RequestParam("trabalhoId") Long trabalhoId) {
		ModelAndView model = new ModelAndView(AliasPaginas.VIEW_LISTA_ENTREGAS);
		Trabalho trabalho = this.trabalhoController.findOne(trabalhoId);
		List<Entrega> entregas = this.entregaController.findByTrabalho(trabalho);
		DateTime dataTrabalho = new DateTime(trabalho.getDataLimite());
		if (entregas.size() == 0) {
			model.addObject(TipoMensagem.VARIAVEL_VIEW_TIPO.getValor(), TipoMensagem.ALERTA.getValor());
			model.addObject(Mensagems.VARIAVEL_VIEW_MENSAGEM, "Ainda não houve entregas!");
			model.addObject("isEntrega", false);
		}
		else {
			model.addObject("isEntrega", true);
			model.addObject("entregas", entregas);
			model.addObject("foraPrazo", dataTrabalho.isBeforeNow());// se estiver fora do prazo
			// desabilita atualizaçoes
		}
		model.addObject("trabalho", trabalho);
		return model;
	}

	@RequestMapping("/downloadEntrega")
	public void downloadArquivo(@RequestParam("entregaId") Long entregaId, HttpServletRequest request,
	                            HttpServletResponse response) {
		Entrega entrega = this.entregaController.findOne(entregaId);
		String nomeArquivo = entrega.getNomeAnexo();
		try {
			response.setHeader("Content-Disposition", "attachment; filename=" + nomeArquivo);
			FileCopyUtils.copy(entrega.getAnexo(), response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/notas")
	public ModelAndView publicarNotas(@RequestParam MultiValueMap<String, String> param) {
		ModelAndView model = this.listaTrabalhoDisciplina();
		Long trabalhoId = Long.parseLong(param.getFirst("trabalhoId"));
		param.remove("trabalhoId");
		Trabalho trabalho = this.trabalhoController.findOne(trabalhoId);
		try {
			this.entregaController.publicarNotas(param.toSingleValueMap(), trabalho);
			model.addObject(TipoMensagem.VARIAVEL_VIEW_TIPO.getValor(), TipoMensagem.SUCESSO.getValor());
			model.addObject(Mensagems.VARIAVEL_VIEW_MENSAGEM, "Notas Atualizadas !!");
		} catch (RNException e) {
			model.addObject(TipoMensagem.VARIAVEL_VIEW_TIPO.getValor(), TipoMensagem.ERRO.getValor());
			model.addObject(Mensagems.VARIAVEL_VIEW_MENSAGEM, e.getMessage());
		}
		return model;
	}
}
