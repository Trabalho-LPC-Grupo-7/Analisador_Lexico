package compiller;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class AnalisadorLexico {
    
    public AnalisadorLexico(){
    
    }
    
    //instancia da classe 'simbolos', para acessar os simbolos da classe
    //Simbolos s = new Simbolos();
    
    //abaixo o metodo para pegar o codigo fonte e ler o seu tamanho todo e retorna o tamanho
    public int passarCursor(String codigoFonte){
       int tamanhoCodigo = codigoFonte.length();
        return tamanhoCodigo; 
    }
    
    public ArrayList<Token> verificarCaractere (String codigoFonte){
        //instancia da classe simbolos para acessar os metodos da classe
        Simbolos s = new Simbolos();
        //criando um array list para armazenar o simbolo e dizer se existe(simbolo) ou nao(error)
        ArrayList<Token> simbolo = new ArrayList<>();
        int tamanho = passarCursor(codigoFonte);
        
        int ponteiro1=0;
        String palavra="";
        
        for (int i = 0; i <= tamanho; i++) {
            
            if (i == tamanho || codigoFonte.charAt(i) == ' ') {
                for (int j = ponteiro1; j < i; j++) {               
                    palavra += codigoFonte.charAt(j);
                }
                
                if(palavra!=""){
                    Token t = new Token();
                    t.setLinha(i);
                    t.setToken(palavra);
                    t.setEstado(s.existenciaClasse(palavra));
                    simbolo.add(t);
                }
                ponteiro1 = i + 1;
                palavra = "";  
            }
        }
        
//        for(int i=0; i<tamanho; i++){
//            if(s.existencia(codigoFonte.charAt(i))==1){
//                
//               Token t = new Token();
//               t.setToken(String.valueOf(codigoFonte.charAt(i)));
//               t.setEstado("simbolo");
//               simbolo.add(t);
//            }else{
//               Token t = new Token();
//               t.setToken(String.valueOf(codigoFonte.charAt(i)));
//               t.setEstado("error");
//               simbolo.add(t);
//            }  
//        }
        return simbolo;
    }
    
}