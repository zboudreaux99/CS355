package edu.odu.cs.cs355.PipeLine;

import edu.odu.cs.cs355.PipeLine.Pipes.Pipe;
import edu.odu.cs.cs355.PipeLine.Pipes.PipeKit;

public class TestKit {
    public TestKit() {
    }

    public static void main(String args[]) {
        String desired = args.length > 0 ? args[0] : "";
        boolean found = false;
        PipeKit kit = new PipeKit();
        for (int i = 0; i < kit.size(); ++i) {
            Pipe p = kit.getPipe(i);
            String className = p.getClass().getName();
            if (p != null && (desired.equals("") || className.endsWith(desired))) {
                System.out.print(className + ": ");
                found = true;
                for (int j = 0; j < 4; ++j) {
                    if (p.isOpenAtThisEnd(j))
                        System.out.print(j + "=>" + p.emergeDirection(j) + " ");
                }
                System.out.println();
            }
        }
        if (desired.length() > 0 && !found) {
            System.out.println(desired + " is not an implemented pipe.");
        }
}
}
