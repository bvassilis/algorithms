package gr.vb.it.data;

public class BinarySearchArray<Comparative> {

    private Comparative[] arrayT;

    public BinarySearchArray(Comparative[] arrayT) {
        this.arrayT = arrayT;
    }

    public int find(Comparative searchT) {
        int lowerBoundT = 0;
        int upperBoundT = arrayT.length - 1;
        int curT = -1;
        while (true) {
            curT = (lowerBoundT + upperBoundT) / 2;
            if (arrayT[curT].equals(searchT)) {
                return curT;
            } else if (lowerBoundT > upperBoundT) {
                return curT;
            } else {
                if (arrayT[curT].compare(searchT) > 0) {
                    lowerBoundT = curT + 1; //
                } else {
                    upperBoundT = curT - 1; //
                }
            } 
        }
    }

    public static interface Comparative {
        int compare(Comparative comparative);
    }
}

