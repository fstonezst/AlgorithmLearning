//import java.util.*;
public class Eight_6{
	/*切割木棍问题*/
	public static void main(String[] args){
        int[] p = new int[]{1,3,4,5,8,9,10};
        System.out.println(maxPrice(p));
	}
	public static int maxPrice(int[] p )
    {
        int[] maxPrice = new int[p.length+1];
        int[] trace = new int[maxPrice.length];
        for (int k = 1;k <= p.length; ++k)
        {
            maxPrice[k] = p[k-1];
        }
        for (int i = 1; i < maxPrice.length;++i)
        {
            trace[i] = 0;
            for (int j = 1 ; j < i;++j)
            {
                int price = maxPrice[j] + maxPrice[i-j] ;
                if ( price > maxPrice[i]) {
                    maxPrice[i] = price;
                    trace[i] = j;
                }
            }
        }
        StringBuffer s = new StringBuffer();
        int i = p.length;
        while (trace[i] != 0)
        {
            s.append(trace[i] + "|");
            i = i - trace[i];
        }
        s.append(i);
        System.out.println(s.toString());
        return maxPrice[p.length];
    }


}
