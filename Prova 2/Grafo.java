package provaJava;

import java.util.*;

public class Grafo {

    // MAP PARA ARMAZENAR O GRAFO COMPLETO
    private final Map<String, List<Vertice>> adjacencias;

    // MAP PARA ARMAZENAR O CAMINHO MAIS CURTO QUE FOI ACHADO
    Map<String, String> predecessores = new HashMap<>();

    // CONSTRUTOR DO GRAFO, APENAS INICIALIZA O MAP
    public Grafo() {
        adjacencias = new HashMap<>();
    }

    // MÉTODO PARA ADICIONAR ARESTAS E MONTAR O GRAFO
    public void adicionarAresta(String cidade, String destino, int distancia) {

        // SE NÃO TIVER A CIDADE NO MAP DO GRAFO, ADICIONA ELA E CRIA UM VÉRTICE COM O DESTINO
        adjacencias.computeIfAbsent(cidade, k -> new ArrayList<>()).add(new Vertice(destino, distancia));

        // MESMA COISA MAS COM A CIDADE DE DESTINO
        adjacencias.computeIfAbsent(destino, k -> new ArrayList<>()).add(new Vertice(cidade, distancia));
    }

    // MÉTODO PARA PEGAR OS VÉRTICES DA CIDADE
    public List<Vertice> obterAdjacentes(String cidade) {
        // SE A CIDADE ESTIVER NO MAP, RETORNA OS VÉRTICES DELA, SE NÃO RETORNA UMA LISTA VAZIA
        return adjacencias.getOrDefault(cidade, Collections.emptyList());
    }

    // ALGORITMO DE DIJKSTRA, PRECISA SER PASSADO UMA CIDADE DE ORIGEM E UMA CIDADE DE DESTINO
    public int dijkstra(String cidadeOrigem, String cidadeDestino) {

        // CRIA UMA LISTA DE FILA DE PRIORIDADE PARA ARMAZENAR OS VERTICES E COMPARAR QUAL O MENOR
        // ESSA CLASSE PRIORITY QUEUE É MUITO BOA PORQUE ADICIONA AS CIDADES E JÁ CALCULA PRA MIM QUAL É A MENOR DISTANCIA ENTRE ELAS
        PriorityQueue<VerticeComDistancia> filaPrioridade = new PriorityQueue<>(Comparator.comparingInt(VerticeComDistancia::distancia));

        // UM MAP PARA ARMAZENAR A CIDADE E A DISTANCIA
        Map<String, Integer> distancias = new HashMap<>();

        // ADICIONAR DISTANCIAS - PARA CADA CIDADE DO GRAFO
        for (String cidade : adjacencias.keySet()) {

            // SE A CIDADE FOR IGUAL A CIDADE DE ORIGEM, O VALOR DA DISTANCIA É 0, CASO CONTRÁRIO O VALOR É INFINITO
            distancias.put(cidade, cidade.equals(cidadeOrigem) ? 0 : Integer.MAX_VALUE);

            // ADICIONA NA COMPARAÇÃO A CIDADE E SUA DISTANCIA
            filaPrioridade.add(new VerticeComDistancia(cidade, distancias.get(cidade)));
        }

        // ENQUANTO TIVER CIDADE NA FILA DE COMPARAÇÃO
        while (!filaPrioridade.isEmpty()) {

            // PEGA E REMOVE A CIDADE ATUAL DA LISTA DE COMPARAÇÃO
            VerticeComDistancia atual = filaPrioridade.poll();

            // SE CHEGOU NA CIDADE DE DESTINO, RETORNA A DISTANCIA
            if (atual.vertice().equals(cidadeDestino)) {
                return distancias.get(cidadeDestino);
            }

            // CALCULO SOMANDO AS DISTANCIA DAS CIDADES E ATUALIZANDO
            // PARA CADA VERTICE DA CIDADE ATUAL
            for (Vertice vizinho : obterAdjacentes(atual.vertice())) {

                // A NOVA DISTANCIA RECEBE A DISTANCIA ATUAL ACUMULADA E A DISTANCIA DO VIZINHO
                int novaDistancia = distancias.get(atual.vertice()) + vizinho.distancia();

                // SE A NOVA DISTANCIA ACUMULADA FOR MENOR DO QUE A DISTANCIA DO VIZINHO
                if (novaDistancia < distancias.get(vizinho.destino())) {

                    // VAI SER ATUALIZADO NO MAP PARA PEGAR O VERTICE COM A NOVA DISTANCIA ACUMULADA
                    distancias.put(vizinho.destino(), novaDistancia);
                    filaPrioridade.add(new VerticeComDistancia(vizinho.destino(), novaDistancia));

                    // AQUI SALVA QUAL É A CIDADE DE DESTINO QUE ESTÁ INDO, PARA PRINTAR O CAMINHO NO FINAL
                    predecessores.put(vizinho.destino(), atual.vertice());
                }
            }
        }

        // SE DER ERRADO VAI RETORNAR -1
        return -1;
    }

    private record VerticeComDistancia(String vertice, int distancia) {}

    // MÉTODO PARA OBTER QUAL FOI O CAMINHO FEITO
    public List<String> obterCaminho(String cidadeOrigem, String cidadeDestino, Map<String, String> predecessores) {

        // LISTA DAS CIDADES DO CAMINHO
        List<String> caminho = new ArrayList<>();
        String atual = cidadeDestino;

        // ENQUANTO A CIDADE ATUAL NÃO FOR NULA E NÃO FOR A CIDADE DE ORIGEM
        while (atual != null && !atual.equals(cidadeOrigem)) {

            // ADICIONA A CIDADE NO INDICE 0
            caminho.add(0, atual);
            atual = predecessores.get(atual);
        }
        
        // ADICIONA A CIDADE DE ORIGEM NO INDICE 0
        if (atual != null) {
            caminho.add(0, atual);
        }

        return caminho;
    }
    
    // MÉTODO PARA PUXAR OS PREDECESSORES
    public Map<String, String> getPredecessores() {
        return predecessores;
    }

}
