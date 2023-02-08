package com.gesoft.food.api.execeptionhandler;
import lombok.Getter;

@Getter
public enum ProblemType {
	ACESSO_NEGADO("/acesso_negado", "Acesso negado"),	
	RECURSO_NAO_ENCONTRADO("/recurso_não_encontrado", "Recurso não encontrado"),
	PARAMETRO_INVALIDO("/parametro_invlido","Parâmetro invalido."),
	PROPRIEDADE_IGNORADA("/propriedade-ignorada","Propriedade ignorada"),
	PROPRIEDADE_INRECONHESIVEL("/propriedade-inreconhecivel","Propriedade Inreconhecivel"),
	MENSAGEM_INCOMPREENSIVEL("/menssagem-incompreesivel","Mensagem incompreesível"),
	ENTIDADE_NAO_ENCONTRADA("/recurso-nao-encontrada", "Recurso não encontrada"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"), 
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio");;
	
	private String title;
	private String uri;
	
	ProblemType(String path, String title) {
		this.uri = "https://algafood.com.br" + path;
		this.title = title;
	}
	
}