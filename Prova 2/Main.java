package provaJava;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Grafo grafo = getGrafo();

        Scanner sc = new Scanner(System.in);
        System.out.println("Estrutura de Dados - Prova 2");

        printCidades();
        System.out.print("\nEscolha uma cidade de origem: ");
        String cidadeOrigemDijkstra;
        switch (sc.nextInt()){
            case 1 -> cidadeOrigemDijkstra = "São Paulo";
            case 2 -> cidadeOrigemDijkstra = "Rio de Janeiro";
            case 3 ->  cidadeOrigemDijkstra = "Vitória";
            case 4 ->  cidadeOrigemDijkstra = "Recife";
            case 5 ->  cidadeOrigemDijkstra = "Salvador";
            case 6 -> cidadeOrigemDijkstra = "Natal";
            default ->  cidadeOrigemDijkstra = "Nenhuma";
        }

        System.out.print("Escolha uma cidade de Destino: ");
        String cidadeDestinoDijkstra;
        switch (sc.nextInt()){
            case 1 ->  cidadeDestinoDijkstra = "São Paulo";
            case 2 ->  cidadeDestinoDijkstra = "Rio de Janeiro";
            case 3 ->  cidadeDestinoDijkstra = "Vitória";
            case 4 ->  cidadeDestinoDijkstra = "Recife";
            case 5 ->  cidadeDestinoDijkstra = "Salvador";
            case 6 ->  cidadeDestinoDijkstra = "Natal";
            default ->  cidadeDestinoDijkstra = "Nenhuma";
        }

        int menorDistancia = grafo.dijkstra(cidadeOrigemDijkstra, cidadeDestinoDijkstra);

        if (menorDistancia != -1) {
            System.out.println("\nMenor distância entre " + cidadeOrigemDijkstra + " e " + cidadeDestinoDijkstra + ": " + menorDistancia);
        } else {
            System.out.println("\nCidades não encontradas, digite as opções válidas");
        }

        Map<String, String> processores = grafo.getPredecessores();
        List<String> caminho = grafo.obterCaminho(cidadeOrigemDijkstra,cidadeDestinoDijkstra,processores);
        String seta = "";
        for(String x : caminho){
            System.out.print(seta + x);
            seta = " -> ";
        }
    }

    private static void printCidades(){
        System.out.println("\nCIDADES\n1 - São Paulo");
        System.out.println("2 - Rio de Janeiro");
        System.out.println("3 - Vitória");
        System.out.println("4 - Recife");
        System.out.println("5 - Salvador");
        System.out.println("6 - Natal");
    }

    private static Grafo getGrafo() {
        Grafo grafo = new Grafo();
        grafo.adicionarAresta("São Paulo", "Rio de Janeiro", 300);
        grafo.adicionarAresta("São Paulo", "Recife", 400);
        grafo.adicionarAresta("São Paulo", "Salvador", 100);
        grafo.adicionarAresta("Rio de Janeiro", "Vitória", 100);
        grafo.adicionarAresta("Rio de Janeiro", "Natal", 70);
        grafo.adicionarAresta("Vitória", "Recife", 50);
        grafo.adicionarAresta("Recife", "Natal", 150);
        grafo.adicionarAresta("Salvador", "Natal", 50);
        return grafo;
    }
}
