

import java.util.*;

class AFND {
    Set<String> estados;
    Set<Character> alfabeto;
    Map<String, Map<Character, Set<String>>> transicoes;
    String estadoInicial;
    Set<String> estadosFinais;

    public AFND(Set<String> estados, Set<Character> alfabeto,
                Map<String, Map<Character, Set<String>>> transicoes,
                String estadoInicial, Set<String> estadosFinais) {
        this.estados = estados;
        this.alfabeto = alfabeto;
        this.transicoes = transicoes;
        this.estadoInicial = estadoInicial;
        this.estadosFinais = estadosFinais;
    }
}
