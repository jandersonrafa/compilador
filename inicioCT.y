%{
	import java.io.*;
	import java.util.*;
%}

/* BYACC Declarations */
%token <sval> IDENTIFICADOR
%token <sval> VALOR
%token <sval> VALOR_INTEIRO
%token <sval> INCLUSAO_ARQUIVO
%token <sval> COMENTARIOS
%token <sval> STRING
%token <sval> STRING_PARAMETRO
%token ABRE_PARENTESES
%token FECHA_PARENTESES
%token ABRE_CHAVES
%token FECHA_CHAVES
%token ABRE_COLCHETES
%token FECHA_COLCHETES
%token PARA
%token SE
%token OPCAO
%token FIM_OPCAO
%token FACA
%token CASO
%token ATE
%token SENAO
%token ENQUANTO
%token RETORNAR
%token FUNCAO_PRINCIPAL
%token FUNCAO
%token INCLUIR
%token VIRGULA
%token PONTO_VIRGULA
%token OPERADOR_ATRIBUIR
%token OPERADOR_COMPARACAO
%token OPERADOR_MENOR_IGUAL
%token OPERADOR_MAIOR_IGUAL
%token OPERADOR_MAIS
%token OPERADOR_MENOS
%token OPERADOR_MENOR
%token DOIS_PONTOS
%token OPERADOR_MAIOR
%token OPERADOR_DIVISAO
%token OPERADOR_MULTIPLICACAO
%token OPERADOR_RESTO_DIVISAO
%token INTEIRO
%token REAL
%token CARACTER
%type <sval> programa
%type <sval> funcao_principal
%type <sval> 
%type <sval> funcao
%type <sval> tipo_dado
%type <sval> inclusao
%type <sval> comandos
%type <sval> comando_para
%type <sval> comando_faca
%type <sval> comando_se
%type <sval> comando_enquanto
%type <sval> comando_caso
%type <sval> comando_caso_opcao
%type <sval> comando_caso_opcoes
%type <sval> parametros
%type <sval> chamada_metodo_parametros
%type <sval> atribuicao
%type <sval> declaracao
%type <sval> atribuicao_valor
%type <sval> operador
%type <sval> operador_condicao
%type <sval> condicao
%type <sval> chamada_metodo
%type <sval> chamada_metodo_identificador

%%
inicio : programa	 { System.out.println($1); }

programa : inclusao programa	{ $$ = $1 + "\n" + $2; }
		 | COMENTARIOS programa { $$ = $1 + "\n" + $2; }
		 | funcao_principal programa { $$ = $1 + "\n" + $2; }
		 | funcao programa { $$ = $1 + "\n" + $2; }
	     |					{ $$ = ""; }

funcao_principal : FUNCAO_PRINCIPAL ABRE_CHAVES comandos FECHA_CHAVES { $$ = "int main() {\n " + $3 + "}\n"; }
funcao : FUNCAO tipo_dado IDENTIFICADOR ABRE_PARENTESES parametros FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES { $$ = $2 + " " + $3 + "(" + $5 + ") {\n " + $8 + "}\n"; }
	   | FUNCAO tipo_dado IDENTIFICADOR ABRE_PARENTESES FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES { $$ = $2 + " " + $3 + "() {\n " + $7 + "}\n"; }

inclusao : INCLUIR INCLUSAO_ARQUIVO	{ $$ = "#include " + $2; }

comandos : declaracao comandos		{ $$ = $1 + $2; }
		 | atribuicao comandos		{ $$ = $1 + $2; }
		 | atribuicao_valor comandos		{ $$ = $1 + "\n"+ $2; }
		 | comando_para comandos		{ $$ = $1 + $2; }
		 | comando_se comandos		{ $$ = $1 + $2; }
		 | comando_faca comandos		{ $$ = $1 + $2; } 
		 | comando_caso comandos		{ $$ = $1 + $2; } 		 
		 | comando_enquanto comandos		{ $$ = $1 + $2; }
		 | chamada_metodo comandos		{ $$ = $1 + ";\n" + $2; }		 
		 | COMENTARIOS comandos		{ $$ = $1  +"\n" + $2; }		 
		 |	RETORNAR VALOR	{ $$ = "return "+ $2 + ";\n"; }
		 |	RETORNAR STRING	{ $$ = "return "+ $2 + ";\n"; }		 
		 |	RETORNAR IDENTIFICADOR	{ $$ = "return "+ $2 + ";\n"; }
		 |					{ $$ = ""; }
		 

comando_para : PARA ABRE_PARENTESES IDENTIFICADOR OPERADOR_ATRIBUIR VALOR PONTO_VIRGULA IDENTIFICADOR operador_condicao IDENTIFICADOR PONTO_VIRGULA IDENTIFICADOR OPERADOR_MAIS OPERADOR_MAIS FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES{ $$ = "for(" + $3 + " = " + $5 + "; " + $7 +" <= " + $9 + "; " + $11 + "++" +"){\n" + $16 + "}\n"; }
			 | PARA ABRE_PARENTESES IDENTIFICADOR OPERADOR_ATRIBUIR VALOR PONTO_VIRGULA IDENTIFICADOR operador_condicao VALOR PONTO_VIRGULA IDENTIFICADOR OPERADOR_MAIS OPERADOR_MAIS FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES{ $$ = "for(" + $3 + " = " + $5 + "; " + $7 +" <= " + $9 + "; " + $11 + "++" +"){\n" + $16 + "}\n"; }

condicao :  atribuicao_valor operador_condicao VALOR {  $$ = $1 + " " + $2 + " " + $3; }
			| atribuicao_valor operador_condicao IDENTIFICADOR {  $$ =  $1 + " " + $2 + " " + $3; }
			
comando_faca : FACA ABRE_CHAVES comandos FECHA_CHAVES ATE ABRE_PARENTESES condicao FECHA_PARENTESES		{  $$ = "do{\n" + $3 + "\n}while(" + $7 + ");\n"; }
			
comando_se : SE ABRE_PARENTESES condicao FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES	{  $$ = "if (" + $3 + "){\n" + $6+ "}\n"; }
		   | SE ABRE_PARENTESES condicao FECHA_PARENTESES comandos 	{  $$ = "if (" + $3 + ")\n" + $5+ "\n"; }
		   | SE ABRE_PARENTESES condicao FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES	SENAO ABRE_CHAVES comandos FECHA_CHAVES{  $$ = "if (" + $3 + "){\n" + $6+ "\n} else {\n" + $10 + "\n}"; }
		   
comando_caso : CASO ABRE_PARENTESES IDENTIFICADOR FECHA_PARENTESES ABRE_CHAVES comando_caso_opcoes FECHA_CHAVES {  $$ = "switch (" + $3 + "){\n" + $6+ "}\n"; }
			 | CASO ABRE_PARENTESES IDENTIFICADOR ABRE_COLCHETES IDENTIFICADOR FECHA_COLCHETES FECHA_PARENTESES ABRE_CHAVES comando_caso_opcoes FECHA_CHAVES {  $$ = "switch (" + $3 + "[" + $5 + "]){\n" + $9+ "}\n"; }
			 | CASO ABRE_PARENTESES IDENTIFICADOR ABRE_COLCHETES VALOR_INTEIRO FECHA_COLCHETES FECHA_PARENTESES ABRE_CHAVES comando_caso_opcoes FECHA_CHAVES {  $$ = "switch (" + $3 + "[" + $5 + "]){\n" + $9+ "}\n"; }

comando_caso_opcoes : comando_caso_opcao {  $$ = $1; }
					| comando_caso_opcao comando_caso_opcoes {  $$ = $1 + $2; }
			 
comando_caso_opcao : OPCAO STRING DOIS_PONTOS comandos FIM_OPCAO {  $$ = "case" + $2 + ":\n" + $4 + "break;\n"; }			 
   
comando_enquanto : ENQUANTO ABRE_PARENTESES condicao FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES	{  $$ = "while (" + $3 + "){\n" + $6+ "}\n"; }
		   | ENQUANTO ABRE_PARENTESES condicao FECHA_PARENTESES comandos 	{  $$ = "while (" + $3 + ")\n" + $5+ "\n"; }
		   			 
parametros : tipo_dado IDENTIFICADOR	{  $$ = $1 + " " + $2 ; }		   
		   | tipo_dado IDENTIFICADOR VIRGULA parametros	{  $$ = $1 + " " + $2 + ", " + $4; }

declaracao : tipo_dado IDENTIFICADOR	{  $$ = $1 + " " + $2 + ";\n"; }
		   | tipo_dado IDENTIFICADOR ABRE_COLCHETES VALOR_INTEIRO FECHA_COLCHETES	{  $$ = $1 + " " + $2 + "[" + $4 + "];\n"; }
		   | tipo_dado IDENTIFICADOR ABRE_COLCHETES IDENTIFICADOR FECHA_COLCHETES	{  $$ = $1 + " " + $2 + "[" + $4 + "];\n"; }

atribuicao : IDENTIFICADOR OPERADOR_ATRIBUIR atribuicao_valor	{  $$ = $1 + " = " + $3 + ";\n"; }		   
		   | IDENTIFICADOR ABRE_COLCHETES IDENTIFICADOR FECHA_COLCHETES OPERADOR_ATRIBUIR atribuicao_valor	{  $$ = $1 + "["+ $3 + "] = " + $6 + ";\n"; }
		   | IDENTIFICADOR ABRE_COLCHETES VALOR_INTEIRO FECHA_COLCHETES OPERADOR_ATRIBUIR atribuicao_valor	{  $$ = $1 + "["+ $3 + "] = " + $6 + ";\n"; }
	   
atribuicao_valor : VALOR	{  $$ = $1; }				
				| chamada_metodo_identificador	{  $$ = $1; }
				| chamada_metodo_identificador	OPERADOR_MAIS OPERADOR_MAIS {  $$ = $1 + "++"; }
				| OPERADOR_MAIS OPERADOR_MAIS chamada_metodo_identificador {  $$ = "++" + $3; }
				| chamada_metodo_identificador	OPERADOR_MENOS OPERADOR_MENOS {  $$ = $1 + "--"; }
				| OPERADOR_MENOS OPERADOR_MENOS chamada_metodo_identificador {  $$ = "--" + $3; }
				| VALOR operador atribuicao_valor	{  $$ = $1 + " " + $2 + " " + $3; }
				| chamada_metodo_identificador operador atribuicao_valor	{  $$ = $1 + " " + $2 + " " + $3; }				
				| ABRE_PARENTESES atribuicao_valor	FECHA_PARENTESES {  $$ = "(" + $2 + ")"; }
				| ABRE_PARENTESES atribuicao_valor	FECHA_PARENTESES operador atribuicao_valor {  $$ = "(" + $2 + ") " + $4 + $5; }

tipo_dado: INTEIRO {  $$ = "int"; }
		 | REAL {  $$ = "float"; }
		 | CARACTER {  $$ = "char"; }
			
chamada_metodo_parametros : IDENTIFICADOR	{  $$ = $1 ; }		   
		   | STRING_PARAMETRO	{  $$ = $1 ; }		   	
		   | IDENTIFICADOR ABRE_COLCHETES IDENTIFICADOR FECHA_COLCHETES	{  $$ = $1 + "[" + $3 + "]"; }		   
		   | IDENTIFICADOR VIRGULA chamada_metodo_parametros	{  $$ = $1 + ", " + $3; }
		   | STRING_PARAMETRO VIRGULA chamada_metodo_parametros	{  $$ = $1 + ", " + $3; }
		   
chamada_metodo: IDENTIFICADOR ABRE_PARENTESES FECHA_PARENTESES {  $$ = $1 + "()"; }
			  | IDENTIFICADOR ABRE_PARENTESES chamada_metodo_parametros FECHA_PARENTESES {  $$ = $1 + "(" + $3 +")"; }

chamada_metodo_identificador: IDENTIFICADOR {  $$ = $1; }
							| chamada_metodo {  $$ = $1; }

operador: OPERADOR_DIVISAO {  $$ = "/"; }
		 | OPERADOR_MULTIPLICACAO {  $$ = "*"; }
		 | OPERADOR_RESTO_DIVISAO {  $$ = "%"; }
		 | OPERADOR_MAIS {  $$ = "+"; }
		 | OPERADOR_MENOS {  $$ = "-"; }
		 
operador_condicao: OPERADOR_COMPARACAO {  $$ = "=="; }
		 | OPERADOR_MAIOR_IGUAL {  $$ = ">="; }
		 | OPERADOR_MENOR_IGUAL {  $$ = "<="; }	 
		 | OPERADOR_MENOR {  $$ = "<"; }
		 | OPERADOR_MAIOR {  $$ = ">"; }
		 
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
