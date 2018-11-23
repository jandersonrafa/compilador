//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "inicioCT.y"
	import java.io.*;
	import java.util.*;
//#line 20 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short IDENTIFICADOR=257;
public final static short VALOR=258;
public final static short INCLUSAO_ARQUIVO=259;
public final static short ABRE_PARENTESES=260;
public final static short FECHA_PARENTESES=261;
public final static short ABRE_CHAVES=262;
public final static short FECHA_CHAVES=263;
public final static short PARA=264;
public final static short SE=265;
public final static short RETORNAR=266;
public final static short FUNCAO_PRINCIPAL=267;
public final static short FUNCAO=268;
public final static short INCLUIR=269;
public final static short VIRGULA=270;
public final static short PONTO_VIRGULA=271;
public final static short OPERADOR_ATRIBUIR=272;
public final static short OPERADOR_COMPARACAO=273;
public final static short OPERADOR_MENOR_IGUAL=274;
public final static short OPERADOR_MAIS=275;
public final static short OPERADOR_MENOS=276;
public final static short OPERADOR_DIVISAO=277;
public final static short OPERADOR_MULTIPLICACAO=278;
public final static short OPERADOR_RESTO_DIVISAO=279;
public final static short INTEIRO=280;
public final static short REAL=281;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    1,    1,    2,    3,    3,    5,    6,
    6,    6,    6,    6,    6,    6,    6,    7,    7,    8,
    9,    9,   11,   10,   12,   12,   12,   12,   12,   12,
   12,    4,    4,   13,   13,   13,   13,   13,
};
final static short yylen[] = {                            2,
    1,    2,    2,    2,    0,    4,    9,    8,    2,    2,
    2,    2,    2,    2,    2,    2,    0,   17,   17,   11,
    2,    4,    2,    3,    1,    1,    3,    3,    3,    3,
    5,    1,    1,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    1,    0,    0,    0,    0,   32,
   33,    0,    9,    3,    4,    2,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   38,   34,   35,   36,    0,   37,    0,    0,
    0,    0,    0,   16,   15,   23,    6,   13,   14,   11,
   10,   12,    0,   24,   27,   29,   28,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   31,
    0,    0,    0,    0,    0,    0,    0,    8,   22,    0,
    0,    0,    7,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   20,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   18,   19,
};
final static short yydgoto[] = {                          4,
    5,    6,    7,   23,    8,   24,   25,   26,   63,   27,
   28,   29,   37,
};
final static short yysindex[] = {                      -213,
 -244, -246, -233,    0,    0, -213, -213, -213, -146,    0,
    0, -229,    0,    0,    0,    0, -239, -255, -214, -247,
 -219, -178, -204, -203, -146, -146, -146, -146, -146, -175,
 -214, -187,    0,    0,    0,    0, -214,    0, -214, -207,
 -166, -154, -153,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -251,    0,    0,    0,    0, -255, -167, -255,
 -152, -149, -148, -214, -143, -136, -146, -161, -140,    0,
 -147, -150, -138, -246, -146, -131, -130,    0,    0, -134,
 -144, -129,    0, -160, -135, -133, -132, -146, -126, -124,
 -127, -128, -125,    0, -123, -122, -121, -120, -119, -118,
 -146, -146, -117, -115,    0,    0,
};
final static short yyrindex[] = {                       137,
    0,    0,    0,    0,    0,  137,  137,  137, -114,    0,
    0,    0,    0,    0,    0,    0, -164, -249,    0,    0,
    0,    0,    0,    0, -114, -114, -114, -114, -114,    0,
    0, -209,    0,    0,    0,    0,    0,    0,    0, -199,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -174,    0,    0,
    0,    0,    0,    0,    0,    0, -114, -116,    0,    0,
    0,    0,    0,    0, -114,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -114,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 -114, -114,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
   67,    0,    0,    4,    0,  -25,    0,    0,   68,    0,
    0,  -12,  -13,
};
final static int YYTABLESIZE=153;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         48,
   49,   50,   51,   52,   39,   12,   41,   25,   25,   61,
   25,   25,   42,   25,   25,   25,   25,    9,   54,   38,
   33,   34,   35,   36,   56,   13,   57,   30,   10,   11,
   25,   25,   31,   10,   11,   32,   33,   34,   35,   36,
   43,   73,   40,   18,   64,   19,   66,   37,   37,   80,
   37,   70,   46,    1,    2,    3,   62,   26,   26,   47,
   26,   26,   91,   26,   26,   26,   26,   32,   33,   34,
   35,   36,   14,   15,   16,  103,  104,   62,   44,   45,
   26,   26,   30,   30,   53,   30,   30,   55,   30,   30,
   30,   30,   26,   26,   58,   26,   86,   87,   26,   26,
   26,   26,   59,   60,   65,   30,   30,   68,   74,   67,
   17,   18,   69,   19,   71,   26,   26,   20,   21,   22,
   72,   75,   77,   76,   78,   81,   88,   82,   83,   84,
   92,   85,   93,   10,   11,   94,    5,   89,   90,   99,
  100,   79,  101,  102,   21,  105,   95,  106,   17,   96,
    0,   97,   98,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         25,
   26,   27,   28,   29,   18,    2,   19,  257,  258,  261,
  260,  261,  260,  263,  264,  265,  266,  262,   31,  275,
  276,  277,  278,  279,   37,  259,   39,  257,  280,  281,
  280,  281,  272,  280,  281,  275,  276,  277,  278,  279,
  260,   67,  257,  258,   58,  260,   60,  257,  258,   75,
  260,   64,  257,  267,  268,  269,   53,  257,  258,  263,
  260,  261,   88,  263,  264,  265,  266,  275,  276,  277,
  278,  279,    6,    7,    8,  101,  102,   74,  257,  258,
  280,  281,  257,  258,  260,  260,  261,  275,  263,  264,
  265,  266,  257,  258,  261,  260,  257,  258,  263,  264,
  265,  266,  257,  257,  272,  280,  281,  257,  270,  262,
  257,  258,  261,  260,  258,  280,  281,  264,  265,  266,
  257,  262,  273,  271,  263,  257,  262,  258,  263,  274,
  257,  261,  257,  280,  281,  263,    0,  271,  271,  261,
  261,   74,  262,  262,  261,  263,  275,  263,  263,  275,
   -1,  275,  275,
};
}
final static short YYFINAL=4;
final static short YYMAXTOKEN=281;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"IDENTIFICADOR","VALOR","INCLUSAO_ARQUIVO","ABRE_PARENTESES",
"FECHA_PARENTESES","ABRE_CHAVES","FECHA_CHAVES","PARA","SE","RETORNAR",
"FUNCAO_PRINCIPAL","FUNCAO","INCLUIR","VIRGULA","PONTO_VIRGULA",
"OPERADOR_ATRIBUIR","OPERADOR_COMPARACAO","OPERADOR_MENOR_IGUAL",
"OPERADOR_MAIS","OPERADOR_MENOS","OPERADOR_DIVISAO","OPERADOR_MULTIPLICACAO",
"OPERADOR_RESTO_DIVISAO","INTEIRO","REAL",
};
final static String yyrule[] = {
"$accept : inicio",
"inicio : programa",
"programa : inclusao programa",
"programa : funcao_principal programa",
"programa : funcao programa",
"programa :",
"funcao_principal : FUNCAO_PRINCIPAL ABRE_CHAVES comandos FECHA_CHAVES",
"funcao : FUNCAO tipo_dado IDENTIFICADOR ABRE_PARENTESES parametros FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES",
"funcao : FUNCAO tipo_dado IDENTIFICADOR ABRE_PARENTESES FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES",
"inclusao : INCLUIR INCLUSAO_ARQUIVO",
"comandos : declaracao comandos",
"comandos : atribuicao comandos",
"comandos : atribuicao_valor comandos",
"comandos : comando_para comandos",
"comandos : comando_se comandos",
"comandos : RETORNAR VALOR",
"comandos : RETORNAR IDENTIFICADOR",
"comandos :",
"comando_para : PARA ABRE_PARENTESES IDENTIFICADOR OPERADOR_ATRIBUIR VALOR PONTO_VIRGULA IDENTIFICADOR OPERADOR_MENOR_IGUAL IDENTIFICADOR PONTO_VIRGULA IDENTIFICADOR OPERADOR_MAIS OPERADOR_MAIS FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES",
"comando_para : PARA ABRE_PARENTESES IDENTIFICADOR OPERADOR_ATRIBUIR VALOR PONTO_VIRGULA IDENTIFICADOR OPERADOR_MENOR_IGUAL VALOR PONTO_VIRGULA IDENTIFICADOR OPERADOR_MAIS OPERADOR_MAIS FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES",
"comando_se : SE ABRE_PARENTESES IDENTIFICADOR operador IDENTIFICADOR OPERADOR_COMPARACAO VALOR FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES",
"parametros : tipo_dado IDENTIFICADOR",
"parametros : tipo_dado IDENTIFICADOR VIRGULA parametros",
"declaracao : tipo_dado IDENTIFICADOR",
"atribuicao : IDENTIFICADOR OPERADOR_ATRIBUIR atribuicao_valor",
"atribuicao_valor : VALOR",
"atribuicao_valor : IDENTIFICADOR",
"atribuicao_valor : IDENTIFICADOR OPERADOR_MAIS OPERADOR_MAIS",
"atribuicao_valor : VALOR operador atribuicao_valor",
"atribuicao_valor : IDENTIFICADOR operador atribuicao_valor",
"atribuicao_valor : ABRE_PARENTESES atribuicao_valor FECHA_PARENTESES",
"atribuicao_valor : ABRE_PARENTESES atribuicao_valor FECHA_PARENTESES operador atribuicao_valor",
"tipo_dado : INTEIRO",
"tipo_dado : REAL",
"operador : OPERADOR_DIVISAO",
"operador : OPERADOR_MULTIPLICACAO",
"operador : OPERADOR_RESTO_DIVISAO",
"operador : OPERADOR_MAIS",
"operador : OPERADOR_MENOS",
};

//#line 101 "inicioCT.y"

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
//#line 322 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 48 "inicioCT.y"
{ System.out.println(val_peek(0).sval); }
break;
case 2:
//#line 50 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 3:
//#line 51 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 4:
//#line 52 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 5:
//#line 53 "inicioCT.y"
{ yyval.sval = ""; }
break;
case 6:
//#line 55 "inicioCT.y"
{ yyval.sval = "int main() {\n " + val_peek(1).sval + "}\n"; }
break;
case 7:
//#line 56 "inicioCT.y"
{ yyval.sval = val_peek(7).sval + " " + val_peek(6).sval + "(" + val_peek(4).sval + ") {\n " + val_peek(1).sval + "}\n"; }
break;
case 8:
//#line 57 "inicioCT.y"
{ yyval.sval = val_peek(6).sval + " " + val_peek(5).sval + "() {\n " + val_peek(1).sval + "}\n"; }
break;
case 9:
//#line 59 "inicioCT.y"
{ yyval.sval = "#include " + val_peek(0).sval; }
break;
case 10:
//#line 61 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 11:
//#line 62 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 12:
//#line 63 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 13:
//#line 64 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 14:
//#line 65 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 15:
//#line 66 "inicioCT.y"
{ yyval.sval = "return "+ val_peek(0).sval + ";\n"; }
break;
case 16:
//#line 67 "inicioCT.y"
{ yyval.sval = "return "+ val_peek(0).sval + ";\n"; }
break;
case 17:
//#line 68 "inicioCT.y"
{ yyval.sval = ""; }
break;
case 18:
//#line 71 "inicioCT.y"
{ yyval.sval = "for(" + val_peek(14).sval + " = " + val_peek(12).sval + "; " + val_peek(10).sval +" <= " + val_peek(8).sval + "; " + val_peek(6).sval + "++" +"){\n" + val_peek(1).sval + "}\n"; }
break;
case 19:
//#line 72 "inicioCT.y"
{ yyval.sval = "for(" + val_peek(14).sval + " = " + val_peek(12).sval + "; " + val_peek(10).sval +" <= " + val_peek(8).sval + "; " + val_peek(6).sval + "++" +"){\n" + val_peek(1).sval + "}\n"; }
break;
case 20:
//#line 74 "inicioCT.y"
{  yyval.sval = "if (" + val_peek(8).sval + val_peek(7).sval + val_peek(6).sval + "==" + val_peek(4).sval + ")\n{" + val_peek(1).sval+ "}\n"; }
break;
case 21:
//#line 76 "inicioCT.y"
{  yyval.sval = val_peek(1).sval + " " + val_peek(0).sval ; }
break;
case 22:
//#line 77 "inicioCT.y"
{  yyval.sval = val_peek(3).sval + " " + val_peek(2).sval + ", " + val_peek(0).sval; }
break;
case 23:
//#line 79 "inicioCT.y"
{  yyval.sval = val_peek(1).sval + " " + val_peek(0).sval + ";\n"; }
break;
case 24:
//#line 81 "inicioCT.y"
{  yyval.sval = val_peek(2).sval + " = " + val_peek(0).sval + ";\n"; }
break;
case 25:
//#line 83 "inicioCT.y"
{  yyval.sval = val_peek(0).sval; }
break;
case 26:
//#line 84 "inicioCT.y"
{  yyval.sval = val_peek(0).sval; }
break;
case 27:
//#line 85 "inicioCT.y"
{  yyval.sval = val_peek(2).sval; }
break;
case 28:
//#line 86 "inicioCT.y"
{  yyval.sval = val_peek(2).sval + " " + val_peek(1).sval + " " + val_peek(0).sval; }
break;
case 29:
//#line 87 "inicioCT.y"
{  yyval.sval = val_peek(2).sval + " " + val_peek(1).sval + " " + val_peek(0).sval; }
break;
case 30:
//#line 88 "inicioCT.y"
{  yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 31:
//#line 89 "inicioCT.y"
{  yyval.sval = "(" + val_peek(3).sval + ") " + val_peek(1).sval + val_peek(0).sval; }
break;
case 32:
//#line 91 "inicioCT.y"
{  yyval.sval = "int"; }
break;
case 33:
//#line 92 "inicioCT.y"
{  yyval.sval = "float"; }
break;
case 34:
//#line 94 "inicioCT.y"
{  yyval.sval = "/"; }
break;
case 35:
//#line 95 "inicioCT.y"
{  yyval.sval = "*"; }
break;
case 36:
//#line 96 "inicioCT.y"
{  yyval.sval = "%"; }
break;
case 37:
//#line 97 "inicioCT.y"
{  yyval.sval = "+"; }
break;
case 38:
//#line 98 "inicioCT.y"
{  yyval.sval = "-"; }
break;
//#line 623 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
