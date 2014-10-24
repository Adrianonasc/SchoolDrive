package br.com.pacotePrincipal.util;

public enum Mensagems {
	UsuarioCadastrado("Usuario Cadastrado Com Sucesso"),
	UsuarioExcluido("Usuario Excluido!"),
	ErroOperacaoUsuario("Ocorreu Um Erro !"),
	
	
	VARIAVEL_VIEW("mensagens")
	
	; 
	
	private String mensagem;
	
	public String getMensagem() {
		return mensagem;
	}

	private Mensagems(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
	
}
