import oracle.jvm.hotspot.jfr.ThreadStates;

/**
 * Tests01.
 *
 * @author <a href='mailto:likeguo@apache.com'> likeguo </a>
 */
public class Tests01 {
    
    public static void main(String[] args) {
        
        Boolean a = true;
        Boolean b = Boolean.TRUE;
        Boolean c = new Boolean(true);
        System.out.println(a.equals(b));
        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(b == c);
        System.out.println(true == b);
        
        
        final int[] ints = {1, 2, 3, 4, 5};
        dfs(ints, 0);
        
        try {
            // do something
            System.exit(0);
        } finally {
            System.out.println("“Print from finally”");
        }
    }
    
    static void dfs(final int[] arr, int i) {
        if (i >= arr.length) {
            return;
        }
        System.out.println(arr[i]);
        dfs(arr, i + 1);
    }
}
