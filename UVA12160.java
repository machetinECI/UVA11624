import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class UVA12160 {
    static Queue<Integer>Q;
    static boolean[] visitados;
    static int[] adyacentes;
    static LinkedList<int[]> padres;
    public static void bfs(int destino , int botones){
        int rta=0;
        boolean found = false;
        while(!Q.isEmpty()){
            int u = Q.remove();
            int cont = Q.remove();
            if(u==destino){
                Q.clear();
                rta=cont;
                found=true;
                break;
            }
            for(int i=0; i<botones ; i++){
                int temp = (u+adyacentes[i])%10000;
                if(visitados[temp]==false){
                    Q.add(temp);
                    Q.add(cont+1);
                    visitados[temp]=true;
                }
            }
        }
        if(found){
            System.out.println(rta);
        }
        else{System.out.println("Permanently Locked");}
    }
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        Scanner numeros = new Scanner(System.in);
        String entrada = input.readLine();
        StringTokenizer st = new StringTokenizer(entrada);
        String o = "" ; String d= "" ; String b = "";
        int casos=0;
        while(st.hasMoreTokens()){
            o = st.nextToken();
            d = st.nextToken();
            b = st.nextToken();
        }
        while(!o.equals("0") & !d.equals("0") & !b.equals("0")){
            casos+=1;
            int origen = Integer.parseInt(o);
            int destino = Integer.parseInt(d);
            int botones = Integer.parseInt(b);
            visitados = new boolean[10000];
            Q=new LinkedList<>();
            Q.add(origen);
            Q.add(0);
            visitados[origen]=true;
            adyacentes = new int[botones];
            entrada=input.readLine();
            st = new StringTokenizer(entrada);
            int i=0;
            while(st.hasMoreTokens()){adyacentes[i]=Integer.parseInt(st.nextToken());i+=1;}
            System.out.print("Case "+casos+": ");
            bfs(destino,botones);
            entrada=input.readLine();
            st = new StringTokenizer(entrada);
            while(st.hasMoreTokens()){
                o = st.nextToken();
                d = st.nextToken();
                b = st.nextToken();
            }
        }
    }
}
