package compiller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Simbolos {
    
    //vamos criar uma lista (arrayList) para cada grupo e adicinar os caracteres a ele 
    
    private final List letter = new ArrayList();
    private final List digit = new ArrayList();
    private final List specialSymbol = new ArrayList();
    private final List relational_operator = new ArrayList();
    private final List adding_operator = new ArrayList();
    private final List multiplying_operator = new ArrayList();
    
     //construtor da classe
     public Simbolos (){
         
        //vamos adicionar os simbos que pertecem as letras na lista 'letter'
        //primeiro as minusculas e depois as maiusculas
        letter.add("a"); letter.add("b"); letter.add("c"); letter.add("d");
        letter.add("e"); letter.add("f"); letter.add("g"); letter.add("h"); 
        letter.add("i"); letter.add("j"); letter.add("k"); letter.add("l"); 
        letter.add("m"); letter.add("n"); letter.add("o"); letter.add("p"); 
        letter.add("q"); letter.add("r"); letter.add("s"); letter.add("t"); 
        letter.add("u"); letter.add("v"); letter.add("w"); letter.add("x"); 
        letter.add("y"); letter.add("z"); 
        
        letter.add("A"); letter.add("B"); letter.add("C"); letter.add("D");
        letter.add("E"); letter.add("F"); letter.add("G"); letter.add("H"); 
        letter.add("I"); letter.add("J"); letter.add("K"); letter.add("L"); 
        letter.add("M"); letter.add("N"); letter.add("O"); letter.add("P"); 
        letter.add("Q"); letter.add("R"); letter.add("S"); letter.add("T"); 
        letter.add("U"); letter.add("V"); letter.add("W"); letter.add("X"); 
        letter.add("Y"); letter.add("Z"); 
        
        //vamos adicionar os digitos que pertecem na lista 'digit'
        digit.add("0"); digit.add("1"); digit.add("2"); digit.add("3"); digit.add("4");
        digit.add("5"); digit.add("6"); digit.add("7"); digit.add("8"); digit.add("9");
        
        //vamos adicionar os simbolos especiais que pertecem na lista 'specialSymbol'
        specialSymbol.add(":="); 
        specialSymbol.add("."); specialSymbol.add(","); 
        specialSymbol.add(";"); specialSymbol.add(":"); 
        specialSymbol.add(".."); specialSymbol.add("and");
        specialSymbol.add("not"); specialSymbol.add("if");
        specialSymbol.add("then"); specialSymbol.add("then");
        specialSymbol.add("else"); specialSymbol.add("of");
        specialSymbol.add("while"); specialSymbol.add("do");
        specialSymbol.add("begin"); specialSymbol.add("end");
        specialSymbol.add("read"); specialSymbol.add("write");
        specialSymbol.add("var"); specialSymbol.add("array");
        specialSymbol.add("function"); specialSymbol.add("procedure");
        specialSymbol.add("true"); specialSymbol.add("false");
        specialSymbol.add("char"); specialSymbol.add("integer");
        specialSymbol.add("boolean"); specialSymbol.add("(");
        specialSymbol.add(")"); specialSymbol.add("[");
        specialSymbol.add("]");
        
        relational_operator.add("=");  relational_operator.add("<>");
        relational_operator.add("<");  relational_operator.add("<=");
        relational_operator.add(">=");  relational_operator.add(">");
        relational_operator.add("or");  relational_operator.add("and");
        
        adding_operator.add("+"); adding_operator.add("-");
       
        multiplying_operator.add("*"); multiplying_operator.add("div");
        
     }
     
     
     //metodo para vericar os simbolos que sao validos
     public int existencia (char a){
        if(letter.contains(a)){
            return 1;
        }
        return 0;
     }
     
     //metodo para verificar as palavras chave
     public String existenciaClasse(String palavra){
         if(specialSymbol.contains(palavra)){
             return "SPECIAL SYMBOL";
         }else if(relational_operator.contains(palavra)){
             return "RELATIONAL OPERATOR";
         }else if(adding_operator.contains(palavra)){
             return "ADDING OPERATOR";
         }else if(multiplying_operator.contains(palavra)){
             return "MULTIPLYING OPERATOR";
         }else if(letter.contains(String.valueOf(palavra.charAt(0)))){
             return VerificarIdentificador(palavra);
         }else if((palavra.charAt(0)=='"' && palavra.charAt(palavra.length()-1)=='"') || 
                 String.valueOf(palavra.charAt(0)).equals("'") && 
                 String.valueOf(palavra.charAt(palavra.length()-1)).equals("'")){
             return "CHARACTER CONSTANT";
         }else if(palavra.length()>=2 || digit.contains(palavra)){
             int v=0;
             for(int i=0; i<palavra.length(); i++){
                 if(digit.contains(String.valueOf(palavra.charAt(i)))){
                     v++;
                 }
             }
             if(v==palavra.length()){
                 return "INTEGER CONSTANT";
             }
         }
         return "ERRO";
     }
     
     //metodo para verificar o identificador
     public String VerificarIdentificador(String codigo){
         int v=0;
         if(letter.contains(String.valueOf(codigo.charAt(0)))){
             
            for(int i=0; i<codigo.length(); i++){
             if(letter.contains(String.valueOf(codigo.charAt(i))) || 
                     digit.contains(String.valueOf(codigo.charAt(i)))){
                v=v+1;
             }
         }
         if(v==codigo.length()){
             return "IDENTIFIER";
         }
         v=0;
         }
         return "erro";
     }
}
