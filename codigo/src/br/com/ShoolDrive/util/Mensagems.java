package br.com.ShoolDrive.util;

public enum Mensagems {
	UsuarioCadastrado("Usuario Cadastrado Com Sucesso"),
	UsuarioExcluido("Usuario Excluido!"),
	ErroOperacaoUsuario("Ocorreu Um Erro !"),
	DisciplinaCadastrada("Disciplina Cadastrada"),
	
	
	
	VARIAVEL_VIEW_MENSAGEM("mensagens")
	
	; 
	
	private String mensagem;
	
	public String getMensagem() {
		return mensagem;
	}

	private Mensagems(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
	
}
