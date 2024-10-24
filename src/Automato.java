import java.util.*;

class Automato {
    Set<String> estados;
    Set<Character> alfabeto;
    Map<String, Map<Character, String>> transicoes;
    String estadoInicial;
    Set<String> estadosFinais;

    public Automato(Set<String> estados, Set<Character> alfabeto,
                    Map<String, Map<Character, String>> transicoes,
                    String estadoInicial, Set<String> estadosFinais) {
        this.estados = estados;
        this.alfabeto = alfabeto;
        this.transicoes = transicoes;
        this.estadoInicial = estadoInicial;
        this.estadosFinais = estadosFinais;
    }

    public boolean aceita(String cadeia) {
        String estadoAtual = estadoInicial;
        for (char simbolo : cadeia.toCharArray()) {
            if (!alfabeto.contains(simbolo)) return false;
            estadoAtual = transicoes.getOrDefault(estadoAtual, new HashMap<>()).get(simbolo);
            if (estadoAtual == null) return false;
        }
        return estadosFinais.contains(estadoAtual);
    }
}
