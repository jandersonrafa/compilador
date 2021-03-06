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
se { return Parser.SE; }
opcao { return Parser.OPCAO; }
fim_opcao { return Parser.FIM_OPCAO; }
faca { return Parser.FACA; }
ate { return Parser.ATE; }
caso { return Parser.CASO; }
enquanto { return Parser.ENQUANTO; }
senao { return Parser.SENAO; }
retornar { return Parser.RETORNAR; }
, { return Parser.VIRGULA; }
";" { return Parser.PONTO_VIRGULA; }
":=" { return Parser.OPERADOR_ATRIBUIR; }
":" { return Parser.DOIS_PONTOS; }
"==" { return Parser.OPERADOR_COMPARACAO; }
"<=" { return Parser.OPERADOR_MENOR_IGUAL; }
">=" { return Parser.OPERADOR_MAIOR_IGUAL; }
"<" { return Parser.OPERADOR_MENOR; }
">" { return Parser.OPERADOR_MAIOR; }
"+" { return Parser.OPERADOR_MAIS; }
"-" { return Parser.OPERADOR_MENOS; }
"*" { return Parser.OPERADOR_MULTIPLICACAO; }
"/" { return Parser.OPERADOR_DIVISAO; }
"%" { return Parser.OPERADOR_RESTO_DIVISAO; }
imprima	{ return Parser.IMPRIMA; }
incluir	{ return Parser.INCLUIR; }
inteiro { return Parser.INTEIRO; }
real { return Parser.REAL; }
caracter { return Parser.CARACTER; }
\'.*\'	{ yyparser.yylval = new ParserVal(yytext());
		  return Parser.STRING; }
\".*\"	{ yyparser.yylval = new ParserVal(yytext());
		  return Parser.STRING_PARAMETRO; }
\<.*\>	{ yyparser.yylval = new ParserVal(yytext());
		  return Parser.INCLUSAO_ARQUIVO; }
(("/*".*"*/")|("//".*))	{ yyparser.yylval = new ParserVal(yytext());
		  return Parser.COMENTARIOS; }
"("	{ return Parser.ABRE_PARENTESES; }
")" { return Parser.FECHA_PARENTESES; }
"{"	{ return Parser.ABRE_CHAVES; }
"}" { return Parser.FECHA_CHAVES; }
"["	{ return Parser.ABRE_COLCHETES; }
"]" { return Parser.FECHA_COLCHETES; }
[a-zA-Z][a-zA-Z0-9]*	{ 
		yyparser.yylval = new ParserVal(yytext());
		return Parser.IDENTIFICADOR;
	}
[0-9]+ {
  yyparser.yylval = new ParserVal(yytext());
  return Parser.VALOR_INTEIRO;
}	

{NL}|" "|\t	{  }
