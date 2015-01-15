package br.com.ShoolDrive.view;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.ShoolDrive.controler.IAlunoController;
import br.com.ShoolDrive.controler.IDisciplinaController;
import br.com.ShoolDrive.controler.IEntregaController;
import br.com.ShoolDrive.controler.ITrabalhoController;
import br.com.ShoolDrive.entidade.Entrega;
import br.com.ShoolDrive.entidade.Trabalho;
import br.com.ShoolDrive.exception.RNException;
import br.com.ShoolDrive.util.AliasPaginas;
import br.com.ShoolDrive.util.Mensagems;
import br.com.ShoolDrive.util.TipoMensagem;

@Controller
@Secured("ROLE_ALUNO")
public class AlunoView {

	@Autowired
	private IDisciplinaController disciplinaController;

	@Autowired
	private IAlunoController alunoController;

	@Autowired
	private ITrabalhoController trabalhoController;

	@Autowired
	private IEntregaController entregaControler;

	@RequestMapping("/formListaDiscplinasAluno")
	public ModelAndView listaDisciplinas() {
		ModelAndView model = new ModelAndView(AliasPaginas.VIEW_LISTA_DISCIPLINA_ALUNO);
		String emailAluno = SecurityContextHolder.getContext().getAuthentication().getName();
		Long cursoId = this.alunoController.findByEmail(emailAluno).getCurso().getId();
		try {
			model.addObject("disciplinas", this.disciplinaController.DisciplinasCurso(cursoId, emailAluno));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping("/registraDisciplina")
	public ModelAndView registrarDisciplina(@RequestParam("disciplinaId") Long cursoId) {
		ModelAndView model = new ModelAndView();
		try {
			this.alunoController.registrarDisciplina(cursoId, SecurityContextHolder.getContext().getAuthentication()
																										  .getName());

			model = this.listaDisciplinas();
			model.addObject(TipoMensagem.VARIAVEL_VIEW_TIPO.getValor(), TipoMensagem.SUCESSO.getValor());
			model.addObject(Mensagems.VARIAVEL_VIEW_MENSAGEM, "Aluno Registrado!!");
		} catch (RNException e) {
			model = this.listaDisciplinas();
			model.addObject(TipoMensagem.VARIAVEL_VIEW_TIPO.getValor(), TipoMensagem.ERRO.getValor());
			model.addObject(Mensagems.VARIAVEL_VIEW_MENSAGEM, e.getMensagem());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping("/formListaTrabalho")
	public ModelAndView formTrabalhoAberto() {
		ModelAndView model = new ModelAndView("aluno/listaTrabalho");
		String emailAluno = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addObject("disciplinas", this.alunoController.findByEmail(emailAluno).getDisciplinas());
		return model;
	}


	@RequestMapping("/formTrabalho")
	public ModelAndView formEnviarTrabalho(@RequestParam("trabalhoId") Long trabalhoId) {
		ModelAndView model = new ModelAndView("aluno/enviarTrabalho");
		Trabalho trabalho = this.trabalhoController.findOne(trabalhoId);
		DateTime dataTrabalho = new DateTime(trabalho.getDataLimite());
		String emailAluno = SecurityContextHolder.getContext().getAuthentication().getName();

		Entrega entrega = this.entregaControler.findByAlunoAndTrabalho(trabalhoId, emailAluno);
		if (entrega != null) {
			model.addObject("entrega", entrega);
			model.addObject("status", "Entregue");
		}
		else if (dataTrabalho.isAfterNow()) {// Verifica se a data esta posterior a data atual
			model.addObject("status", "Aberto");
		}
		// data esta anterior a atual
		else if (dataTrabalho.isBeforeNow()) {
			model.addObject("status", "Fechado");
		}
		model.addObject("trabalho", trabalho);
		return model;
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody ModelAndView uploadFileHandler(@RequestParam("file") MultipartFile file,
																		 @RequestParam("Trabalhoid") Long TrabalhoId) {
		ModelAndView model = new ModelAndView();
		model = this.formTrabalhoAberto();
		Entrega entrega = new Entrega();
		String emailAluno = SecurityContextHolder.getContext().getAuthentication().getName();
		if (!file.isEmpty()) {
			try {
				entrega.setAnexo(file.getBytes());
				entrega.setNomeAnexo(file.getOriginalFilename());
				this.trabalhoController.EntregaTrabalho(entrega, emailAluno, TrabalhoId);
				model.addObject(TipoMensagem.VARIAVEL_VIEW_TIPO.getValor(), TipoMensagem.SUCESSO.getValor());
				model.addObject(Mensagems.VARIAVEL_VIEW_MENSAGEM, "Arquivo Enviado Enviado!!");
				return model;
			} catch (RNException e) {
				model.addObject(TipoMensagem.VARIAVEL_VIEW_TIPO.getValor(), TipoMensagem.SUCESSO.getValor());
				model.addObject(Mensagems.VARIAVEL_VIEW_MENSAGEM, e.getMensagem());
				return model;
			} catch (Exception e) {
				model.addObject(TipoMensagem.VARIAVEL_VIEW_TIPO.getValor(), TipoMensagem.SUCESSO.getValor());
				model.addObject(Mensagems.VARIAVEL_VIEW_MENSAGEM, "Ocorreu um erro tente mais Tarde!");
				return model;
			}
		}
		else {
			return model;
		}
	}

}
