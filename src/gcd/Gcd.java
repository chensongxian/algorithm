package gcd;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 最大公约数
 * @Author: csx
 * @Date: 2018/01/08
 */
public class Gcd {
    public static int getGcd(int numberA,int numberB){
        int result=1;
        if(numberA>numberB){
            result=gcdByEuclideanAlgorithm(numberA,numberB);
        }else{
            result=gcdByEuclideanAlgorithm(numberB,numberA);
        }
        return result;
    }

    /**
     * 辗转相除法(欧几里得算法)
     * @param largerNumber
     * @param smallerNumber
     * @return
     */
    public static int gcdByEuclideanAlgorithm(int largerNumber,int smallerNumber){
        if(largerNumber%smallerNumber==0){
            return smallerNumber;
        }else{
            return gcdByEuclideanAlgorithm(smallerNumber,largerNumber%smallerNumber);
        }
    }

    /**
     * 更相减损术
     * @param numberA
     * @param numberB
     * @return
     */
    public static int gcdByMorederogation(int numberA,int numberB){
        if(numberA==numberB){
            return numberA;
        }
        if(numberA>numberB){
            return gcdByMorederogation(numberA-numberB,numberB);
        }else{
            return gcdByMorederogation(numberB-numberA,numberA);
        }
    }

    public static int gcdByMorederogationofImprove(int numberA,int numberB){
        if(numberA==numberB){
            return numberA;
        }
        if(numberA<numberB){
            return gcdByMorederogationofImprove(numberB,numberA);
        }else{
            if((numberA&1)==0 && (numberB&1)==0){
                return gcdByMorederogationofImprove(numberA>>1,numberB>>1)<<1;
            }else if((numberA&1)==0 && !((numberB&1)==0)){
                return gcdByMorederogationofImprove(numberA>>1,numberB);
            }else if(!((numberA&1)==0) && (numberB&1)==0){
                return gcdByMorederogationofImprove(numberA,numberB>>1);
            }else{
                return gcdByMorederogationofImprove(numberA-numberB,numberB);
            }

        }
    }

    public static void main(String[] args) {
//        int gcd=getGcd(60,40);
//        int gcd=gcdByMorederogation(60,40);
        int gcd=gcdByMorederogationofImprove(60,40);
        System.out.println(gcd);
    }
}
