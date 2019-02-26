import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author Asus PC
 */
public class UVA859{
    static int L ; static int C; static int P;
    static int posY[]={0,0,1,1,1};
    static int posX[]={-1,1,0,-1,1};
    static boolean mat [][];
    static boolean visitados [][];
    static ArrayList <ArrayList<Integer>> soluciones;
    static Queue <ArrayList<Integer>> Q;
    public static boolean isValid(int y , int x){
        return x>=0 && y>=0 && y<=L && x<=C;
    }
    public static void bfs(int li, int ci){
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(li); temp.add(ci); temp.add(0);
        Q.add(temp);
        visitados[li][ci]=true;
        for(int i=0 ; i<3 ; i++){
            int yi = li+posY[i];
            int xi = ci+posX[i];
            if(isValid(yi,xi) && !visitados[yi][xi] && !mat[yi][xi]){
                ArrayList<Integer> sol = new ArrayList<>();
                sol.add(yi); sol.add(xi); sol.add(1);
                soluciones.add(sol);
            }
        }
        while(!Q.isEmpty()){
            ArrayList<Integer> u = Q.remove();
            for(int i=0 ; i<5 ; i++){
                int py = u.get(0)+posY[i]; int px = u.get(1)+posX[i];
                int hy = u.get(0)+2*posY[i]; int hx = u.get(1)+2*posX[i];
                if(isValid(py,px) && isValid(hy,hx) && mat[py][px] && !visitados[hy][hx] && !mat[hy][hx]){
                    visitados[hy][hx]=true;
                    ArrayList<Integer> sol = new ArrayList<>();
                    sol.add(hy); sol.add(hx); sol.add(u.get(2)+1);
                    soluciones.add(sol);
                    Q.add(sol);
                }
            }
        }
        Collections.sort(soluciones, new Comparator<ArrayList<Integer>> () {
            @Override
            public int compare(ArrayList<Integer> a, ArrayList<Integer> b) {
                if(a.get(0)!=b.get(0)){
                    return b.get(0).compareTo(a.get(0));
                }
                return a.get(1).compareTo(b.get(1));
            }
        });
        for(int i=0 ; i<soluciones.size() ; i++){
            ArrayList<Integer> print = soluciones.get(i);
            System.out.println(print.get(0)+" "+print.get(1)+" "+print.get(2));
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String entrada;
        int  k=0;
        while((entrada=input.readLine())!=null){
            if(k!=0){System.out.println();}
            k+=1;
            StringTokenizer st = new StringTokenizer(entrada);
            L = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            P = 2*C;
            mat = new boolean [L+1][C+1];
            visitados = new boolean [L+1][C+1];
            Q= new LinkedList<>();
            soluciones = new ArrayList<>();
            for(int i=0; i<2*P ; i++){
                entrada = input.readLine();
                st = new StringTokenizer(entrada);
                int li=Integer.parseInt(st.nextToken());
                int ci=Integer.parseInt(st.nextToken());
                mat[li][ci]=true;
            }
            entrada = input.readLine();
            st = new StringTokenizer(entrada);
            int li=Integer.parseInt(st.nextToken());
            int ci=Integer.parseInt(st.nextToken());
            bfs(li,ci);
        }
    }
}
