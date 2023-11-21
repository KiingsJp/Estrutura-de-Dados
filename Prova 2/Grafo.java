package provaJava;

import java.util.*;

public class Grafo {

    private final Map<String, List<Vertice>> adjacencias;
    Map<String, String> predecessores = new HashMap<>();

    public Grafo() {
        adjacencias = new HashMap<>();
    }

    public void adicionarAresta(String cidade, String destino, int distancia) {
        adjacencias.computeIfAbsent(cidade, k -> new ArrayList<>()).add(new Vertice(destino, distancia));
        adjacencias.computeIfAbsent(destino, k -> new ArrayList<>()).add(new Vertice(cidade, distancia));
    }

    public List<Vertice> obterAdjacentes(String cidade) {
        return adjacencias.getOrDefault(cidade, Collections.emptyList());
    }

    public int dijkstra(String cidadeOrigem, String cidadeDestino) {
        PriorityQueue<VerticeComDistancia> filaPrioridade = new PriorityQueue<>(Comparator.comparingInt(VerticeComDistancia::distancia));
        Map<String, Integer> distancias = new HashMap<>();

        for (String cidade : adjacencias.keySet()) {
            distancias.put(cidade, cidade.equals(cidadeOrigem) ? 0 : Integer.MAX_VALUE);
            filaPrioridade.add(new VerticeComDistancia(cidade, distancias.get(cidade)));
        }

        while (!filaPrioridade.isEmpty()) {
            VerticeComDistancia atual = filaPrioridade.poll();

            if (atual.vertice().equals(cidadeDestino)) {
                return distancias.get(cidadeDestino);
            }

            for (Vertice vizinho : obterAdjacentes(atual.vertice())) {
                int novaDistancia = distancias.get(atual.vertice()) + vizinho.distancia();
                if (novaDistancia < distancias.get(vizinho.destino())) {
                    distancias.put(vizinho.destino(), novaDistancia);
                    predecessores.put(vizinho.destino(), atual.vertice());
                    filaPrioridade.add(new VerticeComDistancia(vizinho.destino(), novaDistancia));
                }
            }
        }

        return -1;
    }

    public List<String> obterCaminho(String cidadeOrigem, String cidadeDestino, Map<String, String> predecessores) {
        List<String> caminho = new ArrayList<>();
        String atual = cidadeDestino;

        while (atual != null && !atual.equals(cidadeOrigem)) {
            caminho.add(0, atual);
            atual = predecessores.get(atual);
        }

        if (atual != null) {
            caminho.add(0, atual);
        }

        return caminho;
    }

    private record VerticeComDistancia(String vertice, int distancia) {
    }

    public Map<String, String> getPredecessores() {
        return predecessores;
    }

}
