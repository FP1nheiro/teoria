import java.util.*;

public class Automato6 {
    public static Automato criarAutomato() {
        Set<String> estados = new HashSet<>(Collections.singletonList("q0"));
        Set<Character> alfabeto = new HashSet<>(Arrays.asList('0', '1'));
        Map<String, Map<Character, String>> transicoes = new HashMap<>();

        transicoes.put("q0", Map.of('0', "q0", '1', "q0"));

        String estadoInicial = "q0";
        Set<String> estadosFinais = new HashSet<>(Collections.singletonList("q0"));

        return new Automato(estados, alfabeto, transicoes, estadoInicial, estadosFinais);
    }

    public static void main(String[] args) {
        Automato automato = criarAutomato();
        System.out.println(automato.aceita("")); // true
        System.out.println(automato.aceita("10101")); // true
    }
}
