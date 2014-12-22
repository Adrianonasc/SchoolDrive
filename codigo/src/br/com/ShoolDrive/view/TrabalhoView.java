package br.com.ShoolDrive.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.ShoolDrive.controler.IDisciplinaController;
import br.com.ShoolDrive.controler.IEntregaController;
import br.com.ShoolDrive.controler.IProfessorController;
import br.com.ShoolDrive.controler.ITrabalhoController;
import br.com.ShoolDrive.convert.ConvertDisciplina;
import br.com.ShoolDrive.entidade.Disciplina;
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

        String ano = trabalho.getDataLimite().substring(0, 4);
        String mes = trabalho.getDataLimite().substring(5, 7);
        String dia = trabalho.getDataLimite().substring(8, 10);

        String dataFormatada = dia + "/" + mes + "/" + ano;
        trabalho.setDataLimite(dataFormatada);
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


}
