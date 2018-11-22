import java.io.*;

%%

%byaccj

%{

	// Armazena uma referencia para o parser
	private Parser yyparser;

	// Construtor recebendo o parser como parametro adicional
	public Yylex(Reader r, Parser yyparser){
		this(r);
		this.yyparser = yyparser;
	}	

%}

NL = \n | \r | \r\n

%%

funcao_principal { return Parser.FUNCAO_PRINCIPAL; }
funcao { return Parser.FUNCAO; }
, { return Parser.VIRGULA; }
incluir	{ return Parser.INCLUIR; }
(real|inteiro)	{ 
			yyparser.yylval = new ParserVal(yytext());
		  return Parser.TIPO_DADO; }
\<.*\>	{ yyparser.yylval = new ParserVal(yytext());
		  return Parser.INCLUSAO_ARQUIVO; }
"("	{ return Parser.ABRE_PARENTESES; }
")" { return Parser.FECHA_PARENTESES; }
"{"	{ return Parser.ABRE_CHAVES; }
"}" { return Parser.FECHA_CHAVES; }
[a-zA-Z][a-zA-Z0-9]*	{ 
		yyparser.yylval = new ParserVal(yytext());
		return Parser.IDENTIFICADOR;
	}
{NL}|" "|\t	{  }
