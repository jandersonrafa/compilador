%{
	import java.io.*;
	import java.util.*;
%}

/* BYACC Declarations */
%token <sval> IDENTIFICADOR
%token <sval> TIPO_DADO
%token <sval> INCLUSAO_ARQUIVO
%token ABRE_PARENTESES
%token FECHA_PARENTESES
%token ABRE_CHAVES
%token FECHA_CHAVES
%token FUNCAO_PRINCIPAL
%token FUNCAO
%token INCLUIR
%token VIRGULA
%type <sval> programa
%type <sval> funcao_principal
%type <sval> funcao
%type <sval> inclusao
%type <sval> comandos
%type <sval> parametros
%type <sval> declaracao

%%
inicio : programa	 { System.out.println($1); }

programa : inclusao programa	{ $$ = $1 + "\n" + $2; }
		 | funcao_principal programa { $$ = $1 + "\n" + $2; }
		 | funcao programa { $$ = $1 + "\n" + $2; }
	     |					{ $$ = ""; }

funcao_principal : FUNCAO_PRINCIPAL ABRE_CHAVES comandos FECHA_CHAVES { $$ = "int main() {\n " + $3 + "}\n"; }
funcao : FUNCAO TIPO_DADO IDENTIFICADOR ABRE_PARENTESES parametros FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES { $$ = $2 + " " + $3 + "(" + $5 + ") {\n " + $8 + "}\n"; }
	   | FUNCAO TIPO_DADO IDENTIFICADOR ABRE_PARENTESES FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES { $$ = $2 + " " + $3 + "() {\n " + $7 + "}\n"; }

inclusao : INCLUIR INCLUSAO_ARQUIVO	{ $$ = "#include " + $2; }

comandos : declaracao		{ $$ = $1; }
		 |					{ $$ = ""; }

parametros : TIPO_DADO IDENTIFICADOR	{  $$ = $1 + " " + $2 ; }		   
		   | TIPO_DADO IDENTIFICADOR VIRGULA parametros	{  $$ = "int " + $2 + ", " + $4; }

declaracao : TIPO_DADO IDENTIFICADOR	{  $$ = "int " + $2 + ";\n"; }

%%

	// Referencia ao JFlex
	private Yylex lexer;

	/* Interface com o JFlex */
	private int yylex(){
		int yyl_return = -1;
		try {
			yyl_return = lexer.yylex();
		} catch (IOException e) {
			System.err.println("Erro de IO: " + e);
		}
		return yyl_return;
	}

	/* Reporte de erro */
	public void yyerror(String error){
		System.err.println("Error: " + error);
	}

	// Interface com o JFlex eh criado no construtor
	public Parser(Reader r){
		lexer = new Yylex(r, this);
	}

	// Main
	public static void main(String[] args){
		try{ 
			Parser yyparser = new Parser(new FileReader(args[0]));
			yyparser.yyparse();
			} catch (IOException ex) {
				System.err.println("Error: " + ex);
			}
	}
