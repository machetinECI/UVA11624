import java.util.Scanner;
class UVA100 {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNextInt()){
        int a = input.nextInt();
        int b = input.nextInt();
                int maximo=-1;
                int min = Math.min(a,b);
                int max = Math.max(a,b);
                for(int i=min ; i<max+1; i++){
                    int temp = recursion(i,0);
                    if (temp>maximo){
                        maximo=temp;
                    }
                }
                System.out.println(a+" "+b+" "+maximo);
            }
        }
    public static int recursion(int n , int cont){
        if(n==1){
            return cont+1;
        }
        else {
            if (n%2==0){
                return recursion(n/2 , cont+1);
            }
            else{
                return recursion((3*n)+1 , cont+1);
            }
        }
    }
}
   

