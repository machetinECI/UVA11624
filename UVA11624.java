import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;
class UVA11624 {
    static Queue<Pair> Q;
    static int [][] mat;
    static int f;
    static int c;
    static  int [] posy = {-1,0,0,1};
    static  int [] posx = {0,-1,1,0};
    static void search(){
        
        boolean salida = false;
        
        while(!Q.isEmpty()){
            
            Pair u= Q.remove();
            
            for(int k=0 ; k<4 ; k++){
                int y = u.i+posy[k];
                int x = u.j+posx[k];
                if(x<0 || y<0 || x>=c || y>=f){
                    if(mat[u.i][u.j]>=0){
                        salida=true;
                        Q.clear();
                        System.out.println(mat[u.i][u.j]+1);
                        break;
                    }
                }
                else{
                    if(mat[y][x]==-2 && mat[u.i][u.j]>=0){
                        mat[y][x]=mat[u.i][u.j]+1;
                        Q.add(new Pair(y,x));
                    }
                    else{
                        if(x>=0 && y>=0 && y<f && x<c){
                            if((mat[y][x]==-2 ||mat[y][x]>=0) &&mat[u.i][u.j]==-1){
                                mat[y][x]=-1;
                                Q.add(new Pair(y,x));
                            }
                        }
                    }
                }
            }
        }
        if(!salida){
            System.out.println("IMPOSSIBLE");
        }
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int casos = Integer.parseInt(input.readLine());
        StringTokenizer en;
        for(int cont=0; cont<casos ; cont++){
            en = new StringTokenizer(input.readLine());
            f = Integer.parseInt(en.nextToken());
            c = Integer.parseInt(en.nextToken());
            Q = new LinkedList<>();
            Queue <Pair>fuegos = new LinkedList<>();
            int x=-1;
            int y=-1;
            mat = new int [f][c];
            for(int i=0; i<f ; i++){
                char fila [] = input.readLine().toCharArray();
                for(int j=0 ; j<c ; j++){
                    if(String.valueOf(fila[j]).equals("J")){Q.add(new Pair(i,j));mat[i][j]=0;}
                    else if(String.valueOf(fila[j]).equals("F")){fuegos.add(new Pair(i,j));mat[i][j]=-1;}
                    else if(String.valueOf(fila[j]).equals("#")){fuegos.add(new Pair(i,j));mat[i][j]=-3;}
                    else{mat[i][j]=-2;}
                }
            }
            while(!fuegos.isEmpty()){Pair u=fuegos.remove();Q.add(u);}
            search();
        }
    }
    static class Pair
	{
		int i,j;
		Pair(int x, int y){i = x; j  = y;}
	}
}
