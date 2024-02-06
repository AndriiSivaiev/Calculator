import java.util.Scanner;

public class Calculator {
    public class Main {



        private static final String[] ROMANUNITS = new String[] {"","I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        private static final String[] ROMANTENS = new String[] {"","X", "XX", "XXX", "LX", "L", "LX", "LXX", "LXXX", "XC", "C"};
        public static void main(String[] args) {
            try{
                Scanner in = new Scanner(System.in);
                System.out.println(calc(in.nextLine()));
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        public static String calc(String input) throws Exception {
            String[] inputData = input.split(" ");
            if((inputData.length != 3)){
                throw new Exception("Не верный формат строки.\nДоступно только два операнда и один оператор (+, -, /, *).");
            }
            int a = tryParse(inputData[0]);
            String operator = inputData[1];
            int b = tryParse(inputData[2]);
            int answ = 0;
            boolean isRoman = false;
            if((a == 0) && (b == 0)){
                a = romanToArabic(inputData[0]);
                b = romanToArabic(inputData[2]);
                isRoman = true;
            }
            if(((a == 0) || (b == 0)) || ((a > 10) || (b > 10))){
                throw new Exception("Не верный формат операндов.\n Оба операндов должны быть одной системы счисления, а также от принадлежать диапозону [1..10].");
            }
            switch(operator){
                case "+":
                    answ = a + b;
                    break;
                case "-":
                    answ = a - b;
                    break;
                case "*":
                    answ = a * b;
                    break;
                case "/":
                    answ = a / b;
                    break;
                default:
                    throw new Exception("Не верный формат оператора.\nОператор должен быть (+, -, /, *).");
            }
            if(isRoman && answ < 1){
                throw new Exception("Не верный формат строки.\nОтвет в данной системе счисления не может быть меньше 1.");
            }
            return isRoman ? arabicToRoman(answ) : Integer.toString(answ);
        }
        private static int tryParse(String value){
            try {
                return Integer.parseInt(value);
            }
            catch (NumberFormatException e) {
                return 0;
            }
        }
        private static int romanToArabic(String value){
            for(int i = 1; i < ROMANUNITS.length; i++){
                if(ROMANUNITS[i].equals(value)){
                    return  i;
                }
            }
            return 0;
        }
        private static String arabicToRoman(int value){
            return ROMANTENS[value / 10] + ROMANUNITS[value % 10];
        }
    }

}
