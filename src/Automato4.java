import java.util.*;

public class Automato4 {
    public static Automato criarAutomato() {
        Set<String> estados = new HashSet<>(Collections.singletonList("q0"));
        Set<Character> alfabeto = new HashSet<>(Arrays.asList('0', '1'));
        Map<String, Map<Character, String>> transicoes = new HashMap<>();

        String estadoInicial = "q0";
        Set<String> estadosFinais = new HashSet<>();

        return new Automato(estados, alfabeto, transicoes, estadoInicial, estadosFinais);
    }

    public static void main(String[] args) {
        Automato automato = criarAutomato();
        System.out.println(automato.aceita("")); // false
        System.out.println(automato.aceita("0011")); // false
    }
}
