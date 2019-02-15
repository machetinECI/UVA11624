import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class UVA10313 {
    static long [][] memo = new long [310][310];
    public static void dpPrices(int c , int n){
        for(int i=0 ; i<c ; i++){
            for(int j=0 ;j<n ; j++){
                if(i==0){memo[i][j]=1;}
                else if(j==0 && i>0){memo[i][j]=0;}
                else{
                    if(j>i){memo[i][j]=memo[i][j-1];}
                    else{memo[i][j]=memo[i-j][j]+memo[i][j-1];}
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        dpPrices(305,305);
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String entrada;
        while((entrada=input.readLine())!=null){
            int[] prices = new int [3];
            StringTokenizer numeros = new StringTokenizer(entrada);
            int cont=0;
            while(numeros.hasMoreTokens()){
                prices[cont]=Integer.parseInt(numeros.nextToken());
                cont+=1;
            }
            if(cont==1){
                System.out.println(memo[prices[0]][prices[0]]);
            }
            else if(cont==2){
                if(prices[1]>prices[0]){prices[1]=prices[0];}
                System.out.println(memo[prices[0]][prices[1]]);
            }
           else if(cont==3){
                if(prices[2]>prices[0]){prices[2]=prices[0];}
                if(prices[1]==0){System.out.println(memo[prices[0]][prices[2]]);}
                else{
                    if(prices[1]>0){prices[1]=prices[1]-1;}
                    System.out.println(memo[prices[0]][prices[2]]-memo[prices[0]][prices[1]]);
                }
            }
        }
    }
}
