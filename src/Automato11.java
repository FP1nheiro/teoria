import java.util.*;

public class Automato11 {

    public static Automato converterAFNDParaAFD(AFND afnd) {
        // Conjunto de estados do AFD (inicialmente vazio)
        Set<Set<String>> estadosAFD = new HashSet<>();
        // Fila para processar os estados do AFD
        Queue<Set<String>> fila = new LinkedList<>();
        // Mapeamento das transições do AFD
        Map<Set<String>, Map<Character, Set<String>>> transicoesAFD = new HashMap<>();
        // Mapeamento dos nomes dos estados do AFD
        Map<Set<String>, String> nomesEstadosAFD = new HashMap<>();
        // Estado inicial do AFD
        Set<String> estadoInicialAFD = new HashSet<>();
        estadoInicialAFD.add(afnd.estadoInicial);

        // Adiciona o estado inicial à fila e aos estados do AFD
        fila.add(estadoInicialAFD);
        estadosAFD.add(estadoInicialAFD);
        nomesEstadosAFD.put(estadoInicialAFD, "Q0");

        // Processa cada estado da fila
        while (!fila.isEmpty()) {
            Set<String> estadoAtual = fila.poll();
            Map<Character, Set<String>> transicoesAtuais = new HashMap<>();

            // Para cada símbolo do alfabeto, calcula as transições do AFD
            for (char simbolo : afnd.alfabeto) {
                Set<String> novoEstado = new HashSet<>();
                for (String subestado : estadoAtual) {
                    if (afnd.transicoes.containsKey(subestado) &&
                            afnd.transicoes.get(subestado).containsKey(simbolo)) {
                        novoEstado.addAll(afnd.transicoes.get(subestado).get(simbolo));
                    }
                }

                // Se o novo estado não é vazio, adiciona a transição
                if (!novoEstado.isEmpty()) {
                    transicoesAtuais.put(simbolo, novoEstado);

                    // Se o novo estado ainda não foi processado, adiciona à fila
                    if (!estadosAFD.contains(novoEstado)) {
                        estadosAFD.add(novoEstado);
                        fila.add(novoEstado);
                        nomesEstadosAFD.put(novoEstado, "Q" + nomesEstadosAFD.size());
                    }
                }
            }
            transicoesAFD.put(estadoAtual, transicoesAtuais);
        }

        // Mapeia as transições para o formato do AFD
        Map<String, Map<Character, String>> transicoesMapeadas = new HashMap<>();
        for (Map.Entry<Set<String>, Map<Character, Set<String>>> entrada : transicoesAFD.entrySet()) {
            String nomeEstadoAtual = nomesEstadosAFD.get(entrada.getKey());
            Map<Character, String> transicaoMapeada = new HashMap<>();
            for (Map.Entry<Character, Set<String>> transicao : entrada.getValue().entrySet()) {
                String nomeNovoEstado = nomesEstadosAFD.get(transicao.getValue());
                transicaoMapeada.put(transicao.getKey(), nomeNovoEstado);
            }
            transicoesMapeadas.put(nomeEstadoAtual, transicaoMapeada);
        }

        // Determina os estados finais do AFD
        Set<String> estadosFinaisAFD = new HashSet<>();
        for (Set<String> estado : estadosAFD) {
            for (String subestado : estado) {
                if (afnd.estadosFinais.contains(subestado)) {
                    estadosFinaisAFD.add(nomesEstadosAFD.get(estado));
                    break;
                }
            }
        }

        // Retorna o AFD convertido
        return new Automato(new HashSet<>(nomesEstadosAFD.values()), afnd.alfabeto, transicoesMapeadas, "Q0", estadosFinaisAFD);
    }

    public static void main(String[] args) {
        Set<String> estados = new HashSet<>(Arrays.asList("q0", "q1", "q2"));
        Set<Character> alfabeto = new HashSet<>(Arrays.asList('0', '1'));
        Map<String, Map<Character, Set<String>>> transicoes = new HashMap<>();

        transicoes.put("q0", new HashMap<>());
        transicoes.get("q0").put('0', new HashSet<>(Arrays.asList("q0", "q1")));
        transicoes.get("q0").put('1', new HashSet<>(Arrays.asList("q0")));
        transicoes.put("q1", new HashMap<>());
        transicoes.get("q1").put('1', new HashSet<>(Arrays.asList("q2")));
        transicoes.put("q2", new HashMap<>());

        String estadoInicial = "q0";
        Set<String> estadosFinais = new HashSet<>(Collections.singleton("q1"));

        AFND afnd = new AFND(estados, alfabeto, transicoes, estadoInicial, estadosFinais);
        Automato afd = converterAFNDParaAFD(afnd);

        System.out.println("AFD Gerado:");
        System.out.println("Estados: " + afd.estados);
        System.out.println("Estado Inicial: " + afd.estadoInicial);
        System.out.println("Estados Finais: " + afd.estadosFinais);
        System.out.println("Transições:");
        for (String estado : afd.transicoes.keySet()) {
            System.out.println("  " + estado + " -> " + afd.transicoes.get(estado));
        }
    }
}
