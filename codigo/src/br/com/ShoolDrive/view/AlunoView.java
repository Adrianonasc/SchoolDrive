package br.com.ShoolDrive.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.ShoolDrive.controler.IAlunoController;
import br.com.ShoolDrive.controler.IDisciplinaController;
import br.com.ShoolDrive.exception.RNException;
import br.com.ShoolDrive.util.AliasPaginas;
import br.com.ShoolDrive.util.Mensagems;
import br.com.ShoolDrive.util.TipoMensagem;

@Controller
public class AlunoView {

	@Autowired
	private IDisciplinaController disciplinaController;

	@Autowired
	private IAlunoController alunoController;

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


}
