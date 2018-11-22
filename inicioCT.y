%{
	import java.io.*;
	import java.util.*;
%}

/* BYACC Declarations */
%token <sval> IDENTIFICADOR
%token <sval> VALOR
%token <sval> INCLUSAO_ARQUIVO
%token ABRE_PARENTESES
%token FECHA_PARENTESES
%token ABRE_CHAVES
%token FECHA_CHAVES
%token PARA
%token FUNCAO_PRINCIPAL
%token FUNCAO
%token INCLUIR
%token VIRGULA
%token PONTO_VIRGULA
%token OPERADOR_ATRIBUIR
%token OPERADOR_MENOR_IGUAL
%token OPERADOR_MAIS
%token INTEIRO
%token REAL
%type <sval> programa
%type <sval> funcao_principal
%type <sval> 
%type <sval> funcao
%type <sval> tipo_dado
%type <sval> inclusao
%type <sval> comandos
%type <sval> comando_para
%type <sval> parametros
%type <sval> atribuicao
%type <sval> declaracao

%%
inicio : programa	 { System.out.println($1); }

programa : inclusao programa	{ $$ = $1 + "\n" + $2; }
		 | funcao_principal programa { $$ = $1 + "\n" + $2; }
		 | funcao programa { $$ = $1 + "\n" + $2; }
	     |					{ $$ = ""; }

funcao_principal : FUNCAO_PRINCIPAL ABRE_CHAVES comandos FECHA_CHAVES { $$ = "int main() {\n " + $3 + "}\n"; }
funcao : FUNCAO tipo_dado IDENTIFICADOR ABRE_PARENTESES parametros FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES { $$ = $2 + " " + $3 + "(" + $5 + ") {\n " + $8 + "}\n"; }
	   | FUNCAO tipo_dado IDENTIFICADOR ABRE_PARENTESES FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES { $$ = $2 + " " + $3 + "() {\n " + $7 + "}\n"; }

inclusao : INCLUIR INCLUSAO_ARQUIVO	{ $$ = "#include " + $2; }

comandos : declaracao comandos		{ $$ = $1 + $2; }
		 | atribuicao comandos		{ $$ = $1 + $2; }
		 | comando_para comandos		{ $$ = $1 + $2; }
		 |					{ $$ = ""; }

comando_para : PARA ABRE_PARENTESES IDENTIFICADOR OPERADOR_ATRIBUIR VALOR PONTO_VIRGULA IDENTIFICADOR OPERADOR_MENOR_IGUAL IDENTIFICADOR PONTO_VIRGULA IDENTIFICADOR OPERADOR_MAIS OPERADOR_MAIS FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES{ $$ = "for(" + $3 + " = " + $5 + "; " + $7 +" <= " + $9 + "; " + $11 + "++" +"){\n" + $16 + "}\n"; }
			 | 
		 
parametros : tipo_dado IDENTIFICADOR	{  $$ = $1 + " " + $2 ; }		   
		   | tipo_dado IDENTIFICADOR VIRGULA parametros	{  $$ = $1 + " " + $2 + ", " + $4; }

declaracao : tipo_dado IDENTIFICADOR	{  $$ = $1 + " " + $2 + ";\n"; }

atribuicao : IDENTIFICADOR OPERADOR_ATRIBUIR VALOR	{  $$ = $1 + " = " + $3 + ";\n"; }

tipo_dado: INTEIRO {  $$ = "int"; }
		 | REAL {  $$ = "float"; }

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
