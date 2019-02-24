import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class UVA532 {
    static char [][][] memo;
    static int [][][] path;
    static Queue<Trio> Q;
    static boolean visitados [][][];
    static int [] movZ = {-1,1,0,0,0,0};
    static int [] movX = {0,0,1,-1,0,0};
    static int [] movY = {0,0,0,0,1,-1};
    public static void bfs(int L , int R , int C){
        int X = 0; int Y = 0; int Z = 0;
        boolean flag = false;
        while(!Q.isEmpty()){
            Trio u=Q.remove();
            int zi = u.l;
            int xi= u.r;
            int yi= u.c;
            for(int i=0; i<6 ; i++){
                Z = zi+movZ[i];
                X = xi+movX[i];
                Y = yi+movY[i];
                if(Z>=0 && Z<L && X>=0 && X<R && Y>=0 && Y<C){
                    if(String.valueOf(memo[Z][X][Y]).equals("E")){
                        Q.clear();
                        flag=true;
                        path[Z][X][Y]=path[zi][xi][yi]+1;
                        break;
                    }
                    else if (visitados[Z][X][Y]==false){
                        Q.add(new Trio(Z,X,Y));
                        visitados[Z][X][Y]=true;
                        path[Z][X][Y]=path[zi][xi][yi]+1;
                    }
                }
            }
        }
        if(flag){
            System.out.println("Escaped in "+path[Z][X][Y]+" minute(s).");
        }
        else{
            System.out.println("Trapped!");
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String entrada = input.readLine();
        StringTokenizer tk = new StringTokenizer(entrada);
        int L=-1; int R=-1; int C=-1;
        while(tk.hasMoreTokens()){
            L=Integer.parseInt(tk.nextToken());
            R=Integer.parseInt(tk.nextToken());
            C=Integer.parseInt(tk.nextToken());
        }
        while(L!=0 && R!=0 && C!=0){
            memo = new char [L][R][C];
            path = new int [L][R][C];
            Q=new LinkedList<>();
            visitados=new boolean[L][R][C];
            for(int z=0 ; z<L ; z++){
                for(int i=0 ; i<R ; i++){
                    char[] linea = input.readLine().toCharArray();
                    for(int j=0 ; j<C ; j++){
                        memo[z][i][j]=linea[j];
                        if(String.valueOf(linea[j]).equals("S")){
                            Q.add(new Trio(z,i,j));
                            visitados[z][i][j]=true;
                        }
                        if(String.valueOf(linea[j]).equals("#")){visitados[z][i][j]=true;}
                    }
                }
                entrada=input.readLine();
            }
            entrada=input.readLine();
            bfs(L,R,C);
            tk = new StringTokenizer(entrada);
            while(tk.hasMoreTokens()){
                L=Integer.parseInt(tk.nextToken());
                R=Integer.parseInt(tk.nextToken());
                C=Integer.parseInt(tk.nextToken());
            }
        }
    }
     static class Trio{
         int l,r,c;
         Trio(int ll , int i , int j){
             l=ll; r=i; c=j;
         }
     }
}
