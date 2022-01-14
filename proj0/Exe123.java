public class Exe123 {
    public static void main(String[] args) {
        int i ;
        int sum = 0;
        for(i = 0; i < args.length; i++){
            sum = sum + Integer.parseInt(args[i]);
        }
        System.out.println(sum);
    }
}
