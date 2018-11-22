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
para { return Parser.PARA; }
, { return Parser.VIRGULA; }
";" { return Parser.PONTO_VIRGULA; }
":=" { return Parser.OPERADOR_ATRIBUIR; }
"<=" { return Parser.OPERADOR_MENOR_IGUAL; }
"+" { return Parser.OPERADOR_MAIS; }
incluir	{ return Parser.INCLUIR; }
inteiro { return Parser.INTEIRO; }
real { return Parser.REAL; }
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
[a-zA-Z0-9]*	{ 
		yyparser.yylval = new ParserVal(yytext());
		return Parser.VALOR;
	}	
{NL}|" "|\t	{  }
