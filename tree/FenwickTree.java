package tree;

public class FenwickTree {
    public static void main(String[] args) {
        long[] values = { 0, +1, -2, +3, -4, +5, -6 };
        FenwickTreeRangeUpdatePointQuery ft = new FenwickTreeRangeUpdatePointQuery(values);
        System.err.println(" 1 -> 3 "+ft.rangeQuery(1, 3));
        System.err.println(" 1 -> 6 "+ft.rangeQuery(1, 6));
        System.err.println(" 3 -> 6 "+ft.rangeQuery(3, 6));
        ft.updateRange(1, 4, 10); // Add +10 to interval [1, 4] in O(log(n))
        ft.get(1); // 11
        ft.get(4); // 6
        ft.get(5); // 5

        ft.updateRange(3, 6, -20); // Add -20 to interval [1, 4] in O(log(n))
        ft.get(3); // -7
        ft.get(4); // -14
        ft.get(5); // -15
    }
}
