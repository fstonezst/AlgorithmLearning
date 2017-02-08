//import java.util.*;
public class Eight_6{
	/*切割木棍问题*/
	public static void main(String[] args){
        int[] p = new int[]{1,3,4,5,8,9,10};
             System.out.println(maxPrice(p));
	}
	public static int maxPrice(int[] p)
        {
             int[] maxPrice = new int[p.length+1];
             int[] trace = new int[maxPrice.length];
        for (int k = 1;k <= p.length; ++k)
        {
            maxPrice[k] = p[k-1];
        }
        for (int i = 1; i < maxPrice.length;++i)//从1开始计算长度为i的木棍所能得到的最大价格
        {
            trace[i] = 0;
            for (int j = 1 ; j < i;++j)//长度为i的木i棍最大价格划分的可能情况为 price（j） j： i 到i-1 与 max（i-j）之和。
            {
                    int price = maxPrice[j] + maxPrice[i-j];
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
