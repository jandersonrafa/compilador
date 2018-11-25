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
public final static short IMPRIMA=283;
public final static short VIRGULA=284;
public final static short PONTO_VIRGULA=285;
public final static short OPERADOR_ATRIBUIR=286;
public final static short OPERADOR_COMPARACAO=287;
public final static short OPERADOR_MENOR_IGUAL=288;
public final static short OPERADOR_MAIOR_IGUAL=289;
public final static short OPERADOR_MAIS=290;
public final static short OPERADOR_MENOS=291;
public final static short OPERADOR_MENOR=292;
public final static short DOIS_PONTOS=293;
public final static short OPERADOR_MAIOR=294;
public final static short OPERADOR_DIVISAO=295;
public final static short OPERADOR_MULTIPLICACAO=296;
public final static short OPERADOR_RESTO_DIVISAO=297;
public final static short INTEIRO=298;
public final static short REAL=299;
public final static short CARACTER=300;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    1,    1,    1,    1,    1,    2,    3,
    3,    5,    6,    6,    6,    6,    6,    6,    6,    6,
    6,    6,    6,    6,    6,    6,    6,    7,    7,   21,
   21,    8,    9,    9,    9,   11,   11,   11,   13,   13,
   12,   10,   10,   14,   14,   17,   17,   17,   16,   16,
   16,   16,   18,   18,   18,   18,   18,   18,   18,   18,
   18,   18,    4,    4,    4,   15,   15,   15,   15,   15,
   22,   22,   23,   23,   19,   19,   19,   19,   19,   20,
   20,   20,   20,   20,   24,
};
final static short yylen[] = {                            2,
    1,    2,    2,    2,    2,    2,    2,    0,    4,    9,
    8,    2,    2,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    2,    2,    2,    2,    0,   17,   17,    3,
    3,    8,    7,    5,   11,    7,   10,   10,    1,    2,
    5,    7,    5,    2,    4,    2,    5,    5,    3,    3,
    6,    6,    1,    1,    3,    3,    3,    3,    3,    3,
    3,    5,    1,    1,    1,    1,    1,    4,    3,    3,
    3,    4,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    4,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,   63,   64,   65,    0,    1,
    0,    0,    0,    0,    0,    0,    0,    0,    3,    0,
    0,   12,    6,    7,    0,    2,    4,    5,    0,    0,
    0,    0,    0,    0,    0,   49,   74,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   78,   79,   75,   76,   77,    0,    0,
    0,    0,    0,    0,    0,   23,    0,    0,    0,    0,
    0,   26,   24,   25,    0,    9,   16,   18,   17,   20,
   19,   14,   13,   15,   21,   22,    0,    0,    0,    0,
    0,    0,    0,   71,    0,   59,    0,   56,   58,   55,
   57,   60,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   48,   47,   51,   52,    0,    0,    0,   72,
    0,    0,   80,   82,   81,   83,   84,    0,    0,    0,
    0,    0,    0,   85,    0,    0,    0,    0,   69,   70,
   62,    0,   31,   30,    0,   34,    0,    0,    0,    0,
    0,   43,    0,    0,    0,   68,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   11,   45,    0,    0,    0,
    0,    0,   40,   36,    0,    0,   42,   10,    0,    0,
   32,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   41,   37,   38,    0,    0,   35,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   28,
   29,
};
final static short yydgoto[] = {                          9,
   10,   11,   12,   13,   14,   48,   49,   50,   51,   52,
   53,  171,  172,  122,  105,   54,   55,   56,   69,  138,
  115,   57,   38,   58,
};
final static short yysindex[] = {                      -229,
 -262, -229, -263, -137, -223,    0,    0,    0,    0,    0,
 -229, -229, -232, -229, -229, -229, -249, -235,    0, -133,
 -227,    0,    0,    0, -228,    0,    0,    0, -222, -215,
 -181,  -97, -235, -213, -204,    0,    0,  -53, -201, -133,
 -135, -125, -126, -117, -116, -136, -112, -111, -133, -133,
 -133, -133, -133, -133, -133, -133, -133, -133, -109, -191,
 -122, -118, -114,    0,    0,    0,    0,    0, -235,  -96,
 -104, -104, -115, -110, -235,    0,  -84, -235, -133,  -81,
 -235,    0,    0,    0, -198,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0, -256,  -79,  -74, -235,
 -235, -234,  -99,    0,  -76,    0,  -97,    0,    0,    0,
    0,    0,  -89, -192,  -64,  -65, -230,  -60,  -59,  -50,
  -40,  -58,    0,    0,    0,    0,  -37, -198, -198,    0,
 -235,  -39,    0,    0,    0,    0,    0, -159,  208,  -54,
  -41, -127,  239,    0, -133,  -61,  -33,  -35,    0,    0,
    0,  -56,    0,    0, -133,    0,  -25,  -46,  -28,  -24,
 -133,    0,  -21, -137, -133,    0,   -7,  -15, -235,   -9,
  -46,  -13,    2,    4,    6,    0,    0,   10, -192,    8,
    5,   -6,    0,    0,   12,   20,    0,    0, -146,   28,
    0, -133,  -46,  -46,   19,   22, -133,   32,   41,   44,
   56,   57,   50,    0,    0,    0,   31,   39,    0,   40,
   46,   66,   70,   72,   74, -133, -133,   55,   75,    0,
    0,
};
final static short yyrindex[] = {                       341,
    0,  341,    0,    0,    0,    0,    0,    0,    0,    0,
  341,  341,    0,  341,  341,  341,    0,    0,    0,   81,
    0,    0,    0,    0,  177,    0,    0,    0,    0,    0,
    1,   45,    0,    0,    0,    0,    0,   89,  -87, -194,
    0,    0,    0,    0,    0,    0,    0,    0, -194, -194,
 -194, -194, -194, -194, -194, -194, -187, -194,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -231, -200,    0,    0,    0,    0,   81,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   84,   86,    0,    0,    0,  133,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -194,    0,
    0,    0, -194,    0,   81,   87,    0,    0,    0,    0,
    0,    0,    0,    0,   81,    0,    0,    0,    0,    0,
   81,    0,    0,    0,   81,    0,    0,    0,    0,    0,
   88,    0,    0,    0,    0,    0,    0,    0,    0,  -43,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   85,    0,    0,    0,    0,   81,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   81,   81,    0,    0,    0,
    0,
};
final static short yygindex[] = {                         0,
  104,    0,    0,   -4,    0,  -38,    0,    0,    0,    0,
    0,    0, -132,  193,  -80,  249,  531,   13,  -31,  182,
  -77,    3,   64,    0,
};
final static int YYTABLESIZE=547;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         21,
   73,   76,   20,  118,  119,   17,   75,   29,  120,   30,
   87,   88,   89,   90,   91,   92,   93,   94,   95,   96,
   37,   31,   32,   18,   25,   78,   78,    1,   33,   59,
   36,    2,   78,  127,  141,   37,   22,  142,  183,   60,
  116,    6,    7,    8,   53,   70,   61,  149,  150,  128,
    3,    4,    5,   62,   34,   35,   79,   79,  102,   78,
  199,  200,   63,   79,  103,   98,   17,   99,    6,    7,
    8,   37,   27,   37,   37,  131,   71,   37,   27,   27,
   37,  106,   63,   37,   18,   27,   72,  112,   54,   79,
  114,  181,  121,  114,  133,  134,  135,  153,  154,  136,
  156,  137,   37,   37,  162,   19,  163,   74,   74,   74,
  195,  196,  125,  126,   23,   24,  168,   26,   27,   28,
   82,   83,  175,   39,   32,   84,  178,   40,   77,  159,
   33,  160,   61,   37,  108,  109,   41,   42,   78,   79,
   43,   44,  102,  151,   45,   46,   80,   81,  103,   47,
  104,   85,   31,  198,   97,   86,   34,   35,  203,  121,
    6,    7,    8,  100,    6,    7,    8,  101,  107,   73,
   73,   37,  113,   73,  110,  117,   46,  218,  219,   73,
  111,  114,   73,   73,  129,   73,   73,   73,  130,  123,
   73,   73,   64,   65,  124,   73,  132,   66,   67,   68,
  139,  140,   73,   73,  143,  144,  147,   73,   73,   73,
   73,   73,   73,   33,   33,  145,  146,   33,  152,  148,
   33,  157,  164,   33,  158,  170,   33,   33,  167,   33,
   33,   33,  165,  166,   33,   33,   73,   74,  169,   33,
  173,   66,   67,   68,  174,  176,   33,   33,   15,  179,
   15,  180,  182,  184,   33,   33,   33,   73,   73,   15,
   15,   73,   15,   15,   15,   73,  185,   73,  186,  191,
   73,   73,  187,   73,   73,   73,  188,  193,   73,   73,
   73,   73,   73,   73,  190,  194,  192,   73,   73,   73,
   73,   73,   73,  197,   73,   73,   73,   73,   73,   73,
   73,   53,   53,  201,  204,   53,  202,  205,   53,   53,
  206,   53,  207,  208,   53,   53,  209,   53,   53,   53,
  210,  220,   53,   53,   53,   53,   53,   53,  211,  212,
  214,   53,   53,   53,  215,  213,   53,  216,   53,  217,
    8,  221,   53,   53,   53,   54,   54,   27,   66,   54,
   67,   44,   54,   54,   39,   54,  177,   27,   54,   54,
  189,   54,   54,   54,    0,    0,   54,   54,   54,   54,
   54,   54,    0,    0,    0,   54,   54,   54,    0,    0,
   54,    0,   54,    0,    0,    0,   54,   54,   54,   61,
   61,    0,    0,   61,    0,    0,   61,   61,    0,   61,
    0,    0,   61,   61,    0,   61,   61,   61,    0,    0,
   61,   61,   61,   61,   61,   61,    0,    0,    0,   61,
   61,   61,    0,    0,   61,    0,   61,    0,    0,    0,
   61,   61,   61,   46,   46,    0,    0,   46,    0,    0,
   46,    0,    0,   46,    0,    0,   46,   46,    0,   46,
   46,   46,    0,    0,   46,   46,   46,   46,   46,   46,
    0,    0,    0,    0,   39,   32,   46,   46,   40,    0,
    0,   33,    0,  155,   46,   46,   46,   41,   42,    0,
    0,   43,   44,    0,    0,   45,   46,    0,    0,    0,
   47,    0,    0,    0,    0,   39,   32,   34,   35,   40,
    0,    0,   33,    0,  161,    6,    7,    8,   41,   42,
    0,    0,   43,   44,    0,    0,   45,   46,    0,    0,
    0,   47,    0,    0,    0,    0,    0,    0,   34,   35,
   16,    0,   16,    0,    0,    0,    6,    7,    8,    0,
    0,   16,   16,    0,   16,   16,   16,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                          4,
    0,   40,  266,   81,   85,  268,   38,  257,  265,  259,
   49,   50,   51,   52,   53,   54,   55,   56,   57,   58,
   18,  257,  258,  286,  257,  257,  258,  257,  264,  257,
   18,  261,  264,  268,  265,   33,  260,  268,  171,  268,
   79,  298,  299,  300,    0,   33,  269,  128,  129,  284,
  280,  281,  282,  269,  290,  291,  257,  258,  257,  291,
  193,  194,  264,  264,  263,  257,  268,  259,  298,  299,
  300,   69,  267,   71,   72,  107,  290,   75,  273,  267,
   78,   69,  264,   81,  286,  273,  291,   75,    0,  290,
   78,  169,   97,   81,  287,  288,  289,  257,  258,  292,
  139,  294,  100,  101,  143,    2,  145,  295,  296,  297,
  257,  258,  100,  101,   11,   12,  155,   14,   15,   16,
  257,  258,  161,  257,  258,  262,  165,  261,  264,  257,
  264,  259,    0,  131,   71,   72,  270,  271,  264,  266,
  274,  275,  257,  131,  278,  279,  264,  264,  263,  283,
  265,  264,  257,  192,  264,  267,  290,  291,  197,  164,
  298,  299,  300,  286,  298,  299,  300,  286,  265,  257,
  258,  169,  257,  261,  290,  257,    0,  216,  217,  267,
  291,  169,  270,  271,  284,  273,  274,  275,  265,  269,
  278,  279,  290,  291,  269,  283,  286,  295,  296,  297,
  265,  267,  290,  291,  265,  265,  265,  295,  296,  297,
  298,  299,  300,  257,  258,  266,  257,  261,  258,  257,
  264,  276,  284,  267,  266,  272,  270,  271,  285,  273,
  274,  275,  266,  269,  278,  279,  290,  291,  264,  283,
  269,  295,  296,  297,  269,  267,  290,  291,    0,  257,
    2,  267,  262,  267,  298,  299,  300,  257,  258,   11,
   12,  261,   14,   15,   16,  265,  265,  267,  265,  265,
  270,  271,  267,  273,  274,  275,  267,  266,  278,  279,
  280,  281,  282,  283,  277,  266,  293,  287,  288,  289,
  290,  291,  292,  266,  294,  295,  296,  297,  298,  299,
  300,  257,  258,  285,  273,  261,  285,  267,  264,  265,
  267,  267,  257,  257,  270,  271,  267,  273,  274,  275,
  290,  267,  278,  279,  280,  281,  282,  283,  290,  290,
  265,  287,  288,  289,  265,  290,  292,  266,  294,  266,
    0,  267,  298,  299,  300,  257,  258,  267,  265,  261,
  265,  265,  264,  265,  267,  267,  164,  273,  270,  271,
  179,  273,  274,  275,   -1,   -1,  278,  279,  280,  281,
  282,  283,   -1,   -1,   -1,  287,  288,  289,   -1,   -1,
  292,   -1,  294,   -1,   -1,   -1,  298,  299,  300,  257,
  258,   -1,   -1,  261,   -1,   -1,  264,  265,   -1,  267,
   -1,   -1,  270,  271,   -1,  273,  274,  275,   -1,   -1,
  278,  279,  280,  281,  282,  283,   -1,   -1,   -1,  287,
  288,  289,   -1,   -1,  292,   -1,  294,   -1,   -1,   -1,
  298,  299,  300,  257,  258,   -1,   -1,  261,   -1,   -1,
  264,   -1,   -1,  267,   -1,   -1,  270,  271,   -1,  273,
  274,  275,   -1,   -1,  278,  279,  280,  281,  282,  283,
   -1,   -1,   -1,   -1,  257,  258,  290,  291,  261,   -1,
   -1,  264,   -1,  266,  298,  299,  300,  270,  271,   -1,
   -1,  274,  275,   -1,   -1,  278,  279,   -1,   -1,   -1,
  283,   -1,   -1,   -1,   -1,  257,  258,  290,  291,  261,
   -1,   -1,  264,   -1,  266,  298,  299,  300,  270,  271,
   -1,   -1,  274,  275,   -1,   -1,  278,  279,   -1,   -1,
   -1,  283,   -1,   -1,   -1,   -1,   -1,   -1,  290,  291,
    0,   -1,    2,   -1,   -1,   -1,  298,  299,  300,   -1,
   -1,   11,   12,   -1,   14,   15,   16,
};
}
final static short YYFINAL=9;
final static short YYMAXTOKEN=300;
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
//#line 517 "Parser.java"
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
//#line 78 "inicioCT.y"
{ System.out.println(val_peek(0).sval); }
break;
case 2:
//#line 80 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 3:
//#line 81 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 4:
//#line 82 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 5:
//#line 83 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 6:
//#line 84 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 7:
//#line 85 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 8:
//#line 86 "inicioCT.y"
{ yyval.sval = ""; }
break;
case 9:
//#line 88 "inicioCT.y"
{ yyval.sval = "int main() {\n " + val_peek(1).sval + "}\n"; }
break;
case 10:
//#line 89 "inicioCT.y"
{ yyval.sval = val_peek(7).sval + " " + val_peek(6).sval + "(" + val_peek(4).sval + ") {\n " + val_peek(1).sval + "}\n"; }
break;
case 11:
//#line 90 "inicioCT.y"
{ yyval.sval = val_peek(6).sval + " " + val_peek(5).sval + "() {\n " + val_peek(1).sval + "}\n"; }
break;
case 12:
//#line 92 "inicioCT.y"
{ yyval.sval = "#include " + val_peek(0).sval; }
break;
case 13:
//#line 94 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 14:
//#line 95 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 15:
//#line 96 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + ";\n"+ val_peek(0).sval; }
break;
case 16:
//#line 97 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 17:
//#line 98 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 18:
//#line 99 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 19:
//#line 100 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 20:
//#line 101 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 21:
//#line 102 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + ";\n" + val_peek(0).sval; }
break;
case 22:
//#line 103 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + ";\n" + val_peek(0).sval; }
break;
case 23:
//#line 104 "inicioCT.y"
{ yyval.sval = val_peek(1).sval  +"\n" + val_peek(0).sval; }
break;
case 24:
//#line 105 "inicioCT.y"
{ yyval.sval = "return "+ val_peek(0).sval + ";\n"; }
break;
case 25:
//#line 106 "inicioCT.y"
{ yyval.sval = "return "+ val_peek(0).sval + ";\n"; }
break;
case 26:
//#line 107 "inicioCT.y"
{ yyval.sval = "return "+ val_peek(0).sval + ";\n"; }
break;
case 27:
//#line 108 "inicioCT.y"
{ yyval.sval = ""; }
break;
case 28:
//#line 111 "inicioCT.y"
{ yyval.sval = "for(" + val_peek(14).sval + " = " + val_peek(12).sval + "; " + val_peek(10).sval +" <= " + val_peek(8).sval + "; " + val_peek(6).sval + "++" +"){\n" + val_peek(1).sval + "}\n"; }
break;
case 29:
//#line 112 "inicioCT.y"
{ yyval.sval = "for(" + val_peek(14).sval + " = " + val_peek(12).sval + "; " + val_peek(10).sval +" <= " + val_peek(8).sval + "; " + val_peek(6).sval + "++" +"){\n" + val_peek(1).sval + "}\n"; }
break;
case 30:
//#line 114 "inicioCT.y"
{  yyval.sval = val_peek(2).sval + " " + val_peek(1).sval + " " + val_peek(0).sval; }
break;
case 31:
//#line 115 "inicioCT.y"
{  yyval.sval =  val_peek(2).sval + " " + val_peek(1).sval + " " + val_peek(0).sval; }
break;
case 32:
//#line 117 "inicioCT.y"
{  yyval.sval = "do{\n" + val_peek(5).sval + "\n}while(" + val_peek(1).sval + ");\n"; }
break;
case 33:
//#line 119 "inicioCT.y"
{  yyval.sval = "if (" + val_peek(4).sval + "){\n" + val_peek(1).sval+ "}\n"; }
break;
case 34:
//#line 120 "inicioCT.y"
{  yyval.sval = "if (" + val_peek(2).sval + ")\n" + val_peek(0).sval+ "\n"; }
break;
case 35:
//#line 121 "inicioCT.y"
{  yyval.sval = "if (" + val_peek(8).sval + "){\n" + val_peek(5).sval+ "\n} else {\n" + val_peek(1).sval + "\n}"; }
break;
case 36:
//#line 123 "inicioCT.y"
{  yyval.sval = "switch (" + val_peek(4).sval + "){\n" + val_peek(1).sval+ "}\n"; }
break;
case 37:
//#line 124 "inicioCT.y"
{  yyval.sval = "switch (" + val_peek(7).sval + "[" + val_peek(5).sval + "]){\n" + val_peek(1).sval+ "}\n"; }
break;
case 38:
//#line 125 "inicioCT.y"
{  yyval.sval = "switch (" + val_peek(7).sval + "[" + val_peek(5).sval + "]){\n" + val_peek(1).sval+ "}\n"; }
break;
case 39:
//#line 127 "inicioCT.y"
{  yyval.sval = val_peek(0).sval; }
break;
case 40:
//#line 128 "inicioCT.y"
{  yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 41:
//#line 130 "inicioCT.y"
{  yyval.sval = "case" + val_peek(3).sval + ":\n" + val_peek(1).sval + "break;\n"; }
break;
case 42:
//#line 132 "inicioCT.y"
{  yyval.sval = "while (" + val_peek(4).sval + "){\n" + val_peek(1).sval+ "}\n"; }
break;
case 43:
//#line 133 "inicioCT.y"
{  yyval.sval = "while (" + val_peek(2).sval + ")\n" + val_peek(0).sval+ "\n"; }
break;
case 44:
//#line 135 "inicioCT.y"
{  yyval.sval = val_peek(1).sval + " " + val_peek(0).sval ; }
break;
case 45:
//#line 136 "inicioCT.y"
{  yyval.sval = val_peek(3).sval + " " + val_peek(2).sval + ", " + val_peek(0).sval; }
break;
case 46:
//#line 138 "inicioCT.y"
{  yyval.sval = val_peek(1).sval + " " + val_peek(0).sval + ";\n"; }
break;
case 47:
//#line 139 "inicioCT.y"
{  yyval.sval = val_peek(4).sval + " " + val_peek(3).sval + "[" + val_peek(1).sval + "];\n"; }
break;
case 48:
//#line 140 "inicioCT.y"
{  yyval.sval = val_peek(4).sval + " " + val_peek(3).sval + "[" + val_peek(1).sval + "];\n"; }
break;
case 49:
//#line 142 "inicioCT.y"
{  yyval.sval = val_peek(2).sval + " = " + val_peek(0).sval + ";\n"; }
break;
case 50:
//#line 143 "inicioCT.y"
{  yyval.sval = val_peek(2).sval + " = " + val_peek(0).sval + ";\n"; }
break;
case 51:
//#line 144 "inicioCT.y"
{  yyval.sval = val_peek(5).sval + "["+ val_peek(3).sval + "] = " + val_peek(0).sval + ";\n"; }
break;
case 52:
//#line 145 "inicioCT.y"
{  yyval.sval = val_peek(5).sval + "["+ val_peek(3).sval + "] = " + val_peek(0).sval + ";\n"; }
break;
case 53:
//#line 147 "inicioCT.y"
{  yyval.sval = val_peek(0).sval; }
break;
case 54:
//#line 148 "inicioCT.y"
{  yyval.sval = val_peek(0).sval; }
break;
case 55:
//#line 149 "inicioCT.y"
{  yyval.sval = val_peek(2).sval + "++"; }
break;
case 56:
//#line 150 "inicioCT.y"
{  yyval.sval = "++" + val_peek(0).sval; }
break;
case 57:
//#line 151 "inicioCT.y"
{  yyval.sval = val_peek(2).sval + "--"; }
break;
case 58:
//#line 152 "inicioCT.y"
{  yyval.sval = "--" + val_peek(0).sval; }
break;
case 59:
//#line 153 "inicioCT.y"
{  yyval.sval = val_peek(2).sval + " " + val_peek(1).sval + " " + val_peek(0).sval; }
break;
case 60:
//#line 154 "inicioCT.y"
{  yyval.sval = val_peek(2).sval + " " + val_peek(1).sval + " " + val_peek(0).sval; }
break;
case 61:
//#line 155 "inicioCT.y"
{  yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 62:
//#line 156 "inicioCT.y"
{  yyval.sval = "(" + val_peek(3).sval + ") " + val_peek(1).sval + val_peek(0).sval; }
break;
case 63:
//#line 158 "inicioCT.y"
{  yyval.sval = "int"; }
break;
case 64:
//#line 159 "inicioCT.y"
{  yyval.sval = "float"; }
break;
case 65:
//#line 160 "inicioCT.y"
{  yyval.sval = "char"; }
break;
case 66:
//#line 162 "inicioCT.y"
{  yyval.sval = val_peek(0).sval ; }
break;
case 67:
//#line 163 "inicioCT.y"
{  yyval.sval = val_peek(0).sval ; }
break;
case 68:
//#line 164 "inicioCT.y"
{  yyval.sval = val_peek(3).sval + "[" + val_peek(1).sval + "]"; }
break;
case 69:
//#line 165 "inicioCT.y"
{  yyval.sval = val_peek(2).sval + ", " + val_peek(0).sval; }
break;
case 70:
//#line 166 "inicioCT.y"
{  yyval.sval = val_peek(2).sval + ", " + val_peek(0).sval; }
break;
case 71:
//#line 168 "inicioCT.y"
{  yyval.sval = val_peek(2).sval + "()"; }
break;
case 72:
//#line 169 "inicioCT.y"
{  yyval.sval = val_peek(3).sval + "(" + val_peek(1).sval +")"; }
break;
case 73:
//#line 171 "inicioCT.y"
{  yyval.sval = val_peek(0).sval; }
break;
case 74:
//#line 172 "inicioCT.y"
{  yyval.sval = val_peek(0).sval; }
break;
case 75:
//#line 174 "inicioCT.y"
{  yyval.sval = "/"; }
break;
case 76:
//#line 175 "inicioCT.y"
{  yyval.sval = "*"; }
break;
case 77:
//#line 176 "inicioCT.y"
{  yyval.sval = "%"; }
break;
case 78:
//#line 177 "inicioCT.y"
{  yyval.sval = "+"; }
break;
case 79:
//#line 178 "inicioCT.y"
{  yyval.sval = "-"; }
break;
case 80:
//#line 180 "inicioCT.y"
{  yyval.sval = "=="; }
break;
case 81:
//#line 181 "inicioCT.y"
{  yyval.sval = ">="; }
break;
case 82:
//#line 182 "inicioCT.y"
{  yyval.sval = "<="; }
break;
case 83:
//#line 183 "inicioCT.y"
{  yyval.sval = "<"; }
break;
case 84:
//#line 184 "inicioCT.y"
{  yyval.sval = ">"; }
break;
case 85:
//#line 186 "inicioCT.y"
{  yyval.sval = "printf(" + val_peek(1).sval +  ")"; }
break;
//#line 1006 "Parser.java"
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
