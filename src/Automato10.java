import java.util.*;

public class Automato10 {
    public static Automato criarAutomato() {
        Set<String> estados = new HashSet<>(Arrays.asList("q0", "q1", "q2"));
        Set<Character> alfabeto = new HashSet<>(Arrays.asList('0', '1'));
        Map<String, Map<Character, String>> transicoes = new HashMap<>();

        transicoes.put("q0", Map.of('0', "q1", '1', "q2"));
        transicoes.put("q1", Map.of('0', "q1", '1', "q0"));
        transicoes.put("q2", Map.of('0', "q1", '1', "q2"));

        String estadoInicial = "q0";
        Set<String> estadosFinais = new HashSet<>(Collections.singletonList("q1"));

        return new Automato(estados, alfabeto, transicoes, estadoInicial, estadosFinais);
    }

    public static void main(String[] args) {
        Automato automato = criarAutomato();
        System.out.println(automato.aceita("0001")); // true
        System.out.println(automato.aceita("0011")); // false
        System.out.println(automato.aceita("00000")); // true
        System.out.println(automato.aceita("1111")); // false
    }
}
