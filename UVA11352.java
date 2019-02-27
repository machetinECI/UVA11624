import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class UVA11532 {
    static int M; static int N;
    static char[][] mat;
    static int target[] = {-1,-1};
    static int jumpY []={-2,-2,-1,-1,1,1,2,2};
    static int jumpX []={-1,1,-2,2,-2,2,-1,1};
    static int posY []={-1,-1,-1,0,0,1,1,1};
    static int posX []={-1,0,1,-1,1,-1,0,1};
    static boolean visitados[][];
    static int path[][];
    static Queue<Pair> Q;
    public static boolean isValid(int y , int x){
        return y>=0 && y<M && x>=0 && x<N;
    }
    public static void setVisitados(int y , int x){
        for(int i=0; i<8 ; i++){
            if(isValid(y+jumpY[i],x+jumpX[i])){
                visitados[y+jumpY[i]][x+jumpX[i]]=true;
            }
        }
    }
    public static void bfs(){
        int x=-1; int y=-1;
        boolean found = false;
        while(!Q.isEmpty()){
            Pair u = Q.remove();
            y=u.i;
            x=u.j;
            for(int f=0 ; f<8 ; f++){
                int yf = y+posY[f];
                int xf = x+posX[f];
                if(isValid(yf,xf)){
                    if(yf==target[0] && xf==target[1]){
                        Q.clear();
                        found = true;
                        break;
                    }
                    else if (!visitados[yf][xf]){
                        visitados[yf][xf]=true;
                        Q.add(new Pair(yf,xf));
                        path[yf][xf]=path[y][x]+1;
                    }
                }
            }
        }
        if(found){
            int rta = path[y][x]+1;
            System.out.println("Minimal possible length of a trip is "+rta);
        }
        else{
            System.out.println("King Peter, you can't go now!");
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String entrada;
        entrada = input.readLine();
        StringTokenizer st = new StringTokenizer(entrada);
        int casos = Integer.parseInt(st.nextToken());
        for(int c=0 ; c<casos ; c++){
            entrada = input.readLine(); st = new StringTokenizer(entrada);
            M=Integer.parseInt(st.nextToken());
            N=Integer.parseInt(st.nextToken());
            mat = new char[M][N];
            visitados = new boolean[M][N];
            path = new int [M][N];
            Q= new LinkedList<>();
            for(int i=0 ; i<M ; i++){
                char [] linea = input.readLine().toCharArray();
                for(int j=0 ; j<N ; j++){
                    mat[i][j]=linea[j];
                    if(String.valueOf(linea[j]).equals("Z")){
                        visitados[i][j]=true;
                        setVisitados(i,j);
                    }
                    if(String.valueOf(linea[j]).equals("A")){
                        Q.add(new Pair(i,j));
                        visitados[i][j]=true;
                    }
                    if(String.valueOf(linea[j]).equals("B")){
                        target[0]=i; target[1]=j;
                    }
                }
            }
            bfs();
        }
    }
    static class Pair{
        int i; int j;
        Pair(int y , int x){
            i=y; j=x;
        }
    }
}
