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
public final static short VALOR_INTEIRO=259;
public final static short INCLUSAO_ARQUIVO=260;
public final static short COMENTARIOS=261;
public final static short STRING=262;
public final static short STRING_PARAMETRO=263;
public final static short ABRE_PARENTESES=264;
public final static short FECHA_PARENTESES=265;
public final static short ABRE_CHAVES=266;
public final static short FECHA_CHAVES=267;
public final static short ABRE_COLCHETES=268;
public final static short FECHA_COLCHETES=269;
public final static short PARA=270;
public final static short SE=271;
public final static short OPCAO=272;
public final static short FIM_OPCAO=273;
public final static short FACA=274;
public final static short CASO=275;
public final static short ATE=276;
public final static short SENAO=277;
public final static short ENQUANTO=278;
public final static short RETORNAR=279;
public final static short FUNCAO_PRINCIPAL=280;
public final static short FUNCAO=281;
public final static short INCLUIR=282;
public final static short VIRGULA=283;
public final static short PONTO_VIRGULA=284;
public final static short OPERADOR_ATRIBUIR=285;
public final static short OPERADOR_COMPARACAO=286;
public final static short OPERADOR_MENOR_IGUAL=287;
public final static short OPERADOR_MAIOR_IGUAL=288;
public final static short OPERADOR_MAIS=289;
public final static short OPERADOR_MENOS=290;
public final static short OPERADOR_MENOR=291;
public final static short DOIS_PONTOS=292;
public final static short OPERADOR_MAIOR=293;
public final static short OPERADOR_DIVISAO=294;
public final static short OPERADOR_MULTIPLICACAO=295;
public final static short OPERADOR_RESTO_DIVISAO=296;
public final static short INTEIRO=297;
public final static short REAL=298;
public final static short CARACTER=299;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    1,    1,    1,    2,    3,    3,    5,
    6,    6,    6,    6,    6,    6,    6,    6,    6,    6,
    6,    6,    6,    6,    7,    7,   21,   21,    8,    9,
    9,    9,   11,   11,   11,   13,   13,   12,   10,   10,
   14,   14,   17,   17,   17,   16,   16,   16,   18,   18,
   18,   18,   18,   18,   18,   18,   18,   18,    4,    4,
    4,   15,   15,   15,   15,   15,   22,   22,   23,   23,
   19,   19,   19,   19,   19,   20,   20,   20,   20,   20,
};
final static short yylen[] = {                            2,
    1,    2,    2,    2,    2,    0,    4,    9,    8,    2,
    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    2,    0,   17,   17,    3,    3,    8,    7,
    5,   11,    7,   10,   10,    1,    2,    5,    7,    5,
    2,    4,    2,    5,    5,    3,    6,    6,    1,    1,
    3,    3,    3,    3,    3,    3,    3,    5,    1,    1,
    1,    1,    1,    4,    3,    3,    3,    4,    1,    1,
    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    1,    0,    0,    0,    3,
    0,   59,   60,   61,    0,   10,    4,    5,    2,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   74,   75,   71,
   72,   73,    0,   20,    0,    0,   70,    0,    0,    0,
    0,    0,   23,   21,   22,    0,    0,    0,    7,   14,
   16,   15,   18,   17,   12,   11,   13,   19,    0,    0,
    0,    0,    0,    0,   67,    0,    0,    0,   46,   55,
    0,    0,    0,    0,    0,    0,    0,   52,   54,    0,
   51,   53,   56,    0,    0,    0,    0,    0,    0,   68,
    0,    0,    0,    0,   76,   78,   77,   79,   80,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   65,   66,    0,    0,   58,    0,   28,   27,    0,
   31,    0,    0,    0,    0,    0,   40,   45,   44,    0,
    0,    0,   64,   47,   48,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    9,   42,    0,    0,    0,    0,
    0,   37,   33,    0,    0,   39,    8,    0,    0,   29,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   38,   34,   35,    0,    0,   32,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   25,   26,
};
final static short yydgoto[] = {                          5,
    6,    7,    8,   32,    9,   33,   34,   35,   36,   37,
   38,  160,  161,  106,   86,   39,   40,   41,   53,  120,
   94,   42,   43,
};
final static short yysindex[] = {                      -169,
 -169, -226, -177, -200,    0,    0, -169, -169, -169,    0,
  205,    0,    0,    0, -193,    0,    0,    0,    0, -202,
 -161,  205, -233, -197, -191, -190, -185, -160, -251, -195,
 -167, -118, -123,  205,  205,  205,  205,  205,  205,  205,
  205,  205, -153, -116, -231, -152, -233,    0,    0,    0,
    0,    0, -233,    0, -109, -105,    0,  -91, -233,  205,
  -89, -233,    0,    0,    0,  -88,  -88,  -96,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -113, -110,
 -233, -244, -260, -102,    0,  -83,  -92,  -84,    0,    0,
 -161,  -93, -141,  -63,  -64, -220,  -59,    0,    0, -106,
    0,    0,    0,  -61,  -48,  -54,  -45, -227, -227,    0,
  -70,  -66, -233,  -38,    0,    0,    0,    0,    0, -248,
  145,  -53,  -42, -103,  175,  -44,  -43,  205,  -56,  -34,
  -41,    0,    0, -233, -233,    0,  -51,    0,    0,  205,
    0,  -29,  -35,  -31,  -30,  205,    0,    0,    0,  -21,
 -177,  205,    0,    0,    0,  -12,  -19, -233,  -13,  -35,
  -15,  -11,  -10,   -9,    0,    0,   -5, -141,   -8,    1,
  -25,    0,    0,   -3,    2,    0,    0, -170,    4,    0,
  205,  -35,  -35,   -6,   -4,  205,    3,    8,   14,   25,
   31,   22,    0,    0,    0,    6,    9,    0,   12,   16,
   26,   27,    5,   40,  205,  205,   30,   42,    0,    0,
};
final static short yyrindex[] = {                       310,
  310,    0,    0,    0,    0,    0,  310,  310,  310,    0,
   44,    0,    0,    0,    0,    0,    0,    0,    0,   72,
  -57, -230,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -230, -230, -230, -230, -230, -230, -230,
 -230, -245,  -14,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0, -100,    0,    0,    0,    0,   44,
    0,    0,    0,    0,    0,    0,    0, -189,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -199, -187,
    0,    0,   47,   48,    0,    0,    0,    0,    0,    0,
   29,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 -230,    0,    0,    0, -230,    0,    0,   44,   49,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   44,
    0,    0,    0,    0,    0,   44,    0,    0,    0,    0,
    0,   44,    0,    0,    0,    0,    0,    0,    0,   51,
    0,    0,    0,    0,    0,    0,    0,    0,  115,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   46,    0,    0,    0,    0,   44,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   44,   44,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  118,    0,    0,   -2,    0,  -22,    0,    0,    0,    0,
    0,    0,  -86,  170,   23,    0,    0,  -18,  -39,  155,
  -60,  -20,   96,
};
final static int YYTABLESIZE=504;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         54,
   15,   97,   57,   81,   56,   63,   64,  107,  138,  139,
   65,   70,   71,   72,   73,   74,   75,   76,   77,   78,
  104,   24,  108,   55,   21,   83,   57,   24,   89,   83,
   23,   84,   57,   85,   90,   84,   24,   95,   57,   11,
   93,   57,   24,   93,  123,   57,   57,  124,   70,   70,
   70,  113,   12,   13,   14,   30,   31,   74,   74,   16,
   57,   45,  103,   44,   74,   46,   58,   43,   43,   75,
   75,   43,   59,  172,   43,   60,   75,   43,   61,  105,
   43,   43,   47,   43,   43,   43,  184,  185,   43,   43,
   74,    1,   57,   66,  136,  188,  189,  170,  141,   43,
   43,   75,  147,   62,   87,  150,   88,   43,   43,   43,
    2,    3,    4,   57,   57,  154,  155,  157,   10,   12,
   13,   14,   67,  164,   17,   18,   19,   48,   49,  167,
  132,  133,   50,   51,   52,   79,   80,   57,   68,   93,
   50,   51,   52,   69,  115,  116,  117,   82,  105,  118,
  126,  119,  127,  144,   45,  145,   69,   69,  187,   91,
   69,   98,   99,  192,   69,   92,   69,   96,   55,   69,
   69,  100,   69,   69,   69,  101,  111,   69,   69,  102,
  109,  110,  207,  208,  112,   69,   69,   69,   69,   69,
   69,  114,   69,   69,   69,   69,   69,   69,   69,   49,
   49,  121,  122,   49,  128,  125,   49,   49,  129,   49,
  130,  131,   49,   49,  134,   49,   49,   49,  135,  137,
   49,   49,  142,  143,  148,  149,  151,  153,   49,   49,
   49,  152,  156,   49,  158,   49,  159,  162,  163,   49,
   49,   49,   50,   50,  168,  165,   50,  169,  171,   50,
   50,  173,   50,  174,  175,   50,   50,  176,   50,   50,
   50,  177,  182,   50,   50,  180,  181,  183,  179,  186,
  205,   50,   50,   50,  194,  193,   50,  190,   50,  191,
  195,  196,   50,   50,   50,   57,   57,  197,  198,   57,
  203,  204,   57,   57,  199,   57,  209,  200,   57,   57,
  201,   57,   57,   57,  202,  206,   57,   57,  210,    6,
   24,   62,   63,   41,   57,   57,   57,   36,   24,   57,
  166,   57,  178,    0,    0,   57,   57,   57,   69,   69,
    0,    0,   69,    0,    0,    0,    0,    0,   69,    0,
    0,   69,   69,    0,   69,   69,   69,    0,    0,   69,
   69,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   69,   69,    0,    0,    0,   69,   69,   69,   69,   69,
   69,   30,   30,    0,    0,   30,    0,    0,   30,    0,
    0,   30,    0,    0,   30,   30,    0,   30,   30,   30,
    0,    0,   30,   30,    0,    0,    0,    0,    0,    0,
    0,   20,   21,   30,   30,   22,    0,    0,   23,    0,
  140,   30,   30,   30,   24,   25,    0,    0,   26,   27,
    0,    0,   28,   29,    0,    0,    0,    0,    0,    0,
    0,   20,   21,   30,   31,   22,    0,    0,   23,    0,
  146,   12,   13,   14,   24,   25,    0,    0,   26,   27,
    0,    0,   28,   29,    0,    0,    0,    0,    0,    0,
    0,   20,   21,   30,   31,   22,    0,    0,   23,    0,
    0,   12,   13,   14,   24,   25,    0,    0,   26,   27,
    0,    0,   28,   29,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   30,   31,    0,    0,    0,    0,    0,
    0,   12,   13,   14,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         22,
    3,   62,   23,   43,   23,  257,  258,  268,  257,  258,
  262,   34,   35,   36,   37,   38,   39,   40,   41,   42,
  265,  267,  283,  257,  258,  257,   47,  273,   47,  257,
  264,  263,   53,  265,   53,  263,  267,   60,   59,  266,
   59,   62,  273,   62,  265,   66,   67,  268,  294,  295,
  296,   91,  297,  298,  299,  289,  290,  257,  258,  260,
   81,  264,   81,  257,  264,  268,  264,  257,  258,  257,
  258,  261,  264,  160,  264,  266,  264,  267,  264,   82,
  270,  271,  285,  273,  274,  275,  257,  258,  278,  279,
  290,  261,  113,  289,  113,  182,  183,  158,  121,  289,
  290,  289,  125,  264,  257,  128,  259,  297,  298,  299,
  280,  281,  282,  134,  135,  134,  135,  140,    1,  297,
  298,  299,  290,  146,    7,    8,    9,  289,  290,  152,
  108,  109,  294,  295,  296,  289,  290,  158,  257,  158,
  294,  295,  296,  267,  286,  287,  288,  264,  151,  291,
  257,  293,  259,  257,  264,  259,  257,  258,  181,  265,
  261,   66,   67,  186,  265,  257,  267,  257,  257,  270,
  271,  268,  273,  274,  275,  289,  269,  278,  279,  290,
  283,  265,  205,  206,  269,  286,  287,  288,  289,  290,
  291,  285,  293,  294,  295,  296,  297,  298,  299,  257,
  258,  265,  267,  261,  266,  265,  264,  265,  257,  267,
  265,  257,  270,  271,  285,  273,  274,  275,  285,  258,
  278,  279,  276,  266,  269,  269,  283,  269,  286,  287,
  288,  266,  284,  291,  264,  293,  272,  269,  269,  297,
  298,  299,  257,  258,  257,  267,  261,  267,  262,  264,
  265,  267,  267,  265,  265,  270,  271,  267,  273,  274,
  275,  267,  266,  278,  279,  265,  292,  266,  277,  266,
  266,  286,  287,  288,  267,  273,  291,  284,  293,  284,
  267,  257,  297,  298,  299,  257,  258,  257,  267,  261,
  265,  265,  264,  265,  289,  267,  267,  289,  270,  271,
  289,  273,  274,  275,  289,  266,  278,  279,  267,    0,
  267,  265,  265,  265,  286,  287,  288,  267,  273,  291,
  151,  293,  168,   -1,   -1,  297,  298,  299,  257,  258,
   -1,   -1,  261,   -1,   -1,   -1,   -1,   -1,  267,   -1,
   -1,  270,  271,   -1,  273,  274,  275,   -1,   -1,  278,
  279,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  289,  290,   -1,   -1,   -1,  294,  295,  296,  297,  298,
  299,  257,  258,   -1,   -1,  261,   -1,   -1,  264,   -1,
   -1,  267,   -1,   -1,  270,  271,   -1,  273,  274,  275,
   -1,   -1,  278,  279,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  257,  258,  289,  290,  261,   -1,   -1,  264,   -1,
  266,  297,  298,  299,  270,  271,   -1,   -1,  274,  275,
   -1,   -1,  278,  279,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  257,  258,  289,  290,  261,   -1,   -1,  264,   -1,
  266,  297,  298,  299,  270,  271,   -1,   -1,  274,  275,
   -1,   -1,  278,  279,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  257,  258,  289,  290,  261,   -1,   -1,  264,   -1,
   -1,  297,  298,  299,  270,  271,   -1,   -1,  274,  275,
   -1,   -1,  278,  279,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  289,  290,   -1,   -1,   -1,   -1,   -1,
   -1,  297,  298,  299,
};
}
final static short YYFINAL=5;
final static short YYMAXTOKEN=299;
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
null,null,null,"IDENTIFICADOR","VALOR","VALOR_INTEIRO","INCLUSAO_ARQUIVO",
"COMENTARIOS","STRING","STRING_PARAMETRO","ABRE_PARENTESES","FECHA_PARENTESES",
"ABRE_CHAVES","FECHA_CHAVES","ABRE_COLCHETES","FECHA_COLCHETES","PARA","SE",
"OPCAO","FIM_OPCAO","FACA","CASO","ATE","SENAO","ENQUANTO","RETORNAR",
"FUNCAO_PRINCIPAL","FUNCAO","INCLUIR","VIRGULA","PONTO_VIRGULA",
"OPERADOR_ATRIBUIR","OPERADOR_COMPARACAO","OPERADOR_MENOR_IGUAL",
"OPERADOR_MAIOR_IGUAL","OPERADOR_MAIS","OPERADOR_MENOS","OPERADOR_MENOR",
"DOIS_PONTOS","OPERADOR_MAIOR","OPERADOR_DIVISAO","OPERADOR_MULTIPLICACAO",
"OPERADOR_RESTO_DIVISAO","INTEIRO","REAL","CARACTER",
};
final static String yyrule[] = {
"$accept : inicio",
"inicio : programa",
"programa : inclusao programa",
"programa : COMENTARIOS programa",
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
"comandos : comando_faca comandos",
"comandos : comando_caso comandos",
"comandos : comando_enquanto comandos",
"comandos : chamada_metodo comandos",
"comandos : COMENTARIOS comandos",
"comandos : RETORNAR VALOR",
"comandos : RETORNAR STRING",
"comandos : RETORNAR IDENTIFICADOR",
"comandos :",
"comando_para : PARA ABRE_PARENTESES IDENTIFICADOR OPERADOR_ATRIBUIR VALOR PONTO_VIRGULA IDENTIFICADOR operador_condicao IDENTIFICADOR PONTO_VIRGULA IDENTIFICADOR OPERADOR_MAIS OPERADOR_MAIS FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES",
"comando_para : PARA ABRE_PARENTESES IDENTIFICADOR OPERADOR_ATRIBUIR VALOR PONTO_VIRGULA IDENTIFICADOR operador_condicao VALOR PONTO_VIRGULA IDENTIFICADOR OPERADOR_MAIS OPERADOR_MAIS FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES",
"condicao : atribuicao_valor operador_condicao VALOR",
"condicao : atribuicao_valor operador_condicao IDENTIFICADOR",
"comando_faca : FACA ABRE_CHAVES comandos FECHA_CHAVES ATE ABRE_PARENTESES condicao FECHA_PARENTESES",
"comando_se : SE ABRE_PARENTESES condicao FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES",
"comando_se : SE ABRE_PARENTESES condicao FECHA_PARENTESES comandos",
"comando_se : SE ABRE_PARENTESES condicao FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES SENAO ABRE_CHAVES comandos FECHA_CHAVES",
"comando_caso : CASO ABRE_PARENTESES IDENTIFICADOR FECHA_PARENTESES ABRE_CHAVES comando_caso_opcoes FECHA_CHAVES",
"comando_caso : CASO ABRE_PARENTESES IDENTIFICADOR ABRE_COLCHETES IDENTIFICADOR FECHA_COLCHETES FECHA_PARENTESES ABRE_CHAVES comando_caso_opcoes FECHA_CHAVES",
"comando_caso : CASO ABRE_PARENTESES IDENTIFICADOR ABRE_COLCHETES VALOR_INTEIRO FECHA_COLCHETES FECHA_PARENTESES ABRE_CHAVES comando_caso_opcoes FECHA_CHAVES",
"comando_caso_opcoes : comando_caso_opcao",
"comando_caso_opcoes : comando_caso_opcao comando_caso_opcoes",
"comando_caso_opcao : OPCAO STRING DOIS_PONTOS comandos FIM_OPCAO",
"comando_enquanto : ENQUANTO ABRE_PARENTESES condicao FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES",
"comando_enquanto : ENQUANTO ABRE_PARENTESES condicao FECHA_PARENTESES comandos",
"parametros : tipo_dado IDENTIFICADOR",
"parametros : tipo_dado IDENTIFICADOR VIRGULA parametros",
"declaracao : tipo_dado IDENTIFICADOR",
"declaracao : tipo_dado IDENTIFICADOR ABRE_COLCHETES VALOR_INTEIRO FECHA_COLCHETES",
"declaracao : tipo_dado IDENTIFICADOR ABRE_COLCHETES IDENTIFICADOR FECHA_COLCHETES",
"atribuicao : IDENTIFICADOR OPERADOR_ATRIBUIR atribuicao_valor",
"atribuicao : IDENTIFICADOR ABRE_COLCHETES IDENTIFICADOR FECHA_COLCHETES OPERADOR_ATRIBUIR atribuicao_valor",
"atribuicao : IDENTIFICADOR ABRE_COLCHETES VALOR_INTEIRO FECHA_COLCHETES OPERADOR_ATRIBUIR atribuicao_valor",
"atribuicao_valor : VALOR",
"atribuicao_valor : chamada_metodo_identificador",
"atribuicao_valor : chamada_metodo_identificador OPERADOR_MAIS OPERADOR_MAIS",
"atribuicao_valor : OPERADOR_MAIS OPERADOR_MAIS chamada_metodo_identificador",
"atribuicao_valor : chamada_metodo_identificador OPERADOR_MENOS OPERADOR_MENOS",
"atribuicao_valor : OPERADOR_MENOS OPERADOR_MENOS chamada_metodo_identificador",
"atribuicao_valor : VALOR operador atribuicao_valor",
"atribuicao_valor : chamada_metodo_identificador operador atribuicao_valor",
"atribuicao_valor : ABRE_PARENTESES atribuicao_valor FECHA_PARENTESES",
"atribuicao_valor : ABRE_PARENTESES atribuicao_valor FECHA_PARENTESES operador atribuicao_valor",
"tipo_dado : INTEIRO",
"tipo_dado : REAL",
"tipo_dado : CARACTER",
"chamada_metodo_parametros : IDENTIFICADOR",
"chamada_metodo_parametros : STRING_PARAMETRO",
"chamada_metodo_parametros : IDENTIFICADOR ABRE_COLCHETES IDENTIFICADOR FECHA_COLCHETES",
"chamada_metodo_parametros : IDENTIFICADOR VIRGULA chamada_metodo_parametros",
"chamada_metodo_parametros : STRING_PARAMETRO VIRGULA chamada_metodo_parametros",
"chamada_metodo : IDENTIFICADOR ABRE_PARENTESES FECHA_PARENTESES",
"chamada_metodo : IDENTIFICADOR ABRE_PARENTESES chamada_metodo_parametros FECHA_PARENTESES",
"chamada_metodo_identificador : IDENTIFICADOR",
"chamada_metodo_identificador : chamada_metodo",
"operador : OPERADOR_DIVISAO",
"operador : OPERADOR_MULTIPLICACAO",
"operador : OPERADOR_RESTO_DIVISAO",
"operador : OPERADOR_MAIS",
"operador : OPERADOR_MENOS",
"operador_condicao : OPERADOR_COMPARACAO",
"operador_condicao : OPERADOR_MAIOR_IGUAL",
"operador_condicao : OPERADOR_MENOR_IGUAL",
"operador_condicao : OPERADOR_MENOR",
"operador_condicao : OPERADOR_MAIOR",
};

//#line 181 "inicioCT.y"

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
//#line 495 "Parser.java"
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
//#line 76 "inicioCT.y"
{ System.out.println(val_peek(0).sval); }
break;
case 2:
//#line 78 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 3:
//#line 79 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 4:
//#line 80 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 5:
//#line 81 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 6:
//#line 82 "inicioCT.y"
{ yyval.sval = ""; }
break;
case 7:
//#line 84 "inicioCT.y"
{ yyval.sval = "int main() {\n " + val_peek(1).sval + "}\n"; }
break;
case 8:
//#line 85 "inicioCT.y"
{ yyval.sval = val_peek(7).sval + " " + val_peek(6).sval + "(" + val_peek(4).sval + ") {\n " + val_peek(1).sval + "}\n"; }
break;
case 9:
//#line 86 "inicioCT.y"
{ yyval.sval = val_peek(6).sval + " " + val_peek(5).sval + "() {\n " + val_peek(1).sval + "}\n"; }
break;
case 10:
//#line 88 "inicioCT.y"
{ yyval.sval = "#include " + val_peek(0).sval; }
break;
case 11:
//#line 90 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 12:
//#line 91 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 13:
//#line 92 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n"+ val_peek(0).sval; }
break;
case 14:
//#line 93 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 15:
//#line 94 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 16:
//#line 95 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 17:
//#line 96 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 18:
//#line 97 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 19:
//#line 98 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + ";\n" + val_peek(0).sval; }
break;
case 20:
//#line 99 "inicioCT.y"
{ yyval.sval = val_peek(1).sval  +"\n" + val_peek(0).sval; }
break;
case 21:
//#line 100 "inicioCT.y"
{ yyval.sval = "return "+ val_peek(0).sval + ";\n"; }
break;
case 22:
//#line 101 "inicioCT.y"
{ yyval.sval = "return "+ val_peek(0).sval + ";\n"; }
break;
case 23:
//#line 102 "inicioCT.y"
{ yyval.sval = "return "+ val_peek(0).sval + ";\n"; }
break;
case 24:
//#line 103 "inicioCT.y"
{ yyval.sval = ""; }
break;
case 25:
//#line 106 "inicioCT.y"
{ yyval.sval = "for(" + val_peek(14).sval + " = " + val_peek(12).sval + "; " + val_peek(10).sval +" <= " + val_peek(8).sval + "; " + val_peek(6).sval + "++" +"){\n" + val_peek(1).sval + "}\n"; }
break;
case 26:
//#line 107 "inicioCT.y"
{ yyval.sval = "for(" + val_peek(14).sval + " = " + val_peek(12).sval + "; " + val_peek(10).sval +" <= " + val_peek(8).sval + "; " + val_peek(6).sval + "++" +"){\n" + val_peek(1).sval + "}\n"; }
break;
case 27:
//#line 109 "inicioCT.y"
{  yyval.sval = val_peek(2).sval + " " + val_peek(1).sval + " " + val_peek(0).sval; }
break;
case 28:
//#line 110 "inicioCT.y"
{  yyval.sval =  val_peek(2).sval + " " + val_peek(1).sval + " " + val_peek(0).sval; }
break;
case 29:
//#line 112 "inicioCT.y"
{  yyval.sval = "do{\n" + val_peek(5).sval + "\n}while(" + val_peek(1).sval + ");\n"; }
break;
case 30:
//#line 114 "inicioCT.y"
{  yyval.sval = "if (" + val_peek(4).sval + "){\n" + val_peek(1).sval+ "}\n"; }
break;
case 31:
//#line 115 "inicioCT.y"
{  yyval.sval = "if (" + val_peek(2).sval + ")\n" + val_peek(0).sval+ "\n"; }
break;
case 32:
//#line 116 "inicioCT.y"
{  yyval.sval = "if (" + val_peek(8).sval + "){\n" + val_peek(5).sval+ "\n} else {\n" + val_peek(1).sval + "\n}"; }
break;
case 33:
//#line 118 "inicioCT.y"
{  yyval.sval = "switch (" + val_peek(4).sval + "){\n" + val_peek(1).sval+ "}\n"; }
break;
case 34:
//#line 119 "inicioCT.y"
{  yyval.sval = "switch (" + val_peek(7).sval + "[" + val_peek(5).sval + "]){\n" + val_peek(1).sval+ "}\n"; }
break;
case 35:
//#line 120 "inicioCT.y"
{  yyval.sval = "switch (" + val_peek(7).sval + "[" + val_peek(5).sval + "]){\n" + val_peek(1).sval+ "}\n"; }
break;
case 36:
//#line 122 "inicioCT.y"
{  yyval.sval = val_peek(0).sval; }
break;
case 37:
//#line 123 "inicioCT.y"
{  yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 38:
//#line 125 "inicioCT.y"
{  yyval.sval = "case" + val_peek(3).sval + ":\n" + val_peek(1).sval + "break;\n"; }
break;
case 39:
//#line 127 "inicioCT.y"
{  yyval.sval = "while (" + val_peek(4).sval + "){\n" + val_peek(1).sval+ "}\n"; }
break;
case 40:
//#line 128 "inicioCT.y"
{  yyval.sval = "while (" + val_peek(2).sval + ")\n" + val_peek(0).sval+ "\n"; }
break;
case 41:
//#line 130 "inicioCT.y"
{  yyval.sval = val_peek(1).sval + " " + val_peek(0).sval ; }
break;
case 42:
//#line 131 "inicioCT.y"
{  yyval.sval = val_peek(3).sval + " " + val_peek(2).sval + ", " + val_peek(0).sval; }
break;
case 43:
//#line 133 "inicioCT.y"
{  yyval.sval = val_peek(1).sval + " " + val_peek(0).sval + ";\n"; }
break;
case 44:
//#line 134 "inicioCT.y"
{  yyval.sval = val_peek(4).sval + " " + val_peek(3).sval + "[" + val_peek(1).sval + "];\n"; }
break;
case 45:
//#line 135 "inicioCT.y"
{  yyval.sval = val_peek(4).sval + " " + val_peek(3).sval + "[" + val_peek(1).sval + "];\n"; }
break;
case 46:
//#line 137 "inicioCT.y"
{  yyval.sval = val_peek(2).sval + " = " + val_peek(0).sval + ";\n"; }
break;
case 47:
//#line 138 "inicioCT.y"
{  yyval.sval = val_peek(5).sval + "["+ val_peek(3).sval + "] = " + val_peek(0).sval + ";\n"; }
break;
case 48:
//#line 139 "inicioCT.y"
{  yyval.sval = val_peek(5).sval + "["+ val_peek(3).sval + "] = " + val_peek(0).sval + ";\n"; }
break;
case 49:
//#line 141 "inicioCT.y"
{  yyval.sval = val_peek(0).sval; }
break;
case 50:
//#line 142 "inicioCT.y"
{  yyval.sval = val_peek(0).sval; }
break;
case 51:
//#line 143 "inicioCT.y"
{  yyval.sval = val_peek(2).sval + "++"; }
break;
case 52:
//#line 144 "inicioCT.y"
{  yyval.sval = "++" + val_peek(0).sval; }
break;
case 53:
//#line 145 "inicioCT.y"
{  yyval.sval = val_peek(2).sval + "--"; }
break;
case 54:
//#line 146 "inicioCT.y"
{  yyval.sval = "--" + val_peek(0).sval; }
break;
case 55:
//#line 147 "inicioCT.y"
{  yyval.sval = val_peek(2).sval + " " + val_peek(1).sval + " " + val_peek(0).sval; }
break;
case 56:
//#line 148 "inicioCT.y"
{  yyval.sval = val_peek(2).sval + " " + val_peek(1).sval + " " + val_peek(0).sval; }
break;
case 57:
//#line 149 "inicioCT.y"
{  yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 58:
//#line 150 "inicioCT.y"
{  yyval.sval = "(" + val_peek(3).sval + ") " + val_peek(1).sval + val_peek(0).sval; }
break;
case 59:
//#line 152 "inicioCT.y"
{  yyval.sval = "int"; }
break;
case 60:
//#line 153 "inicioCT.y"
{  yyval.sval = "float"; }
break;
case 61:
//#line 154 "inicioCT.y"
{  yyval.sval = "char"; }
break;
case 62:
//#line 156 "inicioCT.y"
{  yyval.sval = val_peek(0).sval ; }
break;
case 63:
//#line 157 "inicioCT.y"
{  yyval.sval = val_peek(0).sval ; }
break;
case 64:
//#line 158 "inicioCT.y"
{  yyval.sval = val_peek(3).sval + "[" + val_peek(1).sval + "]"; }
break;
case 65:
//#line 159 "inicioCT.y"
{  yyval.sval = val_peek(2).sval + ", " + val_peek(0).sval; }
break;
case 66:
//#line 160 "inicioCT.y"
{  yyval.sval = val_peek(2).sval + ", " + val_peek(0).sval; }
break;
case 67:
//#line 162 "inicioCT.y"
{  yyval.sval = val_peek(2).sval + "()"; }
break;
case 68:
//#line 163 "inicioCT.y"
{  yyval.sval = val_peek(3).sval + "(" + val_peek(1).sval +")"; }
break;
case 69:
//#line 165 "inicioCT.y"
{  yyval.sval = val_peek(0).sval; }
break;
case 70:
//#line 166 "inicioCT.y"
{  yyval.sval = val_peek(0).sval; }
break;
case 71:
//#line 168 "inicioCT.y"
{  yyval.sval = "/"; }
break;
case 72:
//#line 169 "inicioCT.y"
{  yyval.sval = "*"; }
break;
case 73:
//#line 170 "inicioCT.y"
{  yyval.sval = "%"; }
break;
case 74:
//#line 171 "inicioCT.y"
{  yyval.sval = "+"; }
break;
case 75:
//#line 172 "inicioCT.y"
{  yyval.sval = "-"; }
break;
case 76:
//#line 174 "inicioCT.y"
{  yyval.sval = "=="; }
break;
case 77:
//#line 175 "inicioCT.y"
{  yyval.sval = ">="; }
break;
case 78:
//#line 176 "inicioCT.y"
{  yyval.sval = "<="; }
break;
case 79:
//#line 177 "inicioCT.y"
{  yyval.sval = "<"; }
break;
case 80:
//#line 178 "inicioCT.y"
{  yyval.sval = ">"; }
break;
//#line 964 "Parser.java"
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
