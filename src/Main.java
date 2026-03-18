
  /*
0: Maranhão
1: Piauí
2: Ceará
3: Rio Grande do Norte
4: Paraíba
5: Pernambuco
6: Alagoas
7: Sergipe
8: Bahia*/
  import java.util.*;


  public class Main {
      public static void main(String[] args) {

          In in = new In("dados/nordeste.txt");
          Graph G = new Graph(in);

          Scanner sc = new Scanner(System.in);

          System.out.println("Correlação de número com estado:");
          System.out.println("0 - MA; 1 - PI; 2 - CE; 3 - RN; 4 - PB; 5 - PE; 6 - AL; 7 - SE; 8 - BA");

          System.out.print("Origem (0-8): ");
          int X = sc.nextInt();

          System.out.print("Destino (0-8): ");
          int Y = sc.nextInt();

          String[] estados = {
                  "MA", "PI", "CE", "RN", "PB", "PE", "AL", "SE", "BA"
          };

          DepthFirstPaths dfs = new DepthFirstPaths(G, X);
          BreadthFirstPaths bfs = new BreadthFirstPaths(G, X);




          System.out.println("\n--- Conectividade ---");
          if (dfs.hasPathTo(Y)) {
              System.out.println("É possível ir de " + estados[X] + " até " + estados[Y]);
          } else {
              System.out.println("Não é possível.");
          }

          System.out.println("\n--- Caminho DFS ---");
          if (dfs.hasPathTo(Y)) {
              for (int v : dfs.pathTo(Y)) {
                  System.out.print(estados[v] + " ");
              }
          }

          System.out.println("\n\n--- Caminho BFS ---");
          if (bfs.hasPathTo(Y)) {
              for (int v : bfs.pathTo(Y)) {
                  System.out.print(estados[v] + " ");
              }
          }

          int count = 0;
          for (int v = 0; v < G.V(); v++) {
              if (dfs.hasPathTo(v)) count++;
          }
          System.out.println("\n\nAlcançáveis: " + count);

          System.out.println("\n--- Ordem DFS (aproximada) ---");
          for (int v = 0; v < G.V(); v++) {
              if (dfs.hasPathTo(v)) {
                  System.out.print(estados[v] + " ");
              }
          }

          System.out.println("\n\n--- Ordem BFS ---");

          Map<Integer, List<Integer>> camadas = new TreeMap<>();

          for (int v = 0; v < G.V(); v++) {
              if (bfs.hasPathTo(v)) {
                  int d = bfs.distTo(v);
                  camadas.putIfAbsent(d, new ArrayList<>());
                  camadas.get(d).add(v);
              }
          }

          for (int d : camadas.keySet()) {
              for (int v : camadas.get(d)) {
                  System.out.print(estados[v] + " ");
              }
          }
      }
  }