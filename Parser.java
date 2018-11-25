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
public final static short VALOR_INTEIRO=258;
public final static short INCLUSAO_ARQUIVO=259;
public final static short COMENTARIOS=260;
public final static short STRING=261;
public final static short STRING_PARAMETRO=262;
public final static short ABRE_PARENTESES=263;
public final static short FECHA_PARENTESES=264;
public final static short ABRE_CHAVES=265;
public final static short FECHA_CHAVES=266;
public final static short ABRE_COLCHETES=267;
public final static short FECHA_COLCHETES=268;
public final static short PARA=269;
public final static short SE=270;
public final static short OPCAO=271;
public final static short FIM_OPCAO=272;
public final static short FACA=273;
public final static short CASO=274;
public final static short ATE=275;
public final static short SENAO=276;
public final static short ENQUANTO=277;
public final static short RETORNAR=278;
public final static short FUNCAO_PRINCIPAL=279;
public final static short FUNCAO=280;
public final static short INCLUIR=281;
public final static short IMPRIMA=282;
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
    0,    1,    1,    1,    1,    1,    1,    1,    2,    3,
    3,    5,    6,    6,    6,    6,    6,    6,    6,    6,
    6,    6,    6,    6,    6,    6,    6,    7,    7,   21,
   21,    8,    9,    9,    9,   11,   11,   11,   13,   13,
   12,   10,   10,   14,   14,   17,   17,   17,   17,   16,
   16,   16,   16,   18,   18,   18,   18,   18,   18,   18,
   18,   18,   18,    4,    4,    4,   15,   15,   15,   15,
   15,   22,   22,   23,   23,   19,   19,   19,   19,   19,
   20,   20,   20,   20,   20,   24,
};
final static short yylen[] = {                            2,
    1,    2,    2,    2,    2,    2,    2,    0,    4,    9,
    8,    2,    2,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    2,    2,    2,    2,    0,   17,   17,    3,
    3,    8,    7,    5,   11,    7,   10,   10,    1,    2,
    5,    7,    5,    2,    4,    2,    2,    5,    5,    3,
    3,    6,    6,    1,    1,    3,    3,    3,    3,    3,
    3,    3,    5,    1,    1,    1,    1,    1,    4,    3,
    3,    3,    4,    1,    1,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    1,    4,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,   64,   65,   66,    0,    1,
    0,    0,    0,    0,    0,    0,    0,    0,    3,    0,
    0,   12,    6,    7,    0,   47,    2,    4,    5,    0,
    0,    0,    0,    0,    0,    0,   50,   75,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   79,   80,   76,   77,   78,    0,
    0,    0,    0,    0,    0,    0,   23,    0,    0,    0,
    0,    0,   26,   24,   25,    0,    9,   16,   18,   17,
   20,   19,   14,   13,   15,   21,   22,    0,    0,    0,
    0,    0,    0,    0,   72,    0,   60,    0,   57,   59,
   56,   58,   61,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   52,   53,    0,    0,    0,
   73,    0,    0,   81,   83,   82,   84,   85,    0,    0,
    0,    0,    0,    0,   86,    0,    0,    0,    0,   70,
   71,   63,    0,   31,   30,    0,   34,    0,    0,    0,
    0,    0,   43,    0,    0,    0,   69,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   11,   45,    0,    0,
    0,    0,    0,   40,   36,    0,    0,   42,   10,    0,
    0,   32,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   41,   37,   38,    0,    0,   35,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   28,   29,
};
final static short yydgoto[] = {                          9,
   10,   11,   12,   13,   14,   49,   50,   51,   52,   53,
   54,  172,  173,  123,  106,   55,   56,   57,   70,  139,
  116,   58,   39,   59,
};
final static short yysindex[] = {                      -224,
 -259, -224, -237, -133, -221,    0,    0,    0,    0,    0,
 -224, -224, -203, -224, -224, -224, -189, -238,    0,  346,
 -191,    0,    0,    0, -220,    0,    0,    0,    0, -190,
 -184, -177, -161, -238, -188, -186,    0,    0, -145, -187,
  346, -155, -150, -129, -139, -123, -164, -110, -106,  346,
  346,  346,  346,  346,  346,  346,  346,  346,  346, -101,
 -141, -114, -105, -162,    0,    0,    0,    0,    0, -238,
  -73,  -59,  -59,  -89,  -91, -238,    0,  -54, -238,  346,
  -53, -238,    0,    0,    0, -125,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -257,  -63,  -51,
 -238, -238, -233,  -69,    0,  -44,    0, -161,    0,    0,
    0,    0,    0,  -66, -132,  -42,  -43, -243,  -38,  -34,
  -31,  -26,  -29, -114, -105,    0,    0,  -21, -125, -125,
    0, -238,  -20,    0,    0,    0,    0,    0, -115, -151,
  -35,  -24,  -90,  303,    0,  346,  -40,  -23,  -22,    0,
    0,    0,  -37,    0,    0,  346,    0,  -15,  -32,  -19,
  -18,  346,    0,  -11, -133,  346,    0,   -6,  -10, -238,
   -4,  -32,   -3,    2,    4,    3,    0,    0,    6, -132,
  -14,   12,  -28,    0,    0,   19,   20,    0,    0,  -83,
   21,    0,  346,  -32,  -32,   -7,   22,  346,   31,   27,
   39,   52,   54,   46,    0,    0,    0,   26,   30,    0,
   38,   40,   56,   64,   68,   69,  346,  346,   70,   72,
    0,    0,
};
final static short yyrindex[] = {                       339,
    0,  339,    0,    0,    0,    0,    0,    0,    0,    0,
  339,  339,    0,  339,  339,  339,    0,    0,    0,   74,
    0,    0,    0,    0,  173,    0,    0,    0,    0,    0,
    0,    1,   44,    0,    0,    0,    0,    0,   87,  -88,
 -195,    0,    0,    0,    0,    0,    0,    0,    0, -195,
 -195, -195, -195, -195, -195, -195, -195, -213, -195,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -228, -226,    0,    0,    0,    0,   74,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   82,   84,    0,    0,    0,  130,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  216,  260,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -195,
    0,    0,    0, -195,    0,   74,   85,    0,    0,    0,
    0,    0,    0,    0,    0,   74,    0,    0,    0,    0,
    0,   74,    0,    0,    0,   74,    0,    0,    0,    0,
    0,   86,    0,    0,    0,    0,    0,    0,    0,    0,
  -45,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   83,    0,    0,    0,    0,   74,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   74,   74,    0,    0,
    0,    0,
};
final static short yygindex[] = {                         0,
  181,    0,    0,   -2,    0,  -41,    0,    0,    0,    0,
    0,    0, -149,  189,  -81,  637,  596,    9,  -36,  178,
  -78,  -12,  115,    0,
};
final static int YYTABLESIZE=653;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         77,
   74,   21,   76,  119,  120,   38,  121,   17,   88,   89,
   90,   91,   92,   93,   94,   95,   96,   97,   32,   33,
  142,   38,  184,  143,   34,   18,   37,   20,   79,   79,
   80,   80,    1,  128,   79,    2,   80,   22,  117,    6,
    7,    8,   71,   54,  200,  201,   61,  150,  151,  129,
   35,   36,   27,   25,    3,    4,    5,   38,   27,   38,
   38,   79,   80,   38,   18,   60,   38,   30,   31,   38,
   27,  132,    6,    7,    8,   64,   27,   62,  107,   17,
   75,   75,   75,   63,  113,   64,   55,  115,   38,   38,
  115,  182,   83,   84,  103,  122,   85,   18,  157,  104,
   72,  105,  163,   73,  164,   40,   33,   78,   41,  126,
  127,   34,   79,  156,  169,   99,  100,   42,   43,   38,
  176,   44,   45,   81,  179,   46,   47,   65,   66,   62,
   48,  103,   67,   68,   69,   80,  104,   35,   36,   82,
  152,  154,  155,   74,   75,    6,    7,    8,   67,   68,
   69,  199,   86,  134,  135,  136,  204,   38,  137,   87,
  138,   98,  122,    6,    7,    8,  160,  161,   74,   74,
  101,   74,   46,  196,  197,  219,  220,   74,  115,  102,
   74,   74,   19,   74,   74,   74,  109,  110,   74,   74,
  108,   23,   24,   74,   27,   28,   29,   32,  112,  111,
   74,   74,  114,  118,  124,   74,   74,   74,   74,   74,
   74,   33,   33,  130,   33,   49,  125,   33,  133,  131,
   33,  140,  141,   33,   33,  144,   33,   33,   33,  145,
  147,   33,   33,  146,  148,  149,   33,  153,  171,  158,
  159,  166,  165,   33,   33,  167,  168,  170,  174,  175,
  180,   33,   33,   33,  177,  181,  183,   74,   74,   48,
   74,  191,  185,  193,   74,  186,   74,  187,  188,   74,
   74,  189,   74,   74,   74,  192,  202,   74,   74,   74,
   74,   74,   74,  194,  195,  198,   74,   74,   74,   74,
   74,   74,  206,   74,   74,   74,   74,   74,   74,   74,
   54,   54,  205,   54,  207,  203,   54,   54,  208,   54,
  209,  210,   54,   54,  211,   54,   54,   54,  212,  215,
   54,   54,   54,   54,   54,   54,  213,  216,  214,   54,
   54,   54,  217,  218,   54,  221,   54,  222,    8,   27,
   54,   54,   54,   55,   55,   67,   55,   68,   44,   55,
   55,   39,   55,  178,   27,   55,   55,  190,   55,   55,
   55,    0,    0,   55,   55,   55,   55,   55,   55,    0,
    0,    0,   55,   55,   55,    0,    0,   55,    0,   55,
    0,    0,    0,   55,   55,   55,   62,   62,    0,   62,
    0,    0,   62,   62,    0,   62,    0,    0,   62,   62,
    0,   62,   62,   62,    0,    0,   62,   62,   62,   62,
   62,   62,    0,    0,    0,   62,   62,   62,    0,    0,
   62,    0,   62,    0,    0,    0,   62,   62,   62,   46,
   46,    0,   46,    0,    0,   46,    0,    0,   46,    0,
    0,   46,   46,    0,   46,   46,   46,    0,    0,   46,
   46,   46,   46,   46,   46,    0,    0,    0,    0,    0,
    0,   46,   46,    0,    0,    0,    0,    0,    0,   46,
   46,   46,   49,   49,    0,   49,    0,    0,   49,    0,
    0,   49,    0,    0,   49,   49,    0,   49,   49,   49,
    0,    0,   49,   49,   49,   49,   49,   49,    0,    0,
    0,    0,    0,    0,   49,   49,    0,    0,    0,    0,
    0,    0,   49,   49,   49,    0,   48,   48,    0,   48,
    0,    0,   48,    0,    0,   48,    0,    0,   48,   48,
    0,   48,   48,   48,    0,    0,   48,   48,   48,   48,
   48,   48,    0,    0,    0,    0,    0,    0,   48,   48,
    0,    0,    0,    0,    0,    0,   48,   48,   48,   40,
   33,    0,   41,    0,    0,   34,    0,  162,    0,    0,
    0,   42,   43,    0,    0,   44,   45,    0,    0,   46,
   47,    0,    0,    0,   48,    0,    0,    0,    0,    0,
    0,   35,   36,    0,    0,   16,    0,   16,    0,    6,
    7,    8,   40,   33,    0,   41,   16,   16,   34,   16,
   16,   16,    0,    0,   42,   43,    0,    0,   44,   45,
    0,    0,   46,   47,    0,    0,    0,   48,    0,    0,
    0,    0,    0,    0,   35,   36,   15,    0,   15,    0,
    0,    0,    6,    7,    8,    0,    0,   15,   15,   26,
   15,   15,   15,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         41,
    0,    4,   39,   82,   86,   18,  264,  267,   50,   51,
   52,   53,   54,   55,   56,   57,   58,   59,  257,  258,
  264,   34,  172,  267,  263,  285,   18,  265,  257,  258,
  257,  258,  257,  267,  263,  260,  263,  259,   80,  297,
  298,  299,   34,    0,  194,  195,  267,  129,  130,  283,
  289,  290,  266,  257,  279,  280,  281,   70,  272,   72,
   73,  290,  289,   76,  285,  257,   79,  257,  258,   82,
  266,  108,  297,  298,  299,  263,  272,  268,   70,  267,
  294,  295,  296,  268,   76,  263,    0,   79,  101,  102,
   82,  170,  257,  258,  257,   98,  261,  285,  140,  262,
  289,  264,  144,  290,  146,  257,  258,  263,  260,  101,
  102,  263,  263,  265,  156,  257,  258,  269,  270,  132,
  162,  273,  274,  263,  166,  277,  278,  289,  290,    0,
  282,  257,  294,  295,  296,  265,  262,  289,  290,  263,
  132,  257,  258,  289,  290,  297,  298,  299,  294,  295,
  296,  193,  263,  286,  287,  288,  198,  170,  291,  266,
  293,  263,  165,  297,  298,  299,  257,  258,  257,  258,
  285,  260,    0,  257,  258,  217,  218,  266,  170,  285,
  269,  270,    2,  272,  273,  274,   72,   73,  277,  278,
  264,   11,   12,  282,   14,   15,   16,  257,  290,  289,
  289,  290,  257,  257,  268,  294,  295,  296,  297,  298,
  299,  257,  258,  283,  260,    0,  268,  263,  285,  264,
  266,  264,  266,  269,  270,  264,  272,  273,  274,  264,
  257,  277,  278,  265,  264,  257,  282,  258,  271,  275,
  265,  265,  283,  289,  290,  268,  284,  263,  268,  268,
  257,  297,  298,  299,  266,  266,  261,  257,  258,    0,
  260,  276,  266,  292,  264,  264,  266,  264,  266,  269,
  270,  266,  272,  273,  274,  264,  284,  277,  278,  279,
  280,  281,  282,  265,  265,  265,  286,  287,  288,  289,
  290,  291,  266,  293,  294,  295,  296,  297,  298,  299,
  257,  258,  272,  260,  266,  284,  263,  264,  257,  266,
  257,  266,  269,  270,  289,  272,  273,  274,  289,  264,
  277,  278,  279,  280,  281,  282,  289,  264,  289,  286,
  287,  288,  265,  265,  291,  266,  293,  266,    0,  266,
  297,  298,  299,  257,  258,  264,  260,  264,  264,  263,
  264,  266,  266,  165,  272,  269,  270,  180,  272,  273,
  274,   -1,   -1,  277,  278,  279,  280,  281,  282,   -1,
   -1,   -1,  286,  287,  288,   -1,   -1,  291,   -1,  293,
   -1,   -1,   -1,  297,  298,  299,  257,  258,   -1,  260,
   -1,   -1,  263,  264,   -1,  266,   -1,   -1,  269,  270,
   -1,  272,  273,  274,   -1,   -1,  277,  278,  279,  280,
  281,  282,   -1,   -1,   -1,  286,  287,  288,   -1,   -1,
  291,   -1,  293,   -1,   -1,   -1,  297,  298,  299,  257,
  258,   -1,  260,   -1,   -1,  263,   -1,   -1,  266,   -1,
   -1,  269,  270,   -1,  272,  273,  274,   -1,   -1,  277,
  278,  279,  280,  281,  282,   -1,   -1,   -1,   -1,   -1,
   -1,  289,  290,   -1,   -1,   -1,   -1,   -1,   -1,  297,
  298,  299,  257,  258,   -1,  260,   -1,   -1,  263,   -1,
   -1,  266,   -1,   -1,  269,  270,   -1,  272,  273,  274,
   -1,   -1,  277,  278,  279,  280,  281,  282,   -1,   -1,
   -1,   -1,   -1,   -1,  289,  290,   -1,   -1,   -1,   -1,
   -1,   -1,  297,  298,  299,   -1,  257,  258,   -1,  260,
   -1,   -1,  263,   -1,   -1,  266,   -1,   -1,  269,  270,
   -1,  272,  273,  274,   -1,   -1,  277,  278,  279,  280,
  281,  282,   -1,   -1,   -1,   -1,   -1,   -1,  289,  290,
   -1,   -1,   -1,   -1,   -1,   -1,  297,  298,  299,  257,
  258,   -1,  260,   -1,   -1,  263,   -1,  265,   -1,   -1,
   -1,  269,  270,   -1,   -1,  273,  274,   -1,   -1,  277,
  278,   -1,   -1,   -1,  282,   -1,   -1,   -1,   -1,   -1,
   -1,  289,  290,   -1,   -1,    0,   -1,    2,   -1,  297,
  298,  299,  257,  258,   -1,  260,   11,   12,  263,   14,
   15,   16,   -1,   -1,  269,  270,   -1,   -1,  273,  274,
   -1,   -1,  277,  278,   -1,   -1,   -1,  282,   -1,   -1,
   -1,   -1,   -1,   -1,  289,  290,    0,   -1,    2,   -1,
   -1,   -1,  297,  298,  299,   -1,   -1,   11,   12,   13,
   14,   15,   16,
};
}
final static short YYFINAL=9;
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
null,null,null,"IDENTIFICADOR","VALOR_INTEIRO","INCLUSAO_ARQUIVO","COMENTARIOS",
"STRING","STRING_PARAMETRO","ABRE_PARENTESES","FECHA_PARENTESES","ABRE_CHAVES",
"FECHA_CHAVES","ABRE_COLCHETES","FECHA_COLCHETES","PARA","SE","OPCAO",
"FIM_OPCAO","FACA","CASO","ATE","SENAO","ENQUANTO","RETORNAR",
"FUNCAO_PRINCIPAL","FUNCAO","INCLUIR","IMPRIMA","VIRGULA","PONTO_VIRGULA",
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
"programa : atribuicao programa",
"programa : declaracao programa",
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
"comandos : comando_imprima comandos",
"comandos : COMENTARIOS comandos",
"comandos : RETORNAR VALOR_INTEIRO",
"comandos : RETORNAR STRING",
"comandos : RETORNAR IDENTIFICADOR",
"comandos :",
"comando_para : PARA ABRE_PARENTESES IDENTIFICADOR OPERADOR_ATRIBUIR VALOR_INTEIRO PONTO_VIRGULA IDENTIFICADOR operador_condicao IDENTIFICADOR PONTO_VIRGULA IDENTIFICADOR OPERADOR_MAIS OPERADOR_MAIS FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES",
"comando_para : PARA ABRE_PARENTESES IDENTIFICADOR OPERADOR_ATRIBUIR VALOR_INTEIRO PONTO_VIRGULA IDENTIFICADOR operador_condicao VALOR_INTEIRO PONTO_VIRGULA IDENTIFICADOR OPERADOR_MAIS OPERADOR_MAIS FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES",
"condicao : atribuicao_valor operador_condicao VALOR_INTEIRO",
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
"declaracao : tipo_dado atribuicao",
"declaracao : tipo_dado IDENTIFICADOR ABRE_COLCHETES VALOR_INTEIRO FECHA_COLCHETES",
"declaracao : tipo_dado IDENTIFICADOR ABRE_COLCHETES IDENTIFICADOR FECHA_COLCHETES",
"atribuicao : IDENTIFICADOR OPERADOR_ATRIBUIR atribuicao_valor",
"atribuicao : IDENTIFICADOR OPERADOR_ATRIBUIR atribuicao_valor",
"atribuicao : IDENTIFICADOR ABRE_COLCHETES IDENTIFICADOR FECHA_COLCHETES OPERADOR_ATRIBUIR atribuicao_valor",
"atribuicao : IDENTIFICADOR ABRE_COLCHETES VALOR_INTEIRO FECHA_COLCHETES OPERADOR_ATRIBUIR atribuicao_valor",
"atribuicao_valor : VALOR_INTEIRO",
"atribuicao_valor : chamada_metodo_identificador",
"atribuicao_valor : chamada_metodo_identificador OPERADOR_MAIS OPERADOR_MAIS",
"atribuicao_valor : OPERADOR_MAIS OPERADOR_MAIS chamada_metodo_identificador",
"atribuicao_valor : chamada_metodo_identificador OPERADOR_MENOS OPERADOR_MENOS",
"atribuicao_valor : OPERADOR_MENOS OPERADOR_MENOS chamada_metodo_identificador",
"atribuicao_valor : VALOR_INTEIRO operador atribuicao_valor",
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
"comando_imprima : IMPRIMA ABRE_PARENTESES chamada_metodo_parametros FECHA_PARENTESES",
};

//#line 189 "inicioCT.y"

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
//#line 539 "Parser.java"
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
//#line 77 "inicioCT.y"
{ System.out.println(val_peek(0).sval); }
break;
case 2:
//#line 79 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 3:
//#line 80 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 4:
//#line 81 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 5:
//#line 82 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 6:
//#line 83 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 7:
//#line 84 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 8:
//#line 85 "inicioCT.y"
{ yyval.sval = ""; }
break;
case 9:
//#line 87 "inicioCT.y"
{ yyval.sval = "int main() {\n " + val_peek(1).sval + "}\n"; }
break;
case 10:
//#line 88 "inicioCT.y"
{ yyval.sval = val_peek(7).sval + " " + val_peek(6).sval + "(" + val_peek(4).sval + ") {\n " + val_peek(1).sval + "}\n"; }
break;
case 11:
//#line 89 "inicioCT.y"
{ yyval.sval = val_peek(6).sval + " " + val_peek(5).sval + "() {\n " + val_peek(1).sval + "}\n"; }
break;
case 12:
//#line 91 "inicioCT.y"
{ yyval.sval = "#include " + val_peek(0).sval; }
break;
case 13:
//#line 93 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 14:
//#line 94 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 15:
//#line 95 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + ";\n"+ val_peek(0).sval; }
break;
case 16:
//#line 96 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 17:
//#line 97 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 18:
//#line 98 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 19:
//#line 99 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 20:
//#line 100 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 21:
//#line 101 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + ";\n" + val_peek(0).sval; }
break;
case 22:
//#line 102 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + ";\n" + val_peek(0).sval; }
break;
case 23:
//#line 103 "inicioCT.y"
{ yyval.sval = val_peek(1).sval  +"\n" + val_peek(0).sval; }
break;
case 24:
//#line 104 "inicioCT.y"
{ yyval.sval = "return "+ val_peek(0).sval + ";\n"; }
break;
case 25:
//#line 105 "inicioCT.y"
{ yyval.sval = "return "+ val_peek(0).sval + ";\n"; }
break;
case 26:
//#line 106 "inicioCT.y"
{ yyval.sval = "return "+ val_peek(0).sval + ";\n"; }
break;
case 27:
//#line 107 "inicioCT.y"
{ yyval.sval = ""; }
break;
case 28:
//#line 110 "inicioCT.y"
{ yyval.sval = "for(" + val_peek(14).sval + " = " + val_peek(12).sval + "; " + val_peek(10).sval +" <= " + val_peek(8).sval + "; " + val_peek(6).sval + "++" +"){\n" + val_peek(1).sval + "}\n"; }
break;
case 29:
//#line 111 "inicioCT.y"
{ yyval.sval = "for(" + val_peek(14).sval + " = " + val_peek(12).sval + "; " + val_peek(10).sval +" <= " + val_peek(8).sval + "; " + val_peek(6).sval + "++" +"){\n" + val_peek(1).sval + "}\n"; }
break;
case 30:
//#line 113 "inicioCT.y"
{  yyval.sval = val_peek(2).sval + " " + val_peek(1).sval + " " + val_peek(0).sval; }
break;
case 31:
//#line 114 "inicioCT.y"
{  yyval.sval =  val_peek(2).sval + " " + val_peek(1).sval + " " + val_peek(0).sval; }
break;
case 32:
//#line 116 "inicioCT.y"
{  yyval.sval = "do{\n" + val_peek(5).sval + "\n}while(" + val_peek(1).sval + ");\n"; }
break;
case 33:
//#line 118 "inicioCT.y"
{  yyval.sval = "if (" + val_peek(4).sval + "){\n" + val_peek(1).sval+ "}\n"; }
break;
case 34:
//#line 119 "inicioCT.y"
{  yyval.sval = "if (" + val_peek(2).sval + ")\n" + val_peek(0).sval+ "\n"; }
break;
case 35:
//#line 120 "inicioCT.y"
{  yyval.sval = "if (" + val_peek(8).sval + "){\n" + val_peek(5).sval+ "\n} else {\n" + val_peek(1).sval + "\n}"; }
break;
case 36:
//#line 122 "inicioCT.y"
{  yyval.sval = "switch (" + val_peek(4).sval + "){\n" + val_peek(1).sval+ "}\n"; }
break;
case 37:
//#line 123 "inicioCT.y"
{  yyval.sval = "switch (" + val_peek(7).sval + "[" + val_peek(5).sval + "]){\n" + val_peek(1).sval+ "}\n"; }
break;
case 38:
//#line 124 "inicioCT.y"
{  yyval.sval = "switch (" + val_peek(7).sval + "[" + val_peek(5).sval + "]){\n" + val_peek(1).sval+ "}\n"; }
break;
case 39:
//#line 126 "inicioCT.y"
{  yyval.sval = val_peek(0).sval; }
break;
case 40:
//#line 127 "inicioCT.y"
{  yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 41:
//#line 129 "inicioCT.y"
{  yyval.sval = "case" + val_peek(3).sval + ":\n" + val_peek(1).sval + "break;\n"; }
break;
case 42:
//#line 131 "inicioCT.y"
{  yyval.sval = "while (" + val_peek(4).sval + "){\n" + val_peek(1).sval+ "}\n"; }
break;
case 43:
//#line 132 "inicioCT.y"
{  yyval.sval = "while (" + val_peek(2).sval + ")\n" + val_peek(0).sval+ "\n"; }
break;
case 44:
//#line 134 "inicioCT.y"
{  yyval.sval = val_peek(1).sval + " " + val_peek(0).sval ; }
break;
case 45:
//#line 135 "inicioCT.y"
{  yyval.sval = val_peek(3).sval + " " + val_peek(2).sval + ", " + val_peek(0).sval; }
break;
case 46:
//#line 137 "inicioCT.y"
{  yyval.sval = val_peek(1).sval + " " + val_peek(0).sval + ";\n"; }
break;
case 47:
//#line 138 "inicioCT.y"
{  yyval.sval = val_peek(1).sval + " " + val_peek(0).sval ; }
break;
case 48:
//#line 139 "inicioCT.y"
{  yyval.sval = val_peek(4).sval + " " + val_peek(3).sval + "[" + val_peek(1).sval + "];\n"; }
break;
case 49:
//#line 140 "inicioCT.y"
{  yyval.sval = val_peek(4).sval + " " + val_peek(3).sval + "[" + val_peek(1).sval + "];\n"; }
break;
case 50:
//#line 142 "inicioCT.y"
{  yyval.sval = val_peek(2).sval + " = " + val_peek(0).sval + ";\n"; }
break;
case 51:
//#line 143 "inicioCT.y"
{  yyval.sval = val_peek(2).sval + " = " + val_peek(0).sval + ";\n"; }
break;
case 52:
//#line 144 "inicioCT.y"
{  yyval.sval = val_peek(5).sval + "["+ val_peek(3).sval + "] = " + val_peek(0).sval + ";\n"; }
break;
case 53:
//#line 145 "inicioCT.y"
{  yyval.sval = val_peek(5).sval + "["+ val_peek(3).sval + "] = " + val_peek(0).sval + ";\n"; }
break;
case 54:
//#line 147 "inicioCT.y"
{  yyval.sval = val_peek(0).sval; }
break;
case 55:
//#line 148 "inicioCT.y"
{  yyval.sval = val_peek(0).sval; }
break;
case 56:
//#line 149 "inicioCT.y"
{  yyval.sval = val_peek(2).sval + "++"; }
break;
case 57:
//#line 150 "inicioCT.y"
{  yyval.sval = "++" + val_peek(0).sval; }
break;
case 58:
//#line 151 "inicioCT.y"
{  yyval.sval = val_peek(2).sval + "--"; }
break;
case 59:
//#line 152 "inicioCT.y"
{  yyval.sval = "--" + val_peek(0).sval; }
break;
case 60:
//#line 153 "inicioCT.y"
{  yyval.sval = val_peek(2).sval + " " + val_peek(1).sval + " " + val_peek(0).sval; }
break;
case 61:
//#line 154 "inicioCT.y"
{  yyval.sval = val_peek(2).sval + " " + val_peek(1).sval + " " + val_peek(0).sval; }
break;
case 62:
//#line 155 "inicioCT.y"
{  yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 63:
//#line 156 "inicioCT.y"
{  yyval.sval = "(" + val_peek(3).sval + ") " + val_peek(1).sval + val_peek(0).sval; }
break;
case 64:
//#line 158 "inicioCT.y"
{  yyval.sval = "int"; }
break;
case 65:
//#line 159 "inicioCT.y"
{  yyval.sval = "float"; }
break;
case 66:
//#line 160 "inicioCT.y"
{  yyval.sval = "char"; }
break;
case 67:
//#line 162 "inicioCT.y"
{  yyval.sval = val_peek(0).sval ; }
break;
case 68:
//#line 163 "inicioCT.y"
{  yyval.sval = val_peek(0).sval ; }
break;
case 69:
//#line 164 "inicioCT.y"
{  yyval.sval = val_peek(3).sval + "[" + val_peek(1).sval + "]"; }
break;
case 70:
//#line 165 "inicioCT.y"
{  yyval.sval = val_peek(2).sval + ", " + val_peek(0).sval; }
break;
case 71:
//#line 166 "inicioCT.y"
{  yyval.sval = val_peek(2).sval + ", " + val_peek(0).sval; }
break;
case 72:
//#line 168 "inicioCT.y"
{  yyval.sval = val_peek(2).sval + "()"; }
break;
case 73:
//#line 169 "inicioCT.y"
{  yyval.sval = val_peek(3).sval + "(" + val_peek(1).sval +")"; }
break;
case 74:
//#line 171 "inicioCT.y"
{  yyval.sval = val_peek(0).sval; }
break;
case 75:
//#line 172 "inicioCT.y"
{  yyval.sval = val_peek(0).sval; }
break;
case 76:
//#line 174 "inicioCT.y"
{  yyval.sval = "/"; }
break;
case 77:
//#line 175 "inicioCT.y"
{  yyval.sval = "*"; }
break;
case 78:
//#line 176 "inicioCT.y"
{  yyval.sval = "%"; }
break;
case 79:
//#line 177 "inicioCT.y"
{  yyval.sval = "+"; }
break;
case 80:
//#line 178 "inicioCT.y"
{  yyval.sval = "-"; }
break;
case 81:
//#line 180 "inicioCT.y"
{  yyval.sval = "=="; }
break;
case 82:
//#line 181 "inicioCT.y"
{  yyval.sval = ">="; }
break;
case 83:
//#line 182 "inicioCT.y"
{  yyval.sval = "<="; }
break;
case 84:
//#line 183 "inicioCT.y"
{  yyval.sval = "<"; }
break;
case 85:
//#line 184 "inicioCT.y"
{  yyval.sval = ">"; }
break;
case 86:
//#line 186 "inicioCT.y"
{  yyval.sval = "printf(" + val_peek(1).sval +  ")"; }
break;
//#line 1032 "Parser.java"
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
